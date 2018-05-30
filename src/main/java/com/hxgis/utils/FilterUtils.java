package com.hxgis.utils;

import java.io.*;

/**
 * Created by Li Hong on 2018/3/18 0018.
 */
public class FilterUtils {

    public static  String FilterSpeed(String str){//此方法过滤风速异常值
        try{
           File f=new File("F:/log.txt");
            f.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            PrintStream printStream = new PrintStream(fileOutputStream);
            if(str==null&&str==""){
               // System.setOut(printStream);
                System.out.println("默认输出到控制台的这一句，输出到了文件 log.txt");
                return "0";
            }else {
                double db= Double.parseDouble(str);
                double a = 40;
                double b = 0;
                if(db>a||db<b){
                    //System.setOut(printStream);
                    System.out.println("默认输出到控制台的这一句，输出到了文件 log.txt");
                    return "0";
                }
            }
        }catch (Exception e){
            return "0";
        }
        return str;
    }
    public static  String FilterDir(String str){//此方法过滤风速向异常值
        try{
            if(str==null&&str==""){
                return "0";
            }else {
                double db= Double.parseDouble(str);
                double a = 360;
                double b = 0;
                if(db>a||db<b){
                    return "0";
                }
            }
        }catch (Exception e){
            System.out.println("风向值异常");
        }
        return str;

    }
    public static  String FilterTemp(String str){//此方法过滤风速向异常值

        try{
            if(str==null&&str==""){
                return "0";
            }else {
                double db= Double.parseDouble(str);
                double a = 60;
                double b = -60;
                if(db>a||db<b){
                    return "0";
                }
            }
        }catch (Exception e){
            System.out.println("温度值异常");
        }
        return str;

    }
    public static  String FilterHum(String str){//此方法过滤风速向异常值

        try{
            if(str==null&&str==""){
                return "0";
            }else {
                double db= Double.parseDouble(str);
                double a = 0;
                double b = 100;
                if(db<a||db>b){
                    return "0";
                }
            }
        }catch (Exception e){
            System.out.println("湿度值异常");
        }
        return str;

    }
    public static  String FilterPre(String str){//此方法过滤风速向异常值

        try{
            if(str==null&&str==""){
                return "0";
            }else {
                double db= Double.parseDouble(str);
                double a = 0;
                double b = 2000;
                if(db<a||db>b){
                    return "0";
                }
            }
        }catch (Exception e){
            System.out.println("湿度值异常");
        }
        return str;

    }
    public static  String FilterPower(String str){//此方法过滤功率异常值

        try{
            if(str==null&&str==""){
                return "0";
            }else {
                double db= Double.parseDouble(str);
                double a = 0;
                //double b = 2000;
                if(db<0){
                    return "0";
                }
            }
        }catch (Exception e){
            System.out.println("功率值异常");
        }
        return str;

    }

    public static  boolean print(String filePath, String code) {
        try {

            File tofile = new File(filePath);
            FileWriter fw = new FileWriter(tofile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            //GetNowDate getnowdata=new GetNowDate();//Java-取得服务器当前的各种具体时间
            //System.out.println(getnowdata.getDate()+":"+code);
            //pw.println(getnowdata.getDate()+":"+code);
            pw.close();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
