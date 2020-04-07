package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * 喷绘方案-基本信息
 */
@TableName("AMOS_NS_INKJET_INFO")
public class AmosNsInkjetInfo extends Model<AmosNsInkjetInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 喷绘方案ID
     */
    @TableId("INKJET_ID")
    @JSONField(name = "inkjetId")
    private String inkjetId;

    /**
     * 喷绘方案ID
     */
    /**
     * 业务流水号
     */
    @TableField("SERIAL_NUM")
    @JSONField(name = "serialNum")
    private String serialNum;

    /**
     * 申请人
     */
    @TableField("PROPOSER")
    @JSONField(name = "proposer")
    private String proposer;

    /**
     * 申请时间
     */
    @TableField("APPLY_DATE")
    @JSONField(name = "applyDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp applyDate;

    /**
     * 航空器类别
     */
    @TableField("AIRCRAFT_CATEGORY")
    @JSONField(name = "aircraftCategory")
    private String aircraftCategory;

    /**
     * 申请类别
     */
    @TableField("APPLY_CATEGORY")
    @JSONField(name = "applyCategory")
    private String applyCategory;

    /**
     * 注册号ID
     */
    @TableField("REGISTRATION_NUM")
    @JSONField(name = "registrationNum")
    private String registrationNum;

    /**
     * 航空器型别
     */
    @TableField("AIRCRAFT_TYPE")
    @JSONField(name = "aircraftType")
    private String aircraftType;

    /**
     * 备注
     */
    @TableField("NOTE")
    @JSONField(name = "note")
    private String note;

    /**
     * 标题内容
     */
    @TableField("TITLE_CONTENT")
    @JSONField(name = "titleContent")
    private String titleContent;

    /**
     * 正文内容
     */
    @TableField("BODY_CONTENT")
    @JSONField(name = "bodyContent")
    private String bodyContent;

    /**
     * 正文内容1
     */
    @TableField("BODY_CONTENT1")
    @JSONField(name = "bodyContent1")
    private String bodyContent1;

    /**
     * 声明
     */
    @TableField("STATEMENT")
    @JSONField(name = "statement")
    private String statement;

    /**
     * 重要提示
     */
    @TableField("MAIN_HINT")
    @JSONField(name = "mainHint")
    private String mainHint;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    @JSONField(name = "createDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /**
     * 状态
     */
    @TableField("STATUS")
    @JSONField(name = "status")
    private String status;

    /**
     * 审核日期
     */
    @TableField("APPROVE_DATE")
    @JSONField(name = "approveDate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp approveDate;

    /**
     * 初审人
     */
    @TableField("FIRST_APPROVER")
    @JSONField(name = "firstApprover")
    private String firstApprover;

    /**
     * 批复编号
     */
    @TableField("REPLY_NO")
    @JSONField(name = "replyNo")
    private String replyNo;

    /**
     * 批复批准人
     */
    @TableField("APPROVER")
    @JSONField(name = "approver")
    private String approver;

    /**
     * 批复盖章
     */
    @TableField("SEAL")
    @JSONField(name = "seal")
    private String seal;

    /**
     * 文件夹ID
     */
    @TableField("FOLD_ID")
    @JSONField(name = "foldId")
    private String foldId;

    /**
     * 文件夹名称
     */
    @TableField("FOLD_NAME")
    @JSONField(name = "foldName")
    private String foldName;

    /**
     * 创建人ID
     */
    @TableField("CREATEUSERID")
    @JSONField(name = "createuserid")
    private String createuserid;

    /**
     * 地区管理局ID
     */
    @TableField("AREA_ADMINISTRATION_ID")
    @JSONField(name = "areaAdministrationId")
    private String areaAdministrationId;

    /**
     * 创建部门ID
     */
    @TableField("CREATE_DEPT_ID")
    @JSONField(name = "createDeptId")
    private String createDeptId;

    /**
     * 归档审核人ID
     */
    @TableField("ARCHIVER_ID")
    @JSONField(name = "archiverId")
    private String archiverId;

    /**
     * 地区管理局
     */
    @TableField("AREA_ADMINISTRATION")
    @JSONField(name = "areaAdministration")
    private String areaAdministration;

    /**
     * 初审人联系电话
     */
    @TableField("AC_OFFICE_PHONE")
    @JSONField(name = "acOfficePhone")
    private String acOfficePhone;

    /**
     * 盖章图片路径
     */
    @TableField("PICTURE_PATH")
    @JSONField(name = "picturePath")
    private String picturePath;

    /**
     * 批复方案文件ID
     */
    @TableField("APPLICAT_FILE_ID")
    @JSONField(name = "applicatFileId")
    private String applicatFileId;

    public String getInkjetId() {
        return inkjetId;
    }

    public void setInkjetId(String inkjetId) {
        this.inkjetId = inkjetId;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    public String getAircraftCategory() {
        return aircraftCategory;
    }

    public void setAircraftCategory(String aircraftCategory) {
        this.aircraftCategory = aircraftCategory;
    }

    public String getApplyCategory() {
        return applyCategory;
    }

    public void setApplyCategory(String applyCategory) {
        this.applyCategory = applyCategory;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getBodyContent1() {
        return bodyContent1;
    }

    public void setBodyContent1(String bodyContent1) {
        this.bodyContent1 = bodyContent1;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getMainHint() {
        return mainHint;
    }

    public void setMainHint(String mainHint) {
        this.mainHint = mainHint;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Timestamp approveDate) {
        this.approveDate = approveDate;
    }

    public String getFirstApprover() {
        return firstApprover;
    }

    public void setFirstApprover(String firstApprover) {
        this.firstApprover = firstApprover;
    }

    public String getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(String replyNo) {
        this.replyNo = replyNo;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    @Override
    protected Serializable pkVal() {
        return this.inkjetId;
    }

    @Override
    public String toString() {
        return "AmosNsInkjetInfo{" + "inkjetId=" + inkjetId + ", serialNum=" + serialNum + ", proposer=" + proposer + ", applyDate=" + applyDate + ", aircraftCategory=" + aircraftCategory + ", applyCategory=" + applyCategory + ", registrationNum=" + registrationNum + ", aircraftType=" + aircraftType + ", note=" + note + ", titleContent=" + titleContent + ", bodyContent=" + bodyContent + ", bodyContent1=" + bodyContent1 + ", statement=" + statement + ", mainHint=" + mainHint + ", createDate=" + createDate + ", status=" + status + ", approveDate=" + approveDate + ", firstApprover=" + firstApprover + ", replyNo=" + replyNo + ", approver=" + approver + ", seal=" + seal + ", foldId=" + foldId + ", foldName=" + foldName + ", createuserid=" + createuserid + ", areaAdministrationId=" + areaAdministrationId + ", createDeptId=" + createDeptId + ", archiverId=" + archiverId + ", areaAdministration=" + areaAdministration + ", acOfficePhone=" + acOfficePhone + ", picturePath=" + picturePath + ", applicatFileId=" + applicatFileId + "}";
    }

    public String getFoldId() {
        return foldId;
    }

    public void setFoldId(String foldId) {
        this.foldId = foldId;
    }

    public String getFoldName() {
        return foldName;
    }

    public void setFoldName(String foldName) {
        this.foldName = foldName;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public String getAreaAdministrationId() {
        return areaAdministrationId;
    }

    public void setAreaAdministrationId(String areaAdministrationId) {
        this.areaAdministrationId = areaAdministrationId;
    }

    public String getCreateDeptId() {
        return createDeptId;
    }

    public void setCreateDeptId(String createDeptId) {
        this.createDeptId = createDeptId;
    }

    public String getArchiverId() {
        return archiverId;
    }

    public void setArchiverId(String archiverId) {
        this.archiverId = archiverId;
    }

    public String getAreaAdministration() {
        return areaAdministration;
    }

    public void setAreaAdministration(String areaAdministration) {
        this.areaAdministration = areaAdministration;
    }

    public String getAcOfficePhone() {
        return acOfficePhone;
    }

    public void setAcOfficePhone(String acOfficePhone) {
        this.acOfficePhone = acOfficePhone;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getApplicatFileId() {
        return applicatFileId;
    }

    public void setApplicatFileId(String applicatFileId) {
        this.applicatFileId = applicatFileId;
    }
}
