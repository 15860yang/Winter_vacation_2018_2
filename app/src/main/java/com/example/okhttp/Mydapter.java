package com.example.okhttp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.okhttp.tool.OkHttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 杨豪 on 2018/2/27.
 */

public class Mydapter extends RecyclerView.Adapter<Mydapter.MyViewHolder> {

    private static boolean tochange = false;

    private ArrayList<ArrayList<String>> lists;
    private Context context;
    public Mydapter(Context context){
        this.lists = OkHttpRequest.getScore_inquiryList();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.single_score,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(position == 0){
            tochange = true;
        }else{
            if(!lists.get(position).get(1).equals( lists.get(position-1).get(1) )){
                tochange = true;
            }else {
                tochange = false;
            }
        }
        if(tochange){
            holder.score_No_01.setVisibility(View.VISIBLE);
            holder.score_No_01.setText(lists.get(position).get(0) +
                 "学年度第"+ lists.get(position).get(1)+"学期");
            holder.score_column.setVisibility(View.VISIBLE);
        }else {
            holder.score_No_01.setVisibility(View.GONE);
            holder.score_column.setVisibility(View.GONE);
        }
        setscore(holder,position);
    }

    /**
     * 设置成绩
     * @param holder
     * @param position
     */
    private void setscore(MyViewHolder holder,int position){
        //学年，学期，课程代码，课程名称，课程性质，课程归属，学分，绩点，成绩，辅修标记，补考成绩，重修成绩，开课学院，备注，重修标记
        holder.score_Subject_name.setText(lists.get(position).get(3));
        holder.score_nature.setText(lists.get(position).get(4));
        holder.score_credit.setText(lists.get(position).get(6));
        holder.score_GPA.setText(lists.get(position).get(7));
        holder.score_score.setText(lists.get(position).get(8));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView score_No_01;
        LinearLayout score_column;

        TextView score_Subject_name;
        TextView score_nature;
        TextView score_credit;
        TextView score_GPA;
        TextView score_score;

        public MyViewHolder(View itemView) {
            super(itemView);
            score_No_01 = itemView.findViewById(R.id.score_No_01);
            score_column = itemView.findViewById(R.id.score_column);

            score_Subject_name = itemView.findViewById(R.id.score_Subject_name);
            score_nature = itemView.findViewById(R.id.score_nature);
            score_credit = itemView.findViewById(R.id.score_credit);
            score_GPA = itemView.findViewById(R.id.score_GPA);
            score_score = itemView.findViewById(R.id.score_score);
        }
    }

}
