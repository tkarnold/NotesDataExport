package com.example.notes.util.file;


import com.alibaba.fastjson.JSONObject;
import com.example.notes.api.DominoDocument;
import com.example.notes.util.ConstantData;
import com.taocares.entity.FolderInfoEntity;
import com.taocares.entity.ResponsesEntity;
import lotus.domino.Document;
import lotus.domino.EmbeddedObject;
import lotus.domino.NotesException;
import lotus.domino.View;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
public class FileHttp {
    @Autowired
    FileServerProperties fileServerProperties;
    @Autowired
    private FolderInfoImpl folderInfo;

    public  Map<String,Object> uploadFile(File file, String fileName,String version,String parentId) {
        //返回结果
        Map<String,Object> resultMap = null;

        String url = fileServerProperties.getServerPath()+"/space/uploadFile";//服务端要调用的外部接口
        //httpclients构造post请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //HttpMultipartMode.RFC6532参数的设定是为避免文件名为中文时乱码
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
            builder.addBinaryBody("file",file);
            ContentType strContent=ContentType.create("text/plain",Charset.forName("UTF-8"));
            builder.addTextBody("fileName",fileName,strContent);//post请求中的其他参数
            builder.addTextBody("version",version);//post请求中的其他参数
            builder.addTextBody("parentId",parentId);//post请求中的其他参数
            HttpEntity entity = builder.build();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
               String retStr = EntityUtils.toString(httpEntity, "UTF-8");
                WebResult webResult =  JSONObject.parseObject(retStr,WebResult.class);
                if("1".equals(webResult.getStatus())){
                   // resultMap = new HashMap<>();
                    resultMap = (Map<String,Object>)webResult.getData();
                }
            }
        } catch (Exception e) {
        }finally {//处理结束后关闭httpclient的链接
            try {
                // 释放资源
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }
    /**
     * 附件公共方法
     *
     * @param attachmentPath
     * @param ViewDb
     * @param attachmentId
     * @param dataYwlsh
     * @param zch
     * @param localsession
     * @return
     * @throws NotesException
     * @throws IOException
     */
    public List<Map<String, Object>> getFiles(lotus.domino.Session localsession,String ViewDb,String attachmentPath,  String attachmentId, String dataYwlsh, String zch,String parentFoldId) throws NotesException, IOException {
        List<Map<String, Object>> fileUploadResultlist = new ArrayList<>();
        Map<String, Object> fileUploadResult = null;
        lotus.domino.Database db = localsession.getDatabase("", attachmentPath);
        String foldname = "default";
        if (db == null || db.getViews().size() == 0) {
            System.out.println("获取附件库失败！");
        } else {
            System.out.println("获取附件库成功！");
        }
        View view = db.getView(ViewDb);//viewColumns.size()
        if (view.getColumnCount() == 0) {
            System.out.println("未能找到视图！");
        }
        Vector v1 = view.getColumnNames();
        System.out.println(v1 + ",,..,");
        Document doc = null, docx = null;
        // doc = view.getFirstDocument();
        //根据附件的UNID获取对应的对象
        doc = view.getDocumentByKey(attachmentId);
        // System.out.println(doc.getUniversalID() + "！！！！");
        DominoDocument dd = new DominoDocument(doc);
        //此处unid应该与业务数据提供的unid对应
        //String unid = dd.getUNID();
        // System.out.println("！！ " + unid + "  ！！");
        //获取对应的所有附件
        List<EmbeddedObject> list = dd.getAllFiles();
        //需要创建的文件夹名称
        foldname = dataYwlsh + zch;
        String filename = "default";
        String foldid="";
        //查询文件夹是否已经存在
        ResponsesEntity<FolderInfoEntity> folder = folderInfo.querySubFolderInfos(foldname,null);
        if(folder.getStatus().toString().equals("0"))
            //创建的文件夹id
            foldid= createFolder(foldname,parentFoldId);
        else
            foldid = folder.getData().getId().toString();
        //String foldid = "817637";
        //将UNID对应的多个附件，循环上传，并将返回值插入到附件信息表中
        for (int i = 0; i < list.size(); i++) {
            InputStream is = list.get(i).getInputStream();
            String path = "E:\\attachmentExport\\" + list.get(i).getName();
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
            filename = list.get(i).getName();
            filename = filename.substring(0, filename.lastIndexOf("."));
            fileUploadResult = uploadFile(file, filename, "R1.0", foldid);
            if(fileUploadResult !=null){
                System.out.println("附件 "+fileUploadResult.get("name")+" 上传成功！第"+(i+1)+"条数据 ");
                fileUploadResultlist.add(fileUploadResult);
            }else{
                System.out.println("附件 "+fileUploadResult.get("name")+" 上传失败！第"+(i+1)+"条数据 ");
            }

            if (file.exists()) {
                file.delete();
            }
        }
        return fileUploadResultlist;
    }

    /**
     * 创建文件夹
     */
//    @RequestMapping("/createFolder")
//    @ResponseBody
    public String createFolder(String foldName,String parentFoldId) {
        String path = ConstantData.project + "/" + parentFoldId;//国籍证首次申请
        //foldName = foldName + UUID.randomUUID().toString();
        FolderInfoEntity folder = folderInfo.createFolder(foldName, "", path, "0");
        return folder.getId().toString();
    }

    /**
     * 修改文件夹
     */
   /* @RequestMapping("/updateFolderInfo")
    @ResponseBody*/
    public boolean updateFolderInfo(long foldId, String foldName, String foldDesc) {
        boolean flag = folderInfo.updateFolderInfo(foldId, foldName, foldDesc);
        return flag;
    }

    /**
     * 更新文件夹信息
     */
    public String querySubFolderInfos(String folderName,String parentId) {

        ResponsesEntity<FolderInfoEntity> folder = folderInfo.querySubFolderInfos(folderName,parentId);

        return folder.getStatus().toString();

    }
}
