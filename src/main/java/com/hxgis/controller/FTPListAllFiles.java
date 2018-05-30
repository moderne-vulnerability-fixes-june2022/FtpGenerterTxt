
package com.hxgis.controller;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 列出FTP服务器上指定目录下面的所有文件 
 * @author BenZhou mailto:zhouzb@qq.com 
 * 原文地址:http://zhouzaibao.iteye.com/blog/362866  
 */
public class FTPListAllFiles {
    private static Logger logger = Logger.getLogger(FTPListAllFiles.class);
    public FTPClient ftp;
    public ArrayList<String> arFiles;

    /**
     * 重载构造函数 
     * @param isPrintCommmand 是否打印与FTPServer的交互命令 
     */
    public FTPListAllFiles(boolean isPrintCommmand){
        ftp = new FTPClient();
        arFiles = new ArrayList<String>();
        if(isPrintCommmand){
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        }
    }

    /**
     * 登陆FTP服务器 
     * @param host FTPServer IP地址 
     * @param port FTPServer 端口 
     * @param username FTPServer 登陆用户名 
     * @param password FTPServer 登陆密码 
     * @return 是否登录成功
     * @throws IOException
     */
    public boolean login(String host,int port,String username,String password) throws IOException{
        this.ftp.connect(host,port);
        if(FTPReply.isPositiveCompletion(this.ftp.getReplyCode())){
            if(this.ftp.login(username, password)){
                this.ftp.setControlEncoding("GBK");
                return true;
            }
        }
        if(this.ftp.isConnected()){
            this.ftp.disconnect();
        }
        return false;
    }

    /**
     * 关闭数据链接 
     * @throws IOException
     */
    public void disConnection() throws IOException{
        if(this.ftp.isConnected()){
            this.ftp.disconnect();
        }
    }

    /**
     * 递归遍历出目录下面所有文件 
     * @param pathName 需要遍历的目录，必须以"/"开始和结束 
     * @throws IOException
     */
    public void List(String pathName) throws IOException{
        if(pathName.startsWith("/")&&pathName.endsWith("/")){
            String directory = pathName;
            //更换目录到当前目录  
            this.ftp.changeWorkingDirectory(directory);
            FTPFile[] files = this.ftp.listFiles();
            for(FTPFile file:files){
                if(file.isFile()){
                    arFiles.add(directory+file.getName());
                }else if(file.isDirectory()){
                    List(directory+file.getName()+"/");
                }
            }
        }
    }

    /**
     * 递归遍历目录下面指定的文件名 
     * @param pathName 需要遍历的目录，必须以"/"开始和结束 
     * @param ext 文件的扩展名 
     * @throws IOException
     */
    public void List(String pathName,String ext) throws IOException{
        if(pathName.startsWith("/")&&pathName.endsWith("/")){
            String directory = pathName;
            //更换目录到当前目录  
            this.ftp.changeWorkingDirectory(directory);
            FTPFile[] files = this.ftp.listFiles();
            for(FTPFile file:files){
                if(file.isFile()){
                    if(file.getName().endsWith(ext)){
                        arFiles.add(directory+file.getName());
                    }
                }else if(file.isDirectory()){
                    List(directory+file.getName()+"/",ext);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
      /*  FTPListAllFiles f = new FTPListAllFiles(true);
        if(f.login("10.104.143.81", 21, "qxfwzx", "qxfwzx123")){
            f.List("/","TXT");
        }
        f.disConnection();
        Iterator<String> it = f.arFiles.iterator();
        while(it.hasNext()){
            logger.info(it.next());
        }*/
      download();
    }


    public static boolean download()throws Exception{
        boolean result = false;
        String downLocalPath ="E:/nwp/yb";//下载到本地目录
        String ftpPath = "/wrf/";//ftp目录
        FTPClient ftp = new FTPClient();
        ftp.connect("10.104.143.81",21);//指定ftp IP和端口
        ftp.login("qxfwzx", "qxfwzx123");//用户名和密码
        ftp.setControlEncoding("GBK");//解决中文乱码时使用
        int reply=ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {//是否连接
            ftp.disconnect();
            return result;
        }
        boolean changedir = ftp.changeWorkingDirectory(ftpPath);//指定ftp目录

        if(changedir){
            FTPFile[] files = ftp.listFiles();//加载文件
            OutputStream outputStream =null;
            for (FTPFile ff:files) {

                File localFile = new File(downLocalPath+"/"+ff.getName());
                outputStream = new FileOutputStream(localFile);
              //  ftp.retrieveFile(new String(files[i].getName().getBytes("GBK"),"iso-8859-1"), outputStream);
                ftp.retrieveFile(ff.getName(), outputStream);
                //outputStream.flush();
                outputStream.close();
            }
        }
        if(changedir){
            ftp.changeToParentDirectory();
        }
        if(ftp.isConnected()){
            ftp.logout();
            ftp.disconnect();
        }
        result = true;
        return result;
    }
}  