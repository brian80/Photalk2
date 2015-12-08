package com.study.photalk2;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MemberAsyncTask extends AsyncTask<String, Void, Object>{
    String TAG=this.getClass().getName();
    String host="http://70.12.109.50:8080";
    URL url;
    HttpURLConnection con;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();
        try {
            /*Header 정보 셋팅*/
            url = new URL(host+params[0]);
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true); /*출력가능하도록 설정*/
            con.setDoInput(true);/*입력가능하도록 설정*/
            con.connect();

            /*Body setting */
            PrintWriter writer=null;
            writer = new PrintWriter(new OutputStreamWriter(con.getOutputStream(),"utf-8"));
            writer.append(params[1]);
            writer.flush();
            writer.close();

            int responseCode=0;
            responseCode=con.getResponseCode(); /*요청이 일어남!!*/


            /*서버가 요청을 제대로 처리했다면..*/
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader buffr = null;
                buffr=new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
                String data=null;


                while(true){
                    data=buffr.readLine();
                    if(data==null)break;
                    sb.append(data);
                }
                buffr.close();

                con.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object obj) {
        super.onPostExecute(obj);

        /*서버에서 전송되어온 제이슨을 분석하여 알맞는 디자인을 보여주자!!*/
        try {
            JSONObject jsonObject = new JSONObject((String)obj);
            String requestCode=jsonObject.getString("requestCode");

            switch (requestCode){
                case "insert":          insertView(obj);break;
                case "selectAll":      selectView(obj);break;
                case "select":          selectView(obj);break;
                case "update":        updateView(obj);break;
                case "delete":          deleteView(obj);break;
                case "loginCheck":  loginCheckView(obj);break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*알맞는 뷰처리 메서드 정의*/
    public void insertView(Object obj){
        Log.d(TAG, "insertView 호출");
    }
    public void selectAllView(Object obj){
        Log.d(TAG, "selectAllView 호출");
    }
    public void selectView(Object obj){
        Log.d(TAG, "selectView 호출");
    }
    public void updateView(Object obj){
        Log.d(TAG, "updateView 호출");
    }
    public void deleteView(Object obj){
        Log.d(TAG, "deleteView 호출");
    }
    public void loginCheckView(Object obj){
        Log.d(TAG, "loginCheckView 호출");
    }
}













