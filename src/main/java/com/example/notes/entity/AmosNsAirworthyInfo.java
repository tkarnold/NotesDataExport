package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 适航证-基本信息
 */
@TableName("AMOS_NS_AIRWORTHY_INFO")
public class AmosNsAirworthyInfo extends Model<AmosNsAirworthyInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 适航证ID
     */
    @TableId("AIRWORTHY_ID")
    @JSONField(name = "airworthyId")
    private String airworthyId;

    /**
     * 适航证ID
     */
    /**
     * 业务流水号
     */
    @TableField("SERIALSNUM")
    @JSONField(name = "serialsnum")
    private String serialsnum;

    /**
     * 申请人
     */
    @TableField("APPLICAT")
    @JSONField(name = "applicat")
    private String applicat;

    /**
     * 申请人类别
     */
    @TableField("APPLICAT_TYPE")
    @JSONField(name = "applicatType")
    private String applicatType;

    /**
     * 地区管理局
     */
    @TableField("AREA_ADMINISTRATION")
    @JSONField(name = "areaAdministration")
    private String areaAdministration;

    /**
     * 申请人地址
     */
    @TableField("APPLIICAT_ADDRESS")
    @JSONField(name = "appliicatAddress")
    private String appliicatAddress;

    /**
     * 申请时间
     */
    @TableField("APPLICAT_DATE")
    @JSONField(name = "applicatDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp applicatDate;

    /**
     * 法定代表人
     */
    @TableField("LEGAL_PERSONS")
    @JSONField(name = "legalPersons")
    private String legalPersons;

    /**
     * 申请人联系人
     */
    @TableField("APPLICAT_CONTACT")
    @JSONField(name = "applicatContact")
    private String applicatContact;

    /**
     * 申请人手机号
     */
    @TableField("APPLICAT_MOBILE")
    @JSONField(name = "applicatMobile")
    private String applicatMobile;

    /**
     * 申请人联系电话
     */
    @TableField("APPLICAT_PHONE")
    @JSONField(name = "applicatPhone")
    private String applicatPhone;

    /**
     * 申请人传真
     */
    @TableField("APPLICAT_FAX")
    @JSONField(name = "applicatFax")
    private String applicatFax;

    /**
     * 申请人邮编
     */
    @TableField("APPLICAT_POSTCODE")
    @JSONField(name = "applicatPostcode")
    private String applicatPostcode;

    /**
     * 国籍证ID
     */
    @TableField("NCERTIFICATE_ID")
    @JSONField(name = "ncertificateId")
    private String ncertificateId;

    /**
     * 国籍标志和登记标志
     */
    @TableField("NCERTIFICATE")
    @JSONField(name = "ncertificate")
    private String ncertificate;

    /**
     * 国籍证编号
     */
    @TableField("NCERTIFICATE_NUM")
    @JSONField(name = "ncertificateNum")
    private String ncertificateNum;

    /**
     * 国籍证是否按照特殊流程处理
     */
    @TableField("NCERTIFICATE_IS_SPECIAL")
    @JSONField(name = "ncertificateIsSpecial")
    private String ncertificateIsSpecial;

    /**
     * 国籍证申请状态
     */
    @TableField("NCERTIFICATE_STATUS")
    @JSONField(name = "ncertificateStatus")
    private String ncertificateStatus;

    /**
     * 喷绘方案ID
     */
    @TableField("INKJET_ID")
    @JSONField(name = "inkjetId")
    private String inkjetId;

    /**
     * 喷涂方案批复编号
     */
    @TableField("INKJET_REPLY_NUM")
    @JSONField(name = "inkjetReplyNum")
    private String inkjetReplyNum;

    /**
     * 是否核准喷涂方案
     */
    @TableField("IS_APPROVE_INKJET")
    @JSONField(name = "isApproveInkjet")
    private String isApproveInkjet;

    /**
     * 航空器制造人名称
     */
    @TableField("AIRCRAFT_MANUFACTURER")
    @JSONField(name = "aircraftManufacturer")
    private String aircraftManufacturer;

    /**
     * 航空器类别
     */
    @TableField("AIRCRAFT_CATEGORY")
    @JSONField(name = "aircraftCategory")
    private String aircraftCategory;

    /**
     * 航空器型别
     */
    @TableField("AIRCRAFT_TYPE")
    @JSONField(name = "aircraftType")
    private String aircraftType;

    /**
     * 航空器序列号
     */
    @TableField("AIRCRAFT_NUMBER")
    @JSONField(name = "aircraftNumber")
    private String aircraftNumber;

    /**
     * 航空器出厂日期
     */
    @TableField("MANUFACTURE_DATE")
    @JSONField(name = "manufactureDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp manufactureDate;

    /**
     * 航空器最大起飞全重
     */
    @TableField("AIRCRAFT_MAX_WEIGHT")
    @JSONField(name = "aircraftMaxWeight")
    private String aircraftMaxWeight;

    /**
     * 航空器最大可用业载
     */
    @TableField("AIRCRAFT_MAX_LOAD")
    @JSONField(name = "aircraftMaxLoad")
    private String aircraftMaxLoad;

    /**
     * 航空器座位数
     */
    @TableField("AIRCRAFT_SEAT_SIZE")
    @JSONField(name = "aircraftSeatSize")
    private String aircraftSeatSize;

    /**
     * 航空器是否进口
     */
    @TableField("AIRCRAFT_ISIMPORT")
    @JSONField(name = "aircraftIsimport")
    private String aircraftIsimport;

    /**
     * 航空器是否使用
     */
    @TableField("AIRCRAFT_ISNEW")
    @JSONField(name = "aircraftIsnew")
    private String aircraftIsnew;

    /**
     * 航空器采购来源
     */
    @TableField("AIRCRAFT_FROM")
    @JSONField(name = "aircraftFrom")
    private String aircraftFrom;

    /**
     * 航空器采购来源其他
     */
    @TableField("AIRCRAFT_FROM_OTHER")
    @JSONField(name = "aircraftFromOther")
    private String aircraftFromOther;

    /**
     * 航空器飞行小时数
     */
    @TableField("FLY_HOURS")
    @JSONField(name = "flyHours")
    private String flyHours;

    /**
     * 航空器起落次数
     */
    @TableField("FLY_COUNT")
    @JSONField(name = "flyCount")
    private String flyCount;

    /**
     * 接机地点
     */
    @TableField("PICK_UP_LOCATION")
    @JSONField(name = "pickUpLocation")
    private String pickUpLocation;

    /**
     * 具体交付地点
     */
    @TableField("AIRCRAFT_DELIVERY_ADDRESS")
    @JSONField(name = "aircraftDeliveryAddress")
    private String aircraftDeliveryAddress;

    /**
     * 是否核准方案
     */
    @TableField("IS_APPROVAL_PROJECT")
    @JSONField(name = "isApprovalProject")
    private String isApprovalProject;

    /**
     * 航空器所有人名称
     */
    @TableField("AIRCRAFT_OWNER")
    @JSONField(name = "aircraftOwner")
    private String aircraftOwner;

    /**
     * 航空器所有人地址
     */
    @TableField("AIRCRAFT_OWNER_ADDRESS")
    @JSONField(name = "aircraftOwnerAddress")
    private String aircraftOwnerAddress;

    /**
     * 航空器所有人电话
     */
    @TableField("AIRCRAFT_OWNER_PHONE")
    @JSONField(name = "aircraftOwnerPhone")
    private String aircraftOwnerPhone;

    /**
     * 航空器所有人传真
     */
    @TableField("AIRCRAFT_OWNER_FAX")
    @JSONField(name = "aircraftOwnerFax")
    private String aircraftOwnerFax;

    /**
     * 航空器占有人名称
     */
    @TableField("AIRCRAFT_HOLDER")
    @JSONField(name = "aircraftHolder")
    private String aircraftHolder;

    /**
     * 航空器占有人地址
     */
    @TableField("AIRCRAFT_HOLDER_ADDRESS")
    @JSONField(name = "aircraftHolderAddress")
    private String aircraftHolderAddress;

    /**
     * 航空器占有人电话
     */
    @TableField("AIRCRAFT_HOLDER_PHONE")
    @JSONField(name = "aircraftHolderPhone")
    private String aircraftHolderPhone;

    /**
     * 航空器占有人传真
     */
    @TableField("AIRCRAFT_HOLDER_FAX")
    @JSONField(name = "aircraftHolderFax")
    private String aircraftHolderFax;

    /**
     * 发动机型别
     */
    @TableField("ENGINE_MODEL")
    @JSONField(name = "engineModel")
    private String engineModel;

    /**
     * 发动机制造人名称
     */
    @TableField("ENGINE_CREATEUSER")
    @JSONField(name = "engineCreateuser")
    private String engineCreateuser;

    /**
     * 发动机装机数量
     */
    @TableField("ENGINE_NUM")
    @JSONField(name = "engineNum")
    private String engineNum;

    /**
     * 螺旋桨型别
     */
    @TableField("PROPELLER_MODEL")
    @JSONField(name = "propellerModel")
    private String propellerModel;

    /**
     * 螺旋桨制造人名称
     */
    @TableField("PROPELLER_CREATEUSER")
    @JSONField(name = "propellerCreateuser")
    private String propellerCreateuser;

    /**
     * 螺旋桨装机数量
     */
    @TableField("PROPELLER_NUM")
    @JSONField(name = "propellerNum")
    private String propellerNum;

    /**
     * 适航证类别
     */
    @TableField("AIRWORTHY_CATEGORY")
    @JSONField(name = "airworthyCategory")
    private String airworthyCategory;

    /**
     * 标准适航证是否是运输类飞机
     */
    @TableField("STANDARD_IS_TRANSPORT")
    @JSONField(name = "standardIsTransport")
    private String standardIsTransport;

    /**
     * 标准适航证运输类飞机类型
     */
    @TableField("STANDARD_TRANSPORT_TYPE")
    @JSONField(name = "standardTransportType")
    private String standardTransportType;

    /**
     * 标准适航证用途类型
     */
    @TableField("STANDARD_USE_TYPE")
    @JSONField(name = "standardUseType")
    private String standardUseType;

    /**
     * 标准适航证其余类型
     */
    @TableField("STANDARD_OTHER_TYPE")
    @JSONField(name = "standardOtherType")
    private String standardOtherType;

    /**
     * 特殊适航证类型
     */
    @TableField("SPECIAL_AIRWORTH_TYPE")
    @JSONField(name = "specialAirworthType")
    private String specialAirworthType;

    /**
     * 特殊适航证作业类别
     */
    @TableField("SPECIAL_WORK_TYPE")
    @JSONField(name = "specialWorkType")
    private String specialWorkType;

    /**
     * 特殊适航证作业类别其他用途
     */
    @TableField("SPECIAL_WORK_TYPE_OTHER")
    @JSONField(name = "specialWorkTypeOther")
    private String specialWorkTypeOther;

    /**
     * 实验类适航证
     */
    @TableField("TEST_AIRWORTH_TYPE")
    @JSONField(name = "testAirworthType")
    private String testAirworthType;

    /**
     * 型号合格证(TC)编号
     */
    @TableField("TC_NUM")
    @JSONField(name = "tcNum")
    private String tcNum;

    /**
     * 型号合格证(TC)数据单版次
     */
    @TableField("TC_VERSION")
    @JSONField(name = "tcVersion")
    private String tcVersion;

    /**
     * 型号认可证VTC编号
     */
    @TableField("VTC_NUM")
    @JSONField(name = "vtcNum")
    private String vtcNum;

    /**
     * 型号认可证(VTC)数据单版次
     */
    @TableField("VTC_VERSION")
    @JSONField(name = "vtcVersion")
    private String vtcVersion;

    /**
     * 型号设计批准书(TDA)编号
     */
    @TableField("TDA_NUM")
    @JSONField(name = "tdaNum")
    private String tdaNum;

    /**
     * 型号设计批准书(TDA)数据单版次
     */
    @TableField("TDA_VERSION")
    @JSONField(name = "tdaVersion")
    private String tdaVersion;

    /**
     * 民航局同意的其他情况
     */
    @TableField("OTHERS_CONDITION")
    @JSONField(name = "othersCondition")
    private String othersCondition;

    /**
     * 外国适航当局颁发的型号合格证
     */
    @TableField("FOREIGN_TC_NUM")
    @JSONField(name = "foreignTcNum")
    private String foreignTcNum;

    /**
     * 外国适航当局颁发的型号合格证数据单版次
     */
    @TableField("FOREIGN_TC_VERSION")
    @JSONField(name = "foreignTcVersion")
    private String foreignTcVersion;

    /**
     * 是否给出许可证(pc)编号
     */
    @TableField("IS_PC_NUM")
    @JSONField(name = "isPcNum")
    private String isPcNum;

    /**
     * 生产许可证(pc)编号
     */
    @TableField("PC_NUM")
    @JSONField(name = "pcNum")
    private String pcNum;

    /**
     * 是否给出生产机构批准书(POA)编号
     */
    @TableField("IS_POS_NUM")
    @JSONField(name = "isPosNum")
    private String isPosNum;

    /**
     * 生产机构批准书(POA)编号
     */
    @TableField("POS_NUM")
    @JSONField(name = "posNum")
    private String posNum;

    /**
     * 是否上传经批准的生产检验系统(APIS)文件
     */
    @TableField("IS_APIS_FILE")
    @JSONField(name = "isApisFile")
    private String isApisFile;

    /**
     * 是否上传仅依赖型号合格证(TConly)文件
     */
    @TableField("IS_TCONLY_FILE")
    @JSONField(name = "isTconlyFile")
    private String isTconlyFile;

    /**
     * 是否上传其他制造批准文件
     */
    @TableField("IS_OTHER_MAKE_APPROVE_FILE")
    @JSONField(name = "isOtherMakeApproveFile")
    private String isOtherMakeApproveFile;

    /**
     * 是否上传航空器交付时构型与批准认可型号的差异说明文件
     */
    @TableField("IS_DIFFERENCE_DESCRIPTION_FILE")
    @JSONField(name = "isDifferenceDescriptionFile")
    private String isDifferenceDescriptionFile;

    /**
     * 是否上传预检验报告文件
     */
    @TableField("IS_PRE_CHECK_FILE")
    @JSONField(name = "isPreCheckFile")
    private String isPreCheckFile;

    /**
     * 是否上传适航审定司认为必要的其他文件
     */
    @TableField("IS_NECESSARY_OTHER_FILE")
    @JSONField(name = "isNecessaryOtherFile")
    private String isNecessaryOtherFile;

    /**
     * 出口适航证是否适航
     */
    @TableField("EXPORT_AIRWORTHY_IS_APPLY")
    @JSONField(name = "exportAirworthyIsApply")
    private String exportAirworthyIsApply;

    /**
     * 出口适航证不适用理由
     */
    @TableField("EXPORT_NOT_APPLY_REASON")
    @JSONField(name = "exportNotApplyReason")
    private String exportNotApplyReason;

    /**
     * 签字人
     */
    @TableField("SIGN_USER_NAME")
    @JSONField(name = "signUserName")
    private String signUserName;

    /**
     * 签字日期
     */
    @TableField("SIGN_DATE")
    @JSONField(name = "signDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp signDate;

    /**
     * 签字人职务
     */
    @TableField("SIGN_USER_POSITION")
    @JSONField(name = "signUserPosition")
    private String signUserPosition;

    /**
     * 备注
     */
    @TableField("REMAKERS")
    @JSONField(name = "remakers")
    private String remakers;

    /**
     * 声明
     */
    @TableField("STATEMENT")
    @JSONField(name = "statement")
    private String statement;

    /**
     * 是否重新分派初审(0:否;1:是)
     */
    @TableField("DISPATCH_FIRST_TRIAL")
    @JSONField(name = "dispatchFirstTrial")
    private String dispatchFirstTrial;

    /**
     * 是否由本地区管理局受理
     */
    @TableField("LOCAL_AUTHORITY")
    @JSONField(name = "localAuthority")
    private String localAuthority;

    /**
     * 本次应缴
     */
    @TableField("PAYABLE")
    @JSONField(name = "payable")
    private String payable;

    /**
     * 缴费码
     */
    @TableField("PAY_NUM")
    @JSONField(name = "payNum")
    private String payNum;

    /**
     * 地区管理局确认缴费状态
     */
    @TableField("CONFIRM_PAY_STATUS")
    @JSONField(name = "confirmPayStatus")
    private String confirmPayStatus;

    /**
     * 是否缴费
     */
    @TableField("IS_PAY")
    @JSONField(name = "isPay")
    private String isPay;

    /**
     * 检查人员id
     */
    @TableField("CHECKER")
    @JSONField(name = "checker")
    private String checker;

    /**
     * 检查人员姓名
     */
    @TableField("CHECKER_NAME")
    @JSONField(name = "checkerName")
    private String checkerName;

    /**
     * 证件颁发日期
     */
    @TableField("ISSUE_DATE")
    @JSONField(name = "issueDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp issueDate;

    /**
     * 适航检查结果
     */
    @TableField("CHECK_RESULT")
    @JSONField(name = "checkResult")
    private String checkResult;

    /**
     * 是否催办
     */
    @TableField("IS_URGENT")
    @JSONField(name = "isUrgent")
    private String isUrgent;

    /**
     * 当前流程状态
     */
    @TableField("FLOW_STATUS")
    @JSONField(name = "flowStatus")
    private String flowStatus;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    @JSONField(name = "createDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /**
     * 修改人
     */
    @TableField("UPDATE_USER")
    @JSONField(name = "updateUser")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField("UPDATE_DATE")
    @JSONField(name = "updateDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    /**
     * 适航证编号
     */
    @TableField("AIRWORTHY_NUM")
    @JSONField(name = "airworthyNum")
    private String airworthyNum;

    /**
     * 申请书编号
     */
    @TableField("APPLY_NUM")
    @JSONField(name = "applyNum")
    private String applyNum;

    /**
     * 受理书编号
     */
    @TableField("ACCEPT_NUM")
    @JSONField(name = "acceptNum")
    private String acceptNum;

    /**
     * 授权书编号
     */
    @TableField("MANDATE_NUM")
    @JSONField(name = "mandateNum")
    private String mandateNum;

    /**
     * 审核部门
     */
    @TableField("AUDIT_DEPT")
    @JSONField(name = "auditDept")
    private String auditDept;

    /**
     * 实际初审人
     */
    @TableField("ACTUAL_FIRST_AUDITOR")
    @JSONField(name = "actualFirstAuditor")
    private String actualFirstAuditor;

    /**
     * 受理日期(领导审核日期)
     */
    @TableField("AUDIT_DATE")
    @JSONField(name = "auditDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp auditDate;

    /**
     * 授权日期
     */
    @TableField("MANDATE_DATE")
    @JSONField(name = "mandateDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp mandateDate;

    /**
     * 创建人
     */
    @TableField("CREATE_USER")
    @JSONField(name = "createUser")
    private String createUser;

    /**
     * 签发人
     */
    @TableField("ISSUSER")
    @JSONField(name = "issuser")
    private String issuser;

    /**
     * 签字人类别
     */
    @TableField("SIGN_USER_TYPE")
    @JSONField(name = "signUserType")
    private String signUserType;

    /**
     * 上传文件夹ID
     */
    @TableField("FOLDER_ID")
    @JSONField(name = "folderId")
    private String folderId;

    /**
     * 其他设计标准
     */
    @TableField("OTHER_DESIGN_STANDARD")
    @JSONField(name = "otherDesignStandard")
    private String otherDesignStandard;

    /**
     * 补充型号合格证
     */
    @TableField("SUPPLEMENT_MODEL_CERTIFICATION")
    @JSONField(name = "supplementModelCertification")
    private String supplementModelCertification;

    /**
     * 创建部门
     */
    @TableField("CREATE_DEPT")
    @JSONField(name = "createDept")
    private String createDept;

    /**
     * 上一个授权编号
     */
    @TableField("OLD_MANDATE_NUM")
    @JSONField(name = "oldMandateNum")
    private String oldMandateNum;

    /**
     * 地区管理局ID
     */
    @TableField("AREA_ADMINISTRATION_ID")
    @JSONField(name = "areaAdministrationId")
    private String areaAdministrationId;

    /**
     * 流程上一节点
     */
    @TableField("LAST_NODE")
    @JSONField(name = "lastNode")
    private String lastNode;

    /**
     * 流程上一节点处理人
     */
    @TableField("LASE_NODE_ASSIGN")
    @JSONField(name = "laseNodeAssign")
    private String laseNodeAssign;

    /**
     * 申请书ID
     */
    @TableField("APPLY_WORD_ID")
    @JSONField(name = "applyWordId")
    private String applyWordId;

    /**
     * 受理书ID
     */
    @TableField("ACCEPT_WORD_ID")
    @JSONField(name = "acceptWordId")
    private String acceptWordId;

    /**
     * 授权书ID
     */
    @TableField("MANDATE_WORD_ID")
    @JSONField(name = "mandateWordId")
    private String mandateWordId;

    /**
     * 适航证ID
     */
    @TableField("AIRWORTHY_WORD_ID")
    @JSONField(name = "airworthyWordId")
    private String airworthyWordId;

    /**
     * 审核部门名称
     */
    @TableField("AUDIT_DEPT_NAME")
    @JSONField(name = "auditDeptName")
    private String auditDeptName;

    public String getAirworthyId() {
        return airworthyId;
    }

    public void setAirworthyId(String airworthyId) {
        this.airworthyId = airworthyId;
    }

    public String getSerialsnum() {
        return serialsnum;
    }

    public void setSerialsnum(String serialsnum) {
        this.serialsnum = serialsnum;
    }

    public String getApplicat() {
        return applicat;
    }

    public void setApplicat(String applicat) {
        this.applicat = applicat;
    }

    public String getApplicatType() {
        return applicatType;
    }

    public void setApplicatType(String applicatType) {
        this.applicatType = applicatType;
    }

    public String getAreaAdministration() {
        return areaAdministration;
    }

    public void setAreaAdministration(String areaAdministration) {
        this.areaAdministration = areaAdministration;
    }

    public String getAppliicatAddress() {
        return appliicatAddress;
    }

    public void setAppliicatAddress(String appliicatAddress) {
        this.appliicatAddress = appliicatAddress;
    }

    public Timestamp getApplicatDate() {
        return applicatDate;
    }

    public void setApplicatDate(Timestamp applicatDate) {
        this.applicatDate = applicatDate;
    }

    public String getLegalPersons() {
        return legalPersons;
    }

    public void setLegalPersons(String legalPersons) {
        this.legalPersons = legalPersons;
    }

    public String getApplicatContact() {
        return applicatContact;
    }

    public void setApplicatContact(String applicatContact) {
        this.applicatContact = applicatContact;
    }

    public String getApplicatMobile() {
        return applicatMobile;
    }

    public void setApplicatMobile(String applicatMobile) {
        this.applicatMobile = applicatMobile;
    }

    public String getApplicatPhone() {
        return applicatPhone;
    }

    public void setApplicatPhone(String applicatPhone) {
        this.applicatPhone = applicatPhone;
    }

    public String getApplicatFax() {
        return applicatFax;
    }

    public void setApplicatFax(String applicatFax) {
        this.applicatFax = applicatFax;
    }

    public String getApplicatPostcode() {
        return applicatPostcode;
    }

    public void setApplicatPostcode(String applicatPostcode) {
        this.applicatPostcode = applicatPostcode;
    }

    public String getNcertificateId() {
        return ncertificateId;
    }

    public void setNcertificateId(String ncertificateId) {
        this.ncertificateId = ncertificateId;
    }

    public String getNcertificate() {
        return ncertificate;
    }

    public void setNcertificate(String ncertificate) {
        this.ncertificate = ncertificate;
    }

    public String getNcertificateNum() {
        return ncertificateNum;
    }

    public void setNcertificateNum(String ncertificateNum) {
        this.ncertificateNum = ncertificateNum;
    }

    public String getNcertificateIsSpecial() {
        return ncertificateIsSpecial;
    }

    public void setNcertificateIsSpecial(String ncertificateIsSpecial) {
        this.ncertificateIsSpecial = ncertificateIsSpecial;
    }

    public String getNcertificateStatus() {
        return ncertificateStatus;
    }

    public void setNcertificateStatus(String ncertificateStatus) {
        this.ncertificateStatus = ncertificateStatus;
    }

    public String getInkjetId() {
        return inkjetId;
    }

    public void setInkjetId(String inkjetId) {
        this.inkjetId = inkjetId;
    }

    public String getInkjetReplyNum() {
        return inkjetReplyNum;
    }

    public void setInkjetReplyNum(String inkjetReplyNum) {
        this.inkjetReplyNum = inkjetReplyNum;
    }

    public String getIsApproveInkjet() {
        return isApproveInkjet;
    }

    public void setIsApproveInkjet(String isApproveInkjet) {
        this.isApproveInkjet = isApproveInkjet;
    }

    public String getAircraftManufacturer() {
        return aircraftManufacturer;
    }

    public void setAircraftManufacturer(String aircraftManufacturer) {
        this.aircraftManufacturer = aircraftManufacturer;
    }

    public String getAircraftCategory() {
        return aircraftCategory;
    }

    public void setAircraftCategory(String aircraftCategory) {
        this.aircraftCategory = aircraftCategory;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getAircraftNumber() {
        return aircraftNumber;
    }

    public void setAircraftNumber(String aircraftNumber) {
        this.aircraftNumber = aircraftNumber;
    }

    public Timestamp getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Timestamp manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getAircraftMaxWeight() {
        return aircraftMaxWeight;
    }

    public void setAircraftMaxWeight(String aircraftMaxWeight) {
        this.aircraftMaxWeight = aircraftMaxWeight;
    }

    public String getAircraftMaxLoad() {
        return aircraftMaxLoad;
    }

    public void setAircraftMaxLoad(String aircraftMaxLoad) {
        this.aircraftMaxLoad = aircraftMaxLoad;
    }

    public String getAircraftSeatSize() {
        return aircraftSeatSize;
    }

    public void setAircraftSeatSize(String aircraftSeatSize) {
        this.aircraftSeatSize = aircraftSeatSize;
    }

    public String getAircraftIsimport() {
        return aircraftIsimport;
    }

    public void setAircraftIsimport(String aircraftIsimport) {
        this.aircraftIsimport = aircraftIsimport;
    }

    public String getAircraftIsnew() {
        return aircraftIsnew;
    }

    public void setAircraftIsnew(String aircraftIsnew) {
        this.aircraftIsnew = aircraftIsnew;
    }

    public String getAircraftFrom() {
        return aircraftFrom;
    }

    public void setAircraftFrom(String aircraftFrom) {
        this.aircraftFrom = aircraftFrom;
    }

    public String getAircraftFromOther() {
        return aircraftFromOther;
    }

    public void setAircraftFromOther(String aircraftFromOther) {
        this.aircraftFromOther = aircraftFromOther;
    }

    public String getFlyHours() {
        return flyHours;
    }

    public void setFlyHours(String flyHours) {
        this.flyHours = flyHours;
    }

    public String getFlyCount() {
        return flyCount;
    }

    public void setFlyCount(String flyCount) {
        this.flyCount = flyCount;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getAircraftDeliveryAddress() {
        return aircraftDeliveryAddress;
    }

    public void setAircraftDeliveryAddress(String aircraftDeliveryAddress) {
        this.aircraftDeliveryAddress = aircraftDeliveryAddress;
    }

    public String getIsApprovalProject() {
        return isApprovalProject;
    }

    public void setIsApprovalProject(String isApprovalProject) {
        this.isApprovalProject = isApprovalProject;
    }

    public String getAircraftOwner() {
        return aircraftOwner;
    }

    public void setAircraftOwner(String aircraftOwner) {
        this.aircraftOwner = aircraftOwner;
    }

    public String getAircraftOwnerAddress() {
        return aircraftOwnerAddress;
    }

    public void setAircraftOwnerAddress(String aircraftOwnerAddress) {
        this.aircraftOwnerAddress = aircraftOwnerAddress;
    }

    public String getAircraftOwnerPhone() {
        return aircraftOwnerPhone;
    }

    public void setAircraftOwnerPhone(String aircraftOwnerPhone) {
        this.aircraftOwnerPhone = aircraftOwnerPhone;
    }

    public String getAircraftOwnerFax() {
        return aircraftOwnerFax;
    }

    public void setAircraftOwnerFax(String aircraftOwnerFax) {
        this.aircraftOwnerFax = aircraftOwnerFax;
    }

    public String getAircraftHolder() {
        return aircraftHolder;
    }

    public void setAircraftHolder(String aircraftHolder) {
        this.aircraftHolder = aircraftHolder;
    }

    public String getAircraftHolderAddress() {
        return aircraftHolderAddress;
    }

    public void setAircraftHolderAddress(String aircraftHolderAddress) {
        this.aircraftHolderAddress = aircraftHolderAddress;
    }

    public String getAircraftHolderPhone() {
        return aircraftHolderPhone;
    }

    public void setAircraftHolderPhone(String aircraftHolderPhone) {
        this.aircraftHolderPhone = aircraftHolderPhone;
    }

    public String getAircraftHolderFax() {
        return aircraftHolderFax;
    }

    public void setAircraftHolderFax(String aircraftHolderFax) {
        this.aircraftHolderFax = aircraftHolderFax;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getEngineCreateuser() {
        return engineCreateuser;
    }

    public void setEngineCreateuser(String engineCreateuser) {
        this.engineCreateuser = engineCreateuser;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getPropellerModel() {
        return propellerModel;
    }

    public void setPropellerModel(String propellerModel) {
        this.propellerModel = propellerModel;
    }

    public String getPropellerCreateuser() {
        return propellerCreateuser;
    }

    public void setPropellerCreateuser(String propellerCreateuser) {
        this.propellerCreateuser = propellerCreateuser;
    }

    public String getPropellerNum() {
        return propellerNum;
    }

    public void setPropellerNum(String propellerNum) {
        this.propellerNum = propellerNum;
    }

    public String getAirworthyCategory() {
        return airworthyCategory;
    }

    public void setAirworthyCategory(String airworthyCategory) {
        this.airworthyCategory = airworthyCategory;
    }

    public String getStandardIsTransport() {
        return standardIsTransport;
    }

    public void setStandardIsTransport(String standardIsTransport) {
        this.standardIsTransport = standardIsTransport;
    }

    public String getStandardTransportType() {
        return standardTransportType;
    }

    public void setStandardTransportType(String standardTransportType) {
        this.standardTransportType = standardTransportType;
    }

    public String getStandardUseType() {
        return standardUseType;
    }

    public void setStandardUseType(String standardUseType) {
        this.standardUseType = standardUseType;
    }

    public String getStandardOtherType() {
        return standardOtherType;
    }

    public void setStandardOtherType(String standardOtherType) {
        this.standardOtherType = standardOtherType;
    }

    public String getSpecialAirworthType() {
        return specialAirworthType;
    }

    public void setSpecialAirworthType(String specialAirworthType) {
        this.specialAirworthType = specialAirworthType;
    }

    public String getSpecialWorkType() {
        return specialWorkType;
    }

    public void setSpecialWorkType(String specialWorkType) {
        this.specialWorkType = specialWorkType;
    }

    public String getSpecialWorkTypeOther() {
        return specialWorkTypeOther;
    }

    public void setSpecialWorkTypeOther(String specialWorkTypeOther) {
        this.specialWorkTypeOther = specialWorkTypeOther;
    }

    public String getTestAirworthType() {
        return testAirworthType;
    }

    public void setTestAirworthType(String testAirworthType) {
        this.testAirworthType = testAirworthType;
    }

    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }

    public String getTcVersion() {
        return tcVersion;
    }

    public void setTcVersion(String tcVersion) {
        this.tcVersion = tcVersion;
    }

    public String getVtcNum() {
        return vtcNum;
    }

    public void setVtcNum(String vtcNum) {
        this.vtcNum = vtcNum;
    }

    public String getVtcVersion() {
        return vtcVersion;
    }

    public void setVtcVersion(String vtcVersion) {
        this.vtcVersion = vtcVersion;
    }

    public String getTdaNum() {
        return tdaNum;
    }

    public void setTdaNum(String tdaNum) {
        this.tdaNum = tdaNum;
    }

    public String getTdaVersion() {
        return tdaVersion;
    }

    public void setTdaVersion(String tdaVersion) {
        this.tdaVersion = tdaVersion;
    }

    public String getOthersCondition() {
        return othersCondition;
    }

    public void setOthersCondition(String othersCondition) {
        this.othersCondition = othersCondition;
    }

    public String getForeignTcNum() {
        return foreignTcNum;
    }

    public void setForeignTcNum(String foreignTcNum) {
        this.foreignTcNum = foreignTcNum;
    }

    public String getForeignTcVersion() {
        return foreignTcVersion;
    }

    public void setForeignTcVersion(String foreignTcVersion) {
        this.foreignTcVersion = foreignTcVersion;
    }

    public String getIsPcNum() {
        return isPcNum;
    }

    public void setIsPcNum(String isPcNum) {
        this.isPcNum = isPcNum;
    }

    public String getPcNum() {
        return pcNum;
    }

    public void setPcNum(String pcNum) {
        this.pcNum = pcNum;
    }

    public String getIsPosNum() {
        return isPosNum;
    }

    public void setIsPosNum(String isPosNum) {
        this.isPosNum = isPosNum;
    }

    public String getPosNum() {
        return posNum;
    }

    public void setPosNum(String posNum) {
        this.posNum = posNum;
    }

    public String getIsApisFile() {
        return isApisFile;
    }

    public void setIsApisFile(String isApisFile) {
        this.isApisFile = isApisFile;
    }

    public String getIsTconlyFile() {
        return isTconlyFile;
    }

    public void setIsTconlyFile(String isTconlyFile) {
        this.isTconlyFile = isTconlyFile;
    }

    public String getIsOtherMakeApproveFile() {
        return isOtherMakeApproveFile;
    }

    public void setIsOtherMakeApproveFile(String isOtherMakeApproveFile) {
        this.isOtherMakeApproveFile = isOtherMakeApproveFile;
    }

    public String getIsDifferenceDescriptionFile() {
        return isDifferenceDescriptionFile;
    }

    public void setIsDifferenceDescriptionFile(String isDifferenceDescriptionFile) {
        this.isDifferenceDescriptionFile = isDifferenceDescriptionFile;
    }

    public String getIsPreCheckFile() {
        return isPreCheckFile;
    }

    public void setIsPreCheckFile(String isPreCheckFile) {
        this.isPreCheckFile = isPreCheckFile;
    }

    public String getIsNecessaryOtherFile() {
        return isNecessaryOtherFile;
    }

    public void setIsNecessaryOtherFile(String isNecessaryOtherFile) {
        this.isNecessaryOtherFile = isNecessaryOtherFile;
    }

    public String getExportAirworthyIsApply() {
        return exportAirworthyIsApply;
    }

    public void setExportAirworthyIsApply(String exportAirworthyIsApply) {
        this.exportAirworthyIsApply = exportAirworthyIsApply;
    }

    public String getExportNotApplyReason() {
        return exportNotApplyReason;
    }

    public void setExportNotApplyReason(String exportNotApplyReason) {
        this.exportNotApplyReason = exportNotApplyReason;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
    }

    public Timestamp getSignDate() {
        return signDate;
    }

    public void setSignDate(Timestamp signDate) {
        this.signDate = signDate;
    }

    public String getSignUserPosition() {
        return signUserPosition;
    }

    public void setSignUserPosition(String signUserPosition) {
        this.signUserPosition = signUserPosition;
    }

    public String getRemakers() {
        return remakers;
    }

    public void setRemakers(String remakers) {
        this.remakers = remakers;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getDispatchFirstTrial() {
        return dispatchFirstTrial;
    }

    public void setDispatchFirstTrial(String dispatchFirstTrial) {
        this.dispatchFirstTrial = dispatchFirstTrial;
    }

    public String getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(String localAuthority) {
        this.localAuthority = localAuthority;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getConfirmPayStatus() {
        return confirmPayStatus;
    }

    public void setConfirmPayStatus(String confirmPayStatus) {
        this.confirmPayStatus = confirmPayStatus;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getAirworthyNum() {
        return airworthyNum;
    }

    public void setAirworthyNum(String airworthyNum) {
        this.airworthyNum = airworthyNum;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(String acceptNum) {
        this.acceptNum = acceptNum;
    }

    public String getMandateNum() {
        return mandateNum;
    }

    public void setMandateNum(String mandateNum) {
        this.mandateNum = mandateNum;
    }

    public String getAuditDept() {
        return auditDept;
    }

    public void setAuditDept(String auditDept) {
        this.auditDept = auditDept;
    }

    public String getActualFirstAuditor() {
        return actualFirstAuditor;
    }

    public void setActualFirstAuditor(String actualFirstAuditor) {
        this.actualFirstAuditor = actualFirstAuditor;
    }

    @Override
    protected Serializable pkVal() {
        return this.airworthyId;
    }

    @Override
    public String toString() {
        return "AmosNsAirworthyInfo{" + "airworthyId=" + airworthyId + ", serialsnum=" + serialsnum + ", applicat=" + applicat + ", applicatType=" + applicatType + ", areaAdministration=" + areaAdministration + ", appliicatAddress=" + appliicatAddress + ", applicatDate=" + applicatDate + ", legalPersons=" + legalPersons + ", applicatContact=" + applicatContact + ", applicatMobile=" + applicatMobile + ", applicatPhone=" + applicatPhone + ", applicatFax=" + applicatFax + ", applicatPostcode=" + applicatPostcode + ", ncertificateId=" + ncertificateId + ", ncertificate=" + ncertificate + ", ncertificateNum=" + ncertificateNum + ", ncertificateIsSpecial=" + ncertificateIsSpecial + ", ncertificateStatus=" + ncertificateStatus + ", inkjetId=" + inkjetId + ", inkjetReplyNum=" + inkjetReplyNum + ", isApproveInkjet=" + isApproveInkjet + ", aircraftManufacturer=" + aircraftManufacturer + ", aircraftCategory=" + aircraftCategory + ", aircraftType=" + aircraftType + ", aircraftNumber=" + aircraftNumber + ", manufactureDate=" + manufactureDate + ", aircraftMaxWeight=" + aircraftMaxWeight + ", aircraftMaxLoad=" + aircraftMaxLoad + ", aircraftSeatSize=" + aircraftSeatSize + ", aircraftIsimport=" + aircraftIsimport + ", aircraftIsnew=" + aircraftIsnew + ", aircraftFrom=" + aircraftFrom + ", aircraftFromOther=" + aircraftFromOther + ", flyHours=" + flyHours + ", flyCount=" + flyCount + ", pickUpLocation=" + pickUpLocation + ", aircraftDeliveryAddress=" + aircraftDeliveryAddress + ", isApprovalProject=" + isApprovalProject + ", aircraftOwner=" + aircraftOwner + ", aircraftOwnerAddress=" + aircraftOwnerAddress + ", aircraftOwnerPhone=" + aircraftOwnerPhone + ", aircraftOwnerFax=" + aircraftOwnerFax + ", aircraftHolder=" + aircraftHolder + ", aircraftHolderAddress=" + aircraftHolderAddress + ", aircraftHolderPhone=" + aircraftHolderPhone + ", aircraftHolderFax=" + aircraftHolderFax + ", engineModel=" + engineModel + ", engineCreateuser=" + engineCreateuser + ", engineNum=" + engineNum + ", propellerModel=" + propellerModel + ", propellerCreateuser=" + propellerCreateuser + ", propellerNum=" + propellerNum + ", airworthyCategory=" + airworthyCategory + ", standardIsTransport=" + standardIsTransport + ", standardTransportType=" + standardTransportType + ", standardUseType=" + standardUseType + ", standardOtherType=" + standardOtherType + ", specialAirworthType=" + specialAirworthType + ", specialWorkType=" + specialWorkType + ", specialWorkTypeOther=" + specialWorkTypeOther + ", testAirworthType=" + testAirworthType + ", tcNum=" + tcNum + ", tcVersion=" + tcVersion + ", vtcNum=" + vtcNum + ", vtcVersion=" + vtcVersion + ", tdaNum=" + tdaNum + ", tdaVersion=" + tdaVersion + ", othersCondition=" + othersCondition + ", foreignTcNum=" + foreignTcNum + ", foreignTcVersion=" + foreignTcVersion + ", isPcNum=" + isPcNum + ", pcNum=" + pcNum + ", isPosNum=" + isPosNum + ", posNum=" + posNum + ", isApisFile=" + isApisFile + ", isTconlyFile=" + isTconlyFile + ", isOtherMakeApproveFile=" + isOtherMakeApproveFile + ", isDifferenceDescriptionFile=" + isDifferenceDescriptionFile + ", isPreCheckFile=" + isPreCheckFile + ", isNecessaryOtherFile=" + isNecessaryOtherFile + ", exportAirworthyIsApply=" + exportAirworthyIsApply + ", exportNotApplyReason=" + exportNotApplyReason + ", signUserName=" + signUserName + ", signDate=" + signDate + ", signUserPosition=" + signUserPosition + ", remakers=" + remakers + ", statement=" + statement + ", dispatchFirstTrial=" + dispatchFirstTrial + ", localAuthority=" + localAuthority + ", payable=" + payable + ", payNum=" + payNum + ", confirmPayStatus=" + confirmPayStatus + ", isPay=" + isPay + ", checker=" + checker + ", checkerName=" + checkerName + ", issueDate=" + issueDate + ", checkResult=" + checkResult + ", isUrgent=" + isUrgent + ", flowStatus=" + flowStatus + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + ", airworthyNum=" + airworthyNum + ", applyNum=" + applyNum + ", acceptNum=" + acceptNum + ", mandateNum=" + mandateNum + ", auditDept=" + auditDept + ", actualFirstAuditor=" + actualFirstAuditor + ", auditDate=" + auditDate + ", mandateDate=" + mandateDate + ", createUser=" + createUser + ", issuser=" + issuser + ", signUserType=" + signUserType + ", folderId=" + folderId + ", otherDesignStandard=" + otherDesignStandard + ", supplementModelCertification=" + supplementModelCertification + ", createDept=" + createDept + ", oldMandateNum=" + oldMandateNum + ", areaAdministrationId=" + areaAdministrationId + ", lastNode=" + lastNode + ", laseNodeAssign=" + laseNodeAssign + ", applyWordId=" + applyWordId + ", acceptWordId=" + acceptWordId + ", mandateWordId=" + mandateWordId + ", airworthyWordId=" + airworthyWordId + ", auditDeptName=" + auditDeptName + "}";
    }

    public Timestamp getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Timestamp auditDate) {
        this.auditDate = auditDate;
    }

    public Timestamp getMandateDate() {
        return mandateDate;
    }

    public void setMandateDate(Timestamp mandateDate) {
        this.mandateDate = mandateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getIssuser() {
        return issuser;
    }

    public void setIssuser(String issuser) {
        this.issuser = issuser;
    }

    public String getSignUserType() {
        return signUserType;
    }

    public void setSignUserType(String signUserType) {
        this.signUserType = signUserType;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getOtherDesignStandard() {
        return otherDesignStandard;
    }

    public void setOtherDesignStandard(String otherDesignStandard) {
        this.otherDesignStandard = otherDesignStandard;
    }

    public String getSupplementModelCertification() {
        return supplementModelCertification;
    }

    public void setSupplementModelCertification(String supplementModelCertification) {
        this.supplementModelCertification = supplementModelCertification;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getOldMandateNum() {
        return oldMandateNum;
    }

    public void setOldMandateNum(String oldMandateNum) {
        this.oldMandateNum = oldMandateNum;
    }

    public String getAreaAdministrationId() {
        return areaAdministrationId;
    }

    public void setAreaAdministrationId(String areaAdministrationId) {
        this.areaAdministrationId = areaAdministrationId;
    }

    public String getLastNode() {
        return lastNode;
    }

    public void setLastNode(String lastNode) {
        this.lastNode = lastNode;
    }

    public String getLaseNodeAssign() {
        return laseNodeAssign;
    }

    public void setLaseNodeAssign(String laseNodeAssign) {
        this.laseNodeAssign = laseNodeAssign;
    }

    public String getApplyWordId() {
        return applyWordId;
    }

    public void setApplyWordId(String applyWordId) {
        this.applyWordId = applyWordId;
    }

    public String getAcceptWordId() {
        return acceptWordId;
    }

    public void setAcceptWordId(String acceptWordId) {
        this.acceptWordId = acceptWordId;
    }

    public String getMandateWordId() {
        return mandateWordId;
    }

    public void setMandateWordId(String mandateWordId) {
        this.mandateWordId = mandateWordId;
    }

    public String getAirworthyWordId() {
        return airworthyWordId;
    }

    public void setAirworthyWordId(String airworthyWordId) {
        this.airworthyWordId = airworthyWordId;
    }

    public String getAuditDeptName() {
        return auditDeptName;
    }

    public void setAuditDeptName(String auditDeptName) {
        this.auditDeptName = auditDeptName;
    }
}
