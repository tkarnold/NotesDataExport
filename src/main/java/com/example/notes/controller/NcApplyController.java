package com.example.notes.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.example.notes.api.ConnectToDomino;
import com.example.notes.api.DominoDocument;
import com.example.notes.entity.*;
import com.example.notes.service.*;
import com.example.notes.util.ConstantData;
import com.example.notes.util.DominoDataImportUtil;
import com.example.notes.util.file.FileHttp;
import com.example.notes.util.file.FolderInfoImpl;
import com.taocares.entity.FolderInfoEntity;
import com.taocares.entity.ResponsesEntity;
import lotus.domino.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/ncapply")
public class NcApplyController {

    @Autowired
    private INcApplyService ncApplyService;
    //国籍证申请
    @Autowired
    private IAmosNsNcertificateInfoService amosNsNcertificateInfoService;

    @Autowired
    private FolderInfoImpl folderInfo;

    @Autowired
    private FileHttp filehttp;
    @Autowired
    private IAmosNsAirworthyInfoService amosNsAirworthyInfoService;
    @Autowired
    private IAmosNsAirworthyFileService amosNsAirworthyFileService;
    @Autowired
    private IAmosNsInkjetInfoService amosNsInkjetInfoService;

    @Autowired
    private IAmosNsNcertificateInfoFileService amosNsNcertificateInfoFileService;


    @RequestMapping("/add")
    public void loadAllNotice() {
        NcApply apply = new NcApply();
        apply.setNcid("12asdawe23");
        ncApplyService.insert(apply);
    }

    @RequestMapping("/test1")
    @ResponseBody
    public void test1() throws NotesException {
        String server = "localhost:8086";
        String username = "king";
        String password = "1234567890";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;

        localsession = ctd.setConnection1(server, username, password);
        Registration r = localsession.createRegistration();
        // return flag;
        if (localsession == null) {
            System.out.println("failed to get local session.");
        } else {
            System.out.println("got local session.");
        }

        lotus.domino.Database db = null;
        lotus.domino.Database ncdb = null;
        String attachmentId = "";//附件对应ID
        String dataUnid = "";//数据对应Id
        String attachmentPath = "";//附件路径
        String dataYwlsh = "";//业务流水号
        String zch = "";//注册号
        String foldname = "";//文件夹名称

        try {
            ncdb = localsession.getDatabase("", "ncapply");
            if (ncdb == null || ncdb.getViews().size() == 0) {
                System.out.println("获取国籍证库失败！");
                return;
            } else {
                System.out.println("成功获取国籍证库！");
            }

            //======================获取国籍证业务数据======================
            View ncView = ncdb.getView("流程结束已归档");
            if (ncView.getColumnCount() == 0) {
                System.out.println("未能找到视图！");
            } else {
                System.out.println(ncView.getEntryCount() + "********");
            }
            Document ncdoc = null, ncdocx = null;
            List<AmosNsNcertificateInfo> ncApplyList = new ArrayList<>();
            //ncdoc = ncView.getDocumentByKey("03736EC66B3308CD482582FA00079647");
            ncdoc = ncView.getFirstDocument();//
            System.out.println(ncdoc.getUniversalID());
            int count = 0;
            //循环获取对象实例
            while (ncdoc != null && count != 20) {
                Map<String, Object> fileUploadResult = new HashMap<>();
                //获取国籍证业务数据
                AmosNsNcertificateInfo ncApply = exDate(ncdoc);
                AmosNsNcertificateInfoFile ncapplyfile = getannif(ncdoc, ncApply.getId());
                if (true) {
                    //String infoAbout = DominoDataImportUtil.exValue(ncdoc, "SetDataDirective"); //关联国籍信息库
                    //===========================读取证件库并获取信息================================
                    db = localsession.getDatabase("", "1801126858"); //库地址要变

                    if (db == null) {
                        System.out.println("获取证件信息库失败！");
                    } else {
                        System.out.println("获取证件信息库成功！");

                    }

                    View view = db.getView("**按国籍证编号");//viewColumns.size()  国籍证申请的ncid=证件信息的SN
                    if (view.getColumnCount() == 0) {
                        System.out.println("未能找到视图！");
                    }
                    Vector v1 = view.getColumnNames();
                    System.out.println(v1 + ",,..,");
                    Document doc = view.getDocumentByKey(ncApply.getNcFile());
                    String qfr = DominoDataImportUtil.exValue(doc, "SignatureFileName"); //签发人
                    String qfrzw = DominoDataImportUtil.exValue(doc, "JobTitle"); //签发人职务
                    String qfrq = DominoDataImportUtil.exValue(doc, "qianfariqi"); //签发日期
                    //ncApply.setIssuer(qfr);
                    ncApply.setIssuer("徐超群");
                    ncApply.setIssuingDate(qfrq);
                }
                //===========================读取证件库并获取信息END================================
                //============================获取受理日期==========================================
                String shoulisn = ncdoc.getItemValueString("shoulisn");
                Database sldb = localsession.getDatabase("", "1802074241");
                if (sldb == null) {
                    System.out.println("获取受理证件库失败！");
                } else {
                    System.out.println("获取受理证件库成功！");
                }
                View slview = sldb.getView("按流水号");
                Document sldoc = slview.getDocumentByKey(shoulisn);
                String shouliriqi = sldoc.getItemValueString("shouliriqi");
                if (!shouliriqi.equals("")) {
                    ncApply.setAcceptanceDate(Timestamp.valueOf(shouliriqi + " 00:00:00"));
                }
                //============================受理日期END====================================

                //============================获取授权日期==========================================
                String shouquansn = ncdoc.getItemValueString("shouquanshusn");
                Database shouquandb = localsession.getDatabase("", "1802079833");
                if (shouquandb == null) {
                    System.out.println("获取授权库失败！");
                } else {
                    System.out.println("获取授权库成功！");
                }
                View shouquanview = shouquandb.getView("按流水号");
                Document shouquandoc = shouquanview.getDocumentByKey(shouquansn);
                String qianfariqi = shouquandoc.getItemValueString("qianfariqi");
                if (!qianfariqi.equals("")) {
                    ncApply.setAuthorizationDate(Timestamp.valueOf(qianfariqi + " 00:00:00"));
                }
                //============================授权日期END====================================

                //========================获取附件相关信息===========================================
                dataUnid = ncdoc.getUniversalID();//数据的UNID
                attachmentPath = ncdoc.getItemValue("ATTACHMENTSTORAGEPATH").toString();//附件存储路径
                attachmentPath = attachmentPath.substring(attachmentPath.lastIndexOf("/") + 1, attachmentPath.length() - 1);
                System.out.println("attachmentPath=" + attachmentPath);
                attachmentId = ncdoc.getItemValue("ATTACHMENTSTORAGEID").toString();//附件的对应Id
                attachmentId = attachmentId.substring(1, attachmentId.length() - 1);
                dataYwlsh = ncdoc.getItemValueString("SN");//获取业务流水号
                zch = ncdoc.getItemValueString("AirNumber");//获取注册号
                String parentFoldId = "";
                //===========================读取附件库并上传到服务器================================
                if (false) {

                    List<Map<String, Object>> fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, attachmentId, dataYwlsh, zch, parentFoldId);

                   /* insertFile(fileUploadResult,"");
                    ncApply.setFolderId(fileUploadResult.get("ParentId").toString());*/

                }
                //========================附件END=========================
                amosNsNcertificateInfoService.insert(ncApply);
                //循环业务数据，不断获取对象
                ncdocx = ncView.getNextDocument(ncdoc);
                ncdoc = ncdocx;
                count++;
                System.out.println("数据已经迁移" + count + "条！！！");
            }


            System.out.println("数据全部迁移成功");
        } catch (NotesException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("db title = " + db.getLastModified());
    }

    /**
     * 将信息转化成国籍证基础信息
     *
     * @return
     */
    public AmosNsNcertificateInfo exDate(Document document) {
        AmosNsNcertificateInfo aninfo = new AmosNsNcertificateInfo();
        try {
            aninfo.setId(UUID.randomUUID().toString().replace("-", ""));
            aninfo.setSerialsnum(DominoDataImportUtil.exValue(document, "SN"));//流水号
            aninfo.setApplicat(DominoDataImportUtil.exValue(document, "shenqingren"));//申请人
            /* 修改*/
            aninfo.setRegnum(DominoDataImportUtil.exValue(document, "AirNumber"));//国籍标志和登记标志
            aninfo.setAircraftType(DominoDataImportUtil.exValue(document, "AircraftType"));    //航空器型别
            aninfo.setAircraftNumber(DominoDataImportUtil.exValue(document, "xuhao"));    //航空器序列号
            aninfo.setAircraftManufacturer(DominoDataImportUtil.exValue(document, "zhizaozhe"));    //航空器制造人名称
            aninfo.setApplicatEng(DominoDataImportUtil.exValue(document, "name_eng"));    //申请人英文名称
            aninfo.setApplicatEngAddress(DominoDataImportUtil.exValue(document, "add_eng"));    //申请人英文地址
            aninfo.setAreaAdministration(DominoDataImportUtil.exValue(document, "glj"));    //申请人所在地区管理局
            aninfo.setApplicatMobile(DominoDataImportUtil.exValue(document, "dianhua"));    //手机
            aninfo.setApplicatContact(DominoDataImportUtil.exValue(document, "lianxir"));    //联系人
            aninfo.setApplicatPostcode(DominoDataImportUtil.exValue(document, "youbian"));    //邮编
            aninfo.setApplicatFax(DominoDataImportUtil.exValue(document, "chuanzhen"));    //传真
            //申请人类别两个变一个
//            aninfo.setApplicatType(DominoDataImportUtil.exValue(document, "mingcheng"));	//申请人类别-航空器所有人
//            aninfo.setApplicatType(DominoDataImportUtil.exValue(document, "renmingcheng"));	//申请人类别-航空器占有人
            //申请人类别-航空器所有人
            /*修改*/
            if (DominoDataImportUtil.exValue(document, "shenqingrenleibie").contains("航空器所有人")) {
                aninfo.setApplicatType("0");
                aninfo.setAircraftOwner(DominoDataImportUtil.exValue(document, "mingcheng"));
                aninfo.setAircraftOwnerAddress(DominoDataImportUtil.exValue(document, "dizhi"));
            } else {
                aninfo.setAircraftOwner(DominoDataImportUtil.exValue(document, "renmingcheng"));
                aninfo.setAircraftOwnerAddress(DominoDataImportUtil.exValue(document, "rendizhi"));
            }
            //申请人类别-航空器占有人
            aninfo.setAircraftHolder(DominoDataImportUtil.exValue(document, "renmingcheng"));//航空器占有人名称
            aninfo.setAircraftHolderAddress(DominoDataImportUtil.exValue(document, "rendizhi"));//航空器占有人地址

            aninfo.setApplicatUse(DominoDataImportUtil.exValue(document, "yongtu"));    //航空器用途:1,运输-航线运输（客货运）/航线运输（客运）、航线运输（货运）2，通用-公务机、作业飞行、培训、旅游观光、个人飞行、其它
            //aninfo.setManufactureDate(Timestamp.valueOf(DominoDataImportUtil.exValue(document, "chuchanriqi")));	//航空器出厂日期
            aninfo.setEngineModel(DominoDataImportUtil.exValue(document, "fdj_xh"));    //发动机型别
            aninfo.setAircraftMaxWeight(DominoDataImportUtil.exValue(document, "qizhong"));    //最大起飞全重
            aninfo.setAircraftSeatSize(DominoDataImportUtil.exValue(document, "zuowei"));    //座位数

           /* aninfo.setAircraftOwner(DominoDataImportUtil.exValue(document, "mingcheng"));	//航空器所有人名称
            aninfo.setAircraftOwnerAddress(DominoDataImportUtil.exValue(document, "rendizhi"));	//航空器所有人地址
            aninfo.setAircraftHolder(DominoDataImportUtil.exValue(document, "renmingcheng"));	//航空器占有人名称
            aninfo.setAircraftHolderAddress(DominoDataImportUtil.exValue(document, "rendizhi"));	//航空器占有人地址*/

            aninfo.setAircraftDeliveryAddress(DominoDataImportUtil.exValue(document, "hkqjfdd"));    //航空器交付地点（填写国家）

            /*待定*/
            if (DominoDataImportUtil.exValue(document, "yzzgmyhkjdj").length() > 0)
                aninfo.setApplicatIssignChina("1");    //航空器国籍登记情况-原在中国民用航空局登记-国籍登记标志
            aninfo.setApplicatNumChina(DominoDataImportUtil.exValue(document, "print_checkbox2"));//国籍登记标志（文本框）
            if (DominoDataImportUtil.exValue(document, "print_checkbox3").length() > 0)
                aninfo.setApplicatIsnotsignChina("0");    //航空器国籍登记情况-未在中国民用航空局登记
            if (DominoDataImportUtil.exValue(document, "yzwgdqdj").length() > 0)
                aninfo.setApplicatNumForeign("1");    //航空器国籍登记情况-登记国/登记地区名称及国籍登记标志-原在外国/地区登记
            aninfo.setApplicatNumForeign(DominoDataImportUtil.exValue(document, "print_checkbox1"));//原来外国或地区登记（文本框）
            if (DominoDataImportUtil.exValue(document, "print_checkbox5").length() > 0)
                aninfo.setApplicatIsnotsignForeign("0");    //航空器国籍登记情况-注销或未在外国/地区国籍登记的证明

            /*修改*/
            if (DominoDataImportUtil.exValue(document, "xuanzhe").equals("否")) {//是否按照特殊申请程序办理国籍登记证（空白国籍证申请应选择“是”）是
                aninfo.setIsspecial("0");
            } else {
                aninfo.setIsspecial("1");
            }
            //aninfo.setIsspecial(DominoDataImportUtil.exValue(document, "xuanzhe"));	//是否按照特殊申请程序办理国籍登记证（空白国籍证申请应选择“是”）是
            aninfo.setReasons(DominoDataImportUtil.exValue(document, "print_shiyongliyou"));    //适用理由
            aninfo.setMissFileInfo(DominoDataImportUtil.exValue(document, "print_jiaofudidian"));    //所缺证明文件名称、补交地点、日期
            aninfo.setApplicationSignatory(DominoDataImportUtil.exValue(document, "print_sqsqzr"));    //申请书签字人
            String qzrq = DominoDataImportUtil.exValue(document, "print_sqsqzrq");
            if (qzrq.length() > 4 && qzrq.length() < 22) {
                qzrq = "19" + qzrq;
            }
            if (!qzrq.equals("")) {
                qzrq = qzrq.substring(0, qzrq.length() - 4).replace("/", "-");
           /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Date dateqzrq = sdf.parse(qzrq);*/
                aninfo.setSignatureDate(Timestamp.valueOf(qzrq));    //签字日期
            }
            String sqsj = DominoDataImportUtil.exValue(document, "shenqingshijian");
            if (sqsj.length() > 4 && sqsj.length() < 22) {
                sqsj = "19" + sqsj;
            }
            if (!sqsj.equals("")) {
                sqsj = sqsj.substring(0, sqsj.length() - 4).replace("/", "-");
                aninfo.setApplicatDate(Timestamp.valueOf(sqsj));//申请时间
            }
            aninfo.setSignatoryDuty(DominoDataImportUtil.exValue(document, "print_sqsqzrzw"));    //签字人职务
            //绿色补充
            aninfo.setStatement(DominoDataImportUtil.exValue(document, "checkbox_click"));    //声明
            aninfo.setRummager(DominoDataImportUtil.exValue(document, "shouquanduixiang"));    //检查人员
            aninfo.setNcNum(DominoDataImportUtil.exValue(document, "ncidbh"));    //国籍证编号
            aninfo.setRegnumNum(DominoDataImportUtil.exValue(document, "geihaoSN"));//给号函
            aninfo.setAcceptanceNum(DominoDataImportUtil.exValue(document, "shoulibh"));//受理编号
            aninfo.setAuthorizationNum("民航国籍授权批字" + DominoDataImportUtil.exValue(document, "shouquanbh") + "号");//授权书编号
            aninfo.setAuthorization(DominoDataImportUtil.exValue(document, "ShouQuanRen"));//授权人
            aninfo.setApplicatUse(DominoDataImportUtil.exValue(document, "yongtu"));//用途
            aninfo.setAcceptanceFile(DominoDataImportUtil.exValue(document, "shoulisn"));//受理书id
            aninfo.setAuthorizationFile(DominoDataImportUtil.exValue(document, "shouquanshusn"));//授权书id
            aninfo.setNcFile(DominoDataImportUtil.exValue(document, "ncid"));//国籍证id
            aninfo.setApplicatStatus("jxd_ins_14");//申请状态

            //displaycurrentnode
            //FlowName
            //FlowNodeXml
            //FlowStatus 0
            //FullFlowLog
            //CURRENTNODE  结束
            //CURRENTNODEDATA    CURRENTNODEDEPT


        } catch (Exception e) {
            e.printStackTrace();
        }
        return aninfo;
    }

    public AmosNsNcertificateInfoFile getannif(Document document, String annoid) {
        AmosNsNcertificateInfoFile bannif = new AmosNsNcertificateInfoFile();
        bannif.setSn(DominoDataImportUtil.exValue(document, "SN"));
        bannif.setInfoid(annoid);
        bannif.setUnid(DominoDataImportUtil.exValue(document, "DOCUNID"));
        bannif.setAirnumber(DominoDataImportUtil.exValue(document, "AirNumber"));
        bannif.setAttachmentpath(DominoDataImportUtil.exValue(document, "ATTACHMENTSTORAGEPATH"));
        bannif.setAttachmentid(DominoDataImportUtil.exValue(document, "ATTACHMENTSTORAGEID"));

        return bannif;
    }

    /**
     * 将附件信息插入到附件信息表中
     *
     * @param fileUploadResult
     * @return
     */
    public boolean insertFile(Map<String, Object> fileUploadResult, String ncid) {
        boolean falg = true;
        try {
            AmosNsNcertificateFile annf = new AmosNsNcertificateFile();
            annf.setId(UUID.randomUUID().toString().replace("-", ""));
            annf.setBusinessid(ncid);
            annf.setFileName(fileUploadResult.get("name").toString());
            annf.setFilePath(fileUploadResult.get("id").toString());
            annf.setFileStatus("1");
            //annf.setFileTypeId(fileUploadResult.get("type").toString());
            //annf.(fileUploadResult.get("updateTime"));
            //annf.setUploaduser(fileUploadResult.get("updateUser").toString());
            annf.setTablename(fileUploadResult.get("fileId").toString());
            annf.insert();
        } catch (Exception e) {
            falg = false;
            e.printStackTrace();

        }
        return falg;
    }


    /**
     * 保存文件到本地
     *
     * @param is
     * @param filename
     * @return
     * @throws IOException
     */
    public boolean saveFile(InputStream is, String filename) {
        boolean flag = false;
        try {
            int index;
            String destination = "E:\\attachmentExport\\" + filename;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(destination);
            while ((index = is.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            is.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @RequestMapping("/fileupload")
    @ResponseBody
    public Boolean updateData() throws NotesException, IOException {
        boolean flag = true;
        String server = "localhost:8086";
        String username = "tkarn";
        String password = "123456";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;

        localsession = ctd.setConnection1(server, username, password);
        try {
            Wrapper<AmosNsNcertificateInfoFile> wrapper = null;
            List<AmosNsNcertificateInfoFile> fileList = amosNsNcertificateInfoFileService.selectList(wrapper);
            for (AmosNsNcertificateInfoFile file : fileList) {
                //Map<String, Object> fileUploadResult = new HashMap<>();
                List<Map<String, Object>> fileUploadResultlist = new ArrayList<>();
                if (!file.getAttachmentpath().equals("") && !file.getAttachmentid().equals("") && file.getAttachmentpath() != null && file.getAttachmentid() != null && file.getAttachmentpath().equals("weboa/common/attstore_20181117-030024.nsf")) {
                    //if (fileUploadResult != null) {
                    //insertFile(fileUploadResult, file.getInfoid());
                    String attachmentPath = file.getAttachmentpath();
                    attachmentPath = attachmentPath.substring(attachmentPath.lastIndexOf("/") + 1, attachmentPath.length());
                    if (file.getType().equals("2")) {

                        fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, file.getAttachmentid(), file.getSn(), file.getAirnumber(), ConstantData.airworth + "");
                        for (Map<String, Object> fileUploadResult : fileUploadResultlist) {
                            //更改主表
                            AmosNsAirworthyInfo airworthyInfo = amosNsAirworthyInfoService.selectById(file.getInfoid());
                            airworthyInfo.setFolderId(fileUploadResult.get("parentId").toString());
                            amosNsAirworthyInfoService.updateById(airworthyInfo);
                            //插入附件表
                            AmosNsAirworthyFile airworthyFile = new AmosNsAirworthyFile();
                            airworthyFile.setId(IdWorker.get32UUID());
                            airworthyFile.setBusinessid(file.getInfoid());
                            airworthyFile.setFileName(fileUploadResult.get("name").toString());
                            airworthyFile.setFilePath(fileUploadResult.get("id").toString());
                            airworthyFile.setFileStatus("1");
                            airworthyFile.setUploaduser("0401");
                            airworthyFile.setTablename(fileUploadResult.get("fileId").toString());
                            amosNsAirworthyFileService.insert(airworthyFile);
                        }
                    } else if (file.getType().equals("1")) {
                        fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, file.getAttachmentid(), file.getSn(), file.getAirnumber(), ConstantData.nationalitycertificate + "");
                        for (Map<String, Object> fileUploadResult : fileUploadResultlist) {
                            //修改主表

                            AmosNsNcertificateInfo ncertificateInfo = amosNsNcertificateInfoService.selectById(file.getInfoid());
                            ncertificateInfo.setFolderId(fileUploadResult.get("parentId").toString());
                            amosNsNcertificateInfoService.updateById(ncertificateInfo);
                            //插入附件
                            AmosNsNcertificateFile annf = new AmosNsNcertificateFile();
                            annf.setId(IdWorker.get32UUID());
                            annf.setBusinessid(file.getInfoid());
                            annf.setFileName(fileUploadResult.get("name").toString());
                            annf.setFilePath(fileUploadResult.get("id").toString());
                            annf.setFileStatus("1");
                            annf.setUploaduser("0401");
                            annf.setTablename(fileUploadResult.get("fileId").toString());
                            annf.insert();
                        }
                    } else if (file.getType().equals("3")) {
                        fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, file.getAttachmentid(), file.getSn(), file.getAirnumber(), ConstantData.inkjet + "");
                        for (Map<String, Object> fileUploadResult : fileUploadResultlist) {
                            //修改主表
                            AmosNsInkjetInfo inkjetInfo = amosNsInkjetInfoService.selectById(file.getInfoid());
                            inkjetInfo.setFoldId(fileUploadResult.get("parentId").toString());
                            amosNsInkjetInfoService.updateById(inkjetInfo);
                            //插入附件
                            AmosNsInkjetFile annf = new AmosNsInkjetFile();
                            annf.setId(IdWorker.get32UUID());
                            annf.setBusinessid(file.getInfoid());
                            annf.setFileName(fileUploadResult.get("name").toString());
                            annf.setFilePath(fileUploadResult.get("id").toString());
                            annf.setFileStatus("1");
                            annf.setUploaduser("0401");
                            annf.setTablename(fileUploadResult.get("fileId").toString());
                            annf.insert();
                        }
                    }


                }
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
    /* *//**
     * 附件公共方法
     *
     * @param attachmentPath
     * @param ViewDb
     * @param attachmentId
     * @param dataYwlsh
     * @param zch
     * @param localsession
     * @return
     * @throws NotesException
     * @throws IOException
     *//*
    public Map<String, Object> getFiles(String attachmentPath, String ViewDb, String attachmentId, String dataYwlsh, String zch, lotus.domino.Session localsession) throws NotesException, IOException {
        Map<String, Object> fileUploadResult = null;
        lotus.domino.Database db = localsession.getDatabase("", attachmentPath);
        String foldname = "default";
        if (db == null || db.getViews().size() == 0) {
            System.out.println("获取附件库失败！");
        } else {
            System.out.println("获取附件库成功！");
        }
        View view = db.getView(ViewDb);//viewColumns.size()
        if (view.getColumnCount() == 0) {
            System.out.println("未能找到视图！");
        }
        Vector v1 = view.getColumnNames();
        System.out.println(v1 + ",,..,");
        Document doc = null, docx = null;
        // doc = view.getFirstDocument();
        //根据附件的UNID获取对应的对象
        doc = view.getDocumentByKey(attachmentId);
        // System.out.println(doc.getUniversalID() + "！！！！");
        DominoDocument dd = new DominoDocument(doc);
        //此处unid应该与业务数据提供的unid对应
        //String unid = dd.getUNID();
        // System.out.println("！！ " + unid + "  ！！");
        //获取对应的所有附件
        List<EmbeddedObject> list = dd.getAllFiles();
        //dataYwlsh = ncdoc.getItemValueString("SN");//获取业务流水号
        //zch = ncdoc.getItemValueString("AirNumber");//获取注册号
        //需要创建的文件夹名称
        foldname = dataYwlsh + zch;
        String filename = "default";
        String foldid="";
        //查询文件夹是否已经存在
        ResponsesEntity<FolderInfoEntity> folder = folderInfo.querySubFolderInfos(filename,"");
        if(folder.getStatus().toString().equals("0"))
            //创建的文件夹id
            foldid= createFolder(foldname);
        else
            foldid = folder.getData().getId().toString();
        //String foldid = "817637";
        //将UNID对应的多个附件，循环上传，并将返回值插入到附件信息表中
        for (int i = 0; i < list.size(); i++) {
            InputStream is = list.get(i).getInputStream();
            String path = "E:\\attachmentExport\\" + list.get(i).getName();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(path);
            while ((index = is.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            is.close();
            File file = new File(path);//此处需注意
            filename = list.get(i).getName();
            filename = filename.substring(0, filename.lastIndexOf("."));
            fileUploadResult = filehttp.uploadFile(file, filename, "R1.0", foldid);
            if (file.exists()) {
                file.delete();
            }
        }
        return fileUploadResult;
    }

    public String getAcceptDate() {

        return "";
    }
*/

    /**
     * 创建文件夹
     */
//    @RequestMapping("/createFolder")
//    @ResponseBody
    public String createFolder(String foldName) {
        String path = ConstantData.project + "/" + ConstantData.nationalitycertificate;//国籍证首次申请
        //foldName = foldName + UUID.randomUUID().toString();
        FolderInfoEntity folder = folderInfo.createFolder(foldName, "", path, "0");
        return folder.getId().toString();
    }

    /**
     * 修改文件夹
     */
    @RequestMapping("/updateFolderInfo")
    @ResponseBody
    public boolean updateFolderInfo(long foldId, String foldName, String foldDesc) {
        boolean flag = folderInfo.updateFolderInfo(foldId, foldName, foldDesc);
        return flag;
    }

    /**
     * 更新文件夹信息
     */
    public String querySubFolderInfos(String folderName, String parentId) {

        ResponsesEntity<FolderInfoEntity> folder = folderInfo.querySubFolderInfos(folderName, parentId);

        return folder.getStatus().toString();

    }
}
