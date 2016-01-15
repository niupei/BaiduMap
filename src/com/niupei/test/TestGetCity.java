package com.niupei.test;

import com.niupei.bean.City;
import com.niupei.tools.GetCity;

import java.util.ArrayList;

/**
 * 输出城市名称
 */
public class TestGetCity {

    private static City city;

    public static void main(String[] args){

        ArrayList<City> arrayListCity;
        GetCity sheng = new GetCity();
        arrayListCity = sheng.getCity();

        for (int i = 0; i < arrayListCity.size(); i++) {
            city = new City();
            city = arrayListCity.get(i);
            String cityName = city.getName();
            System.out.println(cityName);

        }
    }
}
