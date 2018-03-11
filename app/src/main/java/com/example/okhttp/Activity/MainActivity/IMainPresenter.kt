package com.example.okhttp.Activity.MainActivity

/**
 * Created by 杨豪 on 2018/3/11.
 */
interface IMainPresenter {

    interface LoginListener{
        fun LoginSucccess()
        fun LoginFailed(s:String)
        fun gotyanzhengma(yanzhengma:ByteArray)

        fun initCookiesOk()
        fun getPersonDataOk()

    }
    fun login(email:String, password:String, checkbox:String)

    fun InitCookieData()

    fun getchexkbox()



}