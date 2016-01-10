package com.niupei.get;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by iwan on 16/1/10.
 */
public class ReadByGet extends Thread{

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;



    @Override
    public void run() {

        try {


            //定义URL对象

            URL url = new URL("http://api.map.baidu.com/place/v2/search?" +
                    "ak=xMIlYtbWA3ImOX40wnrhDCxt" +
                    "&output=json" +
                    "&query=%E6%B1%BD%E8%BD%A6%E7%BB%B4%E4%BF%AE%E5%8E%82" +
                    "&page_size=10" +
                    "&page_num=0" +
                    "&scope=1" +
                    "&region=%E5%B9%BF%E5%B7%9E");

            //打开连接
            //定义URLConnection对象接收返回值
            URLConnection connection = url.openConnection();
            //获得网络连接的输入流(字节流)
            InputStream is = connection.getInputStream();
            //准备读取数据流,并指字符流编码是utf-8(字符流)
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            //缓冲读取
            BufferedReader br = new BufferedReader(isr);


            //临时保存数据
            String line;
            //字符串生成器
            StringBuilder builder = new StringBuilder();

            while ((line = br.readLine()) != null) {

                builder.append(line);
            }

            //关闭
            br.close();
            isr.close();
            is.close();

            //输出
//            System.out.println(builder.toString());

            dealData(builder);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //解析
    private void dealData(StringBuilder builder) {

        //json解析器
        //作用:解析字符串和输入流
        JsonParser parser = new JsonParser();

        //json对象
        //可指定文件或者网络
        JsonObject object = (JsonObject) parser.parse(builder.toString());

        //总数
        System.out.println("total="+object.get("total").getAsString());

        //解析内部数组
        JsonArray array = object.get("results").getAsJsonArray();
        String name;
        String address;
        String telephone = null;
        StringBuilder itemBuilder = new StringBuilder();
        for (int i = 0; i <array.size() ; i++) {
//            System.out.println("---------分割线--------");

            JsonObject subObject = array.get(i).getAsJsonObject();
            name = subObject.get("name").getAsString();
            address = subObject.get("address").getAsString();

//            System.out.println("name="+name);
//            System.out.println("address="+ address);
            try {
                telephone = subObject.get("telephone").getAsString();
//                System.out.println("telephone="+telephone);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //结果
            itemBuilder.append(name+":"+address+":"+ telephone+"\n");

        }
        String str = itemBuilder.toString();

        System.out.println(str);

        //写入文件
        new WriteFile().write(str);

    }
}
