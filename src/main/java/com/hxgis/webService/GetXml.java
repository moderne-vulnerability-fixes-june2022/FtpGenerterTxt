package com.hxgis.webService;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Li Hong on 2018/1/19 0019.
 */
public class GetXml {
    /*解析web service的返回值*/
    public static  String getXml (String xml){
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            //System.out.println(root.getStringValue());
            return root.getStringValue();
        }catch (Exception e){
            return "";
        }
    }
    /*解析时间戳*/
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
