package com.study.photalk2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MemberAsync extends AsyncTask<String, Void, Object> {
    String TAG=this.getClass().getName();
    String ip="http://70.12.109.50:8080";
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
            url=new URL(ip+params[0]);
            con=(HttpURLConnection)url.openConnection();

            /* Set Header */
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();

            /* Set Body */
            PrintWriter writer=null;
            try {
                writer=new PrintWriter(new OutputStreamWriter(con.getOutputStream(),"utf-8"));
                writer.append(params[1]);
                writer.append("\r\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /* Get Result Code*/
            int code=0;
            code=con.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                InputStream in=con.getInputStream();
                BufferedReader buffr = new BufferedReader(new InputStreamReader(in, "utf-8"));


                String data=null;
                while(true){
                    data=buffr.readLine();
                    if(data==null)break;
                    sb.append(data);
                }
                buffr.close();
            }
            con.disconnect();
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

        String data=(String)obj;
        try {
            JSONObject jsonObject = new JSONObject(data);
            String requestCode=jsonObject.getString("requestCode");

            switch (requestCode){
                case "insert":insertView(obj);                 break;
                case "loginCheck":loginCheckView(obj);  break;
                case "selectAll":selectAllView(obj);          break;
                case "select":selectView(obj);                 break;
                case "update":updateView(obj);             break;
                case "delete":deleteView(obj);                break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*회원가입*/
    public void insertView(Object obj){
        try {
            JSONObject jsonObject = new JSONObject((String)obj);
            String resultData=jsonObject.getString("resultData");
            Log.d(TAG, "입력 결과 처리입니다."+resultData);

            if(resultData.equals("success")){

            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*로그인 체크*/
    public void loginCheckView(Object obj){
        Log.d(TAG, "로그인 결과 처리입니다.");
    }

    /*회원목록*/
    public void selectAllView(Object obj){
        Log.d(TAG, "회원목록 결과 처리입니다.");
    }

    /*회원정보 1건 조회*/
    public void selectView(Object obj){
        Log.d(TAG, "1건 조회 결과 처리입니다.");
    }
    /*회원수정*/
    public void updateView(Object obj){
        Log.d(TAG, "수정 결과 처리입니다.");
    }
    /*회원삭제*/
    public void deleteView(Object obj){
        Log.d(TAG, "삭제 결과 처리입니다.");
    }
}
