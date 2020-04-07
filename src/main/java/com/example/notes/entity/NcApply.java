package com.example.notes.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("AMOS_NCAPPLY")
public class NcApply {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String ncid;
//    private String title;   //
//    private String ncid;    //
//    private String ncidbh;  //NR11278§
//    private String ncidqfrq;  //2019-12-26§
//    private String shoulisn;  //国籍受理书[201912]00104§
//    private String shoulibh;  //54001219120380000072§
//    private String shouquanshusn;  //授权书20190722§
//    private String shouquanbh;  //[2019]050§
//    private String shenqingren;  //北京路鹰通用航空有限公司§
//    private String sqrid;  //bjly§
//    private String sqrStatus;  //是§
//    private String shenqingshijian;  //2019-12-06 10:58:00§
//    private String airNumber;  //B-10V1§
//    private String geihaoSN;  //RL11995§
//    private String airNumberSN;  //注册号数据库20190779§
//    private String airNumberStatus;  //有效§
//    private String aircraftType;  //A2C§
//    private String aircraftTypeSN;  //机型数据库20183160§
//    private String xuhao;  //324§
//    private String zhizaozhe;  //中国特种飞行器研究所§
//    private String nameEng;  //§
//    private String addEng;  //§
//    private String glj;  //华北地区管理局§
//    private String gljJbr;  //华北局管理员§
//    private String address;  //§
//    private String finalAddress;  //北京市西城区北礼士路甲98号1幢503室§
//    private String dianhua;  //15771521620§
//    private String lianxir;  //尹振羽§
//    private String youbian;  //100037§
//    private String chuanzhen;  //010-52283949§
//    private String yongtu;  //通用/其它§
//    private String chuchanriqi;  //2012-01-08§
//    private String fdjXh;  //ROTAX912A2§
//    private String fdjSn;  //国籍证发动机表20180357§
//    private String qizhong;  //520§
//    private String zuowei;  //2§
//    private String mingcheng;  //北京路鹰通用航空有限公司§
//    private String dizhi;  //北京市西城区北礼士路甲98号1幢503室§
//    private String renmingcheng;  //北京路鹰通用航空有限公司§
//    private String rendizhi;  //北京市西城区北礼士路甲98号1幢503室§
//    private String hkqjfdd;  //中国§
//    private String printCheckbox2;  //§
//    private String printCheckbox1;  //§
//    private String printShiyongliyou;  //无§
//    private String printJiaofudidian;  //无§
//    private String printSqsqzr;  //乔广林§
//    private String printSqsqzrq;  //2019-12-09§
//    private String printSqsqzrzw;  //总经理§
//    private String gjdjzsqs;  //国籍登记证申请书-324.pdf|§
//    private String sqrwj;  //营业执照-北京路鹰通航.pdf|§
//    private String goumaihetong;  //飞机购买合同书324、325.doc.pdf|§
//    private String cqhfjjjws;  //飞机交接文书324、325.pdf|§
//    private String wzczm;  //§
//    private String guojisaomiao;  //B-10V1国籍证.pdf§
//    private String shouquanduixiang;  //§
//    private String qianfariqi;  //§
//    private String shenqingleibie;  //航空器所有人,航空器占有人§
//    private String yzzgmyhkjdj;  //§
//    private String printCheckbox3;  //20060113080120§
//    private String yzwgdqdj;  //§
//    private String printCheckbox5;  //§
//    private String zhengmingwenjian;  //交接文书§
//    private String checkboxClick;  //on§
//    private String xuanzhe;  //否§
//    private String goSpe;  //资料齐全§
}
