package com.example.okhttp.Activity.MainActivity

import android.util.Log
import com.example.okhttp.tool.OkHttpRequest

/**
 * Created by 杨豪 on 2018/3/11.
 */
class MainMdoel :IMainModel{
    override fun InitData() {
        OkHttpRequest.InitCookieData()
        Thread(Runnable {
            while (OkHttpRequest.isgetcookie());
            getchexkbox()
        }).start()
    }

    var presenter:IMainPresenter.LoginListener? = null

    override fun setLoginListener(listener: IMainPresenter.LoginListener) {
        this.presenter = listener
    }

    private val LOGINFAILED = 2//登陆失败
    private val LOGINSUCCESS = 1//登陆成功

    val loginFailed:String = "登录失败！"
    val Illeglemail:String = "账号不能为空！"
    val Illeglpassword:String = "密码不能为空！"
    val Illeglcheckbox:String = "验证码不能为空！"


    override fun Login(email: String, password: String, checkbox: String) {
        if(email == null){
            presenter!!.LoginFailed(Illeglemail)
            return
        }
        if(password == null){
            presenter!!.LoginFailed(Illeglpassword)
            return
        }
        if(checkbox == null){
            presenter!!.LoginFailed(Illeglcheckbox)
            return
        }

        OkHttpRequest.requestOkhttpforLoginhtml(email,password,checkbox)
        Thread(Runnable {
            while ( OkHttpRequest.isLoginSuccessful < 0);
            when(OkHttpRequest.isLoginSuccessful){
                LOGINSUCCESS->{
                    presenter!!.LoginSucccess()
                }
                LOGINFAILED->{
                    presenter!!.LoginFailed(loginFailed)
                }
            }
        }).start()
    }

    override fun GetData(): String {

        return ""
    }

    override fun getchexkbox() {
        Thread(Runnable {
            OkHttpRequest.requestOkhttpforIdentifying_Code()
            while (!OkHttpRequest.isisGetIdentifying_Code());
            presenter!!.gotyanzhengma(OkHttpRequest.getidentifying_code())

        }).start()
    }

    override fun getpersonData() {

        Thread(Runnable {
            OkHttpRequest.getInitWebAdressData()
            OkHttpRequest.requestOkhttpforPersonData()
            while (!OkHttpRequest.isIsgetPersonDta)
            {
                Thread.sleep(2000)
                Log.d("---------","正在获取个人信息")
            }

            presenter!!.getPersonDataOk()
        }).start()
    }

}