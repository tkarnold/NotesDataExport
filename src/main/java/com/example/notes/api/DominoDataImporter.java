package com.example.notes.api;

/**
 * DominoDataImporter domino数据处理接口，负责将domino数据存入到J2EE系统
 *
 * @version 1.0 2011-5-16
 * @author zhanzhengqiang
 */
public interface DominoDataImporter {

    /**
     * 文档处理
     * @param document 待处理文档
     * @return true 成功，false失败
     */
    public boolean dealDocument(DominoDocument document);
}
