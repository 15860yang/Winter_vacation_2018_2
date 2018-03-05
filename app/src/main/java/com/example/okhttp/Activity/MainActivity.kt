package com.example.okhttp.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.okhttp.tool.OkHttpRequest
import com.example.okhttp.R


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
    private val STARTNEXTACTIVITY = 4//开始下一个页面

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
                    login!!.isClickable = true
                    login!!.setBackgroundColor(Color.BLUE)
                }
                LOGINSUCCESS -> {
                    Thread(Runnable {
                        OkHttpRequest.getInitWebAdressData()
                        OkHttpRequest.requestOkhttpforPersonData()
                        while (!OkHttpRequest.isIsgetPersonDta);
                        Thread(Runnable {
                            startnextActivity()
                            Log.d("-------------","登陆成功")
                        }).start()
                    }).start()
                }
                STARTNEXTACTIVITY -> {
                    login!!.setBackgroundColor(Color.BLUE)
                    login!!.isClickable = true
                    var intent = Intent(this@MainActivity,SecondActivity::class.java)
                    Log.d("---------","登陆成功，开始下一个界面")
                    startActivityForResult(intent,1)
                }
            }
        }
    }

    private fun startnextActivity() {
        var me : Message = Message.obtain()
        me.what = STARTNEXTACTIVITY
        handler!!.sendMessage(me)
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
            R.id.image -> {
                Log.d("-------------","获取验证码")
                getcheckbox()
            }
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
            Log.d("----------","获取到验证码----$bts")
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
        login!!.setBackgroundColor(Color.GRAY)
        login!!.isClickable = false
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("------------","来到这里1111111111111")
        when (requestCode){
            1 -> {
                Log.d("------------","来到这里")

                Thread(Runnable {

                    OkHttpRequest.InitCookieData()
                    var boolean = true
                    while (boolean){
                        if(OkHttpRequest.isgetcookie()){
                            boolean = false
                        }
                        Log.d("----","还没有成功获取cookie")
                        Thread.sleep(10)
                    }
                }).start()
                getcheckbox()
            }
        }
    }

}
