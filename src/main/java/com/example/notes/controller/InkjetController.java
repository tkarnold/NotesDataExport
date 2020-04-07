package com.example.notes.controller;
import java.io.*;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.example.notes.api.ConnectToDomino;
import com.example.notes.api.DominoDocument;
import com.example.notes.entity.AmosNsInkjetInfo;
import com.example.notes.entity.AmosNsNcertificateInfoFile;
import com.example.notes.service.IAmosNsInkjetInfoService;
import com.example.notes.service.IAmosNsNcertificateInfoFileService;
import com.example.notes.util.DominoDataImportUtil;
import com.example.notes.util.file.FileHttp;
import com.example.notes.util.file.FolderInfoImpl;
import lotus.domino.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 喷绘方案控制器
 *
 * @author
 * @Date 2020-03-13 20:08:54
 */
@RestController
@RequestMapping("/inkjet")
public class InkjetController {

    @Autowired
    private IAmosNsInkjetInfoService amosNsInkjetInfoService;
    @Autowired
    private IAmosNsNcertificateInfoFileService amosNsNcertificateInfoFileService;
    @Autowired
    private FolderInfoImpl folderInfo;
    @Autowired
    private FileHttp filehttp;


    @RequestMapping("/migrate")
    @ResponseBody
    public void migrate() throws NotesException {
        String server = "localhost:8080";
        String username ="liwncy";
        String password="09094110";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        lotus.domino.Session localsession = null;
        localsession = ctd.setConnection1(server, username, password);
        Registration r = localsession.createRegistration();


        lotus.domino.Database db = null;
        lotus.domino.Database inkjiedb = null; //喷绘方案数据库
        String attachmentId = "";//附件对应ID
        String dataUnid = "";//数据对应Id
        String attachmentPath = "";//附件路径
        String dataYwlsh = "";//业务流水号
        String zch = "";//注册号
        String foldname = "";//文件夹名称

        try {
            inkjiedb = localsession.getDatabase("", "1805213800");
            if (inkjiedb == null || inkjiedb.getViews().size() == 0) {
                System.out.println("获取喷涂方案库失败！");
                return;
            } else {
                System.out.println("获取喷涂方案库成功！");
            }

            //1,======================获取喷涂方案业务数据======================
            View inkjeView = inkjiedb.getView("已办理结束");
            if (inkjeView.getColumnCount() == 0) {
                System.out.println("未能找到视图！");
                return;
            }
            Document inkjeDoc = null, inkjeDocx = null;
            inkjeDoc = inkjeView.getFirstDocument();  //取得第一个文档
            int count = 0;
            //循环获取对象实例
            while (inkjeDoc != null) {

                //获取喷涂方案业务数据
                AmosNsInkjetInfo inkjetInfo = exDate(inkjeDoc);
                if(true){
                    //===========================规范数据信息================================
                    this.normalData(inkjetInfo);
                }
                if(true){
                    //===========================读取喷涂方案审批信息================================
                    db = localsession.getDatabase("", "1805215612"); //库地址要变

                    if (db == null || db.getViews().size() == 0) {
                        System.out.println("获取喷涂方案信息库失败！");
                    } else {
                        System.out.println("获取喷涂方案信息库成功！");
                    }
                    View view = db.getView("按流水号");//viewColumns.size()  喷涂方案的ptid=喷涂归档信息的SN
                    if (view.getColumnCount() == 0) {
                        System.out.println("未能找到视图！");
                    }
                    Document doc = view.getDocumentByKey(DominoDataImportUtil.exValue(inkjeDoc, "ptid"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String sj = DominoDataImportUtil.exValue(doc, "UpdateDateTime");
                    if(!sj.equals("")&&sj.length()>=22) {
                        sj = sj.substring(0, sj.length() - 4).replace("/", "-");
                    }
                    if(!sj.equals("")) {
                        inkjetInfo.setApproveDate(new Timestamp(sdf.parse(sj).getTime()));//UpdateDate
                    }
                }
                if(true){
                    //===========================获取附件相关信息信息================================
                    AmosNsNcertificateInfoFile ncapplyfile = getannif(inkjeDoc,inkjetInfo.getInkjetId(),"3");
                    amosNsNcertificateInfoFileService.insert(ncapplyfile);
                }

                //将喷涂方案数据插入数据库
                amosNsInkjetInfoService.insert(inkjetInfo);
                //循环业务数据，不断获取对象
                inkjeDocx = inkjeView.getNextDocument(inkjeDoc);
                inkjeDoc = inkjeDocx;
                count++;
                System.out.println("数据迁移成功！");
            }

            System.out.println("数据全部迁移成功");
        } catch (NotesException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        System.out.println("db title = " + db.getLastModified());
    }

    /**
     * 将信息转化成喷涂方案信息
     *
     * @return
     */
    public AmosNsInkjetInfo exDate(Document document) {
        AmosNsInkjetInfo inkjetInfo = new AmosNsInkjetInfo();
        try {
            inkjetInfo.setInkjetId(UUID.randomUUID().toString().replace("-", "")); //ID
            inkjetInfo.setSerialNum(DominoDataImportUtil.exValue(document, "SN")); // 业务流水号
            inkjetInfo.setProposer(DominoDataImportUtil.exValue(document, "shenqingren")); // 申请人
            String sqsj = DominoDataImportUtil.exValue(document, "shenqingshijian");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = sdf.parse(sqsj);
            inkjetInfo.setApplyDate(new Timestamp(date.getTime())); // 申请时间 shenqingshijian
//            inkjetInfo.setApplyDate(new Timestamp(new java.util.Date().getTime())); // 申请时间 shenqingshijian
//            inkjetInfo.setAircraftCategory(DominoDataImportUtil.exValue(document, "")); // 航空器类别
//            inkjetInfo.setApplyCategory("10086"); // 申请类别
            inkjetInfo.setRegistrationNum(DominoDataImportUtil.exValue(document, "AirNumber")); // 注册号/临时登记证
            inkjetInfo.setAircraftType(DominoDataImportUtil.exValue(document, "AircraftType")); // 航空器型别
            inkjetInfo.setNote(DominoDataImportUtil.exValue(document, "textarea")); // 备注
            inkjetInfo.setTitleContent(DominoDataImportUtil.exValue(document, "biaotineirong")); // 标题内容
            inkjetInfo.setBodyContent(DominoDataImportUtil.exValue(document, "zhenwenneirong")); // 正文内容
            inkjetInfo.setBodyContent1(DominoDataImportUtil.exValue(document, "feijixinxi"));
            inkjetInfo.setStatement("1"); // 声明
//            inkjetInfo.setMainHint(DominoDataImportUtil.exValue(document, "")); //
//            inkjetInfo.setCreateDate(new Timestamp(new java.util.Date().getTime()));
//            inkjetInfo.setStatus(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setApproveDate(new Timestamp(new java.util.Date().getTime())); // 喷涂批复日期 (待定)
//            inkjetInfo.setFirstApprover(DominoDataImportUtil.exValue(document, "")); //
            inkjetInfo.setReplyNo(DominoDataImportUtil.exValue(document, "ptidbh")); // 喷涂批复编号
            inkjetInfo.setApprover("徐超群"); // 喷涂批复批准人
//            inkjetInfo.setSeal(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setFoldId(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setFoldName(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setCreateuserid(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setAreaAdministrationId(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setCreateDeptId(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setArchiverId(DominoDataImportUtil.exValue(document, ""));
            inkjetInfo.setAreaAdministration(DominoDataImportUtil.exValue(document, "glj"));
//            inkjetInfo.setAcOfficePhone(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setPicturePath(DominoDataImportUtil.exValue(document, ""));
//            inkjetInfo.setApplicatFileId(DominoDataImportUtil.exValue(document, ""));

            inkjetInfo.setCreateuserid("superadmin1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inkjetInfo;
    }
    /**
     * 规范数据信息
     * @param inkjetInfo
     * @return
     */
    public AmosNsInkjetInfo normalData(AmosNsInkjetInfo inkjetInfo){
        /*添加管理局ID*/
        return inkjetInfo;
    }
    /**
     *
     * @param document
     * @param businessId
     * @return
     */
    public AmosNsNcertificateInfoFile getannif(Document document, String businessId , String type){
        AmosNsNcertificateInfoFile bannif = new AmosNsNcertificateInfoFile();
        bannif.setSn(DominoDataImportUtil.exValue(document, "SN"));
        bannif.setInfoid(businessId);
        bannif.setType(type);
        bannif.setUnid(DominoDataImportUtil.exValue(document, "DOCUNID"));
        bannif.setAirnumber(DominoDataImportUtil.exValue(document, "AirNumber"));
        bannif.setAttachmentpath(DominoDataImportUtil.exValue(document, "ATTACHMENTSTORAGEPATH"));
        bannif.setAttachmentid(DominoDataImportUtil.exValue(document, "ATTACHMENTSTORAGEID"));
        return bannif;
    }
}
