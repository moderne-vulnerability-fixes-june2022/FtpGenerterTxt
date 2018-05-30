package com.hxgis.controller;

import com.alibaba.fastjson.JSONArray;
import com.hxgis.dao.ReadDb;
import com.hxgis.utils.CreateFileUtilToJson;
import com.hxgis.utils.DateUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Li Hong on 2018/5/9 0009.
 */
@RestController
@RequestMapping("main")
public class MainController {
    //private Logger logger;

    @Autowired
    private ReadDb readDb;
    //@Scheduled(cron="5 0/5 * * * ?")
    //@RequestMapping("/yb")//读取数据库的预报数据，然后生成json文件，最后上传到41的ftp上去
    public List<Map<String,Object>> yb () throws Exception{
        try {
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfs =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String endTime = sdf.format(DateUtil.calculateByDate(new Date(),+1));
            //System.out.println(endTime);
            Date  startTime= DateUtil.calculateByDate(sdf.parse(endTime),-4);

            List<Map<String,Object>>   list = readDb.readDBYB(sdf.format(startTime),endTime);
            System.out.println(list.size());
            String jsonStr = JSONArray.toJSONString(list);
            String path = "F:\\ybyj";
            String name = "YB";
            CreateFileUtilToJson.createJsonFile(jsonStr,path,name);
            System.out.println(jsonStr);

            FileInputStream in=new FileInputStream(new File("F:\\ybyj\\YB.txt"));/*"+sdfs.format(new Date())+"-*/
            moveFile("10.104.109.41 ", 21, "hbnyyj", "123", path,  "yb/YB.txt", in);

            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //@RequestMapping("/yj")
    //@Scheduled(cron="5 0/5 * * * ?")//读取数据库的预报数据，然后生成json文件，最后上传到41的ftp上去
    public List<Map<String,Object>> yj () throws Exception{
        try {
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");

            String endTime = sdf.format(DateUtil.calculateByDate(new Date(),+1));

            Date  startTime= DateUtil.calculateByDate(sdf.parse(endTime),-7);//结束时间+1天

            List<Map<String,Object>>   list = readDb.readDBYJ(sdf.format(startTime),endTime);
            String jsonStr = JSONArray.toJSONString(list);
            String path = "F:\\ybyj";
            String name = "YJ";
            CreateFileUtilToJson.createJsonFile(jsonStr,path,name);
            System.out.println(jsonStr);
            FileInputStream in=new FileInputStream(new File("F:\\ybyj\\YJ.txt"));/*"+sdfs.format(new Date())+"-*/
            moveFile("10.104.109.41 ", 21, "hbnyyj", "123", path,  "yj/YJ.txt", in);
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static boolean moveFile(String url,int port,String username, String password, String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            //创建路径
            try{
                ftp.makeDirectory(path);
            }catch(Exception e){
                e.printStackTrace();
            }
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(path);
            boolean f= ftp.storeFile(filename, input);
            //logger.error(f);
            System.out.println(f);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;  }


      //每天上午六点下载从81服务器的ftp下载色斑图所用到的文件，然后通过上传到41的ftp上去
     //@Scheduled(cron="0 0 6 1/1 * ?")
    public  void six() {
        Date date =new Date();
        Date newdate = DateUtil.calculateByDate(date,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(newdate);
        String fileName = "wrf_"+ymd+"_12.TXT";
        System.out.println(fileName);
        try {
            //从ftp下载
            downFile("10.104.143.81 ",21,"qxfwzx","qxfwzx123","/wrf/",fileName,"F:/wrf");

            //上传到ftp
            String path = "F:\\wrf";
            FileInputStream in=new FileInputStream(new File("F:\\wrf\\"+fileName+""));/*"+sdfs.format(new Date())+"-*/
            moveFile("10.104.109.41 ", 21, "hbnyyj", "123", path,  "wrf/"+fileName+"", in);
        }catch (Exception e){

        }
    }

    //每天下午4点下载从81服务器的ftp下载色斑图所用到的文件，然后通过上传到41的ftp上去
     //@Scheduled(cron="0 0 16 1/1 * ?")
    public  void twelve() {
        Date date =new Date();
       // Date newdate = DateUtil.calculateByDate(date,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(date);
        String fileName = "wrf_"+ymd+"_00.TXT";
        System.out.println(fileName);

         try {
             //从ftp下载
             downFile("10.104.143.81 ",21,"qxfwzx","qxfwzx123","/wrf/",fileName,"F:/wrf");

             //上传到ftp
             String path = "F:\\wrf";
             FileInputStream in=new FileInputStream(new File("F:\\wrf\\"+fileName+""));/*"+sdfs.format(new Date())+"-*/
             moveFile("10.104.109.41 ", 21, "hbnyyj", "123", path,  "wrf/"+fileName+"", in);
         }catch (Exception e){

         }
    }

    public static boolean downFile(
            String url, //FTP服务器hostname
            int port,//FTP服务器端口
            String username, //FTP登录账号
            String password, //FTP登录密码
            String remotePath,//FTP服务器上的相对路径
            String fileName,//要下载的文件名
            String localPath//下载后保存到本地的路径
    ) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            System.out.println("aaa");
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();

            for(FTPFile ff:fs){
                System.out.println("bb" + fs);

                if(ff.getName().equals(fileName)){
                    System.out.println("dd");
                    File localFile = new File(localPath+"/"+ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    System.out.println("ccc" +ff.getName()+fileName);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }


}
