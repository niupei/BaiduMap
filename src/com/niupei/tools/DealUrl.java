package com.niupei.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.niupei.bean.Book;
import com.niupei.bean.City;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/14.
 */
public class DealUrl {

    private String uCityName;
    ArrayList<Book> arrayListBook;
    private Book book;

    public ArrayList<Book> getUrl(String cityName) {

        arrayListBook = new ArrayList<Book>();

        try {
            //城市名称  转URL码
            uCityName = URLEncoder.encode(cityName, "UTF-8");

            //维修厂总数 分页
            for (int k = 0; k < 50; k++) {

                String pageNum = String.valueOf(k);


                URL url = new URL("http://api.map.baidu.com/place/v2/search?" +
                        "ak=xMIlYtbWA3ImOX40wnrhDCxt" +
                        "&output=json" +
                        "&query=%E6%B1%BD%E8%BD%A6%E7%BB%B4%E4%BF%AE%E5%8E%82" +
                        "&page_size=10" +  //一页几条
                        "&page_num="+pageNum +
                        "&scope=1" +
//                  "&region=%E5%B9%BF%E5%B7%9E");
                        "&region=" + uCityName);

                System.out.println(url.toString());
                //打开Url连接
                //定义URLConnection对象接收返回值
                URLConnection connection = url.openConnection();

                //获得输入流(字节流)
                InputStream is = connection.getInputStream();

                //准备读取数据流,并指字符流编码是utf-8(字符流)
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");

                //缓冲读取
                BufferedReader br = new BufferedReader(isr);

                //临时保存数据对象
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

                //json解析器
                JsonParser mJsonParser = new JsonParser();
                //把JsonElement强制转换成JsonObject
                JsonObject mJsonObject = (JsonObject) mJsonParser.parse(builder.toString());

                //输出总数
                System.out.println(mJsonObject.get("total").getAsString());

                //解析子数组
                JsonArray mJsonArray = mJsonObject.get("results").getAsJsonArray();

                for (int j = 0; j < mJsonArray.size(); j++) {
                    //子对象
                    JsonObject mSubJsonObject = mJsonArray.get(j).getAsJsonObject();

                    //封装到Book对象里
                    book = new Book();
                    book.setName(mSubJsonObject.get("name").getAsString());
                    book.setAddress(mSubJsonObject.get("address").getAsString());
                    try {
                        book.setTelephone(mSubJsonObject.get("telephone").getAsString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    arrayListBook.add(book);
                } //for j

            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayListBook;
    }
}


