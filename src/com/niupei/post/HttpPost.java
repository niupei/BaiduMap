package com.niupei.post;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iwan on 16/1/10.
 */
public class HttpPost {

    public static void main(String[] args){

        new Post().start();

    }
}

//http://api.map.baidu.com/place/v2/search
// ak=xMIlYtbWA3ImOX40wnrhDCxt
// &output=json
// &query=%E6%B1%BD%E8%BD%A6%E7%BB%B4%E4%BF%AE%E5%8E%82
// &page_size=10
// &page_num=0
// &scope=1
// &region=%E5%B9%BF%E5%B7%9E

class Post extends Thread{

    //创建HttpClient对象
    HttpClient client = HttpClients.createDefault();

    @Override
    public void run() {

        //post请求
        org.apache.http.client.methods.HttpPost post = new org.apache.http.client.methods.HttpPost("http://api.map.baidu.com/place/v2/search");


        try {
//keyfrom=cheboa-test&key=1383843413&type=data&doctype=<doctype>&version=1.1&q=要翻译的文本

            List<BasicNameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("ak","xMIlYtbWA3ImOX40wnrhDCxt"));
            parameters.add(new BasicNameValuePair("output","json"));
            parameters.add(new BasicNameValuePair("query","%E6%B1%BD%E8%BD%A6%E7%BB%B4%E4%BF%AE%E5%8E%82"));
            parameters.add(new BasicNameValuePair("page_size","10"));
            parameters.add(new BasicNameValuePair("page_num","0"));
            parameters.add(new BasicNameValuePair("scope","1"));
            parameters.add(new BasicNameValuePair("region","%E5%B9%BF%E5%B7%9E"));



            post.setEntity(new UrlEncodedFormEntity(parameters));
            //连接
            HttpResponse response = client.execute(post);
            //实体
            HttpEntity entity = response.getEntity();
            //实体工具
            String result = EntityUtils.toString(entity,"UTF-8");

            //输出
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

