package com.example.okhttp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 杨豪 on 2018/2/14.
 */

public class OkHttpRequest {
    private static String cookie = null;
    private static String __VIEWSTATE = null;

    /**
     * 声明静态变量
     */
    private static Document htmlData = null;
    private static ArrayList<String> personData = new ArrayList<>();

    private static byte[] identifying_code = null;

    /**
     * 状态声明
     */
    private static boolean isgetCookie = false;
    private static boolean isGetIdentifying_Code = false;
    private static int isLoginSuccessful = -1;
    private static boolean isgetPersonDta = false;

    /**
     * 静态Client声明
     */
    private static OkHttpClient client = new OkHttpClient();

    /**
     * 网络请求url声明
     */

    private final static String HostUrl = "http://222.24.62.120/";
    private final static String Host = "222.24.62.120";

    private final static String checkboxUrl = "CheckCode.aspx";

    private static HashMap<String,String> urlDataMap = new HashMap<>();
    /*
    成绩查询,培养计划,网上报名,网上补考报名,选体育课,全校性选修课,教务公告,补考考试查询,学生补考名单确认,
    学生个人课表,返回首页,查看公告,学生信息,个人信息,学生教学评价,学生提交资料,课堂满意度调查,
    密码修改,教学质量评价
    */
    private static HashMap<String,ArrayList<String>> score_inquiryMap = new HashMap<>();


    private final static String personDataUrl = "xsgrxx.aspx?xh=03161257&xm=杨豪&gnmkdm=N121501";
    private final static String score_inquiry = "xscjcx.aspx?xh=03161257&xm=杨豪&gnmkdm=N121605";
    private final static String Students_personal_schedules = "xskbcx.aspx?xh=03161257&xm=杨豪&gnmkdm=N121603";
    private final static String student_information = "lw_xsxx.aspx?xh=03161257&xm=杨豪&gnmkdm=N121902";


    /**
     * 静态常量声明
     */
    private final static String Connection = "keep-alive";
    private final static String Accept_Language = "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2";
    private final static String Accept_Encoding = "gzip, deflate";
    private final static String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private final static String Cache_Control = "max-age=0";



    /**
     * 初始化cookie  和  __VIEWSTATE
     */
    public static void InitCookieData(){
        Request request = new Request.Builder()
                .url(HostUrl)
                .addHeader("Host",Host)
                .addHeader("Accept",Accept)
                .addHeader("Accept-Language",Accept_Language)
                .addHeader("Accept-Encoding",Accept_Encoding)
                .addHeader("Referer","http://www.xiyou.edu.cn/xxfw/cyfw1.htm")
                .addHeader("Connection",Connection)
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("Cache-Control",Cache_Control)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String ss = response.header("Set-Cookie").toString();
                    String[] cookieArray = ss.split(";");
                    cookie = cookieArray[0];
                    Document document = Jsoup.parse(response.body().string());
                    Elements select = document.select("body[class=login_bg]").select("input");
                    __VIEWSTATE = select.attr("value");
                    isgetCookie = true;
                }
            }
        });
    }


    /**
     * 获取验证码
     */
    public static void requestOkhttpforIdentifying_Code(){
        isGetIdentifying_Code = false;
        while(!isgetCookie);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(HostUrl + checkboxUrl)
                        .header("Cookie",cookie)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            identifying_code = response.body().bytes();
                            isGetIdentifying_Code = true;
                        }else {
                            Log.d("---","失败！");
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * 验证是否获取到验证码
     * @return
     */
    public static boolean isisGetIdentifying_Code(){
        return isGetIdentifying_Code;
    }

    /**
     * 获取验证码
     * @return
     */
    public static byte[] getIdentifying_code() {
        return identifying_code;
    }

    /**
     * 登陆验证
     * @param email
     * @param password
     * @param checkbox
     */
    public static void requestOkhttpforLoginhtml(final String email,final String password,final String checkbox){
        isLoginSuccessful = -1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody requestBody = new FormBody.Builder()
                        .add("__VIEWSTATE",__VIEWSTATE)
                        .add("RadioButtonList1","%D1%A7%C9%FA")
                        .add("TextBox2",password)
                        .add("txtSecretCode",checkbox)
                        .add("txtUserName",email)
                        .add("Button1","")
                        .add("hidPdrs","")
                        .add("hidsc","")
                        .add("lbLanguage","")
                        .add("Textbox1","")
                        .build();

                Request request = new Request.Builder()
                        .url("http://222.24.62.120/default2.aspx")
                        .header("Accept", Accept)
                        .header("Accept-Encoding",Accept_Encoding)
                        .header("Accept-Language",Accept_Language)
                        .header("Connection",Connection)
                        .header("Content-Length","202")
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .header("Cookie",cookie)
                        .header("Host",Host)
                        .header("Referer","http://222.24.62.120/default2.aspx")
                        .post(requestBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()) {

                            Headers headers = response.headers();
                            Log.d("登陆成功的Header---",headers.toString());
                            Document document = Jsoup.parse(response.body().string());
//                            Log.d("登陆成功的源码---",document.toString());
                            htmlData = document;
                            Log.d("=================","----------------------");
                            if(headers.get("Content-Length").toString().compareTo("9000") > 0){
                                Log.d("====","登陆成功");
                                isLoginSuccessful = 1;
                            }else {
                                isLoginSuccessful = 2;
                            }


                        }
                    }
                });
            }
        }).start();
    }

    /**
     * 查看是否登录成功
     * @return
     */
    public static int isLoginSuccessful(){
        return isLoginSuccessful;
    }

    /**
     * 当登陆成功，返回需要的数据
     * @return
     */
    public static  String getDataWhenLoginSuccess(){
        return htmlData.toString();
    }

    /**
     * 获取后续网页网址数据
     * @return
     */
    public static HashMap<String,String> getWebAdressData(){
        return (urlDataMap = ParseData.parse_01_HtmlToMap(htmlData));
    }


    /**
     * 获取个人信息页面
     */
    public static void requestOkhttpforPersonData(){
        isgetPersonDta = false;
        Map<String ,String> map = ParseData.getDataformUrl(personDataUrl);
        FormBody formBody = new FormBody.Builder()
                .add("gnmkdm",map.get("gnmkdm"))
                .add("xh",map.get("xh"))
                .add("xm",map.get("xm"))
                .build();
        Request request = new Request.Builder()
                .url(HostUrl+personDataUrl)
                .header("Host",Host)
                .header("Accept",Accept)
                .header("Accept-Language",Accept_Language)
                .header("Accept-Encoding",Accept_Encoding)
                .header("Referer",HostUrl+urlDataMap.get("返回首页"))
                .header("Cookie",cookie)
                .header("Upgrade-Insecure-Requests","1")
                .header("Connection",Connection)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Document document = Jsoup.parse(response.body().string());
                    Log.d("0-----------------","开始解析数据");
                    Log.d("22222222222222222",ParseData.parse_02_personDataHtmlTOMap(document).toString());
                }
            }
        });
    }



    /**
     * 获取个人课表页面
     * */
    public static void requestOkhttpforStudents_personal_schedules(){
        Map<String,String> map = ParseData.getDataformUrl(Students_personal_schedules);
        FormBody formBody = new FormBody.Builder()
                .add("xh",map.get("xh"))
                .add("xm",map.get("xm"))
                .add("gnmkdm",map.get("gnmkdm"))
                .build();
        Request request = new Request.Builder()
                .url(HostUrl + Students_personal_schedules)
                .header("Host",Host)
                .header("Accept",Accept)
                .header("Accept-Language",Accept_Language)
                .header("Accept-Encoding",Accept_Encoding)
                .header("Referer",HostUrl+urlDataMap.get("返回首页"))
                .header("Cookie",cookie)
                .header("Connection",Connection)
                .header("Upgrade-Insecure-Requests","1")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Document document = Jsoup.parse(response.body().string());
                    Map<String,ArrayList<String>> map = ParseData.parse_02_Stu_per_schHtmlToMap(document);
                }
            }
        });
    }

    /**
     * 获取成绩页面
     */
    /**
     * 查询成绩时的数据
     * */
    private static boolean isgetscore_inquiry = false;
    public static void requestOkhttpforscore_inquiry(){
        Map<String,String> map = ParseData.getDataformUrl(score_inquiry);
        FormBody formBody = new FormBody.Builder()
                .add("xh",map.get("xh"))
                .add("xm",map.get("xm"))
                .add("gnmkdm",map.get("gnmkdm"))
                .build();
        Request request = new Request.Builder()
                .url(HostUrl+score_inquiry)
                .header("Host",Host)
                .header("Accept",Accept)
                .header("Accept-Language",Accept_Language)
                .header("Accept-Encoding",Accept_Encoding)
                .header("Referer",HostUrl+urlDataMap.get("返回首页"))
                .header("Cookie",cookie)
                .header("Connection",Connection)
                .header("Upgrade-Insecure-Requests","1")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Document document = Jsoup.parse(response.body().string());
                String value = ParseData.parse_02_01_score_inquiryHtmlTOMap(document);
                requestOkhttpforscore_inquiry_02(value);

            }
        });
    }


    /**
     * 成绩界面——02 请求
     * @param value
     */
    public static void requestOkhttpforscore_inquiry_02(String value){
        Map<String,String> map = ParseData.getDataformUrl(score_inquiry);
        FormBody formBody = new FormBody.Builder()
                .add("xh",map.get("xh"))
                .add("xm",map.get("xm"))
                .add("gnmkdm",map.get("gnmkdm"))
                .add("__EVENTTARGET","")
                .add("__EVENTARGUMENT","")
                .add("__VIEWSTATE",value)
                .add("ddlXN","")
                .add("ddlXQ","")
                .add("ddl_kcxz","")
                .add("btn_zcj","%C0%FA%C4%EA%B3%C9%BC%A8")
                .build();
        Request request = new Request.Builder()
                .url(HostUrl+score_inquiry)
                .header("Host",Host)
                .header("Accept",Accept)
                .header("Accept-Language",Accept_Language)
                .header("Accept-Encoding",Accept_Encoding)
                .header("Referer",HostUrl+urlDataMap.get("返回首页"))
                .header("Cookie",cookie)
                .header("Connection",Connection)
                .header("Upgrade-Insecure-Requests","1")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                Log.d("获取成绩的header00000======",headers.toString());
                Document document = Jsoup.parse(response.body().string());
                score_inquiryMap = ParseData.parse_02_02_score_inquiryHtmlTOMap(document);
                isgetscore_inquiry = true;
            }
        });
    }

    /**
     * 获取成绩状态查询
     * @return
     */
    public static boolean isgetscore_inquiry(){
        return isgetscore_inquiry;
    }

    /**
     * 获取成绩
     * @return
     */
    public static HashMap<String, ArrayList<String>> getScore_inquiryMap() {
        return score_inquiryMap;
    }
}
