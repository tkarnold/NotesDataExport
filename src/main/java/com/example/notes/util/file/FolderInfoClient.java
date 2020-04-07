package com.example.notes.util.file;


import com.taocares.entity.FolderInfoEntity;
import com.taocares.entity.ResponsesEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="sfss",url="http://192.168.163.157:6001")
@RequestMapping("/folderInfo")
@Component
public interface FolderInfoClient {
    /**
     * 创建文件夹
     * @param folderInfo
     * @return
     */
    @PostMapping(value="/createFolder")
    @ResponseBody
    ResponsesEntity<FolderInfoEntity> createFolder(@RequestBody FolderInfoEntity folderInfo);

    /**
     * 修改文件夹信息
     * @param folderInfo
     * @return
     */
    @PostMapping(value="/updateFolderInfo")
    @ResponseBody
    ResponsesEntity<FolderInfoEntity> updateFolderInfo(@RequestBody FolderInfoEntity folderInfo);

    /**
     * 删除文件夹信息
     * @param fileInfo
     * @return
     */
    @PostMapping("/deleteFolder")
    WebResult deleteFolder(@RequestBody FileInfo fileInfo);

    /**
     *
     * 查询文件夹信息根据文件名称
     * @param folderName
     * @return
     */
    @RequestMapping(value = "/querySubFolderInfos")
    @ResponseBody
    ResponsesEntity<FolderInfoEntity> querySubFolderInfos(@RequestParam("folderName") String folderName,@RequestParam("parentId") String parentId);
/*
    *//**
     *
     * 查询文件夹信息根据文件名称
     * @param folderName
     * @return
     *//*
    @PostMapping(value = "/querySubFolderInfos")
    @ResponseBody
    ResponsesEntity<FolderInfoEntity> querySubFolderInfos(String folderName,String parentId);*/
}
