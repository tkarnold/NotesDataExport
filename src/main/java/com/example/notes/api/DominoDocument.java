package com.example.notes.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lotus.domino.DateTime;
import lotus.domino.Document;
import lotus.domino.EmbeddedObject;
import lotus.domino.Item;
import lotus.domino.NotesException;
import lotus.domino.RichTextItem;

/**
 * DominoDocument Domino文档封装类，提供文档中各种数据的访问方法
 *
 * @author zhanzhengqiang
 * @version 1.0 2011-5-16
 */
public class DominoDocument {

    /**
     * 日志对象
     */
    private static Log log = LogFactory.getLog(DominoDocument.class);

    /**
     * Domino文档
     */
    private Document document;

    /**
     * 构造方法
     *
     * @param document
     */
    public DominoDocument(Document document) {
        this.document = document;
    }

    /**
     * 获取字符串域（单值域）值
     *
     * @param itemName
     * @return 没有返回""
     */
    public String getString(String itemName) {
        String retValue = "";
        try {
            if (document.hasItem(itemName)) {
                retValue = document.getItemValueString(itemName);
            } else {
                log.debug("域" + itemName + "不存在");
            }
        } catch (NotesException e) {
            log.error("获取域" + itemName + "的值失败：" + e.getMessage());
            retValue = "";
        }
        if (log.isDebugEnabled()) {
            log.debug("获取到域[" + itemName + "]的值为：" + retValue);
        }
        return retValue;
    }

    /**
     * 获取字符串列表（多值域）值
     *
     * @param itemName
     * @return 域值列表
     */
    public List getStringList(String itemName) {
        List retValue = new ArrayList();
        try {
            if (document.hasItem(itemName)) {
                log.debug("多值域域值列表：");
                Vector retItem = document.getItemValue(itemName);
                for (int i = 0; i < retItem.size(); i++) {
                    if (log.isDebugEnabled()) {
                        log.debug("域值" + (i + 1) + "为：" + retItem.get(i));
                    }
                    retValue.add(retItem.get(i));
                }
            } else {
                log.debug("域" + itemName + "不存在");
            }
        } catch (NotesException e) {
            log.error("获取域" + itemName + "的值失败：" + e.getMessage());
        }
        return retValue;
    }

    /**
     * 获取日期时间域值
     *
     * @param itemName
     * @return
     */
    public Date getDateTime(String itemName) {
        Date retValue = null;
        try {
            if (document.hasItem(itemName)) {
                Vector retItem = document.getItemValueDateTimeArray(itemName);
                if (!retItem.isEmpty()) {
                    DateTime dateTime = (DateTime) retItem.get(0);
                    retValue = dateTime.toJavaDate();
                }
            } else {
                log.debug("域" + itemName + "不存在");
            }
        } catch (NotesException e) {
            log.error("获取日期时间域" + itemName + "的值失败：" + e.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("获取到日期时间域[" + itemName + "]的值为：" + retValue);
        }
        return retValue;
    }

    /**
     * 获取整数域值
     *
     * @param itemName
     * @return
     */
    public int getInteger(String itemName) {
        int retValue = 0;
        try {
            if (document.hasItem(itemName)) {
                retValue = document.getItemValueInteger(itemName);
            } else {
                log.debug("域" + itemName + "不存在");
            }
        } catch (NotesException e) {
            log.error("获取整数域" + itemName + "的值失败：" + e.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("获取到整数域[" + itemName + "]的值为：" + retValue);
        }
        return retValue;
    }

    /**
     * 根据域名获取域中存放的文件
     *
     * @param fieldName
     * @return
     */
    public EmbeddedObject getFileByFieldName(String fieldName) {
        EmbeddedObject retValue = null;
        try {
            if (document.hasItem(fieldName)) {
                Item item = document.getFirstItem(fieldName);
                if (item.getType() == Item.RICHTEXT) {
                    // 转换为RichTextItem
                    RichTextItem rtfItem = (RichTextItem) item;
                    // 取EmbeddedObject
                    Vector embeddedObjects = rtfItem.getEmbeddedObjects();
                    for (int i = 0; i < embeddedObjects.size(); i++) {
                        EmbeddedObject obj = (EmbeddedObject) embeddedObjects.get(i);
                        if (obj.getType() == EmbeddedObject.EMBED_ATTACHMENT) {
                            retValue = obj;
                            break;
                        }
                    }
                }
            } else {
                log.debug("RTF域[" + fieldName + "]不存在");
            }
        } catch (NotesException e) {
            log.error("获取RTF域" + fieldName + "的值失败：" + e.getMessage());
        }

        // 输出调试信息
        if (log.isDebugEnabled()) {
            if (retValue != null) {
                try {
                    log.debug("获取RTF域[" + fieldName + "]对应文件为：" + retValue.getName());
                } catch (NotesException e) {
                }
            } else {
                log.debug("RTF域[" + fieldName + "]暂时没有文件");
            }
        }

        return retValue;
    }

    /**
     * 根据文件名获取文件
     *
     * @param fileName
     * @return
     */
    public EmbeddedObject getFileByName(String fileName) {
        EmbeddedObject retValue = null;
        try {
            if (document.hasEmbedded()) {
                retValue = document.getAttachment(fileName);
            }
        } catch (NotesException e) {
            log.error("获取文件[" + fileName + "]出错：" + e.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("文件[" + fileName + "]不存在");
        }
        return retValue;
    }

    /**
     * 获取所有文件, 根据文件名获取附件，如果文件名含特殊字符会导致获取文件为null
     * 如文件："附件3：关于中共×××党支部党员大会会议选举结果的报告.doc"
     *
     * @return
     */
    public List getAllFiles() {
        List retValue = new ArrayList();
        try {
            if (document.hasEmbedded()) {
                Vector items = document.getItems();
                for (int i = 0; i < items.size(); i++) {
                    Item item = (Item) items.get(i);
                    // 如果不是附件则跳过
                    if (item.getType() != Item.ATTACHMENT) {
                        continue;
                    }
                    // 根据附件名称获取附件对象
                    String attachName = item.getValueString();
                    if (log.isDebugEnabled()) {
                        log.debug("文件名称：" + attachName);
                    }
                    EmbeddedObject attach = document.getAttachment(attachName);
                    if (attach != null) {
                        retValue.add(attach);
                    } else {
                        log.error("获取文件[" + attachName + "]失败");
                    }
                }
            } else {
                log.debug("文档中没有引入文件");
            }
        } catch (NotesException e) {
            log.error("获取文档所有附加文件出错:" + e.getMessage());
        }

        // 输出调试信息
        if (log.isDebugEnabled()) {
            log.debug("获取到文件列表如下：");
            for (int i = 0; i < retValue.size(); i++) {
                EmbeddedObject obj = (EmbeddedObject) retValue.get(i);
                try {
                    log.debug("第" + (i + 1) + "个文件：" + obj.getName());
                } catch (NotesException e) {
                }
            }
        }
        return retValue;
    }

    /**
     * 获取所有文件
     *
     * @return
     */
    public List getAllFiles2() {
        List retValue = new ArrayList();
        HashMap fileNames = new HashMap();
        try {
            if (document.hasEmbedded()) {
                Vector items = document.getItems();
                for (int i = 0; i < items.size(); i++) {
                    Item item = (Item) items.get(i);
                    // 如果不是RichTextItem则跳过
                    if (item.getType() != Item.RICHTEXT) {
                        continue;
                    }
                    Vector embeddedObjects = ((RichTextItem) item).getEmbeddedObjects();
                    if (embeddedObjects.size() <= 0) {
                        log.debug("域[" + item.getName() + "]不包含文件");
                    }

                    for (int k = 0; k < embeddedObjects.size(); k++) {
                        EmbeddedObject embeddedObject = (EmbeddedObject) embeddedObjects.get(k);
                        if (embeddedObject.getType() == EmbeddedObject.EMBED_ATTACHMENT) {
                            // 判断是否已经添加，没有添加
                            String gettedFileName = embeddedObject.getName();
                            if (fileNames.get(gettedFileName) == null) {
                                retValue.add(embeddedObject);
                                fileNames.put(gettedFileName, "");
                                if (log.isDebugEnabled()) {
                                    log.debug("获取到文件:" + gettedFileName);
                                }
                            }
                        }
                    }
                }
            } else {
                log.debug("文档中没有引入文件");
            }
        } catch (NotesException e) {
            log.error("获取文档所有附加文件出错:" + e.getMessage());
        }

        // 输出调试信息
        if (log.isDebugEnabled()) {
            log.debug("获取到文件列表如下：");
            for (int i = 0; i < retValue.size(); i++) {
                EmbeddedObject obj = (EmbeddedObject) retValue.get(i);
                try {
                    log.debug("第" + (i + 1) + "个文件：" + obj.getName());
                } catch (NotesException e) {
                }
            }
        }
        return retValue;
    }

    /**
     * 获取文档UNID
     *
     * @return 如果不存在则返回null
     */
    public String getUNID() {
        String unid = null;
        try {
            unid = document.getUniversalID();
        } catch (NotesException e) {
            log.error("获取文档UNID出错");
        }
        return unid;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }
}

