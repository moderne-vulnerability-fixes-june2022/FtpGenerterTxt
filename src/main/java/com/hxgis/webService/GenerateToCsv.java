package com.hxgis.webService;
import java.io.*;

/**
 * Created by Li Hong on 2018/1/12 0012.
 */
public class GenerateToCsv {
    /*
    * 判断文件是否存在，如果存在就追加信息，不存在就从新生成一个，并且添加头标题
    * 参数1：数据
    * 参数2：文件名
    * 参数3：文件地址
    * 参数4：内容标题
    * */

    /*
    * 文件过滤，如果文件存在，就追加数据，不存在的话就新创建一个文件，然后写入
    * */
    public static  void createFile(StringBuffer sb, String filename, String url , String title) {
        //System.out.println(sb.toString());
        try {
            File file = new File(url + filename + ".csv");
            StringBuffer stringBuffer = new StringBuffer();
            if(!file.exists()){
                System.out.println("创建文件！！！");
                file.createNewFile();
                stringBuffer.append(title+" \r\n");
                stringBuffer.append(sb);
                writeFileContent(file,stringBuffer);
            }else {
                writeFileContent(file,sb);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

/*文件的最后的写入*/
    public static boolean writeFileContent(File file ,StringBuffer buffer ) throws IOException{
        Boolean bool = false;
        FileWriter fileWriter = null;
        Writer writer = null;
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),"gbk"));
            bool = true;
            out.append(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           if( out!=null){
               out.close();
            }
        }
        return bool;
    }
}
