package com.feicui.listviewdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AAAAA on 2016/6/20.
 */
public class HttpOnline {
    private static final String TAG="HttpOnline";
    private StringBuffer sb=new StringBuffer();

    public String getData(){
        URL url=null;
        //获取网络地址
        try {
            url=new URL("http://gank.io/api/search/query/listview/category/Android/count/10/page/1");
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            //设置请求方式
            urlConnection.setRequestMethod("GET");
            //获取io流
            InputStream is=urlConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //创建缓冲区

            String line=null;
            while ((line = br.readLine()) != null){
                //append() 方法在被选元素的结尾（仍然在内部）插入指定内容。
                sb.append(line);
            }
        } catch (Exception e) {
            Log.d(TAG, "getData: 获取数据异常");
        }
        return sb.toString();
    }

}
