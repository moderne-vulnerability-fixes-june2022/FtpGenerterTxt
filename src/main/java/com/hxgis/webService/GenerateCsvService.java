package com.hxgis.webService;

/**
 * Created by Li Hong on 2018/1/21 0021.
 */
public class GenerateCsvService {
    //风速and功率的文件
    public static  void  speedAndpower(String id){
        String speed =  WebServiceClass.RtWfWtWindSpeed(id);
        String power = WebServiceClass.RtWfWtElepower(id);
        StringBuffer sb = CreateList.spendAndPower( GetXml.getXml(speed), GetXml.getXml(power));
        String filename = "speedAndpower";
        String url = "/run/media/glyb/data/skdata/";
        String title = "时间,功率,风速,电站id,风机ID";
        GenerateToCsv.createFile(sb,filename,url,title);
    }
    //风场实况功率的实时信息
    public static  void  RtWfTotalPower(String id){
        String power =  WebServiceClass.RtWfTotalPower(id);
        StringBuffer sb = CreateList.RtWfTotalPower(GetXml.getXml(power));
        String filename = "RtWfTotalPower";
        String url = "/run/media/glyb/data/skdata/";
        String title = "升压站,功率,时间";
        GenerateToCsv.createFile(sb,filename,url,title);
    }
    //光伏有功功率的实时信息
    //参数是设备号（升压站）
    public static  void  RtGfTotalPower(String id){
        String power =  WebServiceClass.RtGfTotalPower(id);
        StringBuffer sb = CreateList.RtGfTotalPower(GetXml.getXml(power));
        String filename = "RtGfTotalPower";
        String url = "/run/media/glyb/data/skdata/";
        String title = "升压站编号,有功功率,观测时间";
        GenerateToCsv.createFile(sb,filename,url,title);
    }
    //测光塔的实况信息
  //参数是光伏电站编 号
    public static  void  RtLightMeteringTowerInfo(String id){
        String power =  WebServiceClass.RtLightMeteringTowerInfo(id);
        StringBuffer sb = CreateList.RtLightMeteringTowerInfo(GetXml.getXml(power));
        String filename = "RtLightMeteringTowerInfo";
        String url = "/run/media/glyb/data/skdata/";
        String title = "wtid,tradio,dradio,sradio,temp,wspd,wdir,pres,rectime";
        GenerateToCsv.createFile(sb,filename,url,title);
    }
    //测风塔的实时信息
  // 参数是风场编号
      public static  void  RtWindMeasuringTowerInfo(String id){
          String power =  WebServiceClass.RtWindMeasuringTowerInfo(id);
          StringBuffer sb = CreateList.RtWindMeasuringTowerInfo(GetXml.getXml(power));
          String filename = "RtWindMeasuringTowerInfo";
          String url = "/run/media/glyb/data/skdata/";
          String title = "编号,10米风速,10米风向,10气温,10米气压,10米湿度,50米风速,50米风向,70米风速,70米风向,时间";
          GenerateToCsv.createFile(sb,filename,url,title);
      }
}
