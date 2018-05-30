package com.hxgis.controller;

import com.hxgis.utils.DateUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Li Hong on 2018/5/23 0023.
 */

//新能源windows服务器通过ftp下载文件，
  //下载的文件包括
// 1.  预报文件
//2.  预警文件
//3.  色斑图所用的文件

@RestController
@RequestMapping("main")
public class FileDownload {
    @Scheduled(cron="5 0/10 * * * ?")//预报预警文件，每隔10分钟一次
    public void  ybyj (){
        System.out.println(new Date());
        downFile("119.97.206.226",21,"hbnyyj","123","yb/","YB.txt","E:/nwp/yb");
        downFile("119.97.206.226",21,"hbnyyj","123","yj/","YJ.txt","E:/nwp/yj");
    }

    //每天上午六点下载
    @Scheduled(cron="0 0 7 1/1 * ?")
    public  void six() {
        System.out.println("上午六点");
        Date date =new Date();
        Date newdate = DateUtil.calculateByDate(date,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(newdate);
        String fileName = "wrf_"+ymd+"_12.TXT";
        System.out.println(fileName);
        try {
            //从ftp下载
            downFile("119.97.206.226",21,"hbnyyj","123","wrf/",fileName,"E:/nwp/wrf");

        }catch (Exception e){

        }
    }

    //每天下午4点下载
    @Scheduled(cron="0 0 17 1/1 * ?")
    public  void twelve() {
        Date date =new Date();
        System.out.println("开始了");
        // Date newdate = DateUtil.calculateByDate(date,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(date);
        String fileName = "wrf_"+ymd+"_00.TXT";
        System.out.println(fileName);

        try {
            //从ftp下载
            downFile("119.97.206.226",21,"hbnyyj","123","wrf/",fileName,"E:/nwp/wrf");

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
