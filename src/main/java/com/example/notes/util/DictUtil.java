package com.example.notes.util;

import org.springframework.util.StringUtils;

public class DictUtil {
    /**
     * 适航证类型
     * @param label
     * @return
     */
    public static String airworthyType(String label) {
        String value = "";
        switch(label){
            case "标准适航证":
                value = "1";
                break;
            case "特殊适航证":
                value = "2";
                break;
            case "实验类适航证":
                value = "3";
                break;
        }
        return value;
    }
    /**
     * 是否为空
     * @param label
     * @return
     */
    public static String isEmpty(String label) {
        String value = "";
        if(!StringUtils.isEmpty(label)){
            value = "1";
        }
        return value;
    }

    /**
     * 标准适航证运输类飞机类别
     * @param label
     * @return
     */
    public static String transportType(String label) {
        String value = "";
        switch(label){
            case "客运":
                value = "1";
                break;
            case "货运":
                value = "2";
                break;
            case "客/货运":
                value = "3";
                break;
            case "(客运)":
                value = "1";
                break;
            case "(货运)":
                value = "2";
                break;
            case "(客/货运)":
                value = "3";
                break;
        }
        return value;
    }
}
