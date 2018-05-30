package com.hxgis.controller;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import sun.net.TelnetInputStream;

import java.io.*;
import java.net.SocketException;

/**
 * Created by Li Hong on 2018/5/22 0022.
 */
public class Demo {
    private static Logger logger = Logger.getLogger(Demo.class);

    public static FTPClient getFTPClient(String ftpHost,String ftpUsername,String ftpPassword){
        FTPClient ftpClient=new FTPClient();
        try {
            ftpClient=new FTPClient();
            ftpClient.connect(ftpHost);// 连接FTP服务器
            ftpClient.login(ftpUsername, ftpPassword);// 登陆FTP服务器
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                logger.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }else{
                logger.info("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    public static void downloadFtpFile(String ftpHost,String ftpUsername,String ftpPassword,String ftpPath,String localPath,String fileName){
        FTPClient ftpClient=null;
        try {
            ftpClient=getFTPClient(ftpHost, ftpUsername, ftpPassword);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            File localFile=new File(localPath+ File.separatorChar+fileName);
            OutputStream os=new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();
        } catch (FileNotFoundException e) {
            logger.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.error("连接FTP失败");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取错误");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String ftpHost="10.104.143.81";
        String ftpUsername="qxfwzx";
        String ftpPassword="qxfwzx123";
        String ftpPath="/wrf/";
        String localPath="E:/nwp/yb";
        String fileName="*.TXT";
       // downloadFtpFile(ftpHost, ftpUsername, ftpPassword, ftpPath, localPath, fileName);

    }


}
