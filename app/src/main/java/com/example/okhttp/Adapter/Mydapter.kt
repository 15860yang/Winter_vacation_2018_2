package com.example.okhttp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.example.okhttp.R
import com.example.okhttp.tool.OkHttpRequest

import java.util.ArrayList

/**
 * Created by 杨豪 on 2018/2/27.
 */

class Mydapter(private val context: Context) : RecyclerView.Adapter<Mydapter.MyViewHolder>() {

    private var isAllscore = true
    private var year: String? = null

    private val alllists: ArrayList<ArrayList<String>>
    private val singlexueqiscorelist = ArrayList<ArrayList<String>>()

    init {
        this.alllists = OkHttpRequest.score_inquiryList
        this.isAllscore = true
    }

    fun setIsShowAllscore(isShowAllscore: Boolean, year: String) {
        this.year = year
        this.isAllscore = isShowAllscore
        singlexueqiscorelist.clear()
        for (strings in alllists) {
            if (strings[0] + "学年度第" + strings[1] + "学期" == year) {
                singlexueqiscorelist.add(strings)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_score, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (isAllscore) {
            setscoreifAll(holder, position)
            setscore(holder, position)
        } else {
            setSinglexueqiscore(holder, position)
        }

    }

    /**
     * 设置某一学期成绩
     * @param holder
     * @param position
     */
    private fun setSinglexueqiscore(holder: MyViewHolder, position: Int) {
        holder.score_No_01.visibility = View.GONE
        holder.score_column.visibility = View.GONE

        holder.score_Subject_name.text = singlexueqiscorelist[position][3]
        holder.score_nature.text = singlexueqiscorelist[position][4]
        holder.score_credit.text = singlexueqiscorelist[position][6]
        holder.score_GPA.text = singlexueqiscorelist[position][7]
        holder.score_score.text = singlexueqiscorelist[position][8]

    }

    /**
     * 设置全部成绩
     * @param holder
     * @param position
     */
    private fun setscoreifAll(holder: MyViewHolder, position: Int) {
        if (position == 0) {
            tochange = true
        } else {
            if (alllists[position][1] != alllists[position - 1][1]) {
                tochange = true
            } else {
                tochange = false
            }
        }
        if (tochange) {
            holder.score_No_01.visibility = View.VISIBLE
            holder.score_No_01.text = alllists[position][0] +
                    "学年度第" + alllists[position][1] + "学期"
            holder.score_column.visibility = View.VISIBLE
        } else {
            holder.score_No_01.visibility = View.GONE
            holder.score_column.visibility = View.GONE
        }
    }

    /**
     * 设置成绩
     * @param holder
     * @param position
     */
    private fun setscore(holder: MyViewHolder, position: Int) {
        //学年，学期，课程代码，课程名称，课程性质，课程归属，学分，绩点，成绩，辅修标记，补考成绩，重修成绩，开课学院，备注，重修标记
        holder.score_Subject_name.text = alllists[position][3]
        holder.score_nature.text = alllists[position][4]
        holder.score_credit.text = alllists[position][6]
        holder.score_GPA.text = alllists[position][7]
        holder.score_score.text = alllists[position][8]
    }

    override fun getItemCount(): Int {
        return if (isAllscore)
            alllists.size
        else
            singlexueqiscorelist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var score_No_01: TextView
        var score_column: LinearLayout

        var score_Subject_name: TextView
        var score_nature: TextView
        var score_credit: TextView
        var score_GPA: TextView
        var score_score: TextView

        init {
            score_No_01 = itemView.findViewById(R.id.score_No_01)
            score_column = itemView.findViewById(R.id.score_column)
            score_Subject_name = itemView.findViewById(R.id.score_Subject_name)
            score_nature = itemView.findViewById(R.id.score_nature)
            score_credit = itemView.findViewById(R.id.score_credit)
            score_GPA = itemView.findViewById(R.id.score_GPA)
            score_score = itemView.findViewById(R.id.score_score)
        }
    }

    companion object {

        private var tochange = false
    }

}
