package com.example.notes.api;

import lotus.domino.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class ConnectToDomino {

    @Value("${dominoFilePath:#{null}}")
    private String dominoFilePath;

    lotus.domino.Session Session = null;
    String strServer = null;
    String strUserName = null;
    String strPassWord = null;

    //IOR:01016a012900000049444c3a6c6f7475732f646f6d696e6f2f636f7262612f494f626a6563745365727665723a312e3000000000010000000000000074000000010101010f0000003136392e3235342e32352e3234350064acf63a2f310000000438353235363531612d656336382d313036632d656565302d303037653264323233336235004c6f7475734e4f490100014c494301000000010000001400000001016a0101000105000000000001010000000000
    //构造函数
    public ConnectToDomino(String Server, String User, String Pass) {
        strServer = Server;
        strUserName = User;
        strPassWord = Pass;
        //setConnection1(Server,User,Pass);
    }

    public ConnectToDomino() {

    }

    //建立连接
    public boolean setConnection() {
        if (strServer == null || strUserName == null || strPassWord == null) return false;
        try {
            System.out.println("用户登录：" + strServer + "/t" + strUserName);
            String IOR = GetIOR();
            //String IOR = "01016a012900000049444c3a6c6f7475732f646f6d696e6f2f636f7262612f494f626a6563745365727665723a312e3000000000010000000000000074000000010101010f0000003136392e3235342e32352e3234350064acf63a2f310000000438353235363531612d656336382d313036632d656565302d303037653264323233336235004c6f7475734e4f490100014c494301000000010000001400000001016a0101000105000000000001010000000000";
            if (IOR != "")
                System.out.println("成功取得IOR，开始创建Session.....");
            else
                System.out.println("获取IOR失败！");
            Session  =  NotesFactory.createSession(strServer,strUserName,strPassWord);
            //Session  =  NotesFactory.createSession(strServer,"","");
            //Session  =  NotesFactory.createSessionWithIOR(IOR);  //匿名
            //Session = NotesFactory.createSessionWithIOR(IOR, strUserName, strPassWord);  //用户身份

            System.out.println("成功登陆,登录信息：\n服务器名称：" + ((lotus.domino.Session) Session).getServerName() + "/n登录用户名：" +

                    ((lotus.domino.Session) Session).getUserName() + "/n");

            //  System.out.println("1:"+  ((lotus.domino.Session) Session).);
        /*    System.out.println("2:"+);
            System.out.println("3:"+);
            System.out.println("4:"+);
            System.out.println("5:"+);
            System.out.println("6:"+);*/


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //建立连接
    public lotus.domino.Session setConnection1(String server, String username, String password) {
        //if(strServer==null  ||  strUserName==null  ||  strPassWord==null)  return  false;
        try {
            System.out.println("用户登录：" + server + "\t" + username);
            String IOR = GetIOR();
            //String IOR = "01016a012900000049444c3a6c6f7475732f646f6d696e6f2f636f7262612f494f626a6563745365727665723a312e3000000000010000000000000074000000010101010f0000003136392e3235342e32352e3234350064acf63a2f310000000438353235363531612d656336382d313036632d656565302d303037653264323233336235004c6f7475734e4f490100014c494301000000010000001400000001016a0101000105000000000001010000000000";
            if (IOR != "")
                System.out.println("成功取得IOR，开始创建Session.....");
            else
                System.out.println("获取IOR失败！");
            //Session  =  NotesFactory.createSession(IOR,strUserName,strPassWord);
            //Session  =  NotesFactory.createSession(strServer);
            //Session  =  NotesFactory.createSessionWithIOR(IOR);  //匿名

            Session = NotesFactory.createSessionWithIOR(IOR, username, password);  //用户身份

            System.out.println("成功登陆,登录信息：/n服务器名称：" + ((lotus.domino.Session) Session).getServerName() + "/n登录用户名：" +

                    ((lotus.domino.Session) Session).getUserName() + "\n");

            //  System.out.println("1:"+  ((lotus.domino.Session) Session).);
        /*    System.out.println("2:"+);
            System.out.println("3:"+);
            System.out.println("4:"+);
            System.out.println("5:"+);
            System.out.println("6:"+);*/


        } catch (Exception e) {
            e.printStackTrace();
            // return  false;
        }
        return Session;
    }

    //获取IOR
    public String GetIOR() {
        InputStream bin;
        String Result = "";
        int character;
        try {
            //URL url = new URL("http://" + strServer + "/static/diiop_ior.txt");
            //URL url = new URL("D:\\Program Files\\IBM\\Domino\\data\\domino\\html\\diiop_ior.txt");
            //bin = url.openStream();
            bin = new FileInputStream(new File("D:\\Program Files\\IBM\\Domino\\data\\domino\\html\\diiop_ior.txt"));
//            bin = new FileInputStream(new File("F:\\IBM\\diiop_ior.txt"));
//            bin = new FileInputStream(new File(dominoFilePath));
            while ((character = bin.read()) > 0) {
                Result += (char) character;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    //检索文档
    public void Search(String SearchDB, String SearchFormular) {
        try {

            Database db = Session.getDatabase(Session.getServerName(), SearchDB);
            System.out.println("Searching:" + SearchFormular + "...");
            DocumentCollection dc = db.search(SearchFormular);
            Document docResult = dc.getFirstDocument();
            if (docResult == null) return;
            int i;
            int j = 0;
            while (docResult != null) {
                System.out.println(docResult.getItemValueString("FullName"));
                docResult = dc.getNextDocument();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建文档
    public boolean CreateNewDoc(String DBName, String formName, String[] FieldList, String[] ValueList, String AgentName) {
        try {
            Database db = Session.getDatabase(Session.getServerName(), DBName);
            Document doc = db.createDocument();
            Item item = doc.appendItemValue("form", formName);
            doc.computeWithForm(true, false);
            int i;
            for (i = 0; i < FieldList.length; i++) {
                item = doc.replaceItemValue(FieldList[i], ValueList[i]);
            }
            doc.save();
            if (AgentName != null || AgentName != "") {
                Agent agent = db.getAgent(AgentName);
                if (agent != null) {
                    agent.run(doc.getNoteID());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    //关闭
    public void close() {
        try {
            Session.recycle();
            Session = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
