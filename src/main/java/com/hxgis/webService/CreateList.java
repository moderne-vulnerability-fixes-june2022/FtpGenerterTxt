package com.hxgis.webService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Li Hong on 2018/1/12 0012.
 *
 *
 * 创建List 容器
 */

public class CreateList {

    /*
    * 封装测风塔的实时信息
    * */
    public static StringBuffer RtWindMeasuringTowerInfo(String content) {
        StringBuffer sb = new StringBuffer();
        JSONObject obj = JSONObject.parseObject(content);
        if(obj.getString("Success").equals("true")){
            JSONArray JA  = obj.getJSONArray("Data");
            for ( int j = 0 ; j < JA.size(); j++){
                sb.append(JA.getJSONObject(j).getString("wtid")+",") ;
                sb.append(JA.getJSONObject(j).getString("wspd10")+",") ;

                sb.append(JA.getJSONObject(j).getString("wdir10")+",") ;
                sb.append(JA.getJSONObject(j).getString("temp10")+",") ;
                sb.append(JA.getJSONObject(j).getString("pres10")+",") ;
                sb.append(JA.getJSONObject(j).getString("hum10")+",") ;

                sb.append(JA.getJSONObject(j).getString("wspd50")+",") ;
                sb.append(JA.getJSONObject(j).getString("wdir50")+",") ;

                sb.append(JA.getJSONObject(j).getString("wspd70")+",") ;
                sb.append(JA.getJSONObject(j).getString("wdir70")+",") ;

                //sb.append(JA.getJSONObject(j).getString("wspd10")+",") ;

                String time = JA.getJSONObject(j).getString("rectime");
                String str =  time.substring(6,time.length()-2);
                sb.append( GetXml.stampToDate(str)+"\r\n");

            }
        }
        return sb;
    }
    /*
    *光伏有功功率的实时信息
    * */
    public static StringBuffer RtGfTotalPower (String content) {

        StringBuffer sb = new StringBuffer();
        JSONObject obj = JSONObject.parseObject(content);
        if(obj.getString("Success").equals("true")){
            JSONArray JA  = obj.getJSONArray("Data");
            for ( int j = 0 ; j < JA.size(); j++){
                sb.append(JA.getJSONObject(j).getString("wtid")+",") ;
                sb.append(JA.getJSONObject(j).getString("gfpower")+",") ;
                String time = JA.getJSONObject(j).getString("rectime");
                String str =  time.substring(6,time.length()-2);
                sb.append( GetXml.stampToDate(str)+"\r\n");
            }
        }
        return sb;
    }

    //风电场的风速跟功率
    public static StringBuffer spendAndPower(String spendStr,String powerStr){

        StringBuffer sb = new StringBuffer();
        List list = new ArrayList();
        JSONObject spendStrs = JSONObject.parseObject(spendStr);
        JSONObject powerStrs = JSONObject.parseObject(powerStr);
        String spendStatus = spendStrs.getString("Success");
        String powerStatus = powerStrs.getString("Success");
        if(spendStatus.equals("true")&&powerStatus.equals("true")){
            JSONArray JAspend  = spendStrs.getJSONArray("Data");
            JSONArray JApower  = powerStrs.getJSONArray("Data");

            for( int i=0 ; i<JAspend.size(); i++){
                String wtid1 = JAspend.getJSONObject(i).getString("wtid");
                String speed = JAspend.getJSONObject(i).getString("windspeed");
                for(int j = 0; j < JApower.size();j++){
                    String wtid2 = JApower.getJSONObject(j).getString("wtid");
                    String farmid = wtid2.substring(0,6);
                    String power = JApower.getJSONObject(j).getString("power");
                    String time = JApower.getJSONObject(j).getString("rectime");
                    String str =  time.substring(6,time.length()-2);
                    if(wtid1.equals(wtid2)){
                        if(speed!=null&&power!=null){
                            Map<String,Object> map = new HashMap<>();
                            map.put("time",GetXml.stampToDate(str));
                            map.put("power",power);
                            map.put("speed",speed);
                            map.put("farmid",farmid);
                            map.put("wtid",wtid1);

                            sb.append(GetXml.stampToDate(str)+",");
                            sb.append(power+",");
                            sb.append(speed+",");
                            sb.append(farmid+",");
                            sb.append(wtid1+"\r\n");
                            list.add(map);
                        }

                    }
                }
            }
        }
       // System.out.println(list.toString());
        return sb;
    }

    //风电场的有功功率的实时信息
    //参数是设备号（升压站）
    public static StringBuffer RtWfTotalPower (String content){

        StringBuffer sb = new StringBuffer();
        JSONObject obj = JSONObject.parseObject(content);
        if(obj.getString("Success").equals("true")){
            JSONArray JA  = obj.getJSONArray("Data");
            for ( int j = 0 ; j < JA.size(); j++){
                sb.append(JA.getJSONObject(j).getString("wtid")+",") ;
                sb.append(JA.getJSONObject(j).getString("wfpower")+",") ;
                String time = JA.getJSONObject(j).getString("rectime");
                String str =  time.substring(6,time.length()-2);
                sb.append( GetXml.stampToDate(str)+"\r\n");
            }
        }
        return sb;
    }

    //测光塔的实时信息
    //参数是光伏电站编号
    public static StringBuffer RtLightMeteringTowerInfo (String content){
        StringBuffer sb = new StringBuffer();
        JSONObject obj = JSONObject.parseObject(content);
        if(obj.getString("Success").equals("true")){
            JSONArray JA  = obj.getJSONArray("Data");
            for ( int j = 0 ; j < JA.size(); j++){
                sb.append(JA.getJSONObject(j).getString("wtid")+",") ;
                sb.append(JA.getJSONObject(j).getString("tradio")+",") ;
                sb.append(JA.getJSONObject(j).getString("dradio")+",") ;
                sb.append(JA.getJSONObject(j).getString("sradio")+",") ;
                sb.append(JA.getJSONObject(j).getString("temp")+",") ;
                sb.append(JA.getJSONObject(j).getString("wspd")+",") ;
                sb.append(JA.getJSONObject(j).getString("wdir")+",") ;
                sb.append(JA.getJSONObject(j).getString("pres")+",") ;
                String time = JA.getJSONObject(j).getString("rectime");
                String str =  time.substring(6,time.length()-2);
                sb.append( GetXml.stampToDate(str)+"\r\n");
            }
        }
        return sb;
    }

}
