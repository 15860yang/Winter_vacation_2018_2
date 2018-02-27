package com.example.okhttp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.okhttp.Mydapter;
import com.example.okhttp.R;
import com.example.okhttp.tool.OkHttpRequest;


public class ThirdActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();
    }
    public void initView(){
        toolbar = findViewById(R.id.thirdtoobar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("成绩查询-历年成绩");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView title = findViewById(R.id.title);
        title.setText(OkHttpRequest.getStudentName()+",你好！");

        recyclerView = findViewById(R.id.score_RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Mydapter mydapter = new Mydapter(this);
        recyclerView.setAdapter(mydapter);




        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_01:
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
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
