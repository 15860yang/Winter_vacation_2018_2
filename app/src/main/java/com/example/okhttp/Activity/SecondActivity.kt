package com.example.okhttp.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.example.okhttp.tool.OkHttpRequest
import com.example.okhttp.R

import java.util.HashMap

/**
 * Created by 杨豪 on 2018/2/26.
 */

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private var student_num: TextView? = null
    private var student_name: TextView? = null
    private var student_sex: TextView? = null
    private var student_birth: TextView? = null
    private var student_minzu: TextView? = null
    private var student_jiguan: TextView? = null
    private var student_laiyuansheng: TextView? = null
    private var student_xueyuan: TextView? = null
    private var student_zhuanyemingcheng: TextView? = null
    private var student_xingzhengban: TextView? = null
    private var student_xuezhi: TextView? = null
    private var student_dangqiansuozaiji: TextView? = null
    private var student_kaoshenghao: TextView? = null
    private var student_shenfenzhenghao: TextView? = null
    private var student_xuelicengci: TextView? = null


    private var backtoLogin:Button? = null
    private var score_search: Button? = null
    private var stu_per_schedules: Button? = null


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initView()
        initData()
    }

    fun initView() {
        student_num = findViewById(R.id.student_num)
        student_name = findViewById(R.id.student_name)
        student_sex = findViewById(R.id.student_sex)
        student_birth = findViewById(R.id.student_birth)
        student_minzu = findViewById(R.id.student_minzu)
        student_jiguan = findViewById(R.id.student_jiguan)
        student_laiyuansheng = findViewById(R.id.student_laiyuansheng)
        student_xueyuan = findViewById(R.id.student_xueyuan)
        student_zhuanyemingcheng = findViewById(R.id.student_zhuanyemingcheng)
        student_xingzhengban = findViewById(R.id.student_xingzhengban)
        student_xuezhi = findViewById(R.id.student_xuezhi)
        student_dangqiansuozaiji = findViewById(R.id.student_dangqiansuozaiji)
        student_kaoshenghao = findViewById(R.id.student_kaoshenghao)
        student_shenfenzhenghao = findViewById(R.id.student_shenfenzhenghao)
        student_xuelicengci = findViewById(R.id.student_xuelicengci)

        backtoLogin = findViewById(R.id.backtoLogin)
        backtoLogin!!.setOnClickListener(this)

        /**
         * 更改标题
         */
        val title = findViewById<TextView>(R.id.title)
        title.text = OkHttpRequest.studentName + ",你好！"

        score_search = findViewById(R.id.score_search)
        score_search!!.setOnClickListener(this)
        stu_per_schedules = findViewById(R.id.Stu_per_schedules)
        stu_per_schedules!!.setOnClickListener(this)

    }

    fun initData() {

        val student = OkHttpRequest.personData
        Log.d("==========信息=======", student.toString())
        Log.d("-----", "student.get(\"学号\") = " + student["学号："])
        student_num!!.text = student["学号："]
        student_name!!.text = student["姓名："]
        student_sex!!.text = student["性别："]
        student_birth!!.text = student["出生日期："]
        student_minzu!!.text = student["民族："]
        student_jiguan!!.text = student["籍贯："]
        student_laiyuansheng!!.text = student["来源省："]
        student_xueyuan!!.text = student["学院："]
        student_zhuanyemingcheng!!.text = student["专业名称："]
        student_xingzhengban!!.text = student["行政班："]
        student_xuezhi!!.text = student["学制："]
        student_dangqiansuozaiji!!.text = student["当前所在级："]
        student_kaoshenghao!!.text = student["考生号："]
        student_shenfenzhenghao!!.text = student["身份证号："]
        student_xuelicengci!!.text = student["学历层次："]
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.score_search -> score_search()
            R.id.Stu_per_schedules -> getStu_per_schedules()
            R.id.backtoLogin -> finish()
        }
    }


    /**
     * 查询成绩
     * 通过网络请求先获取成绩，再启动活动
     */
    private fun score_search() {
        Thread(Runnable {
            if (!OkHttpRequest.isgetscore_inquiry()) {
                OkHttpRequest.requestOkhttpforscore_inquiry()
            }
        }).start()
        Thread(Runnable {
            while (!OkHttpRequest.isgetscore_inquiry());
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)

            startActivity(intent)
        }).start()
    }

    private fun getStu_per_schedules() {
        Thread(Runnable {
            if (!OkHttpRequest.isgetstu_per_schedules()) {
                OkHttpRequest.requestOkhttpforStu_per_sch()
            }
        }).start()
        Thread(Runnable {
            while (!OkHttpRequest.isgetstu_per_schedules());
            val intent = Intent(this@SecondActivity, ForthActivity::class.java)
            Log.d("---", "2222222222222")
            startActivity(intent)
        }).start()

    }


}
