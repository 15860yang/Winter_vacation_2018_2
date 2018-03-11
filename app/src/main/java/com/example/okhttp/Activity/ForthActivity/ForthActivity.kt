package com.example.okhttp.Activity.ForthActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.Toolbar
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import com.example.okhttp.R
import com.example.okhttp.tool.OkHttpRequest

import java.util.ArrayList
import java.util.HashMap

class ForthActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var weekof_Mon_01: TextView? = null
    private var weekof_Mon_02: TextView? = null
    private var weekof_Mon_03: TextView? = null
    private var weekof_Mon_04: TextView? = null

    private var weekof_Thu_01: TextView? = null
    private var weekof_Thu_02: TextView? = null
    private var weekof_Thu_03: TextView? = null
    private var weekof_Thu_04: TextView? = null

    private var weekof_Wed_01: TextView? = null
    private var weekof_Wed_02: TextView? = null
    private var weekof_Wed_03: TextView? = null
    private var weekof_Wed_04: TextView? = null

    private var weekof_Thr_01: TextView? = null
    private var weekof_Thr_02: TextView? = null
    private var weekof_Thr_03: TextView? = null
    private var weekof_Thr_04: TextView? = null

    private var weekof_Fir_01: TextView? = null
    private var weekof_Fir_02: TextView? = null
    private var weekof_Fir_03: TextView? = null
    private var weekof_Fir_04: TextView? = null

    private var select_kebiao_Spinner: AppCompatSpinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)
        initView()

        initData()
    }

    fun initView() {
        weekof_Mon_01 = findViewById(R.id.weekof_Mon_01)
        weekof_Mon_01!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Mon_02 = findViewById(R.id.weekof_Mon_02)
        weekof_Mon_02!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Mon_03 = findViewById(R.id.weekof_Mon_03)
        weekof_Mon_03!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Mon_04 = findViewById(R.id.weekof_Mon_04)
        weekof_Mon_04!!.movementMethod = ScrollingMovementMethod.getInstance()

        weekof_Thu_01 = findViewById(R.id.weekof_Thu_01)
        weekof_Thu_01!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Thu_02 = findViewById(R.id.weekof_Thu_02)
        weekof_Thu_02!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Thu_03 = findViewById(R.id.weekof_Thu_03)
        weekof_Thu_03!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Thu_04 = findViewById(R.id.weekof_Thu_04)
        weekof_Thu_04!!.movementMethod = ScrollingMovementMethod.getInstance()

        weekof_Wed_01 = findViewById(R.id.weekof_Wed_01)
        weekof_Wed_01!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Wed_02 = findViewById(R.id.weekof_Wed_02)
        weekof_Wed_02!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Wed_03 = findViewById(R.id.weekof_Wed_03)
        weekof_Wed_03!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Wed_04 = findViewById(R.id.weekof_Wed_04)
        weekof_Wed_04!!.movementMethod = ScrollingMovementMethod.getInstance()

        weekof_Thr_01 = findViewById(R.id.weekof_Thr_01)
        weekof_Thr_01!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Thr_02 = findViewById(R.id.weekof_Thr_02)
        weekof_Thr_02!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Thr_03 = findViewById(R.id.weekof_Thr_03)
        weekof_Thr_03!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Thr_04 = findViewById(R.id.weekof_Thr_04)
        weekof_Thr_04!!.movementMethod = ScrollingMovementMethod.getInstance()

        weekof_Fir_01 = findViewById(R.id.weekof_Fir_01)
        weekof_Fir_01!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Fir_02 = findViewById(R.id.weekof_Fir_02)
        weekof_Fir_02!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Fir_03 = findViewById(R.id.weekof_Fir_03)
        weekof_Fir_03!!.movementMethod = ScrollingMovementMethod.getInstance()
        weekof_Fir_04 = findViewById(R.id.weekof_Fir_04)
        weekof_Fir_04!!.movementMethod = ScrollingMovementMethod.getInstance()

        val toolbar = findViewById<Toolbar>(R.id.forthActivityToolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val title :TextView = findViewById(R.id.title)
        title.text = OkHttpRequest.studentName + ",你好！"

        select_kebiao_Spinner = findViewById(R.id.select_kebiao_Spinner)

    }

    fun initData() {
        stu_per_schedules = OkHttpRequest.stu_per_schedules

        weekof_Mon_01!!.text = stu_per_schedules["1----"]!!.get(1)
        weekof_Mon_02!!.text = stu_per_schedules["2----"]!!.get(1)
        weekof_Mon_03!!.text = stu_per_schedules["3----"]!!.get(1)
        weekof_Mon_04!!.text = stu_per_schedules["4----"]!!.get(1)

        weekof_Thu_01!!.text = stu_per_schedules["1----"]!!.get(2)
        weekof_Thu_02!!.text = stu_per_schedules["2----"]!!.get(2)
        weekof_Thu_03!!.text = stu_per_schedules["3----"]!!.get(2)
        weekof_Thu_04!!.text = stu_per_schedules["4----"]!!.get(2)

        weekof_Wed_01!!.text = stu_per_schedules["1----"]!!.get(3)
        weekof_Wed_02!!.text = stu_per_schedules["2----"]!!.get(3)
        weekof_Wed_03!!.text = stu_per_schedules["3----"]!!.get(3)
        weekof_Wed_04!!.text = stu_per_schedules["4----"]!!.get(3)

        weekof_Thr_01!!.text = stu_per_schedules["1----"]!!.get(4)
        weekof_Thr_02!!.text = stu_per_schedules["2----"]!!.get(4)
        weekof_Thr_03!!.text = stu_per_schedules["3----"]!!.get(4)
        weekof_Thr_04!!.text = stu_per_schedules["4----"]!!.get(4)

        weekof_Fir_01!!.text = stu_per_schedules["1----"]!!.get(5)
        weekof_Fir_02!!.text = stu_per_schedules["2----"]!!.get(5)
        weekof_Fir_03!!.text = stu_per_schedules["3----"]!!.get(5)
        weekof_Fir_04!!.text = stu_per_schedules["4----"]!!.get(5)


        strings = ArrayList()
        for (s in stu_per_schedules["realtyear"]!!) {
            strings!!.add(s + "学年度第一学期课表")
            strings!!.add(s + "学年度第二学期课表")
        }
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, strings!!)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        select_kebiao_Spinner!!.adapter = adapter
        select_kebiao_Spinner!!.onItemSelectedListener = this
        select_kebiao_Spinner!!.visibility = View.VISIBLE
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

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

    companion object {

        private var stu_per_schedules = HashMap<String, ArrayList<String>>()
        private var strings: ArrayList<String>? = null
    }
}
