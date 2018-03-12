package com.example.okhttp.Activity.SecondActivity

import com.example.okhttp.tool.OkHttpRequest

/**
 * Created by 杨豪 on 2018/3/12.
 */
class SecondModel :ISecondModel{

    var listener:ISecondPresenter.Listener? = null

    constructor(listener:ISecondPresenter.Listener){
        this.listener = listener
    }

    override fun getstudentData(): HashMap<String, String> {
        return OkHttpRequest.personData
    }

    override fun getTitle(): String {
        return OkHttpRequest.studentName
    }

    override fun getScore() {
        Thread(Runnable {
            if (!OkHttpRequest.isgetscore_inquiry()) {
                OkHttpRequest.requestOkhttpforscore_inquiry()
            }
        }).start()
        Thread(Runnable {
            while (!OkHttpRequest.isgetscore_inquiry());
            listener!!.getSocreOk()
        }).start()
    }

    override fun getStu_per_schedules() {
        Thread(Runnable {
            if (!OkHttpRequest.isgetstu_per_schedules()) {
                OkHttpRequest.requestOkhttpforStu_per_sch()
            }
        }).start()
        Thread(Runnable {
            while (!OkHttpRequest.isgetstu_per_schedules());
            listener!!.getStu_per_schedulesOk()
        }).start()
    }
}