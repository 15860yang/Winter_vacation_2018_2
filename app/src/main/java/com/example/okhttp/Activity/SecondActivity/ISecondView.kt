package com.example.okhttp.Activity.SecondActivity

/**
 * Created by 杨豪 on 2018/3/12.
 */
interface ISecondView {

    fun showData(studentData:HashMap<String,String>)

    fun chagetitle(studentname:String)
    fun StartThirdActivity()
    fun StartForthActivity()


}