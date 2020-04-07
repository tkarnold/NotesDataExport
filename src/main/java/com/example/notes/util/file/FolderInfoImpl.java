package com.example.notes.util.file;

import com.taocares.dto.SimpleKeyValueDto;
import com.taocares.entity.FileInfoEntity;
import com.taocares.entity.FolderInfoEntity;
import com.taocares.entity.ResponsesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FolderInfoImpl
{
    @Autowired
    FolderInfoClient folderInfoClient;

    /**
     * 创建文件夹
     * @param foldName
     * @param foldDesc
     * @param path
     * @param userId
     * @return
     */
    public FolderInfoEntity createFolder(String foldName,String foldDesc,String path,String userId){
        //创建文件夹对象
        FolderInfoEntity entity = new FolderInfoEntity();
        entity.setName(foldName);
        entity.setDescription(foldDesc);
        entity.setPath(path);
        entity.setCreateUser(userId);
        List<FolderInfoEntity> children = new ArrayList<>();
        entity.setChildren(children);
        FileInfoEntity fileDetail = new FileInfoEntity();
        entity.setFileDetail(fileDetail);
        List<SimpleKeyValueDto> folderPathInfo = new ArrayList<>();
        entity.setFolderPathInfo(folderPathInfo);
        //返回结果
        ResponsesEntity<FolderInfoEntity> responsesEntity = folderInfoClient.createFolder(entity);
        FolderInfoEntity folderInfoEntity  = null;
        if("1".equals(responsesEntity.getStatus())){
            folderInfoEntity = responsesEntity.getData();
        }
        return folderInfoEntity;
    }

    /**
     * 更新文件夹信息
     * @param foldId
     * @param foldName
     * @param foldDesc
     * @return
     */
    public boolean updateFolderInfo(long foldId,String foldName,String foldDesc){
        //创建文件夹对象
        FolderInfoEntity entity = new FolderInfoEntity();
        entity.setId(foldId);
        entity.setName(foldName);
        entity.setDescription(foldDesc);
        List<FolderInfoEntity> children = new ArrayList<>();
        entity.setChildren(children);
        FileInfoEntity fileDetail = new FileInfoEntity();
        entity.setFileDetail(fileDetail);
        List<SimpleKeyValueDto> folderPathInfo = new ArrayList<>();
        entity.setFolderPathInfo(folderPathInfo);
        //返回结果
        boolean result = false;//默认更新不成功
        ResponsesEntity responsesEntity = folderInfoClient.updateFolderInfo(entity);;
        if("1".equals(responsesEntity.getStatus())){
            result = true;
        }
        return result;
    }

    /**
     * 删除文件
     * @param fileId
     * @param userId
     * @return
     */
    public boolean deleteFolder(String fileId,String userId){
        boolean result = false;//删除失败
        //组装参数
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFolderIds(fileId);
        fileInfo.setUserId(userId);

        WebResult webResult = folderInfoClient.deleteFolder(fileInfo);
        if("1".equals(webResult.getStatus())){
            result = true;//删除成功
        }
        return  result;
    }

    /**
     *查询文件夹信息
     */
    public  ResponsesEntity<FolderInfoEntity> querySubFolderInfos(String folderName,String parentId){

        FolderInfoEntity folderInfoEntity  = null;

        ResponsesEntity<FolderInfoEntity> responsesEntity = folderInfoClient.querySubFolderInfos(folderName,parentId);

       // folderInfoEntity = responsesEntity.getData();

        //folderInfoEntity.setStatus(respon.toString()sesEntity.getStatus());
        return responsesEntity;
    }

}
