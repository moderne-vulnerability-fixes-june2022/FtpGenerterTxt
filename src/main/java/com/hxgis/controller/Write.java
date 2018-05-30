package com.hxgis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxgis.utils.FileUtils;
import com.hxgis.utils.FilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Li Hong on 2018/5/9 0009.
 */
@RestController
@RequestMapping("write")
public class Write {
    @Autowired
    JdbcTemplate jdbcTemplate;
   // @RequestMapping("/yb")
   //@Scheduled(cron="5 0/15 * * * ?")
    public void yb()throws Exception{
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        String insert="replace  into met_cn_cityforecast(StationNo,CountyName,PublishTime,ForecastStartTime," +
                "h24Weather,h48Weather,h72Weather,h96Weather,h120Weather,h144Weather,h168Weather," +
                "h24HTemp,h48HTemp,h72HTemp,h96HTemp,h120HTemp,h144HTemp,h168HTemp," +
                "h24LTemp,h48LTemp,h72LTemp,h96LTemp,h120LTemp,h144LTemp,h168LTemp," +
                "h24Wind,h48Wind,h72Wind,h96Wind,h120Wind,h144Wind,h168Wind," +
                "h12WeatherCode,h24WeatherCode,h36WeatherCode,h48WeatherCode,h60WeatherCode,h72WeatherCode,h84WeatherCode," +
                "h96WeatherCode,h108WeatherCode,h120WeatherCode,h132WeatherCode,h144WeatherCode,h156WeatherCode,h168WeatherCode)values";
        StringBuffer stringBuffer = FileUtils.BufferReaderFile("/run/media/glyb/data/nwp/yb/YB.txt","UTF-8");
        JSONArray jsonArray = JSONArray.parseArray(stringBuffer.toString());
        int size = jsonArray.size();
        System.out.println("Size: " + size);
        String str = "";
        StringBuffer stringBuffer1 = new StringBuffer(insert);
        for(int  i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            str = "("+ jsonObject.get("StationNo")+",'"+jsonObject.get("CountyName")+"','"+  format.format( jsonObject.get("PublishTime"))+"'," +
                    "'"+  format.format( jsonObject.get("ForecastStartTime"))+"','"+ jsonObject.get("h24Weather")+"','"+ jsonObject.get("h48Weather")+"'," +
                    "'"+jsonObject.get("h72Weather")+"','"+jsonObject.get("h96Weather")+"','"+jsonObject.get("h120Weather")+"','"+jsonObject.get("h144Weather")+"'," +
                    "'"+jsonObject.get("h168Weather")+"',"+ jsonObject.get("h24HTemp")+","+ jsonObject.get("h48HTemp")+","+jsonObject.get("h72HTemp")+"," +
                    ""+jsonObject.get("h96HTemp")+"," +
                    ""+jsonObject.get("h120HTemp")+","+jsonObject.get("h144HTemp")+","+jsonObject.get("h168HTemp")+"," +
                    ""+ jsonObject.get("h24LTemp")+","+jsonObject.get("h48LTemp")+","+ jsonObject.get("h72LTemp")+","+jsonObject.get("h96LTemp")+"," +
                    ""+jsonObject.get("h120LTemp")+","+jsonObject.get("h144LTemp")+","+jsonObject.get("h168LTemp")+"," +
                    "'"+jsonObject.get("h24Wind")+"','"+jsonObject.get("h48Wind")+"','"+jsonObject.get("h72Wind")+"','"+ jsonObject.get("h96Wind")+"'," +
                    "'"+ jsonObject.get("h120Wind")+"','"+jsonObject.get("h144Wind")+"','"+jsonObject.get("h168Wind")+"'," +
                    "'"+jsonObject.get("h12WeatherCode")+"','"+jsonObject.get("h24WeatherCode")+"','"+jsonObject.get("h36WeatherCode")+"'," +
                    "'"+jsonObject.get("h48WeatherCode")+"','"+jsonObject.get("h60WeatherCode")+"','"+jsonObject.get("h72WeatherCode")+"'," +
                    "'"+jsonObject.get("h84WeatherCode")+"','"+jsonObject.get("h96WeatherCode")+"','"+jsonObject.get("h108WeatherCode")+"'," +
                    "'"+jsonObject.get("h120WeatherCode")+"','"+jsonObject.get("h132WeatherCode")+"','"+jsonObject.get("h144WeatherCode")+"'" +
                    ",'"+jsonObject.get("h156WeatherCode")+"','"+jsonObject.get("h168WeatherCode")+"'),";
            stringBuffer1.append(str);
        }
       String newStr =  stringBuffer1.toString().substring(0,stringBuffer1.length()-1);
        jdbcTemplate.execute(newStr);
        //System.out.println(newStr);
    }
    //@RequestMapping("/yj")
    //@Scheduled(cron="5 0/15 * * * ?")
    public void yj()throws Exception{
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        String insert="insert IGNORE into met_forewarn_alarmsignalhb(GUID,Type,alarm_LEVEL,alarm_SIGNAL," +
                "PubUnit,alarm_RANGE,IssueTime,IssueEndTime,OriginalContent,Content,Suggestion,InsertTime,CityRanges)values";
        StringBuffer stringBuffer = FileUtils.BufferReaderFile("/run/media/glyb/data/nwp/yj/YJ.txt","UTF-8");
        JSONArray jsonArray = JSONArray.parseArray(stringBuffer.toString());
        int size = jsonArray.size();
        System.out.println("Size: " + size);
        String str = "";
        StringBuffer stringBuffer1 = new StringBuffer(insert);
        for(int  i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            str = "('"+ jsonObject.get("GUID")+"','"+jsonObject.get("Type")+"'," +
                    "'"+ jsonObject.get("Level")+"','"+ jsonObject.get("Signal")+"'," +
                    "'"+jsonObject.get("PubUnit")+"','"+jsonObject.get("Range")+"','"+format.format(jsonObject.get("IssueTime"))+"','"+format.format(jsonObject.get("IssueEndTime"))+"'," +
                    "'"+jsonObject.get("OriginalContent")+"','"+ jsonObject.get("Content")+"','"+ jsonObject.get("Suggestion")+"','"+format.format(jsonObject.get("InsertTime"))+"'," +
                    "'"+jsonObject.get("CityRanges")+"'),";
            stringBuffer1.append(str);
        }
        String newStr =  stringBuffer1.toString().substring(0,stringBuffer1.length()-1);
       //System.out.println(newStr);
        jdbcTemplate.execute(newStr);
    }

}
