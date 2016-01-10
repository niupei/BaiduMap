package com.niupei.post;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by iwan on 16/1/10.
 */
public class BaiduMap {

    public static void main(String[] args){


        new Shop().start();

    }
}


class Shop extends Thread{

    @Override
    public void run() {


        try {

            //http://api.map.baidu.com/place/v2/search?q=%E9%A5%AD%E5%BA%97&region=%E5%8C%97%E4%BA%AC&output=json&ak=E4805d16520de693a3fe707cdc962045


            //定义URL对象
            URL url = new URL("http://api.map.baidu.com/place/v2/search");
            //打开连接,
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //为连接设定参数
            connection.addRequestProperty("encoding", "UTF-8");
            //设为true,当前connection可以从网络获取数据
            connection.setDoInput(true);
            //设为true,当前connection可以向网络传输数据
            connection.setDoOutput(true);
            //connection访问方法
            connection.setRequestMethod("POST");


            //输出流(向服务器提交数据)
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            //向服务器提交数据(要求返回的数据格式是:doctype=xml / doctye=json)
            bw.write("ak=xMIlYtbWA3ImOX40wnrhDCxt" +
                    "&output=xml" +
                    "&query="+URLEncoder.encode("小吃", "UTF-8") +
                    "&page_size=10" +
                    "&page_num=0" +
                    "&scope=1" +
                    "&region"+
                    URLEncoder.encode("广州", "UTF-8"));

            //强制输出
            bw.flush();

            System.out.println(bw.toString());


            //输入流(下载)
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //临时存数据
            String line;
            //字符串生成器
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                //追加填充builder
                builder.append(line);
            }

            //关闭流

            br.close();
            isr.close();
            is.close();

            bw.close();
            osw.close();
            os.close();

            //输出数据
            System.out.println(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}