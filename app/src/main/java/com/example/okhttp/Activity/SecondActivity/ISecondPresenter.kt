package com.example.okhttp.Activity.SecondActivity

/**
 * Created by 杨豪 on 2018/3/12.
 */
interface ISecondPresenter {

    fun getstudentData():HashMap<String,String>

    fun changeTitle()
    fun showstudentData()
    fun getScore()
    interface Listener {
        fun getSocreOk()
        fun getStu_per_schedulesOk()
    }

    fun getStu_per_schedules()

}