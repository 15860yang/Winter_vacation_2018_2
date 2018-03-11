package com.example.okhttp.Activity.ThirdActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import com.example.okhttp.Adapter.Mydapter
import com.example.okhttp.R
import com.example.okhttp.tool.OkHttpRequest

import java.util.ArrayList


class ThirdActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var toolbar: Toolbar? = null
    private var scorerecyclerView: RecyclerView? = null

    private var spinner: AppCompatSpinner? = null

    private var strings: ArrayList<String>? = null


    private var mydapter: Mydapter? = null
    init {
        mydapter = Mydapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        initView()
    }

    fun initView() {
        toolbar = findViewById(R.id.thirdtoobar)
        setSupportActionBar(toolbar)
        toolbar!!.subtitle = "成绩查询"

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val title = findViewById<TextView>(R.id.title)
        title.text = OkHttpRequest.studentName + ",你好！"

        scorerecyclerView = findViewById(R.id.allscore_RecycleView)
        scorerecyclerView!!.layoutManager = LinearLayoutManager(this)
        scorerecyclerView!!.itemAnimator = DefaultItemAnimator()

        scorerecyclerView!!.adapter = mydapter

        strings = ArrayList()
        strings!!.add("历年成绩")
        for (list in OkHttpRequest.score_inquiryList) {
            val s = list[0] + "学年度第" + list[1] + "学期"
            if (strings!!.size > 0 && strings!![strings!!.size - 1] != s) {
                strings!!.add(s)
            } else if (strings!!.size == 0) {
                strings!!.add(s)
            }
        }
        spinner = findViewById(R.id.thirdSpinner)
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, strings!!)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = adapter
        spinner!!.setSelection(0)
        spinner!!.onItemSelectedListener = this
        spinner!!.visibility = View.VISIBLE

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val s = strings!![position]
        if (s == "历年成绩") {

            mydapter!!.setIsShowAllscore(true, "")
        } else {
            mydapter!!.setIsShowAllscore(false, s)
        }
        mydapter!!.notifyDataSetChanged()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
