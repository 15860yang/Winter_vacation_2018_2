package com.example.okhttp.Activity.ThirdActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import com.example.okhttp.Adapter.Mydapter
import com.example.okhttp.R

import kotlin.collections.ArrayList


class ThirdActivity : AppCompatActivity(), IThirdVIew,AdapterView.OnItemSelectedListener {

    private var toolbar: Toolbar? = null
    private var scorerecyclerView: RecyclerView? = null
    private var spinner: AppCompatSpinner? = null
    private var strings: ArrayList<String>? = null

    private var mydapter: Mydapter? = null
    private var title:TextView? = null

    private var presenter:IThirdPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        initView()
        presenter!!.changeTitle()
        initspinnerData()
    }

    fun initView(){
        toolbar = findViewById(R.id.thirdtoobar)
        setSupportActionBar(toolbar)

        toolbar!!.subtitle = "成绩查询"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        spinner = findViewById(R.id.thirdSpinner)

        title = findViewById<TextView>(R.id.title)
        scorerecyclerView = findViewById(R.id.allscore_RecycleView)
        scorerecyclerView!!.layoutManager = LinearLayoutManager(this)
        scorerecyclerView!!.itemAnimator = DefaultItemAnimator()
        mydapter = Mydapter(this)
        scorerecyclerView!!.adapter = mydapter

        presenter = ThirdPresenter(this)
    }

    override fun changeTitle(studentname:String) {
        title!!.text = studentname + ",你好！"
    }

    override fun initspinnerData(){

        strings = presenter!!.initspinnerData()

        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, strings)
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
