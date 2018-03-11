package com.example.okhttp.Activity.MainActivity

/**
 * Created by 杨豪 on 2018/3/11.
 */
interface IMainModel {

    fun Login(email:String ,password:String ,checkbox:String)

    fun GetData():String

    fun setLoginListener(listener:IMainPresenter.LoginListener)

    fun InitData()

    fun getchexkbox()

    fun getpersonData()
}