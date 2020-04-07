package com.example.notes.util;

import com.example.notes.api.DominoDataImporter;
import com.example.notes.api.DominoDocument;
import com.example.notes.api.ImportParams;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.DocumentCollection;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.View;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * DominoDataImportUtil Domino数据导入工具类，
 *     负责连接Domino数据库，获取文档记录，文档的处理由DominoDataImporter接口实现类完成
 *
 * @version 1.0 2011-5-16
 * @author zhanzhengqiang
 */
public class DominoDataImportUtil {

    /**
     * 日志对象
     */
    private static Log log = LogFactory.getLog(DominoDataImportUtil.class);

    /**
     * 导入失败文档数上限，超过上限导入终止
     */
    private static final int IMPORT_FAIL_NUM_LIMIT = 10;

    /**
     * 私有构造方法
     */
    private DominoDataImportUtil() {
    }

    /**
     * 基于查询条件做数据库导入，如果没有查询条件，则导入整个数据库
     *
     * @param importParams       导入参数
     * @param dominoDataImporter 文档导入处理对象，负责文档导入的业务处理
     * @throws Exception
     */
    public static int[] importByCondition(ImportParams importParams, DominoDataImporter dominoDataImporter) throws Exception {
        // 获取Session
        Session session = getSession(importParams.getServer(), importParams.getPort(),
                importParams.getUserName(), importParams.getPassword());

        // 连接数据库
        Database database = getDataBase(session, importParams.getDatabase());

        // 获取数据库文档
        DocumentCollection documents = getDocuments(database, importParams.getFormula());

        // 计算总文档数
        int count = documents.getCount();
        // 导入成功文档数
        int succNum = 0;
        // 导入失败文档数
        int failNum = 0;

        // 循环处理文档
        Document document = documents.getFirstDocument();
        while (document != null) {
            // 处理文档
            boolean isSucc = dominoDataImporter.dealDocument(new DominoDocument(document));
            // 计算导入成功或失败文档数
            if (isSucc) {
                succNum = succNum + 1;
            } else {
                failNum = failNum + 1;
                // 记录到失败日志文件
            }
            // 失败导入文档超过上线时，终止导入
            if (failNum > IMPORT_FAIL_NUM_LIMIT) {
                break;
            }
            // 获取下一个文档
            document = documents.getNextDocument();
        }

        // 构造返回数组
        int[] retValue = new int[]{succNum, count - succNum};

        // 返回结果
        return retValue;
    }

    /**
     * 根据视图进行数据库导入
     *
     * @param importParams       导入参数
     * @param dominoDataImporter 文档导入处理对象，负责文档导入的业务处理
     * @throws Exception
     */
    public static int[] importByView(ImportParams importParams, DominoDataImporter dominoDataImporter) throws Exception {
        // 获取Session
        Session session = getSession(importParams.getServer(), importParams.getPort(),
                importParams.getUserName(), importParams.getPassword());
        // 连接数据库
        Database database = getDataBase(session, importParams.getDatabase());

        // 获取数据库视图
        View view = getView(database, importParams.getView());

        // 计算总文档数
        int count = view.getEntryCount();
        // 导入成功文档数
        int succNum = 0;
        // 导入失败文档数
        int failNum = 0;

        // 循环处理文档
        Document document = view.getFirstDocument();
        while (document != null) {
            // 处理文档
            boolean isSucc = dominoDataImporter.dealDocument(new DominoDocument(document));
            // 计算导入成功或失败文档数
            if (isSucc) {
                succNum = succNum + 1;
            } else {
                failNum = failNum + 1;
            }
            // 失败导入文档超过上线时，终止导入
            if (failNum > IMPORT_FAIL_NUM_LIMIT) {
                break;
            }
            // 获取下一个文档
            document = view.getNextDocument(document);
        }

        // 构造返回数组
        int[] retValue = new int[]{succNum, count - succNum};

        // 返回结果
        return retValue;
    }

    /**
     * 根据文档UnID导入文档
     *
     * @param importParams       导入参数
     * @param dominoDataImporter 文档导入处理对象，负责文档导入的业务处理
     * @return true 导入成功，false 导入失败
     * @throws Exception
     */
    public static boolean importByUnID(ImportParams importParams, DominoDataImporter dominoDataImporter) throws Exception {
        // 获取Session
        Session session = getSession(importParams.getServer(), importParams.getPort(),
                importParams.getUserName(), importParams.getPassword());
        // 连接数据库
        Database database = getDataBase(session, importParams.getDatabase());
        // 根据UnId获取文档
        if (log.isDebugEnabled()) {
            log.debug("导入文档UnID为：" + importParams.getUnid());
        }

        // 如果数据库没有打开，则打开数据库
        openDataBaseIfNot(database);

        // 根据文档UnID获取文档
        Document document = null;
        try {
            document = database.getDocumentByUNID(importParams.getUnid());
        } catch (NotesException e) {
            throw new Exception("UnID为" + importParams.getUnid() + "的文档不存在");
        }
        if (document == null) {
            throw new Exception("UnID为" + importParams.getUnid() + "的文档为空");
        }
        // 导入文档
        boolean result = dominoDataImporter.dealDocument(new DominoDocument(document));

        // 返回导入结果
        return result;
    }

    /**
     * 获取Domino Session
     *
     * @return
     */
    private static Session getSession(String server, String port,
                                      String userName, String password) throws Exception {
        // 输出配置参数
        if (log.isDebugEnabled()) {
            log.debug("server:" + server + ", port:" + port
                    + ", username:" + userName + ", password:" + password);
        }
        // 获取Session
        Session session = null;
        try {
            String hostString = server;
            if (port != null && port.trim().equals("")) {
                hostString = server + ":" + port;
            }
            session = NotesFactory.createSession(hostString, userName, password);
        } catch (NotesException e) {
            throw new Exception("创建Session失败，请检查相关参数是否正确", e.getCause());
        }
        // 如果获取Sesion为空，则抛出异常
        if (session == null) {
            throw new Exception("创建Session为空，请检查相关参数是否正确");
        }
        return session;
    }

    /**
     * 获取数据库
     *
     * @param session Domino Session
     * @param dbPath  数据库路径
     * @return
     * @throws Exception
     */
    private static Database getDataBase(Session session, String dbPath) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("数据库路径：" + dbPath);
        }
        // 定义返回数据库
        Database database = null;
        try {
            database = session.getDatabase(null, dbPath, false);
        } catch (NotesException e) {
            throw new Exception("获取数据库[" + dbPath + "]失败", e.getCause());
        }
        // 获取数据库为空，抛出异常
        if (database == null) {
            throw new Exception("获取数据库[" + dbPath + "]为空");
        }
        return database;
    }

    /**
     * 打开数据库，如果数据库没有打开
     *
     * @param database 数据库
     * @throws Exception
     */
    private static void openDataBaseIfNot(Database database) throws Exception {
        // 如果数据库已经打开，则直接返回
        if (database.isOpen()) {
            return;
        }
        // 打开数据库，如果打开失败则抛出异常
        if (!database.open()) {
            throw new Exception("打开数据库[" + database.getFilePath() + "]失败");
        }
    }

    /**
     * 获取数据库视图
     *
     * @param database 数据库
     * @param viewName 视图名
     * @return
     * @throws Exception
     */
    private static View getView(Database database, String viewName) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("数据库路径:" + database.getFilePath() + ", 视图名称:" + viewName);
        }
        // 如果数据库没有打开，则打开数据库
        openDataBaseIfNot(database);

        // 定义返回视图
        View view = null;
        try {
            view = database.getView(viewName);
        } catch (NotesException e) {
            String message = "获取数据库[" + database.getFilePath() + "]的视图[" + viewName + "]失败";
            throw new Exception(message, e.getCause());
        }
        // 获取视图为空，抛出异常
        if (view == null) {
            throw new Exception("获取数据库[" + database.getFilePath() + "]的视图[" + viewName + "]为空");
        }
        if (log.isDebugEnabled()) {
            log.debug("共有文档数：" + view.getEntryCount());
        }
        return view;
    }

    /**
     * 获取所有数据库文档
     *
     * @param database 数据库
     * @param formula  查询文档条件
     * @return
     * @throws Exception
     */
    private static DocumentCollection getDocuments(Database database, String formula) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("从数据库[" + database.getFilePath() + "]中获取满足条件[" + formula + "]的文档");
        }
        // 如果数据库没有打开，则打开数据库
        openDataBaseIfNot(database);

        // 获取文档
        DocumentCollection documents = null;
        try {
            // 如果查询条件为空，则获取所有文档
            if (StringUtils.isEmpty(formula)) {
                documents = database.getAllDocuments();
            } else {
                documents = database.search(formula);
            }
        } catch (NotesException e) {
            String message = "获取数据库[" + database.getFilePath() + "]中满足条件[" + formula + "]的文档失败";
            throw new Exception(message, e.getCause());
        }
        if (log.isDebugEnabled()) {
            log.debug("共有文档数:" + documents.getCount());
        }
        return documents;
    }

    /**
     * 从doc中取值
     * @param doc
     * @param value
     * @return
     */
    public static String exValue(Document doc,String value){
        String eValue = "";
        try {
            if(!"".equals(value)&&doc!=null){
                Vector vector = doc.getItemValue(value);
                if(vector.size() == 0){
                    eValue = "";
                }else if(vector.size() == 1){
                    eValue = doc.getItemValue(value).get(0).toString();
                }else {
                    Object[] vs = doc.getItemValue(value).toArray();
                    for (Object v: vs) {
                        eValue += v +",";
                    }
                }
            }
        } catch (NotesException e) {
            e.printStackTrace();
        }
        System.out.println(value+":"+eValue);
        return eValue;
    }
    /**
     * 从doc中取值日期
     * @param doc
     * @param param
     * @return
     */
    public static Timestamp exDate(Document doc,String param, String format){
        Timestamp eDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String value = exValue(doc,param);
            Date date = new Date();
            /*只处理了两种情况:1,与格式相同;2,返回的是时间格式(带ztb)*/
            if(!"".equals(value)){
                if(value.length() > format.length()){
                    value = value.substring(0, format.length()).replace("/", "-");
                }
                eDate = new Timestamp(sdf.parse(value).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(param+":"+eDate);
        return eDate;
    }
}