package com.example.okhttp.Activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okhttp.tool.OkHttpRequest;
import com.example.okhttp.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button login;
    private EditText email;
    private EditText password;
    private ImageView imageView;
    private EditText checkbox;
    private TextView textView;

    private final int SETCHECKBOX = 1;
    private final int LOGINFAILED = 2;//登陆失败
    private final int LOGINSUCCESS = 3;//登陆成功
    private final int GETSORE_INQUIRY = 4;//获取到成绩



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();

    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SETCHECKBOX:
                    byte[] res = (byte[]) msg.obj;
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(res,0,res.length));
                    break;
                case LOGINFAILED:
                    Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                    break;
                case LOGINSUCCESS:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpRequest.getInitWebAdressData();
                            OkHttpRequest.requestOkhttpforPersonData();
                            while(! OkHttpRequest.isIsgetPersonDta());
                            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                            startActivity(intent);
                        }
                    }).start();
                    break;
            }
        }
    };

    public void InitView() {
        login = findViewById(R.id.login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        imageView = findViewById(R.id.image);
        checkbox = findViewById(R.id.checkbox);
        textView = findViewById(R.id.text);

        login.setOnClickListener(this);
        imageView.setOnClickListener(this);
        //初始化cookie和初始化验证码
        OkHttpRequest.InitCookieData();
        getcheckbox();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Login();
                break;
            case R.id.image:
                getcheckbox();
                break;
        }
    }

    /**
     * 获取验证码
     */
    public void getcheckbox(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpRequest.requestOkhttpforIdentifying_Code();
                while(!OkHttpRequest.isisGetIdentifying_Code());
                byte[] bts = OkHttpRequest.getIdentifying_code();
                Message message = Message.obtain();
                message.obj = bts;
                message.what = SETCHECKBOX;
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 登陆函数
     */
    public void Login(){

        if((email.getText().toString().trim()).length() <= 0){
            Toast.makeText(this,"请输入正确的账号!",Toast.LENGTH_SHORT).show();
            return;
        }
        if((password.getText().toString().trim()).length() <= 0){
            Toast.makeText(this,"请输入正确的密码!",Toast.LENGTH_SHORT).show();
            return;
        }
        if((checkbox.getText().toString().trim()).length() <= 0){
            Toast.makeText(this,"请输入正确的验证码!",Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpRequest.requestOkhttpforLoginhtml(email.getText().toString(),
                        password.getText().toString(),checkbox.getText().toString());
                while(OkHttpRequest.isLoginSuccessful() < 0);
                Message message = Message.obtain();
                if(OkHttpRequest.isLoginSuccessful() == 2){
                    message.what = LOGINFAILED;//登陆失败
                }
                if(OkHttpRequest.isLoginSuccessful() == 1){
                    message.what = LOGINSUCCESS;//登陆成功
                }
                handler.sendMessage(message);
            }
        }).start();
    }
    //获取成绩
    public void getScore_inquiry(){
        getScore_inquiry.start();
        sendM_ifgetscore_inquiry.start();
    }

    /**
     * 请求获取成绩的线程
     * @return
     */
    Thread getScore_inquiry = new Thread(new Runnable() {
        @Override
        public void run() {
            OkHttpRequest.requestOkhttpforscore_inquiry();
        }
    });
    /**
     * 当获取成绩成功，发送消息线程
     */
    Thread sendM_ifgetscore_inquiry = new Thread(new Runnable() {
        @Override
        public void run() {
            while(!OkHttpRequest.isgetscore_inquiry());
            Message message = Message.obtain();
            message.what = GETSORE_INQUIRY;
            handler.sendMessage(message);
        }
    });

}
