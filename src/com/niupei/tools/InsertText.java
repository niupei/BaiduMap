package com.niupei.tools;

import java.io.*;

/**
 * 写入到text
 */
public class InsertText {


    /**
     * @param str 写入的内容
     * @param name 文本名称
     */
    public void write(String str,String name) {

        File file = new File(name+".txt");
        if (!file.exists()) {
            try {

                file.createNewFile();

                insetData(file, str);

                System.out.println("写入完成");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            insetData(file, str);
            System.out.println("写入完成");
        }
    }

    //写入
    private void insetData(File file, String str) {

        try {
            //arg1:文件
            //arg2:true追加写入,默认覆盖
            FileOutputStream fos = new FileOutputStream(file,true);
            //写入流(字符流)
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            //缓冲写入
            BufferedWriter bw = new BufferedWriter(osw);


            //开始一行一行写入
//          bw.write("《赠东邻王十三》\n");
            bw.write(str);

            bw.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
