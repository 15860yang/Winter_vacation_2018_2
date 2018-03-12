package com.example.okhttp.Activity.ForthActivity

import com.example.okhttp.tool.OkHttpRequest

/**
 * Created by 杨豪 on 2018/3/12.
 */
class ForthModel:IForthModel {


    override fun getSpinnerData(): ArrayList<String> {
        var strings = ArrayList<String>()
        for (s in OkHttpRequest.stu_per_schedules["realtyear"]!!) {
            strings!!.add(s + "学年度第一学期课表")
            strings!!.add(s + "学年度第二学期课表")
        }
        return strings
    }

    override fun getKebiaoData(): HashMap<String, ArrayList<String>> {
        return OkHttpRequest.stu_per_schedules
    }
}