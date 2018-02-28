package com.example.okhttp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.okhttp.Adapter.Mydapter;
import com.example.okhttp.R;
import com.example.okhttp.tool.OkHttpRequest;

import java.util.ArrayList;


public class ThirdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Toolbar toolbar;
    private RecyclerView scorerecyclerView;
    private Mydapter mydapter;
    private AppCompatSpinner spinner;

    private ArrayList<String> strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();
    }
    public void initView(){
        toolbar = findViewById(R.id.thirdtoobar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("成绩查询");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView title = findViewById(R.id.title);
        title.setText(OkHttpRequest.getStudentName()+",你好！");

        scorerecyclerView = findViewById(R.id.allscore_RecycleView);
        scorerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        scorerecyclerView.setItemAnimator(new DefaultItemAnimator());
        mydapter = new Mydapter(this);
        scorerecyclerView.setAdapter(mydapter);

        strings = new ArrayList<>();
        strings.add("历年成绩");
        for(ArrayList<String> list: OkHttpRequest.getScore_inquiryList()){
            String s = list.get(0) +"学年度第"+ list.get(1)+"学期";
            if(strings.size()>0 && !strings.get(strings.size()-1).equals(s)){
                strings.add(s);
            }else if(strings.size() == 0){
                strings.add(s);
            }
        }
        spinner = findViewById(R.id.thirdSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
        spinner.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = strings.get(position);
        if(s.equals("历年成绩")){
           mydapter.setIsShowAllscore(true,null);
        }else {
            mydapter.setIsShowAllscore(false,s);
        }
        mydapter.notifyDataSetChanged();
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
