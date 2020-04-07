package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@TableName("AMOS_USER")
@Data
public class UserInfoDeal extends Model<UserInfoDeal> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    /**
     * ID
     */
    @TableId(value = "ID",type = IdType.UUID)
    @JSONField(name = "id")
    private String id;

    /**
     * SQR_ATT
     */
    @TableId(value = "SQR_ATT")
    @JSONField(name = "sqr_att")
    private String  sqr_att;

    /**
     * SHENQINGREN
     */
    @TableId(value ="SHENQINGREN")
    @JSONField(name = "shenqingren")
    private String shenqingren;

    /**
     * ADD_PR
     */
    @TableId(value = "ADD_PR")
    @JSONField(name = "add_pr")
    private String add_pr;

    /**
     * ADDRESS
     */
    @TableId(value = "ADDRESS")
    @JSONField(name = "address")
    private String address;

    /**
     * ADDSN
     */
    @TableId(value = "ADDSN")
    @JSONField(name = "addsn")
    private String addsn;

    /**
     * FINALADDRESS
     */
    @TableId(value ="FINALADDRESS")
    @JSONField(name = "finaladdress")
    private String finaladdress;
    /**
     * YOUBIAN
     */
    @TableId(value = "YOUBIAN")
    @JSONField(name = "youbian")
    private String youbian;
    /**
     * SHENQINGREN
     */
    @TableId(value = "SHENQINGREN")
    @JSONField(name = "shenqingshijian")
    private String shenqingshijian;
    /**
     * GLJ
     */
    @TableId(value = "GLJ")
    @JSONField(name = "glj")
    private String glj;
    /**
     * FDDBR
     */
    @TableId(value = "FDDBR")
    @JSONField(name = "fddbr")
    private String fddbr;
    /**
     * OFFICEADDRESS2
     */
    @TableId(value = "OFFICEADDRESS2")
    @JSONField(name = "officeaddress2")
    private String officeaddress2;
    /**
     * JWFX
     */
    @TableId(value = "JWFX")
    @JSONField(name = "jwfx")
    private String jwfx;
    /**
     * ADD_ENG
     */
    @TableId(value = "ADD_ENG")
    @JSONField(name = "add_eng")
    private String add_eng;
    /**
     * NAME_ENG
     */
    @TableId(value = "NAME_ENG")
    @JSONField(name = "name_eng")
    private String name_eng;
    /**
     * SHTYXYBM
     */
    @TableId(value = "SHTYXYBM")
    @JSONField(name = "shtyxybm")
    private String shtyxybm;
    /**
     * SHENFENZHENG
     */
    @TableId(value = "SHENFENZHENG")
    @JSONField(name = "shenfenzheng")
    private String shenfenzheng;
    /**
     * SHENFENZHENGVALIDDATE
     */
    @TableId(value = "SHENFENZHENGVALIDDATE")
    @JSONField(name = "shenfenzhengvaliddate")
    private String shenfenzhengvaliddate;
    /**
     * MANAGE_NAME
     */
    @TableId(value = "MANAGE_NAME")
    @JSONField(name = "manage_name")
    private String manage_name;
    /**
     * MANAGE_MOBILE
     */
    @TableId(value = "MANAGE_MOBILE")
    @JSONField(name = "manage_mobile")
    private String manage_mobile;
    /**
     * OFFICETEL
     */
    @TableId(value = "OFFICETEL")
    @JSONField(name = "officetel")
    private String officetel;
    /**
     * CHUANZHEN
     */
    @TableId(value = "CHUANZHEN")
    @JSONField(name = "chuanzhen")
    private String chuanzhen;
    /**
     * MANAGE_SFZHM
     */
    @TableId(value = "MANAGE_SFZHM")
    @JSONField(name = "manage_sfzhm")
    private String manage_sfzhm;
    /**
     * QQ
     */
    @TableId(value = "QQ")
    @JSONField(name = "qq")
    private String qq;
    /**
     * ACCOUNTNAME
     */
    @TableId(value = "ACCOUNTNAME")
    @JSONField(name = "accountname")
    private String accountname;
    /**
     * EMAIL
     */
    @TableId(value = "EMAIL")
    @JSONField(name = "email")
    private String email;
    /**
     * BEIZHU
     */
    @TableId(value = "BEIZHU")
    @JSONField(name = "beizhu")
    private String beizhu;
    /**
     * DENYREASON
     */
    @TableId(value = "DENYREASON")
    @JSONField(name = "denyreason")
    private String denyreason;
    /**
     * HLJ_JBR
     */
    @TableId(value = "HLJ_JBR")
    @JSONField(name = "glj_jbr")
    private String glj_jbr;

    /**
     * ATTACHMENTSTORAGEID
     */
    @TableId(value = "ATTACHMENTSTORAGEID")
    @JSONField(name = "attachmentstorageid")
    private String attachmentstorageid;
    /**
     * ATTACHMENTSTORAGEPATH
     */
    @TableId(value = "ATTACHMENTSTORAGEPATH")
    @JSONField(name = "attachmentstoragepath")
    private String attachmentstoragepath;




















}
