package com.hxgis.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by Li Hong on 2018/5/21 0021.
 */
public class DownloadAll {


    //1、得到所有路径以及子路径：递归遍历所有文件到路径。
    // 参数：ftp为FTPClient对象，path为当前的路径，pathArray保存当前的路径，并将此路径集合带到主函数中去
    public static void getPath(FTPClient ftp, String path, ArrayList<String> pathArray) throws IOException {
        FTPFile[] files = ftp.listFiles();
        for (FTPFile ftpFile : files) {
            if(ftpFile.getName().equals(".")||ftpFile.getName().equals(".."))continue;
            if(ftpFile.isDirectory()){//如果是目录，则递归调用，查找里面所有文件
                path+="/"+ftpFile.getName();
                pathArray.add(path);
                ftp.changeWorkingDirectory(path);//改变当前路径
                getPath(ftp,path,pathArray);//递归调用
                path=path.substring(0, path.lastIndexOf("/"));//避免对之后的同目录下的路径构造作出干扰，
            }
        }
    }



    /*下载到指定的本地文件夹中，
        download(ftp, pathArray, "c:\\download");程序之前出了写错误，
        为了排查，我把下载分成两部分，
        第一部分先将所有目录创建完成，
        在第二个for循环中进行文件的下载。参数：ftp为FTPClient，pathArray为1中带出的路径集合，后面一个String为本地路径  */
    public static void download(FTPClient ftp,ArrayList<String> pathArray,String localRootPath) throws IOException{
        for (String string : pathArray) {
            String localPath=localRootPath+string;
            File localFile = new File(localPath);
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
        }
        for (String string : pathArray) {
            String localPath=localRootPath+string;//构造本地路径
            ftp.changeWorkingDirectory(string);
            FTPFile[] file=ftp.listFiles();
            for (FTPFile ftpFile : file) {
                if(ftpFile.getName().equals(".")||ftpFile.getName().equals(".."))continue;
                File localFile = new File(localPath);
                if(!ftpFile.isDirectory()){
                    OutputStream is = new FileOutputStream(localFile+"/"+ftpFile.getName());
                    ftp.retrieveFile(ftpFile.getName(), is);
                    is.close();
                }
            }
        }
    }


//测试的主函数，使用的ftpClient为org.apache.commons.net.ftp.FTPClient：
    public static void main(String[] args) throws SocketException, IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect("10.104.143.81");
        ftp.login("qxfwzx","qxfwzx123");
        System.out.println("aaa");
        int reply;
        reply = ftp.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            System.err.println("FTP server refused connection.");
            System.exit(1);
        }
        ftp.setBufferSize(1024);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
        String path="/wrf/";
        ArrayList<String> pathArray=new ArrayList<String>();
       // getPath(ftp,path,pathArray);
        System.out.println("ddd");
        System.out.println(pathArray);
        download(ftp, pathArray, "E:/nwp/yb");
        ftp.logout();
        ftp.disconnect();
    }

}
