package com.hxgis.webService;

/**
 * Created by 33183 on 2017/12/1.
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 通过UrlConnection调用Webservice服务
 *
 */
public class WebServiceClass {

    //返回电站下面所有单台风机的实时风速
    //参数是风场编号
    public static String RtWfWtWindSpeed(String wfid) {
        //wfid=421224
        try
        {
            //服务的地址
            URL wsUrl = new URL("http://10.3.1.12:81/Service.asmx?op=RtWfWtWindSpeed");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream os = conn.getOutputStream();

            //请求体
            String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Header>\n" +
                    "    <MySoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <UserName>hbnyj</UserName>\n" +
                    "      <PassWord>hbnyj1234</PassWord>\n" +
                    "    </MySoapHeader>\n" +
                    "  </soap12:Header>\n" +
                    "  <soap12:Body>\n" +
                    "    <RtWfWtWindSpeed xmlns=\"http://tempuri.org/\">\n" +
                    "      <wfid>"+ wfid +"</wfid>\n" +
                    "    </RtWfWtWindSpeed>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            os.write(soap.getBytes());

            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }

            //String path = "d://RtWfWtWindSpeed.txt";
            //String result = GetXml.getXml(s);
            //GenerateText. WriteStringToFile(path,result);


           //s:<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><RtWfWtWindSpeedResponse xmlns="http://tempuri.org/"><RtWfWtWindSpeedResult>{"Success":true,"ErrorMsg":"","Data":[{"wtid":421224001,"windspeed":"6.00","rectime":"\/Date(1515563647911)\/"},{"wtid":421224002,"windspeed":"7.40","rectime":"\/Date(1515563647911)\/"},{"wtid":421224003,"windspeed":"7.80","rectime":"\/Date(1515563647911)\/"},{"wtid":421224004,"windspeed":"10.00","rectime":"\/Date(1515563647911)\/"},{"wtid":421224005,"windspeed":"7.10","rectime":"\/Date(1515563647911)\/"},{"wtid":421224006,"windspeed":"3.90","rectime":"\/Date(1515563647911)\/"},{"wtid":421224007,"windspeed":"9.30","rectime":"\/Date(1515563647911)\/"},{"wtid":421224008,"windspeed":"6.20","rectime":"\/Date(1515563647911)\/"},{"wtid":421224009,"windspeed":"5.60","rectime":"\/Date(1515563647911)\/"},{"wtid":421224010,"windspeed":"6.30","rectime":"\/Date(1515563647911)\/"},{"wtid":421224011,"windspeed":"5.00","rectime":"\/Date(1515563647911)\/"},{"wtid":421224012,"windspeed":"5.50","rectime":"\/Date(1515563647911)\/"},{"wtid":421224013,"windspeed":"4.70","rectime":"\/Date(1515563647911)\/"},{"wtid":421224014,"windspeed":"4.70","rectime":"\/Date(1515563647911)\/"},{"wtid":421224015,"windspeed":"4.90","rectime":"\/Date(1515563647911)\/"},{"wtid":421224016,"windspeed":"6.30","rectime":"\/Date(1515563647911)\/"}]}</RtWfWtWindSpeedResult></RtWfWtWindSpeedResponse></soap:Body></soap:Envelope>

            is.close();
            os.close();
            conn.disconnect();

            return  s;
        }
        catch(Exception exp)
        {
            return  "";
        }

    }

    //返回电站下面所有单台风机的实时功率
    //参数是风场编号
    public static String RtWfWtElepower(String wfid) {
        //wfid=421224
        try
        {
            //服务的地址
            URL wsUrl = new URL("http://10.3.1.12:81/Service.asmx?op=RtWfWtElepower");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream os = conn.getOutputStream();

            //请求体
            String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Header>\n" +
                    "    <MySoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <UserName>hbnyj</UserName>\n" +
                    "      <PassWord>hbnyj1234</PassWord>\n" +
                    "    </MySoapHeader>\n" +
                    "  </soap12:Header>\n" +
                    "  <soap12:Body>\n" +
                    "    <RtWfWtElepower  xmlns=\"http://tempuri.org/\">\n" +
                    "      <wfid>"+ wfid +"</wfid>\n" +
                    "    </RtWfWtElepower >\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            os.write(soap.getBytes());
            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }

            //System.out.println(s);

           // String path = "d://RtWfWtElepower.txt";
           // String result = GetXml.getXml(s);
            //GenerateText. WriteStringToFile(path,result);
            //s:<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><RtWfWtElepowerResponse xmlns="http://tempuri.org/"><RtWfWtElepowerResult>{"Success":true,"ErrorMsg":"","Data":[{"wtid":421224001,"power":"115.90","rectime":"\/Date(1515564180043)\/"},{"wtid":421224002,"power":"399.30","rectime":"\/Date(1515564180043)\/"},{"wtid":421224003,"power":"-0.40","rectime":"\/Date(1515564180043)\/"},{"wtid":421224004,"power":"430.10","rectime":"\/Date(1515564180043)\/"},{"wtid":421224005,"power":"451.80","rectime":"\/Date(1515564180043)\/"},{"wtid":421224006,"power":"375.40","rectime":"\/Date(1515564180043)\/"},{"wtid":421224007,"power":"374.70","rectime":"\/Date(1515564180043)\/"},{"wtid":421224008,"power":"345.00","rectime":"\/Date(1515564180043)\/"},{"wtid":421224009,"power":"146.00","rectime":"\/Date(1515564180043)\/"},{"wtid":421224010,"power":"244.50","rectime":"\/Date(1515564180043)\/"},{"wtid":421224011,"power":"153.90","rectime":"\/Date(1515564180043)\/"},{"wtid":421224012,"power":"124.70","rectime":"\/Date(1515564180043)\/"},{"wtid":421224013,"power":"145.00","rectime":"\/Date(1515564180043)\/"},{"wtid":421224014,"power":"74.50","rectime":"\/Date(1515564180043)\/"},{"wtid":421224015,"power":"116.10","rectime":"\/Date(1515564180043)\/"},{"wtid":421224016,"power":"72.90","rectime":"\/Date(1515564180043)\/"}]}</RtWfWtElepowerResult></RtWfWtElepowerResponse></soap:Body></soap:Envelope>

            is.close();
            os.close();
            conn.disconnect();

            return  s;
        }
        catch(Exception exp)
        {
            return "";
        }

    }

    //风场实况功率的实时信息
    //参数是设备号（升压站）
    public static String RtWfTotalPower(String wtid) {
        //wfid=421224
        try
        {
            //服务的地址
            URL wsUrl = new URL("http://10.3.1.12:81/Service.asmx?op=RtWfTotalPower");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream os = conn.getOutputStream();

            //请求体
            String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Header>\n" +
                    "    <MySoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <UserName>hbnyj</UserName>\n" +
                    "      <PassWord>hbnyj1234</PassWord>\n" +
                    "    </MySoapHeader>\n" +
                    "  </soap12:Header>\n" +
                    "  <soap12:Body>\n" +
                    "    <RtWfTotalPower xmlns=\"http://tempuri.org/\">\n" +
                    "      <wtid>"+ wtid +"</wtid>\n" +
                    "    </RtWfTotalPower>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            os.write(soap.getBytes());

            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }


           // String path = "d://RtWfTotalPower.txt";
        //    String result = GetXml.getXml(s);
          //  GenerateText. WriteStringToFile(path,result);
            //s:<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><RtWfTotalPowerResponse xmlns="http://tempuri.org/"><RtWfTotalPowerResult>{"Success":true,"ErrorMsg":"","Data":[{"wtid":"421182101","wfpower":0.00,"rectime":"\/Date(1515565830198)\/"}]}</RtWfTotalPowerResult></RtWfTotalPowerResponse></soap:Body></soap:Envelope>

            is.close();
            os.close();
            conn.disconnect();

            return  s;
        }
        catch(Exception exp)
        {
            return "";
        }

    }

     //测风塔的实时信息
    //参数是风场编号
    public static String RtWindMeasuringTowerInfo(String wfid) {
        //wfid=421224
        try
        {
            //服务的地址
            URL wsUrl = new URL("http://10.3.1.12:81/Service.asmx?op=RtWindMeasuringTowerInfo");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream os = conn.getOutputStream();

            //请求体
            String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Header>\n" +
                    "    <MySoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <UserName>hbnyj</UserName>\n" +
                    "      <PassWord>hbnyj1234</PassWord>\n" +
                    "    </MySoapHeader>\n" +
                    "  </soap12:Header>\n" +
                    "  <soap12:Body>\n" +
                    "    <RtWindMeasuringTowerInfo  xmlns=\"http://tempuri.org/\">\n" +
                    "      <wfid>"+ wfid +"</wfid>\n" +
                    "    </RtWindMeasuringTowerInfo>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            os.write(soap.getBytes());

            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }

            //String path = "d://RtWindMeasuringTowerInfo.txt";
            //String result = GetXml.getXml(s);
           //GenerateText. WriteStringToFile(path,result);
            //s:<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><RtWindMeasuringTowerInfoResponse xmlns="http://tempuri.org/"><RtWindMeasuringTowerInfoResult>{"Success":true,"ErrorMsg":"","Data":[{"wtid":421224017,"wspd10":null,"wdir10":null,"temp10":null,"pres10":null,"hum10":null,"wspd50":null,"wdir50":null,"wspd70":null,"wdir70":null,"rectime":"\/Date(1515565915172)\/"}]}</RtWindMeasuringTowerInfoResult></RtWindMeasuringTowerInfoResponse></soap:Body></soap:Envelope>

            is.close();
            os.close();
            conn.disconnect();

            return  s;
        }
        catch(Exception exp)
        {
            return "";
        }

    }

     //光伏有功功率的实时信息
    //参数是设备号（升压站）
    public static String RtGfTotalPower(String wtid) {
        //wfid=421224
        try
        {
            //服务的地址
            URL wsUrl = new URL("http://10.3.1.12:81/Service.asmx?op=RtGfTotalPower");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream os = conn.getOutputStream();

            //请求体
            String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Header>\n" +
                    "    <MySoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <UserName>hbnyj</UserName>\n" +
                    "      <PassWord>hbnyj1234</PassWord>\n" +
                    "    </MySoapHeader>\n" +
                    "  </soap12:Header>\n" +
                    "  <soap12:Body>\n" +
                    "    <RtGfTotalPower xmlns=\"http://tempuri.org/\">\n" +
                    "      <wtid>"+ wtid +"</wtid>\n" +
                    "    </RtGfTotalPower>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            os.write(soap.getBytes());

            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }


            //String url = "d://";
            //String filename = "RtGfTotalPower";
           // String title = "升压站编号,有功功率,观测时间";
           // String result = GetXml.getXml(s);
            //StringBuffer SB = CreateList.RtGfTotalPower(result);
            //GenerateToCsv.createFile(SB,filename,url,title);
           // GenerateText. WriteStringToFile(path,result);
            //s:<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><RtGfTotalPowerResponse xmlns="http://tempuri.org/"><RtGfTotalPowerResult>{"Success":true,"ErrorMsg":"","Data":[{"wtid":"421102101","gfpower":56.25,"rectime":"\/Date(1515565984826)\/"}]}</RtGfTotalPowerResult></RtGfTotalPowerResponse></soap:Body></soap:Envelope>

            is.close();
            os.close();
            conn.disconnect();

            return  s;
        }
        catch(Exception exp)
        {
            return "";
        }

    }

    //测光塔的实时信息
    //参数是光伏电站编号
    public static String RtLightMeteringTowerInfo(String wfid) {
        //wfid=421224
        try
        {
            //服务的地址
            URL wsUrl = new URL("http://10.3.1.12:81/Service.asmx?op=RtLightMeteringTowerInfo");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream os = conn.getOutputStream();

            //请求体
            String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Header>\n" +
                    "    <MySoapHeader xmlns=\"http://tempuri.org/\">\n" +
                    "      <UserName>hbnyj</UserName>\n" +
                    "      <PassWord>hbnyj1234</PassWord>\n" +
                    "    </MySoapHeader>\n" +
                    "  </soap12:Header>\n" +
                    "  <soap12:Body>\n" +
                    "    <RtLightMeteringTowerInfo  xmlns=\"http://tempuri.org/\">\n" +
                    "      <wfid>"+ wfid +"</wfid>\n" +
                    "    </RtLightMeteringTowerInfo>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";
            os.write(soap.getBytes());

            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            String s = "";
            while((len = is.read(b)) != -1){
                String ss = new String(b,0,len,"UTF-8");
                s += ss;
            }


            //String path = "d://RtLightMeteringTowerInfo.txt";
          //  String result = GetXml.getXml(s);
           // GenerateText. WriteStringToFile(path,result);
            //s:<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><RtLightMeteringTowerInfoResponse xmlns="http://tempuri.org/"><RtLightMeteringTowerInfoResult>{"Success":true,"ErrorMsg":"","Data":[{"wtid":420101991,"tradio":470.20,"dradio":null,"sradio":null,"temp":11.10,"wspd":1.90,"wdir":312.20,"pres":null,"rectime":"\/Date(1515566012110)\/"}]}</RtLightMeteringTowerInfoResult></RtLightMeteringTowerInfoResponse></soap:Body></soap:Envelope>

            is.close();
            os.close();
            conn.disconnect();

            return  s;
        }
        catch(Exception exp)
        {
            return "";
        }

    }
}