package com.feicui.listviewdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private List<JsonBean.Results> mArrayList;
    private ListView listView;
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        new MyAsyncTask().execute();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for (int i = 0; i < mArrayList.size(); i++) {
            if (position == i) {
                url = mArrayList.get(position).getUrl();
                Log.d(TAG, "onItemClick: " + url);
            }
        }
        Intent intent = new Intent(this, Base_WebView.class);
        startActivity(intent);
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {
        //获取数据
        @Override
        protected String doInBackground(String... params) {
            HttpOnline httpOnline = new HttpOnline();
            String results = httpOnline.getData();
            return results;
        }
        // 解析数据

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            //字符串转换
            parseJson(json);
            ArrayList<JsonBean.Results> parsegson = (ArrayList<JsonBean.Results>) Parsegson(json);
            DataAapter dataAapter = new DataAapter(MainActivity.this, mArrayList);
            listView.setAdapter(dataAapter);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    //这是一个解析方法，
    private List<JsonBean.Results> Parsegson(String json) {
        mArrayList = new ArrayList<>();
        Gson gson = new Gson();
        //调用fromJson方法解析数据
        JsonBean bean = gson.fromJson(json, JsonBean.class);
        List<JsonBean.Results> data = bean.getResults();
        // 往数据集合中添加数据
        for (int i = 0; i < data.size(); i++) {
            mArrayList.add(data.get(i));
        }
        return mArrayList;
    }

    private String parseJson(String json) {
        List<JsonBean.Results> list = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(json);
            int count = (int) jsonObject.get("count");
            boolean error = (boolean) jsonObject.get("error");
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                String desc = (String) results.getJSONObject(i).get("desc");
                String publishedAt = (String) results.getJSONObject(i).get("publishedAt");
                String readability = (String) results.getJSONObject(i).get("readability");
                String type = (String) results.getJSONObject(i).get("type");
                String url = (String) results.getJSONObject(i).get("url");
                String who = (String) results.getJSONObject(i).get("who");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
