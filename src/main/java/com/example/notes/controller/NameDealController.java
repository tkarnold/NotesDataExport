package com.example.notes.controller;

import com.example.notes.api.ConnectToDomino;
import com.example.notes.entity.UserInfoDeal;
import com.example.notes.service.IUserInfoService;
import com.example.notes.util.DominoDataImportUtil;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userdeal")
public class NameDealController {

    @Autowired
   private IUserInfoService userInfoService;

   @RequestMapping("/userinfo")
   @ResponseBody
    public void dealName() throws NotesException {

        String server = "localhost:8086";
        String username = "tkarn";
        String password = "123456";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;

        localsession = ctd.setConnection1(server, username, password);

        if (localsession == null) {
            System.out.println("failed to get local session.");
        } else {
            System.out.println("got local session.");
        }
        lotus.domino.Database db = null;
        db=localsession.getDatabase("","1801121111");
       if (db == null || db.getViews().size() == 0) {
           System.out.println("获取用户信息库失败！");
           return;
       } else {
           System.out.println("获取用户信息库成功！");
       }
        View view = db.getView("viewByName");
       if (view.getColumnCount() == 0) {
           System.out.println("未能找到视图！");
           return;
       }
       Document doc = null, nextDocx = null;
         doc = view.getFirstDocument();
       int count = 0;
       while(doc != null){

           UserInfoDeal userInfoDeal = exData(doc);
           //将喷涂方案数据插入数据库
           userInfoService.insert(userInfoDeal);
           //循环业务数据，不断获取对象
           nextDocx = view.getNextDocument(doc);
           doc = nextDocx;
           count++;
           System.out.println("数据迁移成功！第"+count+"条");
       }
       System.out.println("数据全部迁移成功");
    }

    private UserInfoDeal exData(Document document){
        UserInfoDeal userInfoDeal = new UserInfoDeal();
        try{
            userInfoDeal.setSqr_att(DominoDataImportUtil.exValue(document,"sqr_att"));//申请人类别
            userInfoDeal.setShenqingren(DominoDataImportUtil.exValue(document,"shenqingren"));//申请人姓名
            userInfoDeal.setAdd_pr(DominoDataImportUtil.exValue(document,"add_pr"));//申请人地址
            userInfoDeal.setAddress(DominoDataImportUtil.exValue(document,"address"));//申请人地址
            userInfoDeal.setFinaladdress(DominoDataImportUtil.exValue(document,"FinalAddress"));//
            userInfoDeal.setYoubian(DominoDataImportUtil.exValue(document,"youbian"));//邮编
            userInfoDeal.setShenqingshijian(DominoDataImportUtil.exValue(document,"shenqingshijian"));//申请时间
            userInfoDeal.setGlj(DominoDataImportUtil.exValue(document,"glj"));//地区管理局
            userInfoDeal.setFddbr(DominoDataImportUtil.exValue(document,"fddbr"));//申请人法定代表人姓名
            userInfoDeal.setOfficeaddress2(DominoDataImportUtil.exValue(document,"officeaddress2"));//邮寄地址
            userInfoDeal.setJwfx(DominoDataImportUtil.exValue(document,"jwfx"));//是否从事境外飞行
            userInfoDeal.setAdd_eng(DominoDataImportUtil.exValue(document,"add_eng"));//英文地址
            userInfoDeal.setName_eng(DominoDataImportUtil.exValue(document,"name_eng"));//英文名称
            userInfoDeal.setShtyxybm(DominoDataImportUtil.exValue(document,"shtyxybm"));//社会统一信用编码
            userInfoDeal.setShenfenzheng(DominoDataImportUtil.exValue(document,"shenfenzheng"));//身份证
            userInfoDeal.setShenfenzhengvaliddate(DominoDataImportUtil.exValue(document,"shenfenzhengVaildDate"));//身份证有效期
            userInfoDeal.setManage_name(DominoDataImportUtil.exValue(document,"manage_name"));//账号管理员姓名
            userInfoDeal.setManage_mobile(DominoDataImportUtil.exValue(document,"manage_mobile"));//账号管理员联系方式
            userInfoDeal.setOfficetel(DominoDataImportUtil.exValue(document,"officetel"));//办公电话
            userInfoDeal.setChuanzhen(DominoDataImportUtil.exValue(document,"chuanzhen"));//传真
            userInfoDeal.setManage_sfzhm(DominoDataImportUtil.exValue(document,"manage_sfzhm"));//账号管理员姓名
            userInfoDeal.setQq(DominoDataImportUtil.exValue(document,"qq"));//即时通讯
            userInfoDeal.setAccountname(DominoDataImportUtil.exValue(document,"AcountName"));//账号
            userInfoDeal.setEmail(DominoDataImportUtil.exValue(document,"EMail"));//激活邮箱
            userInfoDeal.setBeizhu(DominoDataImportUtil.exValue(document,"beizhu"));//备注
            userInfoDeal.setDenyreason(DominoDataImportUtil.exValue(document,"DenyReason"));//审核不通过原因
            userInfoDeal.setGlj_jbr(DominoDataImportUtil.exValue(document,"glj_jbr"));//
            userInfoDeal.setAttachmentstorageid(DominoDataImportUtil.exValue(document,"ATTACHMENTSTORAGEID"));
            userInfoDeal.setAttachmentstoragepath(DominoDataImportUtil.exValue(document,"ATTACHMENTSTORAGEPATH"));


        }catch(Exception e){
            e.printStackTrace();
        }
        return userInfoDeal;
    }

}
