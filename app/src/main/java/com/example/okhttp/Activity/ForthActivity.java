package com.example.okhttp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.okhttp.R;
import com.example.okhttp.tool.OkHttpRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class ForthActivity extends AppCompatActivity {

    private TextView weekof_Mon_01;
    private TextView weekof_Mon_02;
    private TextView weekof_Mon_03;
    private TextView weekof_Mon_04;

    private TextView weekof_Thu_01;
    private TextView weekof_Thu_02;
    private TextView weekof_Thu_03;
    private TextView weekof_Thu_04;

    private TextView weekof_Wed_01;
    private TextView weekof_Wed_02;
    private TextView weekof_Wed_03;
    private TextView weekof_Wed_04;

    private TextView weekof_Thr_01;
    private TextView weekof_Thr_02;
    private TextView weekof_Thr_03;
    private TextView weekof_Thr_04;

    private TextView weekof_Fir_01;
    private TextView weekof_Fir_02;
    private TextView weekof_Fir_03;
    private TextView weekof_Fir_04;


    private static HashMap<String,ArrayList<String>> stu_per_schedules = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        Log.d("---","5555555555555555");
        initView();

        initData();
    }
    public void initView(){
        weekof_Mon_01 = findViewById(R.id.weekof_Mon_01);
        weekof_Mon_02 = findViewById(R.id.weekof_Mon_02);
        weekof_Mon_03 = findViewById(R.id.weekof_Mon_03);
        weekof_Mon_04 = findViewById(R.id.weekof_Mon_04);

        weekof_Thu_01 = findViewById(R.id.weekof_Thu_01);
        weekof_Thu_02 = findViewById(R.id.weekof_Thu_02);
        weekof_Thu_03 = findViewById(R.id.weekof_Thu_03);
        weekof_Thu_04 = findViewById(R.id.weekof_Thu_04);

        weekof_Wed_01 = findViewById(R.id.weekof_Wed_01);
        weekof_Wed_02 = findViewById(R.id.weekof_Wed_02);
        weekof_Wed_03 = findViewById(R.id.weekof_Wed_03);
        weekof_Wed_04 = findViewById(R.id.weekof_Wed_04);

        weekof_Thr_01 = findViewById(R.id.weekof_Thr_01);
        weekof_Thr_02 = findViewById(R.id.weekof_Thr_02);
        weekof_Thr_03 = findViewById(R.id.weekof_Thr_03);
        weekof_Thr_04 = findViewById(R.id.weekof_Thr_04);

        weekof_Fir_01 = findViewById(R.id.weekof_Fir_01);
        weekof_Fir_02 = findViewById(R.id.weekof_Fir_02);
        weekof_Fir_03 = findViewById(R.id.weekof_Fir_03);
        weekof_Fir_04 = findViewById(R.id.weekof_Fir_04);

        Toolbar toolbar = findViewById(R.id.forthActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = findViewById(R.id.title);
        title.setText(OkHttpRequest.getStudentName()+",你好！");

    }

    public void initData(){
        stu_per_schedules = OkHttpRequest.getStu_per_schedules();
        weekof_Mon_01.setText(stu_per_schedules.get("1----").get(1));
        weekof_Mon_02.setText(stu_per_schedules.get("2----").get(1));
        weekof_Mon_03.setText(stu_per_schedules.get("3----").get(1));
        weekof_Mon_04.setText(stu_per_schedules.get("4----").get(1));

        weekof_Thu_01.setText(stu_per_schedules.get("1----").get(2));
        weekof_Thu_02.setText(stu_per_schedules.get("2----").get(2));
        weekof_Thu_03.setText(stu_per_schedules.get("3----").get(2));
        weekof_Thu_04.setText(stu_per_schedules.get("4----").get(2));

        weekof_Wed_01.setText(stu_per_schedules.get("1----").get(3));
        weekof_Wed_02.setText(stu_per_schedules.get("2----").get(3));
        weekof_Wed_03.setText(stu_per_schedules.get("3----").get(3));
        weekof_Wed_04.setText(stu_per_schedules.get("4----").get(3));

        weekof_Thr_01.setText(stu_per_schedules.get("1----").get(4));
        weekof_Thr_01.setText(stu_per_schedules.get("2----").get(4));
        weekof_Thr_01.setText(stu_per_schedules.get("3----").get(4));
        weekof_Thr_01.setText(stu_per_schedules.get("4----").get(4));

        weekof_Fir_04.setText(stu_per_schedules.get("1----").get(5));
        weekof_Fir_04.setText(stu_per_schedules.get("2----").get(5));
        weekof_Fir_04.setText(stu_per_schedules.get("3----").get(5));
        weekof_Fir_04.setText(stu_per_schedules.get("4----").get(5));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
