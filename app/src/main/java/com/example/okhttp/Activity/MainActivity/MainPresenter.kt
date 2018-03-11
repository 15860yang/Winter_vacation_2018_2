package com.example.okhttp.Activity.MainActivity

import android.util.Log

/**
 * Created by 杨豪 on 2018/3/11.
 */
class MainPresenter:IMainPresenter,IMainPresenter.LoginListener {



    var view:IMainVIew? = null
    var model:IMainModel? = null


    constructor(view:IMainVIew){
        this.view = view
        this.model = MainMdoel()
        model!!.setLoginListener(this)
    }

    override fun InitCookieData() {
        model!!.InitData()
    }

    override fun LoginSucccess() {
        Log.d("-----","即将获取个人信息")
        model!!.getpersonData()


    }

    override fun LoginFailed(s:String) {
        view!!.showORUnShowProgress(false)
        view!!.showError(s)
    }

    override fun login(email: String, password: String, checkbox: String) {
        view!!.showORUnShowProgress(true)
        model!!.Login(email,password,checkbox)
    }

    override fun getchexkbox() {
        model!!.getchexkbox()
    }

    override fun gotyanzhengma(yanzhengma: ByteArray) {
        view!!.setyanzhengma(yanzhengma)
    }

    override fun initCookiesOk() {
        model!!.InitData()
    }

    override fun getPersonDataOk() {
        view!!.showORUnShowProgress(false)
        view!!.loginSuccess()

    }
}