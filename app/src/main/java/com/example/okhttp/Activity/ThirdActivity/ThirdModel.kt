package com.example.okhttp.Activity.ThirdActivity

import com.example.okhttp.tool.OkHttpRequest

/**
 * Created by 杨豪 on 2018/3/12.
 */
class ThirdModel:IThirdModel {

    override fun getstudentname(): String {
        return OkHttpRequest.studentName
    }

    override fun getspinnerData():ArrayList<String> {
        var strings = ArrayList<String>()
        strings!!.add("历年成绩")
        for (list in OkHttpRequest.score_inquiryList) {
            val s = list[0] + "学年度第" + list[1] + "学期"
            if (strings!!.size > 0 && strings!![strings!!.size - 1] != s) {
                strings!!.add(s)
            } else if (strings!!.size == 0) {
                strings!!.add(s)
            }
        }
        return strings
    }
}