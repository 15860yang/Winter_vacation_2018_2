//package com.example.okhttp
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//
//class MainActivity : AppCompatActivity(),View.OnClickListener{
//
//
//    private var login : Button = findViewById(R.id.login)
//
//    private var email : EditText = findViewById(R.id.email)
//    private var password : EditText = findViewById(R.id.password)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        InitView()
//    }
//
//    fun InitView(){
//        login.setOnClickListener(this)
//    }
//
//    override fun onClick(v: View?) {
//        when(v!!.id){
//            R.id.login ->  {
//                var a1 = email.text
//                var a2 = password.text
//
//            }
//
//        }
//    }
//
//    fun httprequest(){
//
//    }
//
//}
