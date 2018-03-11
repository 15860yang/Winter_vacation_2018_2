package com.example.okhttp.Activity.MainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import com.example.okhttp.Activity.SecondActivity.SecondActivity

import com.example.okhttp.tool.OkHttpRequest
import com.example.okhttp.R


class MainActivity : AppCompatActivity(),IMainVIew ,View.OnClickListener {


    private var login: Button? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var imageView: ImageView? = null
    private var checkbox: EditText? = null
    private var textView: TextView? = null
    private var passwordswitch: Switch? = null
    private var loadingBar: LinearLayout? = null

    private val SHOWERROR = 5
    private val SHOWORUNSHOWPROGRESS:Int = 6

    private val SETCHECKBOX = 1
    private val LOGINSUCCESS = 3//登陆成功

    private var presenter:IMainPresenter? = null

    @SuppressLint("HandlerLeak")
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                SETCHECKBOX -> {
                        val res = msg.obj as ByteArray
                        imageView!!.setImageBitmap(BitmapFactory.decodeByteArray(res, 0, res.size))
                }

                LOGINSUCCESS -> {
                    afterLogin()
                    var intent = Intent(this@MainActivity, SecondActivity::class.java)
                    Log.d("---------","登陆成功，开始下一个界面")
                    startActivityForResult(intent,1)
                }
                SHOWERROR -> {
                    var s:String = msg.obj as String
                    Toast.makeText(this@MainActivity,s,Toast.LENGTH_SHORT).show()
                }
                SHOWORUNSHOWPROGRESS -> {
                    var a:Boolean = msg.obj as Boolean
                    if(a){
                       whenlogin()
                    }else{
                        afterLogin()
                    }
                }
            }
        }
    }

    override fun loginSuccess() {
        var me : Message = Message.obtain()
        me.what = this.LOGINSUCCESS
        handler!!.sendMessage(me)
    }

    private fun afterLogin() {
        login!!.setBackgroundColor(Color.BLUE)
        login!!.isClickable = true
        loadingBar!!.visibility = View.INVISIBLE
        email!!.isEnabled = true
        password!!.isEnabled = true
        checkbox!!.isEnabled = true
        imageView!!.isClickable = true
        passwordswitch!!.isClickable = true
        login!!.setBackgroundColor(Color.BLUE)
        login!!.isClickable = true
    }


    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)

        InitView()
    }

    fun InitView() {
        login = findViewById(R.id.login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        password!!.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        passwordswitch = findViewById(R.id.passwordswitch)
        passwordswitch!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                //明文
                password!!.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else{
                //密文
                password!!.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            }
        }
        loadingBar = findViewById(R.id.loadingBar)
        imageView = findViewById(R.id.image)
        checkbox = findViewById(R.id.checkbox)
        textView = findViewById(R.id.text)

        login!!.setOnClickListener(this)
        imageView!!.setOnClickListener(this)

        //初始化cookie和初始化验证码
        presenter!!.InitCookieData()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.login -> {
                presenter!!.login(email!!.text.toString(),
                        password!!.text.toString(), checkbox!!.text.toString())
            }
            R.id.image -> {
                Log.d("-------------","获取验证码")
                presenter!!.getchexkbox()
            }
        }
    }

    private fun whenlogin() {
        login!!.setBackgroundColor(Color.GRAY)
        login!!.isClickable = false
        loadingBar!!.visibility = View.VISIBLE
        email!!.isEnabled = false
        password!!.isEnabled = false
        checkbox!!.isEnabled = false
        imageView!!.isClickable = false
        passwordswitch!!.isClickable = false
    }

    override fun setyanzhengma(yanzhengma: ByteArray) {
        val message:Message = Message.obtain()
        message.obj = yanzhengma
        message.what = SETCHECKBOX
        handler.sendMessage(message)
    }

    override fun showError(s: String) {
        val message:Message = Message.obtain()
        message.obj = s
        message.what = SHOWERROR
        handler.sendMessage(message)
    }

    override fun showORUnShowProgress(show:Boolean) {
        val message:Message = Message.obtain()
        message.obj = show
        message.what = SHOWORUNSHOWPROGRESS
        handler.sendMessage(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            1 -> {
                Thread(Runnable {
                    OkHttpRequest.InitCookieData()
                    var boolean = true
                    while (boolean){
                        if(OkHttpRequest.isgetcookie()){
                            boolean = false
                        }
                    }
                }).start()
                presenter!!.getchexkbox()
                passwordswitch!!.isChecked = false
                password!!.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            }
        }
    }

}
