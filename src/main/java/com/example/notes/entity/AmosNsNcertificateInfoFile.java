package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("AMOS_NS_NCERTIFICATE_INFO_FILE")
public class AmosNsNcertificateInfoFile extends Model<AmosNsNcertificateInfoFile> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    @JSONField(name = "id")
    private String id;
    /**
     * UNID
     */
    @TableId(value = "UNID")
    @JSONField(name = "unid")
    private String unid;
    /**
     * INFOID
     */
    @TableId(value = "INFOID")
    @JSONField(name = "infoid")
    private String infoid;
    /**
     * TYPE
     */
    @TableId(value = "TYPE")
    @JSONField(name = "type")
    private String type;
    /**
     * ATTACHMENTID
     */
    @TableId(value = "ATTACHMENTID")
    @JSONField(name = "attachmentid")
    private String attachmentid;
    /**
     * ATTACHMENTPATH
     */
    @TableId(value = "ATTACHMENTPATH")
    @JSONField(name = "attachmentpath")
    private String attachmentpath;
    /**
     * SN
     */
    @TableId(value = "SN")
    @JSONField(name = "sn")
    private String sn;
    /**
     * AIRNUMBER
     */
    @TableId(value = "AIRNUMBER")
    @JSONField(name = "airnumber")
    private String airnumber;


    @Override
    public String toString() {
        return "AmosNsNcertificateInfoFile{" + "id=" + id + ", unid=" + unid + ", infoid=" + infoid + ", attachmentid=" + attachmentid + ", attachmentpath=" + attachmentpath + ", sn=" + sn + ", airnumber=" + airnumber + "}";
    }

}
