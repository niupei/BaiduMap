package com.niupei.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.niupei.bean.City;
import com.niupei.utils.WriteToExcel;
import jxl.write.WritableWorkbook;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/14.
 */
public class GetCity {



    public ArrayList<City> getCity(){
        ArrayList<City> arrayListCity = new ArrayList<City>();

        try {
            //json解析器
            JsonParser parser = new JsonParser();
            //json对象
            JsonObject object = (JsonObject) parser.parse(new FileReader("city/Guangdong.json"));
            //输出根元素total
//            System.out.println("total=" + object.get("total").getAsString());
            //解析json数组 results[]
            JsonArray array = object.get("results").getAsJsonArray();

            //输出数组内的对象
            for (int i = 0; i < array.size(); i++) {
                JsonObject subObject = array.get(i).getAsJsonObject();

                //城市名
                String cityName = subObject.get("name").getAsString();
//                System.out.println("name="+ subObject.get("name").getAsString());

                City city = new City();
                city.setName(cityName);

                arrayListCity.add(city);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arrayListCity;
    }
}
