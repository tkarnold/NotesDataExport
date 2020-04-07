package com.example.notes.api;

/**
 * ImportParams Domino数据导入参数封装类
 *
 * @version 1.0 2011-5-18
 * @author zhanzhengqiang
 */

public class ImportParams {

    /**
     * Domino服务器地址
     */
    private String server;

    /**
     * Domino服务器端口
     */
    private String port;

    /**
     * 连接Domino服务器用户名
     */
    private String userName;

    /**
     * 连接Domino服务器密码
     */
    private String password;

    /**
     * 导入Domino数据库路径
     */
    private String database;

    /**
     * 导入Domino数据库视图
     */
    private String view;

    /**
     * 导入Domino数据查询条件
     * 如FORM=\"frmDispDocComFile\"&IsTrashDoc!=\"false\"
     */
    private String formula;

    /**
     * 导入Domino文档UnID
     */
    private String unid;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
// 忽略seter，getter方法及构造方法
}

