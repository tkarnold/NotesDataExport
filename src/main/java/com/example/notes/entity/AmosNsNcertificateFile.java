package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("HUSSAR.AMOS_NS_NCERTIFICATE_FILE")
public class AmosNsNcertificateFile extends Model<AmosNsNcertificateFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 附件ID
     */
    @TableId(value = "ID")
    @JSONField(name = "id")
    private String id;

    /**
     * 附件ID
     */
    /**
     * 业务表ID
     */
    @TableField("BUSINESSID")
    @JSONField(name = "businessid")
    private String businessid;

    /**
     * 对应业务表
     */
    @TableField("TABLENAME")
    @JSONField(name = "tablename")
    private String tablename;

    /**
     * 文件状态
     */
    @TableField("FILE_STATUS")
    @JSONField(name = "fileStatus")
    private String fileStatus;

    /**
     * 附件名称
     */
    @TableField("FILE_NAME")
    @JSONField(name = "fileName")
    private String fileName;

    /**
     * 附件地址
     */
    @TableField("FILE_PATH")
    @JSONField(name = "filePath")
    private String filePath;

    /**
     * 上传时间
     */
    @TableField("UPLOADDATE")
    @JSONField(name = "uploaddate", format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp uploaddate;

    /**
     * 上传人
     */
    @TableField("UPLOADUSER")
    @JSONField(name = "uploaduser")
    private String uploaduser;

    /**
     * 未审批：0；通过：1；驳回：2；
     */
    @TableField("ISAUDIT")
    @JSONField(name = "isaudit")
    private String isaudit;

    /**
     * 附件类型id
     */
    @TableField("FILE_TYPE_ID")
    @JSONField(name = "fileTypeId")
    private String fileTypeId;

    /**
     * 附件类型名称
     */
    @TableField("FILE_TYPE_NAME")
    @JSONField(name = "fileTypeName")
    private String fileTypeName;

    /**
     * 驳回备注
     */
    @TableField("REJECTS_REMARKS")
    @JSONField(name = "rejectsRemarks")
    private String rejectsRemarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Timestamp getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Timestamp uploaddate) {
        this.uploaddate = uploaddate;
    }

    public String getUploaduser() {
        return uploaduser;
    }

    public void setUploaduser(String uploaduser) {
        this.uploaduser = uploaduser;
    }

    public String getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit;
    }

    public String getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AmosNsNcertificateFile{" + "id=" + id + ", businessid=" + businessid + ", tablename=" + tablename + ", fileStatus=" + fileStatus + ", fileName=" + fileName + ", filePath=" + filePath + ", uploaddate=" + uploaddate + ", uploaduser=" + uploaduser + ", isaudit=" + isaudit + ", fileTypeId=" + fileTypeId + ", fileTypeName=" + fileTypeName + ", rejectsRemarks=" + rejectsRemarks + "}";
    }

    public String getRejectsRemarks() {
        return rejectsRemarks;
    }

    public void setRejectsRemarks(String rejectsRemarks) {
        this.rejectsRemarks = rejectsRemarks;
    }
}
