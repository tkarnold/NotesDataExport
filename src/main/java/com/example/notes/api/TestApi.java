package com.example.notes.api;

import com.example.notes.entity.AmosNsNcertificateFile;
import com.example.notes.entity.NcApply;
import com.example.notes.util.ConstantData;
import com.example.notes.util.file.FileHttp;
import com.example.notes.util.file.FolderInfoImpl;
import com.taocares.entity.FolderInfoEntity;
import com.taocares.entity.ResponsesEntity;
import lotus.domino.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/test")
public class TestApi {

    @Autowired
    private FolderInfoImpl folderInfo;

    @Autowired
    private FileHttp filehttp;


    @RequestMapping("/test1")
    @ResponseBody
    public void test1(String server, String username, String password) throws NotesException {
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;

        localsession = ctd.setConnection1(server, username, password);
        Registration r = localsession.createRegistration();
        // return flag;
        if (localsession == null) {
            System.out.println("failed to get local session.");
        } else {
            System.out.println("got local session.");
        }
        //------------- try to get database
        lotus.domino.Database db = null;
        try {
            db = localsession.getDatabase("", "ncapply");
        } catch (NotesException e) {
            e.printStackTrace();
        }

        if (db == null) {
            System.out.println("failed to get local db.");
        } else {
            System.out.println("got local db.");
        }
        // 获取视图
        View view = db.getView("按UNID查看");
        // 取数据
        Document doc = null, docx = null;
        List<NcApply> ncApplyList = new ArrayList<>();

        doc = view.getFirstDocument();
        int i = 0;
        while (doc != null && i != 1) {
            NcApply ncApply = new NcApply();
            //String title = (String)doc.getItemValue("Title").get(0);
            //String sqrid = (String)doc.getItemValue("sqrid").get(0);
//                ncApply.setTitle(doc.getItemValue("Title").size()>0?(String)doc.getItemValue("Title").get(0):"");
//                ncApply.setNameEng(doc.getItemValue("name_eng").size()>0?(String)doc.getItemValue("name_eng").get(0):"");
//                ncApply.setSqrid(doc.getItemValue("sqrid").size()>0?(String)doc.getItemValue("sqrid").get(0):"");
//                Vector o = doc.getItemValue("$UpdatedBy");
//                Vector b = doc.getItemValue("$Revisions");
//                System.out.println(doc.getItemValue("ATTACHMENTSTORAGEID").size()>0?(String)doc.getItemValue("ATTACHMENTSTORAGEID").get(0):"");
//            exValue(doc,"$UpdatedBy");
//            exValue(doc,"CustomFormDetail");
//                ncApply.setTitle(exValue(doc,"Title"));
//                ncApply.setNameEng(exValue(doc,"name_eng"));
//                ncApply.setSqrid(exValue(doc,"sqrid"));
            ncApplyList.add(ncApply);
            i++;
            docx = view.getNextDocument(doc);
            //recycle the document we're done with,in the loop body,that's necessary
            //if (doc != null) doc.recycle();
            doc = docx;
            //if (docx != null) docx.recycle();
        }
        System.out.println("db title = " + db.getLastModified());

    }

    @RequestMapping("/test2")
    @ResponseBody
    public void test2(String server, String username, String password) throws NotesException, IOException {
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        //ctd.start();
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;
        String unid = "";
        localsession = ctd.setConnection1(server, username, password);
        Registration r = localsession.createRegistration();
        // return flag;
        if (localsession == null) {
            System.out.println("failed to get local session.");
        } else {
            System.out.println("got local session.");
        }

        //------------- try to get database
        lotus.domino.Database db = null;
        lotus.domino.Database ncdb = null;
        try {
        /* ncdb= localsession.getDatabase("", "ncapply");
         if (ncdb == null) {
            System.out.println("获取国籍证库失败！");
         } else {
            System.out.println("成功获取国籍证库！");
         }
         View ncView = ncdb.getView("按UNID查看");
         Document ncdoc = null, ncdocx = null;
         ncdoc = ncView.getFirstDocument();
         System.out.println(ncdoc.getUniversalID());
         System.out.println(ncdoc.getItemValue("ATTACHMENTSTORAGEID"));*/
        /* while(ncdoc != null){

         //ncdoc.

            ncdocx = ncView.getNextDocument(ncdoc);
            ncdoc=ncdocx;
         }*/

            //读取附件库并导出到本地
            db = localsession.getDatabase("", "attstore_20180518-030035");
        } catch (NotesException e) {
            e.printStackTrace();
        }

        if (db == null) {
            System.out.println("获取附件库失败！");
        } else {
            System.out.println("获取附件库成功！");
        }
        View view = db.getView("AttachmentView");
        Vector v1 = view.getColumnNames();
        System.out.println(v1 + ",,..,");
        Document doc = null, docx = null;
        doc = view.getFirstDocument();
        int j = 0;
        while (doc != null && j != 5) {
            j++;
            System.out.println(doc.getUniversalID() + "！！！！");
            DominoDocument dd = new DominoDocument(doc);
            //dd.
            unid = dd.getUNID();
            System.out.println("！！ " + unid + "  ！！");

            List<EmbeddedObject> list = dd.getAllFiles();
            for (int i = 0; i < list.size(); i++) {
                InputStream is = list.get(i).getInputStream();
                if (saveFile(is, unid + list.get(i).getName())) {
                    System.out.println("第" + (i + 1) + "个文件保存成功！");
                } else {
                    System.out.println("第" + (i + 1) + "个文件保存失败！");
                }
            }
            docx = view.getNextDocument(doc);
            //if (doc != null) doc.recycle();
            doc = docx;
            // if (docx != null) docx.recycle();
        }
        System.out.println("db title = " + db.getLastModified());
        // try {

    }

    public boolean saveFile(InputStream is, String filename) throws IOException {
        boolean flag = false;
        try {
            int index;
            String destination = "E:\\attachmentExport\\" + filename;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(destination);
            while ((index = is.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            is.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 从doc中取值
     *
     * @param doc
     * @param value
     * @return
     */
    public String exValue(Document doc, String value) {
        String eValue = "";
        try {
            if (!"".equals(value) && doc != null) {
                Vector vector = doc.getItemValue(value);
                if (vector.size() == 0) {
                    eValue = "";
                } else if (vector.size() == 1) {
                    eValue = (String) doc.getItemValue(value).get(0);
                } else {
                    Object[] vs = doc.getItemValue(value).toArray();
                    for (Object v : vs) {
                        eValue += v + ",";
                    }
                }
            }
        } catch (NotesException e) {
            e.printStackTrace();
        }
        System.out.println(eValue);
        return eValue;
    }

    /**
     * 附件上传测试
     *
     * @return
     */
    @RequestMapping("/testfile")
    @ResponseBody
    public String uploadfiletest() throws NotesException, IOException {
        String server = "219.143.231.102:1352";
        String username = "admin";
        String password = "a";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        //ctd.start();
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;
        String unid = "";
        localsession = ctd.setConnection1(server, username, password);
        Registration r = localsession.createRegistration();
        // return flag;
        if (localsession == null) {
            System.out.println("failed to get local session.");
        } else {
            System.out.println("got local session.");
        }

        Map<String, Object> fileUploadResult = null;

        //------------- try to get database
        lotus.domino.Database db = null;
        db = localsession.getDatabase("", "attstore_20180518-030035");

        if (db == null) {
            System.out.println("获取附件库失败！");
        } else {
            System.out.println("获取附件库成功！");
        }
        View view = db.getView("AttachmentView");
        Document doc = null, docx = null;
        String attachmentId = "FC1F43327A85541A482582A50006B710";
        // doc = view.getFirstDocument();
        //根据附件的UNID获取对应的对象
        doc = view.getDocumentByKey(attachmentId);
        // System.out.println(doc.getUniversalID() + "！！！！");
        DominoDocument dd = new DominoDocument(doc);
        //此处unid应该与业务数据提供的unid对应
        unid = dd.getUNID();
        System.out.println("！！ " + unid + "  ！！");
        //获取对应的所有附件
        List<EmbeddedObject> list = dd.getAllFiles();
        String dataYwlsh = "国籍申请0318";
        String zch = "20200317";
        //dataYwlsh = doc.getItemValueString("ywlsh");//获取业务流水号
        //zch = doc.getItemValueString("zch");//获取注册号
        //需要创建的文件夹名称
        String foldname = dataYwlsh + zch;
        //创建的文件夹id
        //String foldid = createFolder(foldname);
        String foldid = "817637";
        //将UNID对应的多个附件，循环上传，并将返回值插入到附件信息表中
        for (int i = 0; i < list.size(); i++) {
            InputStream is = list.get(i).getInputStream();
            String path = "E:\\attachmentExport\\"+list.get(i).getName();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(path);
            while ((index = is.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            is.close();
            File file = new File(path);//此处需注意
            fileUploadResult = filehttp.uploadFile(file, list.get(i).getName(), "R1.0", foldid);
            //=============将返回的附件信息插入到业务数据中================
            //ncApply.setFileid();
            //将返回的附件信息插入到附件信息表中
            if (insertFile(fileUploadResult)) {
                System.out.println("插入附件表成功！");
            } else {
                System.out.println("插入附件表失败！");
            }

        }
        return "上传成功！";
    }



        public String createFolder(String foldName) {
            String path = ConstantData.project + "/" + ConstantData.nationalitycertificate;//国籍证首次申请
            //foldName = foldName + UUID.randomUUID().toString();
            FolderInfoEntity folder = folderInfo.createFolder(foldName, "", path, "0");
            return folder.getId().toString();
        }


    /**
     * 将附件信息插入到附件信息表中
     *
     * @param fileUploadResult
     * @return
     */
    public boolean insertFile(Map<String, Object> fileUploadResult) {
        boolean falg = true;
        try {
            AmosNsNcertificateFile annf = new AmosNsNcertificateFile();
            annf.setId(fileUploadResult.get("id").toString());
            annf.setFileName(fileUploadResult.get("name").toString());
            annf.setFilePath(fileUploadResult.get("path").toString());
            annf.setFileStatus(fileUploadResult.get("status").toString());
            annf.setFileTypeId(fileUploadResult.get("type").toString());
             //annf.set(fileUploadResult.get("updateTime"));
            //annf.setUploaddate();
            //annf.setUploaduser(fileUploadResult.get("updateUser").toString());
            annf.setTablename(fileUploadResult.get("fileId").toString());
            annf.insert();
        } catch (Exception e) {
            falg = false;
            e.printStackTrace();

        }
        return falg;
    }
    @RequestMapping("/testfolderinfo")
    @ResponseBody
    public String testqueryfolder(){
        ResponsesEntity<FolderInfoEntity> folderInfoEntity = folderInfo.querySubFolderInfos("测试文件夹",null);
        if(folderInfoEntity.getStatus().toString().equals("0"))
            return folderInfoEntity.getStatus().toString();
        else
            return folderInfoEntity.getData().getId().toString();
    }

}

