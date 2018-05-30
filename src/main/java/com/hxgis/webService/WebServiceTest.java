package com.hxgis.webService;

/**
 * Created by 33183 on 2017/12/1.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 通过UrlConnection调用Webservice服务
 *
 */
@Component
public class WebServiceTest {

    /*public String getSoapHeader(){
        Map<String,String> map = CacheKit.get("PageCache","signInfo");
        if(map==null){
            map = WebKit.getSignInfo();
            CacheKit.put("PageCache","signInfo",map);
        }
        int thirdType = Integer.valueOf(map.get("thirdType"));
        int secret1 = Integer.valueOf(map.get("secret1"));
        String secret2 = map.get("secret2");

        //上面代码为从缓存中取到我们需求传递到认证头的数据 下面开始添加认证头
        StringBuffer soapHeader = new StringBuffer();
        soapHeader.append("<soap:Header>");
        soapHeader.append("<SecurityHeader xmlns=\"http://www.hzsun.com/\">");
        soapHeader.append("<ThirdType>"+thirdType+"</ThirdType>");
        soapHeader.append("<Secret1>"+secret1+"</Secret1>");
        soapHeader.append("<Secret2>"+secret2+"</Secret2>");
        soapHeader.append("</SecurityHeader>");
        soapHeader.append("</soap:Header>");
        return soapHeader.toString();
    }*/
    /*@Scheduled(cron="0/2 * * * * ?")*/
    public  void cc () throws Exception {

       //WebServiceClass.RtWfWtWindSpeed("421224");
        //WebServiceClass.RtWfWtElepower("421224");
       // WebServiceClass.RtWfTotalPower("421182101");
       // WebServiceClass.RtWindMeasuringTowerInfo("421224");
        //WebServiceClass.RtGfTotalPower("421102101");
        //WebServiceClass.RtLightMeteringTowerInfo("420101");
        String content ="<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><RtGfTotalPowerResponse xmlns=\"http://tempuri.org/\"><RtGfTotalPowerResult>{\"Success\":true,\"ErrorMsg\":\"\",\"Data\":[{\"wtid\":\"421102101\",\"gfpower\":56.25,\"rectime\":\"\\/Date(1515565984826)\\/\"}]}</RtGfTotalPowerResult></RtGfTotalPowerResponse></soap:Body></soap:Envelope>";
        // WriteStringToFile(path,result);
        //jsonStringToList(result);
        //analysis();//解析数据

       String result = GetXml.getXml(content);
        System.out.println(result);
        StringBuffer sb = CreateList.RtGfTotalPower(result);
        String path = "d://";
        String name = "lihong";
        String title = "id, gfpower,rection";
        GenerateToCsv.createFile(sb,name,path,title);
    }

/*
* 读取txt文本文件
* */
    public static  void analysis (){
       InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream("C:\\RtWfWtElepower.txt"), "UTF-8");
            BufferedReader brname = new BufferedReader(isr);
            String sname = null;
            while (1==1) {
                if(!(sname = brname.readLine()).equals("")&&sname.contains("{")){
                    JSONObject o = JSONObject.parseObject(sname);
                    String status = o.getString("Success");
                    if(status.equals("true")){
                        JSONArray JA  = o.getJSONArray("Data");
                        for( int i=0 ; i<JA.size(); i++){
                            String power = JA.getJSONObject(i).getString("wtid");
                            String time = JA.getJSONObject(i).getString("rectime");
                            String str =  time.substring(6,time.length()-2);
                            System.out.println(GetXml.stampToDate(str));
                        }
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static void jsonStringToList(String rsContent) throws Exception
    {
        JSONObject obj = JSONObject.parseObject(rsContent);
            if(obj.getString("Success").equals("true")){
                JSONArray JA  = obj.getJSONArray("Data");
                for( int j=0 ; j<JA.size(); j++){
                    String power = JA.getJSONObject(j).getString("wtid");
                    String time = JA.getJSONObject(j).getString("rectime");
                    String str =  time.substring(6,time.length()-2);
                    System.out.println(GetXml.stampToDate(str));
                }
            }
    }
}
