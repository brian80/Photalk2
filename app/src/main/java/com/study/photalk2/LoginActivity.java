package com.study.photalk2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }
    /*회원가입 요청 메서드 */
    public void join(){
        MemberAsyncTask memberAsyncTask = new MemberAsyncTask();
        memberAsyncTask.execute("/member/regist.do","email=zino1187@naver.com&pwd=1234");
    }


    public void btnClick(View view){
        if(view.getId() == R.id.bt_join){
            join();
        }else if(view.getId()==R.id.bt_login){

        }
    }
}
























