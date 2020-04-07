package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("HUSSAR.AMOS_NS_NCERTIFICATE_INFO")
public class AmosNsNcertificateInfo extends Model<AmosNsNcertificateInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID")
    @JSONField(name = "id")
    private String id;

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
     * 地区管理局
     */
    @TableField("AREA_ADMINISTRATION")
    @JSONField(name = "areaAdministration")
    private String areaAdministration;

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
     * 国籍标志和登记标志
     */
    @TableField("REGNUM")
    @JSONField(name = "regnum")
    private String regnum;

    /**
     * 给号函编号
     */
    @TableField("REGNUM_NUM")
    @JSONField(name = "regnumNum")
    private String regnumNum;

    /**
     * 航空器类别
     */
    @TableField("AIRCRAFT_CATEGORY")
    @JSONField(name = "aircraftCategory")
    private String aircraftCategory;

    /**
     * 航空器制造人名称
     */
    @TableField("AIRCRAFT_MANUFACTURER")
    @JSONField(name = "aircraftManufacturer")
    private String aircraftManufacturer;

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
     * 喷涂方案
     */
    @TableField("INKJET")
    @JSONField(name = "inkjet")
    private String inkjet;

    /**
     * 申请人类别
     */
    @TableField("APPLICAT_TYPE")
    @JSONField(name = "applicatType")
    private String applicatType;

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
     * 申请人英文名称
     */
    @TableField("APPLICAT_ENG")
    @JSONField(name = "applicatEng")
    private String applicatEng;

    /**
     * 申请人英文地址
     */
    @TableField("APPLICAT_ENG_ADDRESS")
    @JSONField(name = "applicatEngAddress")
    private String applicatEngAddress;

    /**
     * 原在中国民用航空局登记
     */
    @TableField("APPLICAT_ISSIGN_CHINA")
    @JSONField(name = "applicatIssignChina")
    private String applicatIssignChina;

    /**
     * 国籍登记标志
     */
    @TableField("APPLICAT_NUM_CHINA")
    @JSONField(name = "applicatNumChina")
    private String applicatNumChina;

    /**
     * 原在外国/地区登记
     */
    @TableField("APPLICAT_ISSIGN_FOREIGN")
    @JSONField(name = "applicatIssignForeign")
    private String applicatIssignForeign;

    /**
     * 登记国/登记地区名称及国籍登记标志
     */
    @TableField("APPLICAT_NUM_FOREIGN")
    @JSONField(name = "applicatNumForeign")
    private String applicatNumForeign;

    /**
     * 是否按照特殊申请程序办理国籍登记证
     */
    @TableField("ISSPECIAL")
    @JSONField(name = "isspecial")
    private String isspecial;

    /**
     * 适用理由
     */
    @TableField("REASONS")
    @JSONField(name = "reasons")
    private String reasons;

    /**
     * 所缺证明文件名称及补交地点、日期
     */
    @TableField("MISS_FILE_INFO")
    @JSONField(name = "missFileInfo")
    private String missFileInfo;

    /**
     * 申请书签字人
     */
    @TableField("APPLICATION_SIGNATORY")
    @JSONField(name = "applicationSignatory")
    private String applicationSignatory;

    /**
     * 签字人职务
     */
    @TableField("SIGNATORY_DUTY")
    @JSONField(name = "signatoryDuty")
    private String signatoryDuty;

    /**
     * 签字日期
     */
    @TableField("SIGNATURE_DATE")
    @JSONField(name = "signatureDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp signatureDate;

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
     * 航空器方案
     */
    @TableField("AIRCRAFT_PLAN")
    @JSONField(name = "aircraftPlan")
    private String aircraftPlan;

    /**
     * 航空器组装
     */
    @TableField("AIRCRAFT_DIY")
    @JSONField(name = "aircraftDiy")
    private String aircraftDiy;

    /**
     * 航空器交付地点
     */
    @TableField("AIRCRAFT_DELIVERY_ADDRESS")
    @JSONField(name = "aircraftDeliveryAddress")
    private String aircraftDeliveryAddress;

    /**
     * 航空器最大起飞全重
     */
    @TableField("AIRCRAFT_MAX_WEIGHT")
    @JSONField(name = "aircraftMaxWeight")
    private String aircraftMaxWeight;

    /**
     * 最大可用业载
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
     * 航空器用途
     */
    @TableField("APPLICAT_USE")
    @JSONField(name = "applicatUse")
    private String applicatUse;

    /**
     * 所缺证明文件
     */
    @TableField("MISS_FILE")
    @JSONField(name = "missFile")
    private String missFile;

    /**
     * 申请人地址
     */
    @TableField("APPLICAT_ADDRESS")
    @JSONField(name = "applicatAddress")
    private String applicatAddress;

    /**
     * 备注
     */
    @TableField("REMARKS")
    @JSONField(name = "remarks")
    private String remarks;

    /**
     * 声明
     */
    @TableField("STATEMENT")
    @JSONField(name = "statement")
    private String statement;

    /**
     * 申请状态
     */
    @TableField("APPLICAT_STATUS")
    @JSONField(name = "applicatStatus")
    private String applicatStatus;

    /**
     * 受理书编号
     */
    @TableField("ACCEPTANCE_NUM")
    @JSONField(name = "acceptanceNum")
    private String acceptanceNum;

    /**
     * 授权书编号
     */
    @TableField("AUTHORIZATION_NUM")
    @JSONField(name = "authorizationNum")
    private String authorizationNum;

    /**
     * 完成时间
     */
    @TableField("FINISH_TIME")
    @JSONField(name = "finishTime", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp finishTime;

    /**
     * 未在中国民用航空局登记
     */
    @TableField("APPLICAT_ISNOTSIGN_CHINA")
    @JSONField(name = "applicatIsnotsignChina")
    private String applicatIsnotsignChina;

    /**
     * 注销或未在外国/地区国籍登记的证明
     */
    @TableField("APPLICAT_ISNOTSIGN_FOREIGN")
    @JSONField(name = "applicatIsnotsignForeign")
    private String applicatIsnotsignForeign;

    /**
     * 检查人
     */
    @TableField("RUMMAGER")
    @JSONField(name = "rummager")
    private String rummager;

    /**
     * 授权人
     */
    @TableField("AUTHORIZATION")
    @JSONField(name = "authorization")
    private String authorization;

    /**
     * 授权时间
     */
    @TableField("AUTHORIZATION_DATE")
    @JSONField(name = "authorizationDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp authorizationDate;

    /**
     * 受理时间
     */
    @TableField("ACCEPTANCE_DATE")
    @JSONField(name = "acceptanceDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp acceptanceDate;

    /**
     * 登记人
     */
    @TableField("CREATE_USER")
    @JSONField(name = "createUser")
    private String createUser;

    /**
     * 登记时间
     */
    @TableField("CREATE_DATE")
    @JSONField(name = "createDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /**
     * 签发人
     */
    @TableField("ISSUER")
    @JSONField(name = "issuer")
    private String issuer;

    /**
     * 签发日期
     */
    @TableField("ISSUING_DATE")
    @JSONField(name = "issuingDate")
    private String issuingDate;

    /**
     * 国籍证编号
     */
    @TableField("NC_NUM")
    @JSONField(name = "ncNum")
    private String ncNum;

    /**
     * 签字人类型
     */
    @TableField("SIGNATORY_TYPE")
    @JSONField(name = "signatoryType")
    private String signatoryType;

    /**
     * 航空器交付地点类型
     */
    @TableField("AIRCRAFT_DELIVERY_ADDRESS_TYPE")
    @JSONField(name = "aircraftDeliveryAddressType")
    private String aircraftDeliveryAddressType;

    /**
     * 现场检查情况
     */
    @TableField("CHECK_RESULT")
    @JSONField(name = "checkResult")
    private String checkResult;

    /**
     * 文件夹ID
     */
    @TableField("FOLDER_ID")
    @JSONField(name = "folderId")
    private String folderId;

    /**
     * 创建部门
     */
    @TableField("CREATE_DEPT")
    @JSONField(name = "createDept")
    private String createDept;

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
     * 申请书id
     */
    @TableField("APPLICAT_FILE")
    @JSONField(name = "applicatFile")
    private String applicatFile;

    /**
     * 受理书id
     */
    @TableField("ACCEPTANCE_FILE")
    @JSONField(name = "acceptanceFile")
    private String acceptanceFile;

    /**
     * 授权书id
     */
    @TableField("AUTHORIZATION_FILE")
    @JSONField(name = "authorizationFile")
    private String authorizationFile;

    /**
     * 国籍证id
     */
    @TableField("NC_FILE")
    @JSONField(name = "ncFile")
    private String ncFile;

    /**
     * 国籍证反面id
     */
    @TableField("NC_BACK_FILE")
    @JSONField(name = "ncBackFile")
    private String ncBackFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAreaAdministration() {
        return areaAdministration;
    }

    public void setAreaAdministration(String areaAdministration) {
        this.areaAdministration = areaAdministration;
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

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getRegnumNum() {
        return regnumNum;
    }

    public void setRegnumNum(String regnumNum) {
        this.regnumNum = regnumNum;
    }

    public String getAircraftCategory() {
        return aircraftCategory;
    }

    public void setAircraftCategory(String aircraftCategory) {
        this.aircraftCategory = aircraftCategory;
    }

    public String getAircraftManufacturer() {
        return aircraftManufacturer;
    }

    public void setAircraftManufacturer(String aircraftManufacturer) {
        this.aircraftManufacturer = aircraftManufacturer;
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

    public String getInkjet() {
        return inkjet;
    }

    public void setInkjet(String inkjet) {
        this.inkjet = inkjet;
    }

    public String getApplicatType() {
        return applicatType;
    }

    public void setApplicatType(String applicatType) {
        this.applicatType = applicatType;
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

    public String getApplicatEng() {
        return applicatEng;
    }

    public void setApplicatEng(String applicatEng) {
        this.applicatEng = applicatEng;
    }

    public String getApplicatEngAddress() {
        return applicatEngAddress;
    }

    public void setApplicatEngAddress(String applicatEngAddress) {
        this.applicatEngAddress = applicatEngAddress;
    }

    public String getApplicatIssignChina() {
        return applicatIssignChina;
    }

    public void setApplicatIssignChina(String applicatIssignChina) {
        this.applicatIssignChina = applicatIssignChina;
    }

    public String getApplicatNumChina() {
        return applicatNumChina;
    }

    public void setApplicatNumChina(String applicatNumChina) {
        this.applicatNumChina = applicatNumChina;
    }

    public String getApplicatIssignForeign() {
        return applicatIssignForeign;
    }

    public void setApplicatIssignForeign(String applicatIssignForeign) {
        this.applicatIssignForeign = applicatIssignForeign;
    }

    public String getApplicatNumForeign() {
        return applicatNumForeign;
    }

    public void setApplicatNumForeign(String applicatNumForeign) {
        this.applicatNumForeign = applicatNumForeign;
    }

    public String getIsspecial() {
        return isspecial;
    }

    public void setIsspecial(String isspecial) {
        this.isspecial = isspecial;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getMissFileInfo() {
        return missFileInfo;
    }

    public void setMissFileInfo(String missFileInfo) {
        this.missFileInfo = missFileInfo;
    }

    public String getApplicationSignatory() {
        return applicationSignatory;
    }

    public void setApplicationSignatory(String applicationSignatory) {
        this.applicationSignatory = applicationSignatory;
    }

    public String getSignatoryDuty() {
        return signatoryDuty;
    }

    public void setSignatoryDuty(String signatoryDuty) {
        this.signatoryDuty = signatoryDuty;
    }

    public Timestamp getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(Timestamp signatureDate) {
        this.signatureDate = signatureDate;
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

    public String getAircraftPlan() {
        return aircraftPlan;
    }

    public void setAircraftPlan(String aircraftPlan) {
        this.aircraftPlan = aircraftPlan;
    }

    public String getAircraftDiy() {
        return aircraftDiy;
    }

    public void setAircraftDiy(String aircraftDiy) {
        this.aircraftDiy = aircraftDiy;
    }

    public String getAircraftDeliveryAddress() {
        return aircraftDeliveryAddress;
    }

    public void setAircraftDeliveryAddress(String aircraftDeliveryAddress) {
        this.aircraftDeliveryAddress = aircraftDeliveryAddress;
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

    public String getApplicatUse() {
        return applicatUse;
    }

    public void setApplicatUse(String applicatUse) {
        this.applicatUse = applicatUse;
    }

    public String getMissFile() {
        return missFile;
    }

    public void setMissFile(String missFile) {
        this.missFile = missFile;
    }

    public String getApplicatAddress() {
        return applicatAddress;
    }

    public void setApplicatAddress(String applicatAddress) {
        this.applicatAddress = applicatAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getApplicatStatus() {
        return applicatStatus;
    }

    public void setApplicatStatus(String applicatStatus) {
        this.applicatStatus = applicatStatus;
    }

    public String getAcceptanceNum() {
        return acceptanceNum;
    }

    public void setAcceptanceNum(String acceptanceNum) {
        this.acceptanceNum = acceptanceNum;
    }

    public String getAuthorizationNum() {
        return authorizationNum;
    }

    public void setAuthorizationNum(String authorizationNum) {
        this.authorizationNum = authorizationNum;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AmosNsNcertificateInfo{" + "id=" + id + ", serialsnum=" + serialsnum + ", applicat=" + applicat + ", areaAdministration=" + areaAdministration + ", applicatDate=" + applicatDate + ", legalPersons=" + legalPersons + ", applicatContact=" + applicatContact + ", applicatMobile=" + applicatMobile + ", applicatPhone=" + applicatPhone + ", applicatFax=" + applicatFax + ", applicatPostcode=" + applicatPostcode + ", regnum=" + regnum + ", regnumNum=" + regnumNum + ", aircraftCategory=" + aircraftCategory + ", aircraftManufacturer=" + aircraftManufacturer + ", aircraftType=" + aircraftType + ", aircraftNumber=" + aircraftNumber + ", manufactureDate=" + manufactureDate + ", inkjet=" + inkjet + ", applicatType=" + applicatType + ", aircraftOwner=" + aircraftOwner + ", aircraftOwnerAddress=" + aircraftOwnerAddress + ", aircraftHolder=" + aircraftHolder + ", aircraftHolderAddress=" + aircraftHolderAddress + ", applicatEng=" + applicatEng + ", applicatEngAddress=" + applicatEngAddress + ", applicatIssignChina=" + applicatIssignChina + ", applicatIsnotsignChina=" + applicatIsnotsignChina + ", applicatNumChina=" + applicatNumChina + ", applicatIssignForeign=" + applicatIssignForeign + ", applicatIsnotsignForeign=" + applicatIsnotsignForeign + ", applicatNumForeign=" + applicatNumForeign + ", isspecial=" + isspecial + ", reasons=" + reasons + ", missFileInfo=" + missFileInfo + ", applicationSignatory=" + applicationSignatory + ", signatoryDuty=" + signatoryDuty + ", signatureDate=" + signatureDate + ", aircraftIsimport=" + aircraftIsimport + ", aircraftIsnew=" + aircraftIsnew + ", aircraftFrom=" + aircraftFrom + ", aircraftFromOther=" + aircraftFromOther + ", aircraftPlan=" + aircraftPlan + ", aircraftDiy=" + aircraftDiy + ", aircraftDeliveryAddress=" + aircraftDeliveryAddress + ", aircraftMaxWeight=" + aircraftMaxWeight + ", aircraftMaxLoad=" + aircraftMaxLoad + ", aircraftSeatSize=" + aircraftSeatSize + ", engineModel=" + engineModel + ", engineCreateuser=" + engineCreateuser + ", engineNum=" + engineNum + ", propellerModel=" + propellerModel + ", propellerCreateuser=" + propellerCreateuser + ", propellerNum=" + propellerNum + ", applicatUse=" + applicatUse + ", missFile=" + missFile + ", applicatAddress=" + applicatAddress + ", remarks=" + remarks + ", statement=" + statement + ", applicatStatus=" + applicatStatus + ", acceptanceNum=" + acceptanceNum + ", authorizationNum=" + authorizationNum + ", finishTime=" + finishTime + ", rummager=" + rummager + ", authorization=" + authorization + ", authorizationDate=" + authorizationDate + ", acceptanceDate=" + acceptanceDate + ", createUser=" + createUser + ", createDate=" + createDate + ", issuer=" + issuer + ", issuingDate=" + issuingDate + ", ncNum=" + ncNum + ", signatoryType=" + signatoryType + ", aircraftDeliveryAddressType=" + aircraftDeliveryAddressType + ", checkResult=" + checkResult + ", folderId=" + folderId + ", createDept=" + createDept + ", areaAdministrationId=" + areaAdministrationId + ", lastNode=" + lastNode + ", laseNodeAssign=" + laseNodeAssign + ", applicatFile=" + applicatFile + ", acceptanceFile=" + acceptanceFile + ", authorizationFile=" + authorizationFile + ", ncFile=" + ncFile + ", ncBackFile=" + ncBackFile + "}";
    }

    public String getApplicatIsnotsignChina() {
        return applicatIsnotsignChina;
    }

    public void setApplicatIsnotsignChina(String applicatIsnotsignChina) {
        this.applicatIsnotsignChina = applicatIsnotsignChina;
    }

    public String getApplicatIsnotsignForeign() {
        return applicatIsnotsignForeign;
    }

    public void setApplicatIsnotsignForeign(String applicatIsnotsignForeign) {
        this.applicatIsnotsignForeign = applicatIsnotsignForeign;
    }

    public String getRummager() {
        return rummager;
    }

    public void setRummager(String rummager) {
        this.rummager = rummager;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Timestamp getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAuthorizationDate(Timestamp authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public Timestamp getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Timestamp acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getNcNum() {
        return ncNum;
    }

    public void setNcNum(String ncNum) {
        this.ncNum = ncNum;
    }

    public String getSignatoryType() {
        return signatoryType;
    }

    public void setSignatoryType(String signatoryType) {
        this.signatoryType = signatoryType;
    }

    public String getAircraftDeliveryAddressType() {
        return aircraftDeliveryAddressType;
    }

    public void setAircraftDeliveryAddressType(String aircraftDeliveryAddressType) {
        this.aircraftDeliveryAddressType = aircraftDeliveryAddressType;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
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

    public String getApplicatFile() {
        return applicatFile;
    }

    public void setApplicatFile(String applicatFile) {
        this.applicatFile = applicatFile;
    }

    public String getAcceptanceFile() {
        return acceptanceFile;
    }

    public void setAcceptanceFile(String acceptanceFile) {
        this.acceptanceFile = acceptanceFile;
    }

    public String getAuthorizationFile() {
        return authorizationFile;
    }

    public void setAuthorizationFile(String authorizationFile) {
        this.authorizationFile = authorizationFile;
    }

    public String getNcFile() {
        return ncFile;
    }

    public void setNcFile(String ncFile) {
        this.ncFile = ncFile;
    }

    public String getNcBackFile() {
        return ncBackFile;
    }

    public void setNcBackFile(String ncBackFile) {
        this.ncBackFile = ncBackFile;
    }
}
