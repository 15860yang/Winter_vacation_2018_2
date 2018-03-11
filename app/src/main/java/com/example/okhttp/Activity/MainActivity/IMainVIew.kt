package com.example.okhttp.Activity.MainActivity

/**
 * Created by 杨豪 on 2018/3/11.
 */
interface IMainVIew {
    fun showError(s:String)
    fun showORUnShowProgress(show:Boolean)
    fun setyanzhengma(yanzhengma:ByteArray)
    fun loginSuccess()
}