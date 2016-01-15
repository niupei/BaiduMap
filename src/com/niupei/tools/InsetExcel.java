package com.niupei.tools;

import com.niupei.bean.Book;
import com.niupei.bean.City;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/14.
 */
public class InsetExcel {
    private Book book;
    private City city;

    public void excel() {

        WritableWorkbook wbook = null;
        try {
            wbook = Workbook.createWorkbook(new File("广东.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获得城市名称
        ArrayList<City> arrayListCity;
        GetCity sheng = new GetCity();
        arrayListCity = sheng.getCity();

        for (int i = 0; i < arrayListCity.size(); i++) {
            city = new City();
            city = arrayListCity.get(i);
            String cityName = city.getName();
            //输出市区名称
            System.out.println(cityName);

            //解析URL
            DealUrl dealUrl = new DealUrl();
            ArrayList<Book> mArrayListBook = dealUrl.getUrl(cityName);

            //arg: 表名，表的位置
            WritableSheet sheet = wbook.createSheet(cityName, i);

            //创建单元格对象，参数：列 行 值
            //循环导出数组
            for (int j = 0; j < mArrayListBook.size(); j++) {
                book = new Book();
                book = mArrayListBook.get(j);

                System.out.println(book.getName());
                System.out.println(book.getAddress());
                System.out.println(book.getTelephone());

                //准备数据插入的位置
                Label la1 = new Label(0, j, book.getName());
                Label la2 = new Label(1, j, book.getAddress());
                Label la3 = new Label(2, j, book.getTelephone());

                //插入值
                try {
                    sheet.addCell(la1);
                    sheet.addCell(la2);
                    sheet.addCell(la3);

                } catch (WriteException e) {
                    e.printStackTrace();
                }

            }//for j
        } //for i

        //关闭流
        //输出文件到目标路径
        try {
            wbook.write();
            wbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}

