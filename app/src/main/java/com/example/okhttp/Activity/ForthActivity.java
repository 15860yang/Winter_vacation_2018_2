package com.example.okhttp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.okhttp.R;
import com.example.okhttp.tool.OkHttpRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class ForthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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

    private AppCompatSpinner select_kebiao_Spinner;
    private TextView select_kebiao_text;

    private static HashMap<String,ArrayList<String>> stu_per_schedules = new HashMap<>();
    private static ArrayList<String> strings;



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
        weekof_Mon_01.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Mon_02 = findViewById(R.id.weekof_Mon_02);
        weekof_Mon_02.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Mon_03 = findViewById(R.id.weekof_Mon_03);
        weekof_Mon_03.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Mon_04 = findViewById(R.id.weekof_Mon_04);
        weekof_Mon_04.setMovementMethod(ScrollingMovementMethod.getInstance());

        weekof_Thu_01 = findViewById(R.id.weekof_Thu_01);
        weekof_Thu_01.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Thu_02 = findViewById(R.id.weekof_Thu_02);
        weekof_Thu_02.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Thu_03 = findViewById(R.id.weekof_Thu_03);
        weekof_Thu_03.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Thu_04 = findViewById(R.id.weekof_Thu_04);
        weekof_Thu_04.setMovementMethod(ScrollingMovementMethod.getInstance());

        weekof_Wed_01 = findViewById(R.id.weekof_Wed_01);
        weekof_Wed_01.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Wed_02 = findViewById(R.id.weekof_Wed_02);
        weekof_Wed_02.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Wed_03 = findViewById(R.id.weekof_Wed_03);
        weekof_Wed_03.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Wed_04 = findViewById(R.id.weekof_Wed_04);
        weekof_Wed_04.setMovementMethod(ScrollingMovementMethod.getInstance());

        weekof_Thr_01 = findViewById(R.id.weekof_Thr_01);
        weekof_Thr_01.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Thr_02 = findViewById(R.id.weekof_Thr_02);
        weekof_Thr_02.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Thr_03 = findViewById(R.id.weekof_Thr_03);
        weekof_Thr_03.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Thr_04 = findViewById(R.id.weekof_Thr_04);
        weekof_Thr_04.setMovementMethod(ScrollingMovementMethod.getInstance());

        weekof_Fir_01 = findViewById(R.id.weekof_Fir_01);
        weekof_Fir_01.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Fir_02 = findViewById(R.id.weekof_Fir_02);
        weekof_Fir_02.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Fir_03 = findViewById(R.id.weekof_Fir_03);
        weekof_Fir_03.setMovementMethod(ScrollingMovementMethod.getInstance());
        weekof_Fir_04 = findViewById(R.id.weekof_Fir_04);
        weekof_Fir_04.setMovementMethod(ScrollingMovementMethod.getInstance());

        Toolbar toolbar = findViewById(R.id.forthActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = findViewById(R.id.title);
        title.setText(OkHttpRequest.getStudentName()+",你好！");

        select_kebiao_text = findViewById(R.id.select_kebiao_text);
        select_kebiao_Spinner = findViewById(R.id.select_kebiao_Spinner);

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
        weekof_Thr_02.setText(stu_per_schedules.get("2----").get(4));
        weekof_Thr_03.setText(stu_per_schedules.get("3----").get(4));
        weekof_Thr_04.setText(stu_per_schedules.get("4----").get(4));

        weekof_Fir_01.setText(stu_per_schedules.get("1----").get(5));
        weekof_Fir_02.setText(stu_per_schedules.get("2----").get(5));
        weekof_Fir_03.setText(stu_per_schedules.get("3----").get(5));
        weekof_Fir_04.setText(stu_per_schedules.get("4----").get(5));


        strings = new ArrayList<>();
        for(String s:stu_per_schedules.get("realtyear")){
            strings.add(s+"学年度第一学期课表");
            strings.add(s+"学年度第二学期课表");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_kebiao_Spinner.setAdapter(adapter);
        select_kebiao_Spinner.setOnItemSelectedListener(this);
        select_kebiao_Spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        select_kebiao_text.setText("");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
