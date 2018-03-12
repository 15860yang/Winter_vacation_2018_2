package com.example.okhttp.Activity.SecondActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.okhttp.Activity.ForthActivity.ForthActivity
import com.example.okhttp.Activity.ThirdActivity.ThirdActivity

import com.example.okhttp.R

/**
 * Created by 杨豪 on 2018/2/26.
 */

class SecondActivity : AppCompatActivity(), ISecondView,View.OnClickListener {

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

    private var title:TextView? = null


    private var backtoLogin:Button? = null
    private var score_search: Button? = null
    private var stu_per_schedules: Button? = null

    private var presenter:SecondPresenter? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initView()
        presenter = SecondPresenter(this)
        presenter!!.changeTitle()
        presenter!!.showstudentData()

    }

    /**
     * 初始化界面控件
     */
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

        title = findViewById(R.id.title)

        backtoLogin = findViewById(R.id.backtoLogin)
        backtoLogin!!.setOnClickListener(this)

        score_search = findViewById(R.id.score_search)
        score_search!!.setOnClickListener(this)
        stu_per_schedules = findViewById(R.id.Stu_per_schedules)
        stu_per_schedules!!.setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.score_search -> presenter!!.getScore()
            R.id.Stu_per_schedules -> presenter!!.getStu_per_schedules()
            R.id.backtoLogin -> finish()
        }
    }

    /**
     * 重写back（返回）键
     */
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun chagetitle(studentname: String) {
        title!!.text = studentname + ",你好！"
    }

    override fun showData(studentData:HashMap<String,String>) {

        Log.d("==========信息=======", studentData.toString())
        Log.d("-----", "student.get(\"学号\") = " + studentData["学号："])
        student_num!!.text = studentData["学号："]
        student_name!!.text = studentData["姓名："]
        student_sex!!.text = studentData["性别："]
        student_birth!!.text = studentData["出生日期："]
        student_minzu!!.text = studentData["民族："]
        student_jiguan!!.text = studentData["籍贯："]
        student_laiyuansheng!!.text = studentData["来源省："]
        student_xueyuan!!.text = studentData["学院："]
        student_zhuanyemingcheng!!.text = studentData["专业名称："]
        student_xingzhengban!!.text = studentData["行政班："]
        student_xuezhi!!.text = studentData["学制："]
        student_dangqiansuozaiji!!.text = studentData["当前所在级："]
        student_kaoshenghao!!.text = studentData["考生号："]
        student_shenfenzhenghao!!.text = studentData["身份证号："]
        student_xuelicengci!!.text = studentData["学历层次："]
    }

    override fun StartThirdActivity() {
        val intent = Intent(this@SecondActivity,ThirdActivity::class.java)
        startActivity(intent)
    }

    override fun StartForthActivity() {
        val intent = Intent(this@SecondActivity,ForthActivity::class.java)
        startActivity(intent)
    }
}
