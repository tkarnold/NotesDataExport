package com.example.notes.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.example.notes.api.ConnectToDomino;
import com.example.notes.constant.NotesConstant;
import com.example.notes.entity.AmosNsAirworthyFile;
import com.example.notes.entity.AmosNsAirworthyInfo;
import com.example.notes.entity.AmosNsNcertificateInfoFile;
import com.example.notes.service.IAmosNsAirworthyFileService;
import com.example.notes.service.IAmosNsAirworthyInfoService;
import com.example.notes.service.IAmosNsNcertificateInfoFileService;
import com.example.notes.util.DictUtil;
import com.example.notes.util.DominoDataImportUtil;
import com.example.notes.util.ValidationUtil;
import lotus.domino.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * 适航证控制器
 *
 * @author
 * @Date 2020-03-13 20:08:54
 */
@RestController
@RequestMapping("/airworthy")
public class AirworthyController {

    @Autowired
    private IAmosNsAirworthyInfoService amosNsAirworthyInfoService;
    @Autowired
    private IAmosNsAirworthyFileService amosNsAirworthyFileService;
    @Autowired
    private IAmosNsNcertificateInfoFileService amosNsNcertificateInfoFileService;


    @RequestMapping("/add")
    public void loadAllNotice() {
//        AmosNsAirworthyInfo apply = new AmosNsAirworthyInfo();
//        apply.setAirworthyId(IdWorker.get32UUID());
//        apply.setAcceptNum("10086");
//        apply.insert();
        updateData();
    }
    public Boolean updateData(){
        Wrapper<AmosNsNcertificateInfoFile> wrapper = null;
        List<AmosNsNcertificateInfoFile> fileList = amosNsNcertificateInfoFileService.selectList(wrapper);
        for (AmosNsNcertificateInfoFile file:fileList) {

            if(false){
                //更改主表
                AmosNsAirworthyInfo airworthyInfo = amosNsAirworthyInfoService.selectById(file.getInfoid());
                airworthyInfo.setFolderId("wenjianid");
                amosNsAirworthyInfoService.updateById(airworthyInfo);
                //插入附件表
                AmosNsAirworthyFile airworthyFile = new AmosNsAirworthyFile();
                airworthyFile.setBusinessid(file.getInfoid());

                amosNsAirworthyFileService.insert(airworthyFile);
            }
        }
        return false;
    }
    @RequestMapping("/migrate")
    @ResponseBody
    public void migrate() throws NotesException {
        String server = "localhost:8080";
        String username ="liwncy";
        String password="09094110";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        lotus.domino.Session localsession = null;
        localsession = ctd.setConnection1(server, username, password);
        //Registration r = localsession.createRegistration();

        try {
//            Database mainDb = localsession.getDatabase("", NotesConstant.AIRWORTHY_DB_OLD);
            Database mainDb = localsession.getDatabase("", NotesConstant.AIRWORTHY_DB_NEW);
            if (mainDb == null || mainDb.getViews().size() == 0) {
                System.out.println("获取适航证基本信息库失败！");
                return;
            } else {
                System.out.println("获取适航证基本信息库成功！");
            }

            //1,======================获取适航证基本信息业务数据======================
            View mainView = mainDb.getView("流程结束已归档");
            if (mainView.getColumnCount() == 0) {
                System.out.println("未能找到视图！");
                return;
            }
            Document mainDoc = null, nextDocx = null;
            mainDoc = mainView.getFirstDocument();  //取得第一个文档
            int count = 0;
            //循环获取对象实例
            while (mainDoc != null) {

                //1,========获取适航证业务数据=========
                AmosNsAirworthyInfo airworthyInfo = this.exData(mainDoc);
                //=====================获取喷涂方案编号================================
                String ptsn = DominoDataImportUtil.exValue(mainDoc, "ptsn");
                if(ptsn!=null && !"".equals(ptsn)){
                    Database ptDb = localsession.getDatabase("", NotesConstant.INKJET_DB);
                    if (ptDb == null || ptDb.getViews().size() == 0) {
                        System.out.println("获取喷涂方案库失败！");
                        return;
                    } else {
                        System.out.println("获取喷涂方案库失败！");
                    }
                    View ptView = ptDb.getView("按流水号");
                    if (ptView.getColumnCount() == 0) {
                        System.out.println("未能找到视图！");
                        return;
                    }
                    Document ptDoc  =ptView.getDocumentByKey(ptsn);
                    airworthyInfo.setInkjetReplyNum(DominoDataImportUtil.exValue(ptDoc, "ptidbh"));
                }else{
                    airworthyInfo.setInkjetReplyNum("");
                }
                //=================================喷涂方案信息END=========================================
                if (true) {
                    //===========================规范数据信息================================
                    this.normalData(airworthyInfo);
                }
                if (true) {
                    //1,===========================读取获取受理审批信息================================
                    Database otherDb = localsession.getDatabase("", NotesConstant.AIRWORTHY_ACCEPT_DB); //库地址要变

                    if (otherDb == null || otherDb.getViews().size() == 0) {
                        System.out.println("获取信息库失败！");
                    } else {
                        System.out.println("获取信息库成功！");
                    }
                    View otherDbView = otherDb.getView("按流水号");
                    if (otherDbView.getColumnCount() == 0) {
                        System.out.println("未能找到视图！");
                    }
                    //主表和附表的对应关系:喷涂方案的ptid=喷涂归档信息的SN
                    Document otherDoc = otherDbView.getDocumentByKey(mainDoc.getItemValueString("shoulisn"));
                    //找到并插入相关字段
                    /*受理日期*/
                    if (!"".equals(otherDoc.getItemValueString("shouliriqi"))) {
                        airworthyInfo.setAuditDate(DominoDataImportUtil.exDate(otherDoc, "shouliriqi", "yyyy-MM-dd"));
                    }
                    //2,============================读取获取授权书证件信息==========================================
                    Database otherDb2 = localsession.getDatabase("", NotesConstant.AIRWORTHY_AUTHORIZE_DB);
                    if (otherDb2 == null) {
                        System.out.println("获取授权库失败！");
                    } else {
                        System.out.println("获取授权库成功！");
                    }
                    View otherDb2View = otherDb2.getView("按流水号");
                    //主表和附表的对应关系:喷涂方案的ptid=喷涂归档信息的SN
                    Document other2Doc = otherDb2View.getDocumentByKey(mainDoc.getItemValueString("shouquanshusn"));
                    //找到并插入相关字段
                    /*授权日期*/
                    if (!"".equals(other2Doc.getItemValueString("qianfariqi"))) {
                        airworthyInfo.setMandateDate(DominoDataImportUtil.exDate(other2Doc, "qianfariqi", "yyyy-MM-dd"));
                    }
                }
                if (true) {
                    //===========================获取附件相关信息信息================================
                    AmosNsNcertificateInfoFile ncapplyfile = getannif(mainDoc, airworthyInfo.getAirworthyId(), "2");
                    amosNsNcertificateInfoFileService.insert(ncapplyfile);
                }

                //将喷涂方案数据插入数据库
                amosNsAirworthyInfoService.insert(airworthyInfo);
                //循环业务数据，不断获取对象
                nextDocx = mainView.getNextDocument(mainDoc);
                mainDoc = nextDocx;
                count++;
                System.out.println("数据迁移成功！");
            }
            System.out.println("数据全部迁移成功");
        } catch (NotesException e) {
            e.printStackTrace();
        }

//        System.out.println("db title = " + db.getLastModified());
    }

    /**
     * 将信息转化成适航证信息
     *
     * @return
     */
    public AmosNsAirworthyInfo exData(Document document) {
        AmosNsAirworthyInfo airworthyInfo = new AmosNsAirworthyInfo();
        try {
            airworthyInfo.setAirworthyId(IdWorker.get32UUID());
            airworthyInfo.setSerialsnum(DominoDataImportUtil.exValue(document, "SN"));//业务流水号
            airworthyInfo.setApplicat(DominoDataImportUtil.exValue(document, "shenqingren"));//申请人
//            airworthyInfo.setApplicatType(DominoDataImportUtil.exValue(document, ""));//申请人类别
            airworthyInfo.setAreaAdministration(DominoDataImportUtil.exValue(document, "glj"));//申请人所在地区管理局
//            airworthyInfo.setAppliicatAddress(DominoDataImportUtil.exValue(document, ""));//申请人地址
            airworthyInfo.setApplicatDate(DominoDataImportUtil.exDate(document, "shenqingshijian", "yyyy-MM-dd HH:mm:ss"));//申请时间shenqingshijian
//            airworthyInfo.setLegalPersons(DominoDataImportUtil.exValue(document, ""));//法定代表人
//            airworthyInfo.setApplicatContact(DominoDataImportUtil.exValue(document, ""));//申请人联系人
            airworthyInfo.setApplicatMobile(DominoDataImportUtil.exValue(document, "dianhua"));//申请人手机号
//            airworthyInfo.setApplicatPhone(DominoDataImportUtil.exValue(document, ""));//申请人联系电话
            airworthyInfo.setApplicatFax(DominoDataImportUtil.exValue(document, "chuanzhen"));//申请人传真
            airworthyInfo.setApplicatPostcode(DominoDataImportUtil.exValue(document, "youbian"));//申请人邮编
//            airworthyInfo.setNcertificateId(DominoDataImportUtil.exValue(document, ""));//国籍证ID
//            airworthyInfo.setNcertificate(DominoDataImportUtil.exValue(document, ""));//国籍证登记标记
            airworthyInfo.setNcertificateNum(DominoDataImportUtil.exValue(document, "ncidbh"));//国籍证编号
            airworthyInfo.setNcertificate(DominoDataImportUtil.exValue(document, "AirNumber"));//注册号
//            airworthyInfo.setNcertificateIsSpecial(DominoDataImportUtil.exValue(document, ""));//国籍证是否按照特殊流程处理
//            airworthyInfo.setNcertificateStatus(DominoDataImportUtil.exValue(document, ""));//国籍证申请状态
//            airworthyInfo.setInkjetId(DominoDataImportUtil.exValue(document, ""));//喷绘方案ID
            //airworthyInfo.setInkjetReplyNum(DominoDataImportUtil.exValue(document, "ptsn"));//喷涂方案批复编号
//            airworthyInfo.setIsApproveInkjet(DominoDataImportUtil.exValue(document, ""));//是否核准喷涂方案
            airworthyInfo.setAircraftManufacturer(DominoDataImportUtil.exValue(document, "zhizaozhe"));//航空器制造人名称
//            airworthyInfo.setAircraftCategory(DominoDataImportUtil.exValue(document, ""));//航空器类别
            airworthyInfo.setAircraftType(DominoDataImportUtil.exValue(document, "AircraftType"));//航空器型别
            airworthyInfo.setAircraftNumber(DominoDataImportUtil.exValue(document, "xuhao"));//航空器序列号
            airworthyInfo.setManufactureDate(DominoDataImportUtil.exDate(document, "chuchanriqi", "yyyy-MM-dd HH:mm:ss"));//航空器出厂日期chuchanriqi
//            airworthyInfo.setAircraftMaxWeight(DominoDataImportUtil.exValue(document, ""));//航空器最大起飞全重
//            airworthyInfo.setAircraftMaxLoad(DominoDataImportUtil.exValue(document, ""));//航空器最大可用业载
//            airworthyInfo.setAircraftSeatSize(DominoDataImportUtil.exValue(document, ""));//航空器座位数
//            airworthyInfo.setAircraftIsimport(DominoDataImportUtil.exValue(document, ""));//航空器是否进口
//            airworthyInfo.setAircraftIsnew(DominoDataImportUtil.exValue(document, ""));//航空器是否使用
//            airworthyInfo.setAircraftFrom(DominoDataImportUtil.exValue(document, ""));//航空器采购来源
//            airworthyInfo.setAircraftFromOther(DominoDataImportUtil.exValue(document, ""));//航空器采购来源其他
            airworthyInfo.setFlyHours(DominoDataImportUtil.exValue(document, "hangkongqi11"));//航空器飞行小时数
            airworthyInfo.setFlyCount(DominoDataImportUtil.exValue(document, "hangkongqi17"));//航空器起落次数
            airworthyInfo.setPickUpLocation(DominoDataImportUtil.exValue(document, "jiejididian"));//接机地点
            airworthyInfo.setAircraftDeliveryAddress(DominoDataImportUtil.exValue(document, "hkqjfdd"));//具体交付地点
//            airworthyInfo.setIsApprovalProject(DominoDataImportUtil.exValue(document, ""));//是否核准方案
            airworthyInfo.setAircraftOwner(DominoDataImportUtil.exValue(document, "mingcheng"));//航空器所有人名称
            airworthyInfo.setAircraftOwnerAddress(DominoDataImportUtil.exValue(document, "dizhi"));//航空器所有人地址
            airworthyInfo.setAircraftOwnerPhone(DominoDataImportUtil.exValue(document, "lxdh_syr"));//航空器所有人电话
            airworthyInfo.setAircraftOwnerFax(DominoDataImportUtil.exValue(document, "chuanzhen_syr"));//航空器所有人传真
//            airworthyInfo.setAircraftHolder(DominoDataImportUtil.exValue(document, ""));//航空器占有人名称
//            airworthyInfo.setAircraftHolderAddress(DominoDataImportUtil.exValue(document, ""));//航空器占有人地址
//            airworthyInfo.setAircraftHolderPhone(DominoDataImportUtil.exValue(document, ""));//航空器占有人电话
//            airworthyInfo.setAircraftHolderFax(DominoDataImportUtil.exValue(document, ""));//航空器占有人传真
            airworthyInfo.setEngineModel(DominoDataImportUtil.exValue(document, "fdj_xh"));//发动机型别
            airworthyInfo.setEngineCreateuser(DominoDataImportUtil.exValue(document, "fdj_zzr"));//发动机制造人名称
            airworthyInfo.setEngineNum(DominoDataImportUtil.exValue(document, "zjnumber"));//发动机装机数量
            airworthyInfo.setPropellerModel(DominoDataImportUtil.exValue(document, "lxj_xb"));//螺旋桨型别
            airworthyInfo.setPropellerCreateuser(DominoDataImportUtil.exValue(document, "lxj_zzr"));//螺旋桨制造人名称
//            airworthyInfo.setPropellerNum(DominoDataImportUtil.exValue(document, ""));//螺旋桨装机数量
            airworthyInfo.setAirworthyCategory(DominoDataImportUtil.exValue(document, "leibie"));//适航证类别（下面处理）

            airworthyInfo.setStandardIsTransport(DominoDataImportUtil.exValue(document, "lianxiren"));//标准适航证是否是运输类飞机
            airworthyInfo.setStandardTransportType(DominoDataImportUtil.exValue(document, "lianxiren_1"));//标准适航证运输类飞机类型

            //airworthyInfo.setStandardUseType(DominoDataImportUtil.exValue(document, "lianxiren2"));//标准适航证用途类型
            String bzshzfjlx = DominoDataImportUtil.exValue(document, "lianxiren2");
            String bzshzfjlx_new = "";
            if(bzshzfjlx.indexOf("正常类飞机") !=-1){
                bzshzfjlx_new = "1";
            }
            if(bzshzfjlx.indexOf("实用类飞机") !=-1){
                if(bzshzfjlx_new .equals(""))
                    bzshzfjlx_new = "2";
                else
                    bzshzfjlx_new = ",2";
            }
            if(bzshzfjlx.indexOf("特技类飞机") !=-1){
                if(bzshzfjlx_new .equals(""))
                    bzshzfjlx_new = "3";
                else
                    bzshzfjlx_new = bzshzfjlx_new+",3";
            }
            if(bzshzfjlx.indexOf("通勤类飞机") !=-1){
                if(bzshzfjlx_new .equals(""))
                    bzshzfjlx_new = "4";
                else
                    bzshzfjlx_new = bzshzfjlx_new+",4";
            }
            airworthyInfo.setStandardUseType(bzshzfjlx_new);
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren3"))){
                airworthyInfo.setStandardOtherType("1");
            }
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren4"))){
                airworthyInfo.setStandardOtherType("2");
            }
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren5"))){
                airworthyInfo.setStandardOtherType("3");
            }
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren6"))){
                airworthyInfo.setStandardOtherType("4");
            }//标准适航证其余类型

            //特殊适航证类型
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren31"))){
                airworthyInfo.setSpecialAirworthType("1");
            }
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren32"))){
                airworthyInfo.setSpecialAirworthType("2");
            }
            if(!StringUtils.isEmpty(DominoDataImportUtil.exValue(document, "lianxiren33"))){
                airworthyInfo.setSpecialAirworthType("3");
            }

            //airworthyInfo.setSpecialWorkType(DominoDataImportUtil.exValue(document, "lianxiren32"));//特殊适航证作业类别
//            airworthyInfo.setSpecialWorkTypeOther(DominoDataImportUtil.exValue(document, ""));//特殊适航证作业类别其他用途
            String shzzylb = DominoDataImportUtil.exValue(document, "lianxiren32");
            String shzzylb_new = "";
            String[] shzzylbarr = {"农业（喷洒药剂和播种等)","森林和野生动植物保护","航测（摄影、测绘、石油及矿藏勘测等)","巡查（管道、电力线和水渠的巡查等)","天气控制（人工降雨等)","空中广告","其他情况","局方规定的任何其他用处"};
            if(!shzzylb.equals("")) {
                for(int i=1;i<shzzylbarr.length;i++) {
                    if (shzzylb.indexOf(shzzylbarr[i]) != -1) {
                        if(shzzylb_new.equals("")){
                            shzzylb_new= i+"";
                        }else{
                            shzzylb_new=shzzylb_new+","+i;
                        }
                    }
                }
                airworthyInfo.setSpecialWorkTypeOther(shzzylb_new);
            }
//            airworthyInfo.setTestAirworthType(DominoDataImportUtil.exValue(document, ""));//实验类适航证
            airworthyInfo.setTcNum(DominoDataImportUtil.exValue(document, "bianhao77"));//型号合格证(TC)编号
            airworthyInfo.setTcVersion(DominoDataImportUtil.exValue(document, "hangkongqi456"));//型号合格证(TC)数据单版次
            airworthyInfo.setVtcNum(DominoDataImportUtil.exValue(document, "hangkongqi4"));//型号认可证VTC编号
            airworthyInfo.setVtcVersion(DominoDataImportUtil.exValue(document, "hangkongqi5"));//型号认可证(VTC)数据单版次
            airworthyInfo.setTdaNum(DominoDataImportUtil.exValue(document, "hangkongqi6"));//型号设计批准书(TDA)编号
            airworthyInfo.setTdaVersion(DominoDataImportUtil.exValue(document, "hangkongqi7"));//型号设计批准书(TDA)数据单版次
            airworthyInfo.setOthersCondition(DominoDataImportUtil.exValue(document, "hangkongqi8"));//民航局同意的其他情况
            airworthyInfo.setForeignTcNum(DominoDataImportUtil.exValue(document, "hangkongqi9"));//外国适航当局颁发的型号合格证
            airworthyInfo.setForeignTcVersion(DominoDataImportUtil.exValue(document, "hangkongqi112"));//外国适航当局颁发的型号合格证数据单版次
            airworthyInfo.setIsPcNum(DominoDataImportUtil.exValue(document, "shengchangpiz"));//是否给出许可证(pc)编号
//            airworthyInfo.setPcNum(DominoDataImportUtil.exValue(document, ""));//生产许可证(pc)编号
            airworthyInfo.setIsPosNum(DominoDataImportUtil.exValue(document, "shengchangpiz4"));//是否给出生产机构批准书(POA)编号
//            airworthyInfo.setPosNum(DominoDataImportUtil.exValue(document, ""));//生产机构批准书(POA)编号
            airworthyInfo.setIsApisFile(DominoDataImportUtil.exValue(document, "shengchangpiz2"));//是否上传经批准的生产检验系统(APIS)文件
            airworthyInfo.setIsTconlyFile(DominoDataImportUtil.exValue(document, "shengchangpiz3"));//是否上传仅依赖型号合格证(TConly)文件
            airworthyInfo.setIsOtherMakeApproveFile(DominoDataImportUtil.exValue(document, "shengchangpiz42"));//是否上传其他制造批准文件
//            airworthyInfo.setIsDifferenceDescriptionFile(DominoDataImportUtil.exValue(document, ""));//是否上传航空器交付时构型与批准认可型号的差异说明文件
//            airworthyInfo.setIsPreCheckFile(DominoDataImportUtil.exValue(document, ""));//是否上传预检验报告文件
//            airworthyInfo.setIsNecessaryOtherFile(DominoDataImportUtil.exValue(document, ""));//是否上传适航审定司认为必要的其他文件
//            airworthyInfo.setExportAirworthyIsApply(DominoDataImportUtil.exValue(document, ""));//出口适航证是否适航
//            airworthyInfo.setExportNotApplyReason(DominoDataImportUtil.exValue(document, ""));//出口适航证不适用理由
            airworthyInfo.setSignUserName(DominoDataImportUtil.exValue(document, "print_sqsqzr"));//签字人
            airworthyInfo.setSignDate(DominoDataImportUtil.exDate(document, "print_sqsqzrq", "yyyy-MM-dd HH:mm:ss"));//签字日期 print_sqsqzrq
            airworthyInfo.setSignUserPosition(DominoDataImportUtil.exValue(document, "print_sqsqzrzw"));//签字人职务
//            airworthyInfo.setRemakers(DominoDataImportUtil.exValue(document, ""));//备注
            airworthyInfo.setStatement(DominoDataImportUtil.exValue(document, "checkbox_click"));//声明
//            airworthyInfo.setDispatchFirstTrial(DominoDataImportUtil.exValue(document, ""));//是否重新分派初审(0:否;1:是)
//            airworthyInfo.setLocalAuthority(DominoDataImportUtil.exValue(document, ""));//是否由本地区管理局受理
            airworthyInfo.setPayable(DominoDataImportUtil.exValue(document, "cost"));//本次应缴
            airworthyInfo.setPayNum(DominoDataImportUtil.exValue(document, "jiaokuanma"));//缴费码
            airworthyInfo.setConfirmPayStatus(DominoDataImportUtil.exValue(document, "CheckCost"));//地区管理局确认缴费状态
//            airworthyInfo.setIsPay(DominoDataImportUtil.exValue(document, ""));//是否缴费
//            airworthyInfo.setChecker(DominoDataImportUtil.exValue(document, ""));//检查人员id
            airworthyInfo.setCheckerName(DominoDataImportUtil.exValue(document, "shouquanduixiang"));//检查人员姓名
            airworthyInfo.setIssueDate(DominoDataImportUtil.exDate(document, "qianfariqi", "yyyy-MM-dd HH:mm:ss"));//证件颁发日期 qianfariqi
            airworthyInfo.setCheckResult(DominoDataImportUtil.exValue(document, "CheckResults"));//适航检查结果
//            airworthyInfo.setIsUrgent(DominoDataImportUtil.exValue(document, ""));//是否催办
//            airworthyInfo.setFlowStatus(DominoDataImportUtil.exValue(document, ""));//当前流程状态
//            airworthyInfo.setCreateDate(new Timestamp(new java.util.Date().getTime()));//创建时间
//            airworthyInfo.setUpdateUser(DominoDataImportUtil.exValue(document, ""));//修改人
//            airworthyInfo.setUpdateDate(new Timestamp(new java.util.Date().getTime()));//修改时间
            airworthyInfo.setAirworthyNum(DominoDataImportUtil.exValue(document, "shidbh"));//适航证编号
//            airworthyInfo.setApplyNum(DominoDataImportUtil.exValue(document, ""));//申请书编号
            airworthyInfo.setAcceptNum(DominoDataImportUtil.exValue(document, "shoulibh"));//受理书编号
            airworthyInfo.setMandateNum(DominoDataImportUtil.exValue(document, "shouquanshusn"));//授权书编号
//            airworthyInfo.setAuditDept(DominoDataImportUtil.exValue(document, ""));//审核部门
//            airworthyInfo.setActualFirstAuditor(DominoDataImportUtil.exValue(document, ""));//实际初审人
//            airworthyInfo.setAuditDate(new Timestamp(new java.util.Date().getTime()));//受理日期(领导审核日期)
//            airworthyInfo.setMandateDate(new Timestamp(new java.util.Date().getTime()));//授权日期
//            airworthyInfo.setCreateUser(DominoDataImportUtil.exValue(document, ""));//创建人
//            airworthyInfo.setIssuser(DominoDataImportUtil.exValue(document, ""));//签发人
//            airworthyInfo.setSignUserType(DominoDataImportUtil.exValue(document, ""));//签字人类别
//            airworthyInfo.setFolderId(DominoDataImportUtil.exValue(document, ""));//上传文件夹ID
//            airworthyInfo.setOtherDesignStandard(DominoDataImportUtil.exValue(document, ""));//其他设计标准
//            airworthyInfo.setSupplementModelCertification(DominoDataImportUtil.exValue(document, ""));//补充型号合格证
//            airworthyInfo.setCreateDept(DominoDataImportUtil.exValue(document, ""));//创建部门
//            airworthyInfo.setOldMandateNum(DominoDataImportUtil.exValue(document, ""));//上一个授权编号
//            airworthyInfo.setAreaAdministrationId(DominoDataImportUtil.exValue(document, ""));//地区管理局ID
//            airworthyInfo.setLastNode(DominoDataImportUtil.exValue(document, ""));//流程上一节点
//            airworthyInfo.setLaseNodeAssign(DominoDataImportUtil.exValue(document, ""));//流程上一节点处理人
//            airworthyInfo.setApplyWordId(DominoDataImportUtil.exValue(document, ""));//申请书ID
//            airworthyInfo.setAcceptWordId(DominoDataImportUtil.exValue(document, ""));//受理书ID
//            airworthyInfo.setMandateWordId(DominoDataImportUtil.exValue(document, ""));//授权书ID
//            airworthyInfo.setAirworthyWordId(DominoDataImportUtil.exValue(document, ""));//适航证ID
//            airworthyInfo.setAuditDeptName(DominoDataImportUtil.exValue(document, ""));//审核部门名称
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airworthyInfo;
    }

    /**
     * 规范数据信息
     *
     * @param airworthyInfo
     * @return
     */
    public AmosNsAirworthyInfo normalData(AmosNsAirworthyInfo airworthyInfo) {

        /*飞行小时数hangkongqi11*/
        if (!"".equals(airworthyInfo.getFlyHours()) && ValidationUtil.isDouble(airworthyInfo.getFlyHours())) {
            if (airworthyInfo.getFlyHours().indexOf(".") == -1) {
                airworthyInfo.setFlyHours(Integer.parseInt(airworthyInfo.getFlyHours()) + "");
            }else {
                System.out.println(airworthyInfo.getFlyHours());
                String[] strings = airworthyInfo.getFlyHours().split("\\.");
                int carry = 0;
                if (Integer.parseInt(strings[1].charAt(0) + "") >= 5) {
                    carry++;
                }
                airworthyInfo.setFlyHours((Integer.parseInt(strings[0]) + carry) + "");
            }
        } else {
            airworthyInfo.setFlyHours("");
        }
        /*起落次数只保留是数字的数据hangkongqi17*/
        if (!"".equals(airworthyInfo.getFlyCount()) && ValidationUtil.isDouble(airworthyInfo.getFlyCount())) {
            if (airworthyInfo.getFlyCount().indexOf(".") == -1) {
                airworthyInfo.setFlyCount(Integer.parseInt(airworthyInfo.getFlyCount()) + "");
            } else {
                String[] strings = airworthyInfo.getFlyCount().split("\\.");
                int carry = 0;
                if (Integer.parseInt(strings[1].charAt(0) + "") >= 5) {
                    carry++;
                }
                airworthyInfo.setFlyCount((Integer.parseInt(strings[0]) + carry) + "");
            }
        } else {
            airworthyInfo.setFlyCount("");
        }
        //适航证类别
        System.out.println(airworthyInfo.getAirworthyCategory());
        airworthyInfo.setAirworthyCategory(DictUtil.airworthyType(airworthyInfo.getAirworthyCategory()));
        //标准适航证是否是运输类飞机
        airworthyInfo.setStandardIsTransport(DictUtil.isEmpty(airworthyInfo.getStandardIsTransport()));
        //标准适航证用途类型
        airworthyInfo.setStandardTransportType(DictUtil.transportType(airworthyInfo.getStandardTransportType()));
        //特殊适航证作业类别(复选)

        /*签发人设默认值"徐超群"*/
        airworthyInfo.setIssuser("徐超群");
        /*受理书相关*/
        airworthyInfo.setActualFirstAuditor("徐超群");
        airworthyInfo.setAuditDeptName("航空器适航审定司");
        /*授权书相关*/
        /*声明设默认值1*/
        airworthyInfo.setStatement("1");
//        airworthyInfo.setCreateUser("superadmin1old");
        airworthyInfo.setCreateUser("superadmin1new");
        return airworthyInfo;
    }

    /**
     * 提取附件信息
     *
     * @param document
     * @param businessId
     * @return
     */
    public AmosNsNcertificateInfoFile getannif(Document document, String businessId, String type) {
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
