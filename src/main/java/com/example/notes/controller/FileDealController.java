package com.example.notes.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.example.notes.api.ConnectToDomino;
import com.example.notes.entity.*;
import com.example.notes.service.*;
import com.example.notes.util.ConstantData;
import com.example.notes.util.file.FileHttp;
import lotus.domino.NotesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/filedeal")
public class FileDealController {
    @Autowired
    private IAmosNsAirworthyInfoService amosNsAirworthyInfoService;
    @Autowired
    private IAmosNsAirworthyFileService amosNsAirworthyFileService;
    @Autowired
    private IAmosNsInkjetInfoService amosNsInkjetInfoService;

    @Autowired
    private FileHttp filehttp;

    @Autowired
    private IAmosNsNcertificateInfoFileService amosNsNcertificateInfoFileService;

    @Autowired
    private IAmosNsNcertificateInfoService amosNsNcertificateInfoService;

    @RequestMapping("/fileupload")
    @ResponseBody
    public Boolean updateData() throws NotesException, IOException {
        boolean flag = true;
        String server = "localhost:8086";
        String username = "tkarn";
        String password = "123456";
        ConnectToDomino ctd = new ConnectToDomino(server, username, password);
        // boolean flag =  ctd.setConnection();
        lotus.domino.Session localsession = null;

        localsession = ctd.setConnection1(server, username, password);
        try {
            Wrapper<AmosNsNcertificateInfoFile> wrapper = null;
            List<AmosNsNcertificateInfoFile> fileList = amosNsNcertificateInfoFileService.selectList(wrapper);
            int count =1;
            for (AmosNsNcertificateInfoFile file : fileList) {
                    //Map<String, Object> fileUploadResult = new HashMap<>();
                    List<Map<String, Object>> fileUploadResultlist = new ArrayList<>();
                    if (!file.getAttachmentpath().equals("") && !file.getAttachmentid().equals("") && file.getAttachmentpath() != null && file.getAttachmentid() != null && file.getAttachmentpath().equals("weboa/common/attstore_20181117-030024.nsf")) {
                        if(count >=7) {
                        //if (fileUploadResult != null) {
                        //insertFile(fileUploadResult, file.getInfoid());
                        String attachmentPath = file.getAttachmentpath();
                        attachmentPath = attachmentPath.substring(attachmentPath.lastIndexOf("/") + 1, attachmentPath.length());
                       int count1 =1;
                        if (file.getType().equals("2")) {

                            fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, file.getAttachmentid(), file.getSn(), file.getAirnumber(), ConstantData.airworth + "");
                            for (Map<String, Object> fileUploadResult : fileUploadResultlist) {
                                //更改主表
                                AmosNsAirworthyInfo airworthyInfo = amosNsAirworthyInfoService.selectById(file.getInfoid());
                                airworthyInfo.setFolderId(fileUploadResult.get("parentId").toString());
                                amosNsAirworthyInfoService.updateById(airworthyInfo);
                                //插入附件表
                                AmosNsAirworthyFile airworthyFile = new AmosNsAirworthyFile();
                                airworthyFile.setId(IdWorker.get32UUID());
                                airworthyFile.setBusinessid(file.getInfoid());
                                airworthyFile.setFileName(fileUploadResult.get("name").toString());
                                airworthyFile.setFilePath(fileUploadResult.get("id").toString());
                                airworthyFile.setFileStatus("1");
                                airworthyFile.setUploaduser("0401");
                                airworthyFile.setTablename(fileUploadResult.get("fileId").toString());
                                amosNsAirworthyFileService.insert(airworthyFile);
                                System.out.println("第" + count1 + "附件相关数据处理完毕！业务ID为："+file.getInfoid());
                                count1++;
                            }
                        } else if (file.getType().equals("1")) {
                            fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, file.getAttachmentid(), file.getSn(), file.getAirnumber(), ConstantData.nationalitycertificate + "");
                            for (Map<String, Object> fileUploadResult : fileUploadResultlist) {
                                //修改主表

                                AmosNsNcertificateInfo ncertificateInfo = amosNsNcertificateInfoService.selectById(file.getInfoid());
                                ncertificateInfo.setFolderId(fileUploadResult.get("parentId").toString());
                                amosNsNcertificateInfoService.updateById(ncertificateInfo);
                                //插入附件
                                AmosNsNcertificateFile annf = new AmosNsNcertificateFile();
                                annf.setId(IdWorker.get32UUID());
                                annf.setBusinessid(file.getInfoid());
                                annf.setFileName(fileUploadResult.get("name").toString());
                                annf.setFilePath(fileUploadResult.get("id").toString());
                                annf.setFileStatus("1");
                                annf.setUploaduser("0401");
                                annf.setTablename(fileUploadResult.get("fileId").toString());
                                annf.insert();
                                System.out.println("第" + count1 + "附件相关数据处理完毕！业务ID为："+file.getInfoid());
                                count1++;
                            }
                        } else if (file.getType().equals("3")) {
                            fileUploadResultlist = filehttp.getFiles(localsession, "AttachmentView", attachmentPath, file.getAttachmentid(), file.getSn(), file.getAirnumber(), ConstantData.inkjet + "");
                            for (Map<String, Object> fileUploadResult : fileUploadResultlist) {
                                //修改主表
                                AmosNsInkjetInfo inkjetInfo = amosNsInkjetInfoService.selectById(file.getInfoid());
                                inkjetInfo.setFoldId(fileUploadResult.get("parentId").toString());
                                amosNsInkjetInfoService.updateById(inkjetInfo);
                                //插入附件
                                AmosNsInkjetFile annf = new AmosNsInkjetFile();
                                annf.setId(IdWorker.get32UUID());
                                annf.setBusinessid(file.getInfoid());
                                annf.setFileName(fileUploadResult.get("name").toString());
                                annf.setFilePath(fileUploadResult.get("id").toString());
                                annf.setFileStatus("1");
                                annf.setUploaduser("0401");
                                annf.setTablename(fileUploadResult.get("fileId").toString());
                                annf.insert();
                                System.out.println("第" + count1 + "附件相关数据处理完毕！业务ID为："+file.getInfoid());
                                count1++;
                            }
                        }

                        System.out.println("已成功处理第" + count + "条数据！数据ID为：" + file.getId());
                    }
                        count++;
                }


            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
