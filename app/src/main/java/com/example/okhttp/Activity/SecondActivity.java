package com.example.okhttp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okhttp.tool.OkHttpRequest;
import com.example.okhttp.R;

import java.util.HashMap;

/**
 * Created by 杨豪 on 2018/2/26.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView student_num;
    private TextView student_name;
    private TextView student_sex;
    private TextView student_birth;
    private TextView student_minzu;
    private TextView student_jiguan;
    private TextView student_laiyuansheng;
    private TextView student_xueyuan;
    private TextView student_zhuanyemingcheng;
    private TextView student_xingzhengban;
    private TextView student_xuezhi;
    private TextView student_dangqiansuozaiji;
    private TextView student_kaoshenghao;
    private TextView student_shenfenzhenghao;
    private TextView student_xuelicengci;

    private Button score_search;
    private Button stu_per_schedules;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
    }

    public void initView(){
        student_num = findViewById(R.id.student_num);
        student_name = findViewById(R.id.student_name);
        student_sex = findViewById(R.id.student_sex);
        student_birth = findViewById(R.id.student_birth);
        student_minzu = findViewById(R.id.student_minzu);
        student_jiguan = findViewById(R.id.student_jiguan);
        student_laiyuansheng = findViewById(R.id.student_laiyuansheng);
        student_xueyuan = findViewById(R.id.student_xueyuan);
        student_zhuanyemingcheng = findViewById(R.id.student_zhuanyemingcheng);
        student_xingzhengban = findViewById(R.id.student_xingzhengban);
        student_xuezhi = findViewById(R.id.student_xuezhi);
        student_dangqiansuozaiji = findViewById(R.id.student_dangqiansuozaiji);
        student_kaoshenghao = findViewById(R.id.student_kaoshenghao);
        student_shenfenzhenghao = findViewById(R.id.student_shenfenzhenghao);
        student_xuelicengci = findViewById(R.id.student_xuelicengci);

        /**
         * 更改标题
         */
        TextView title = findViewById(R.id.title);
        title.setText(OkHttpRequest.getStudentName()+",你好！");

        score_search = findViewById(R.id.score_search);
        score_search.setOnClickListener(this);
        stu_per_schedules = findViewById(R.id.Stu_per_schedules);
        stu_per_schedules.setOnClickListener(this);

    }

    public void initData(){

        HashMap<String,String> student = OkHttpRequest.getPersonData();
        Log.d("==========信息=======",student.toString());
        Log.d("-----","student.get(\"学号\") = "+student.get("学号："));
        student_num.setText(student.get("学号："));
        student_name.setText(student.get("姓名："));
        student_sex.setText(student.get("性别："));
        student_birth.setText(student.get("出生日期："));
        student_minzu.setText(student.get("民族："));
        student_jiguan.setText(student.get("籍贯："));
        student_laiyuansheng.setText(student.get("来源省："));
        student_xueyuan.setText(student.get("学院："));
        student_zhuanyemingcheng.setText(student.get("专业名称："));
        student_xingzhengban.setText(student.get("行政班："));
        student_xuezhi.setText(student.get("学制："));
        student_dangqiansuozaiji.setText(student.get("当前所在级："));
        student_kaoshenghao.setText(student.get("考生号："));
        student_shenfenzhenghao.setText(student.get("身份证号："));
        student_xuelicengci.setText(student.get("学历层次："));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.score_search:
                score_search();
                break;
            case R.id.Stu_per_schedules:
                getStu_per_schedules();
                break;
        }
    }



    /**
     * 查询成绩
     * 通过网络请求先获取成绩，再启动活动
     */
    private void score_search(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!OkHttpRequest.isgetscore_inquiry()){
                    OkHttpRequest.requestOkhttpforscore_inquiry();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!OkHttpRequest.isgetscore_inquiry());
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);

                startActivity(intent);
            }
        }).start();
    }

    private void getStu_per_schedules() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!OkHttpRequest.isgetstu_per_schedules()){
                    OkHttpRequest.requestOkhttpforStudents_personal_schedules();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!OkHttpRequest.isgetstu_per_schedules());
                Intent intent = new Intent(SecondActivity.this,ForthActivity.class);
                Log.d("---","2222222222222");
                startActivity(intent);
            }
        }).start();

    }


}
