package com.example.okhttp.Activity


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.okhttp.tool.OkHttpRequest
import com.example.okhttp.R
import scut.carson_ho.kawaii_loadingview.Kawaii_LoadingView


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var login: Button? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var imageView: ImageView? = null
    private var checkbox: EditText? = null
    private var textView: TextView? = null

    private val SETCHECKBOX = 1
    private val LOGINFAILED = 2//登陆失败
    private val LOGINSUCCESS = 3//登陆成功


    @SuppressLint("HandlerLeak")
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                SETCHECKBOX -> {
                        val res = msg.obj as ByteArray
                        imageView!!.setImageBitmap(BitmapFactory.decodeByteArray(res, 0, res.size))
                    }
                LOGINFAILED -> {
                    Toast.makeText(this@MainActivity, "登陆失败", Toast.LENGTH_SHORT).show()
                }
                LOGINSUCCESS -> {

                    Thread(Runnable {
                        OkHttpRequest.getInitWebAdressData()
                        OkHttpRequest.requestOkhttpforPersonData()
                        while (!OkHttpRequest.isIsgetPersonDta);
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        startActivity(intent)
                    }).start()
                }
            }
        }
    }

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitView()
    }

    fun InitView() {
        login = findViewById(R.id.login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        imageView = findViewById(R.id.image)
        checkbox = findViewById(R.id.checkbox)
        textView = findViewById(R.id.text)

        login!!.setOnClickListener(this)
        imageView!!.setOnClickListener(this)



        //初始化cookie和初始化验证码
        OkHttpRequest.InitCookieData()
        getcheckbox()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.login -> Login()
            R.id.image -> getcheckbox()
        }
    }

    /**
     * 获取验证码
     */
    fun getcheckbox() {
        Thread(Runnable {
            OkHttpRequest.requestOkhttpforIdentifying_Code()
            while (!OkHttpRequest.isisGetIdentifying_Code());
            val bts = OkHttpRequest.identifying_code
            val message = Message.obtain()
            message.obj = bts
            message.what = SETCHECKBOX
            handler.sendMessage(message)
        }).start()
    }

    /**
     * 登陆函数
     */
    fun Login() {

        if (email!!.text.toString().trim { it <= ' ' }.length <= 0) {
            Toast.makeText(this, "请输入正确的账号!", Toast.LENGTH_SHORT).show()
            return
        }
        if (password!!.text.toString().trim { it <= ' ' }.length <= 0) {
            Toast.makeText(this, "请输入正确的密码!", Toast.LENGTH_SHORT).show()
            return
        }
        if (checkbox!!.text.toString().trim { it <= ' ' }.length <= 0) {
            Toast.makeText(this, "请输入正确的验证码!", Toast.LENGTH_SHORT).show()
            return
        }

        Thread(Runnable {
            OkHttpRequest.requestOkhttpforLoginhtml(email!!.text.toString(),
                    password!!.text.toString(), checkbox!!.text.toString())
            while (OkHttpRequest.isLoginSuccessful < 0);
            val message = Message.obtain()
            if (OkHttpRequest.isLoginSuccessful == 2) {
                message.what = LOGINFAILED//登陆失败
            }
            if (OkHttpRequest.isLoginSuccessful == 1) {
                message.what = LOGINSUCCESS//登陆成功
            }
            handler.sendMessage(message)
        }).start()
    }

}
