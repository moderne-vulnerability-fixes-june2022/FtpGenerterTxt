package com.hxgis.webService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Li Hong on 2018/1/19 0019.
 */

/*生成文本
* 参数1:文件路径地址
* 参数二：文件内容：例如
* {"Success":true,"ErrorMsg":"","Data":[{"wtid":421224001,"windspeed":"3.80","retime":"\/Date(1512112543401)\/"},{"wtid":421224002,"windspeed":"4.80","retime":"\/Date(1512112543401)\/"},{"wtid":421224003,"windspeed":"3.70","retime":"\/Date(1512112543401)\/"},{"wtid":421224004,"windspeed":"3.80","retime":"\/Date(1512112543401)\/"},{"wtid":421224005,"windspeed":"3.80","retime":"\/Date(1512112543401)\/"},{"wtid":421224006,"windspeed":"2.30","retime":"\/Date(1512112543401)\/"},{"wtid":421224007,"windspeed":"2.90","retime":"\/Date(1512112543401)\/"},{"wtid":421224008,"windspeed":"2.20","retime":"\/Date(1512112543401)\/"},{"wtid":421224009,"windspeed":"3.50","retime":"\/Date(1512112543401)\/"},{"wtid":421224010,"windspeed":"3.40","retime":"\/Date(1512112543401)\/"},{"wtid":421224011,"windspeed":"4.80","retime":"\/Date(1512112543401)\/"},{"wtid":421224012,"windspeed":"4.20","retime":"\/Date(1512112543401)\/"},{"wtid":421224013,"windspeed":"2.70","retime":"\/Date(1512112543401)\/"},{"wtid":421224014,"windspeed":"2.50","retime":"\/Date(1512112543401)\/"},{"wtid":421224015,"windspeed":"3.10","retime":"\/Date(1512112543401)\/"},{"wtid":421224016,"windspeed":"4.80","retime":"\/Date(1512112543401)\/"}]}
* */
public class GenerateText {

    public static void WriteStringToFile(String filePath,String content) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = new String(); //原有txt内容
        String s1 = new String();//内容更新
        try {
            File f = new File(filePath);
            if (f.exists()) {
                System.out.print("文件存在");
            } else {
                System.out.print("文件不存在");
                f.createNewFile();// 不存在则创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((str = input.readLine()) != null) {
                s1 += str + "\r\n";
            }
            System.out.println(s1);
            input.close();
            s1 += "\r\n" + simpleDateFormat.format(date) + "\r\n" + content;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
