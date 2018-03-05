package com.example.okhttp.tool

import android.util.Log

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

import java.io.IOException
import java.util.ArrayList
import java.util.HashMap

import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Created by 杨豪 on 2018/2/14.
 */

object OkHttpRequest {
    private var cookie: String? = null
    private var __VIEWSTATE: String? = null
    private var __VIEWSTATE_ofgetScore: String? = null

    /**
     * 声明静态变量
     */
    private var htmlData: Document? = null
    var personData = HashMap<String, String>()
        private set

    /**
     * 获取验证码
     * @return
     */
    var identifying_code: ByteArray? = null
        private set

    /**
     * 状态声明
     */
    private var isgetCookie = false
    private var isGetIdentifying_Code = false
    /**
     * 查看是否登录成功
     * @return
     */
    var isLoginSuccessful = -1
        private set
    var isIsgetPersonDta = false
        private set
    private var isgetscore_inquiry = false
    private var isgetstu_per_schedules = false
    /**
     * 静态Client声明
     */
    private val client = OkHttpClient()

    /**
     * 网络请求url声明
     */

    private val HostUrl = "http://222.24.62.120/"
    private val Host = "222.24.62.120"

    private val checkboxUrl = "CheckCode.aspx"

    private var urlDataMap = HashMap<String, String>()
    /*
    成绩查询,培养计划,网上报名,网上补考报名,选体育课,全校性选修课,教务公告,补考考试查询,学生补考名单确认,
    学生个人课表,返回首页,查看公告,学生信息,个人信息,学生教学评价,学生提交资料,课堂满意度调查,
    密码修改,教学质量评价
    */
    /**
     * 获取成绩
     * @return
     */
    var score_inquiryList = ArrayList<ArrayList<String>>()
        private set
    var stu_per_schedules = HashMap<String, ArrayList<String>>()
        private set


    /**
     * 静态常量声明
     */
    private val Connection = "keep-alive"
    private val Accept_Language = "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"
    private val Accept_Encoding = "gzip, deflate"
    private val Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    private val Cache_Control = "max-age=0"

    /**
     * 获取学生名字
     * @return
     */
    //欢迎您： 杨豪同学 退出
    val studentName: String
        get() {

            val select = htmlData!!.select("body[class=mainbody]").select("div[class=info]")
            val s = StringBuilder(select.text())
            for (i in 0..4) {
                s.deleteCharAt(0)
            }
            for (i in 0..2) {
                s.deleteCharAt(s.length - 1)
            }
            Log.d("-------", s.toString())
            return s.toString()
        }

    /**
     * 登陆之前清空数据，初始化
     */
    private fun initdata() {
        htmlData = null

        identifying_code = null
        isgetstu_per_schedules = false
        isgetCookie = false
        isGetIdentifying_Code = false
        isLoginSuccessful = -1
        isIsgetPersonDta = false
        isgetscore_inquiry = false
        __VIEWSTATE_ofgetScore = null

    }


    /**
     * 初始化cookie  和  __VIEWSTATE
     */
    fun InitCookieData() {
        val request = Request.Builder()
                .url(HostUrl)
                .addHeader("Host", Host)
                .addHeader("Accept", Accept)
                .addHeader("Accept-Language", Accept_Language)
                .addHeader("Accept-Encoding", Accept_Encoding)
                .addHeader("Referer", "http://www.xiyou.edu.cn/xxfw/cyfw1.htm")
                .addHeader("Connection", Connection)
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("Cache-Control", Cache_Control)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val ss = response.header("Set-Cookie")!!.toString()
                    val cookieArray = ss.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    cookie = cookieArray[0]
                    val document = Jsoup.parse(response.body()!!.string())
                    val select = document.select("body[class=login_bg]").select("input")
                    __VIEWSTATE = select.attr("value")
                    isgetCookie = true
                    Log.d("-------------","获取cookie完毕")
                }
            }
        })
    }
    fun isgetcookie():Boolean{
        return isgetCookie
    }


    /**
     * 获取验证码
     */
    fun requestOkhttpforIdentifying_Code() {

        Thread(Runnable {

            isGetIdentifying_Code = false
            Log.d("===========","112221222222222")
            while (!isgetCookie);
            Log.d("===========","112221222222222")


            val request = Request.Builder()
                    .url(HostUrl + checkboxUrl)
                    .header("Cookie", cookie!!)
                    .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        identifying_code = response.body()!!.bytes()
                        isGetIdentifying_Code = true
                    } else {
                        Log.d("---", "失败！")
                    }
                }
            })
        }).start()
    }

    /**
     * 验证是否获取到验证码
     * @return
     */
    fun isisGetIdentifying_Code(): Boolean {
        return isGetIdentifying_Code
    }

    /**
     * 登陆验证
     * @param email
     * @param password
     * @param checkbox
     */
    fun requestOkhttpforLoginhtml(email: String, password: String, checkbox: String) {
        isLoginSuccessful = -1
        initdata()
        Thread(Runnable {
            val requestBody = FormBody.Builder()
                    .add("__VIEWSTATE", __VIEWSTATE!!)
                    .add("RadioButtonList1", "%D1%A7%C9%FA")
                    .add("TextBox2", password)
                    .add("txtSecretCode", checkbox)
                    .add("txtUserName", email)
                    .add("Button1", "")
                    .add("hidPdrs", "")
                    .add("hidsc", "")
                    .add("lbLanguage", "")
                    .add("Textbox1", "")
                    .build()

            val request = Request.Builder()
                    .url("http://222.24.62.120/default2.aspx")
                    .header("Accept", Accept)
                    .header("Accept-Encoding", Accept_Encoding)
                    .header("Accept-Language", Accept_Language)
                    .header("Connection", Connection)
                    .header("Content-Length", "202")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cookie", cookie!!)
                    .header("Host", Host)
                    .header("Referer", "http://222.24.62.120/default2.aspx")
                    .post(requestBody)
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {

                        val headers = response.headers()
                        Log.d("登陆成功的Header---", headers.toString())
                        val document = Jsoup.parse(response.body()!!.string())
                        //                            Log.d("登陆成功的源码---",document.toString());
                        htmlData = document
                        Log.d("=================", "----------------------")
                        if (headers.get("Content-Length")!!.toString().compareTo("9000") > 0) {
                            Log.d("====", "登陆成功")
                            isLoginSuccessful = 1
                        } else {
                            isLoginSuccessful = 2
                        }

                    }
                }
            })
        }).start()
    }

    /**
     * 获取后续网页网址数据
     * @return
     */
    fun getInitWebAdressData() {
        urlDataMap = ParseData.parse_01_HtmlToMap(htmlData!!)
    }


    /**
     * 获取个人信息页面
     */
    fun requestOkhttpforPersonData() {
        isIsgetPersonDta = false
        val map = ParseData.getDataformUrl(urlDataMap["个人信息"]!!)
        val formBody = FormBody.Builder()
                .add("gnmkdm", map["gnmkdm"])
                .add("xh", map["xh"])
                .add("xm", map["xm"])
                .build()
        val request = Request.Builder()
                .url(HostUrl + urlDataMap["个人信息"])
                .header("Host", Host)
                .header("Accept", Accept)
                .header("Accept-Language", Accept_Language)
                .header("Accept-Encoding", Accept_Encoding)
                .header("Referer", HostUrl + urlDataMap["返回首页"])
                .header("Cookie", cookie!!)
                .header("Upgrade-Insecure-Requests", "1")
                .header("Connection", Connection)
                .post(formBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    isIsgetPersonDta = false
                    val document = Jsoup.parse(response.body()!!.string())
                    personData = ParseData.parse_02_personDataHtmlTOMap(document)
                    isIsgetPersonDta = true
                }
            }
        })
    }

    /**
     * 获取个人课表页面
     */
    fun requestOkhttpforStu_per_sch() {
        val map = ParseData.getDataformUrl(urlDataMap["学生个人课表"]!!)
        val formBody = FormBody.Builder()
                .add("xh", map["xh"])
                .add("xm", map["xm"])
                .add("gnmkdm", map["gnmkdm"])
                .build()
        val request = Request.Builder()
                .url(HostUrl + urlDataMap["学生个人课表"])
                .header("Host", Host)
                .header("Accept", Accept)
                .header("Accept-Language", Accept_Language)
                .header("Accept-Encoding", Accept_Encoding)
                .header("Referer", HostUrl + urlDataMap["返回首页"])
                .header("Cookie", cookie!!)
                .header("Connection", Connection)
                .header("Upgrade-Insecure-Requests", "1")
                .post(formBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val document = Jsoup.parse(response.body()!!.string())
                    __VIEWSTATE_ofgetScore = ParseData.parse_02_Stu_per_schHtmlTo__VIEWSTATE(document)
                    stu_per_schedules = ParseData.parse_02_Stu_per_schHtmlToMap(document)
                    isgetstu_per_schedules = true
                }
            }
        })
    }

    fun isgetstu_per_schedules(): Boolean {
        return isgetstu_per_schedules
    }


    /**
     * 获取成绩页面
     * 查询成绩时的数据
     *
     */
    fun requestOkhttpforscore_inquiry() {
        val map = ParseData.getDataformUrl(urlDataMap["成绩查询"]!!)
        val formBody = FormBody.Builder()
                .add("xh", map["xh"])
                .add("xm", map["xm"])
                .add("gnmkdm", map["gnmkdm"])
                .build()
        val request = Request.Builder()
                .url(HostUrl + urlDataMap["成绩查询"])
                .header("Host", Host)
                .header("Accept", Accept)
                .header("Accept-Language", Accept_Language)
                .header("Accept-Encoding", Accept_Encoding)
                .header("Referer", HostUrl + urlDataMap["返回首页"])
                .header("Cookie", cookie!!)
                .header("Connection", Connection)
                .header("Upgrade-Insecure-Requests", "1")
                .post(formBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val document = Jsoup.parse(response.body()!!.string())
                val value = ParseData.parse_02_01_score_inquiryHtmlTOMap(document)
                requestOkhttpforscore_inquiry_02(value)

            }
        })
    }


    /**
     * 成绩界面——02 请求
     * @param value
     */
    private fun requestOkhttpforscore_inquiry_02(value: String) {
        val map = ParseData.getDataformUrl(urlDataMap["成绩查询"]!!)
        val formBody = FormBody.Builder()
                .add("xh", map["xh"])
                .add("xm", map["xm"])
                .add("gnmkdm", map["gnmkdm"])
                .add("__EVENTTARGET", "")
                .add("__EVENTARGUMENT", "")
                .add("__VIEWSTATE", value)
                .add("ddlXN", "")
                .add("ddlXQ", "")
                .add("ddl_kcxz", "")
                .add("btn_zcj", "%C0%FA%C4%EA%B3%C9%BC%A8")
                .build()
        val request = Request.Builder()
                .url(HostUrl + urlDataMap["成绩查询"])
                .header("Host", Host)
                .header("Accept", Accept)
                .header("Accept-Language", Accept_Language)
                .header("Accept-Encoding", Accept_Encoding)
                .header("Referer", HostUrl + urlDataMap["返回首页"])
                .header("Cookie", cookie!!)
                .header("Connection", Connection)
                .header("Upgrade-Insecure-Requests", "1")
                .post(formBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val headers = response.headers()
                Log.d("获取成绩的header00000======", headers.toString())
                val document = Jsoup.parse(response.body()!!.string())
                score_inquiryList = ParseData.parse_02_02_score_inquiryHtmlTOMap(document)
                isgetscore_inquiry = true
            }
        })
    }

    /**
     * 获取成绩状态查询
     * @return
     */
    fun isgetscore_inquiry(): Boolean {
        return isgetscore_inquiry
    }
}
