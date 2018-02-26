package com.example.okhttp;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 杨豪 on 2018/2/25.
 */

public class ParseData {
    /**
     * 解析刚登陆进去的html网页文件
     * @param htmlData
     * @return 返回网页中可点击的链接名称键值对
     */
    public static HashMap<String,String> parse_01_HtmlToMap(Document htmlData){
        HashMap<String ,String> map = new HashMap<>();
        Elements elements = htmlData.select("body[class=mainbody]").select("div[id=bodyDiv]")
                .select("div[id=headDiv]").select("ul[class=nav]").select("li[class=top]");
        for(Element element : elements){
            Elements element11 = element.select("a[class=top_link]");
//            Log.d("-------",element11.toString());
            String s1 = element11.get(0).attr("href");
//            Log.d("---------","s1 = "+ s1);
            if(s1.length() > 2){
                String s2 = element11.get(0).select("span").text();
                map.put(s2,s1);
            }else {
                Elements li = element.select("ul[class=sub]").select("li");
                Log.d("------",li.toString());
                for(Element e : li){
                    String ss1 = e.select("a").attr("onclick");
                    StringBuilder sss = new StringBuilder(ss1);
                    sss.delete(0,7);
                    sss.deleteCharAt(sss.length()-1);
                    sss.deleteCharAt(sss.length()-1);
                    sss.deleteCharAt(sss.length()-1);
                    ss1 = sss.toString();
                    String ss2 = e.select("a").attr("href");
                    map.put(ss1,ss2);
                }
            }
        }
        return map;
    }

    /**
     * 解析上面链接地址
     * @param Url
     * @return 可使用的表单数据键值对
     */
    public static HashMap<String,String> getDataformUrl(String Url){
        HashMap<String,String> Datamap = new HashMap<String,String>();
        int i = Url.indexOf("?");
        String s = Url.substring(0,i);
        String s1 = Url.substring(i+1);

        Datamap.put("firsturl",s);
        String[] strings1 = s1.split("&");
        for(String ss : strings1){
            String[] ss1 = ss.split("=");
            Datamap.put(ss1[0],ss1[1]);
        }
        return Datamap;
    }

    /**
     * 解析个人信息页面数据
     * @param htmlpersonData
     * @return 个人信息--名称 键值对
     */
    public static HashMap<String,String> parse_02_personDataHtmlTOMap(Document htmlpersonData){
        HashMap<String,String> map = new HashMap<>();
        Elements elements = htmlpersonData
                .select("body").select("div").select("div").select("tbody").select("tr");
        Log.d("88888888888","解析完成");
        String[] strings = new String[6];
        int i = 0;
        for(Element element:elements){
            Elements elements1 = element.select("td");
            i = 0;
            for(Element e :elements1){
                Log.d("-------",e.toString());
                strings[i] = e.select("span").text();
                i++;
                if(i >= 6){
                    break;
                }
            }
            map.put(strings[0],strings[1]);
            map.put(strings[2],strings[3]);
            map.put(strings[4],strings[5]);
        }
        return map;
    }

    /**
     * 解析课表页面信息
     * @param htmlData
     * @return 节数和这节啥课的键值对
     */
    public static HashMap<String,ArrayList<String>> parse_02_Stu_per_schHtmlToMap(Document htmlData){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        Elements elements = htmlData.select("body").select("div").select("span[class=formbox]");

        Elements elements2 = elements.select("table[class=formlist noprint]").select("table").select("tr");
        ArrayList<String> headAll = new ArrayList<>();
        ArrayList<String> realyear = new ArrayList<>();
        ArrayList<String> realnum = new ArrayList<>();
        int i = 1;

        Elements elem1 = elements2.get(0).select("td").select("select");

        String sw = elem1.get(0).select("option[selected=selected]").text();
        for(Element e : elem1.get(0).select("option")){
            realyear.add(e.text());
        }

        String s1 = elem1.get(1).select("option[selected=selected]").text();
        for(Element e :elem1.get(1).select("option")){
            realnum.add(e.text());
        }
        headAll.add(sw+"学年度"+s1+"学期学生个人课程表");
        map.put("realtyear",realyear);
        map.put("realnum",realnum);
        map.put("head",headAll);
        Elements elements1 = elements.select("table[class=blacktab]").select("table").select("tr");
        i = 1;
        for(Element s :elements1 ){
            if( i == 3 || i == 5 || i == 7 || i == 9 || i == 11){
                Elements elements3 = s.select("td");
                ArrayList<String> list = new ArrayList<>();
                for(Element element : elements3){
                    list.add(element.text());
                }
                if(i == 3 || i == 7 || i == 11){
                    list.remove(0);
                }
                map.put(i/2+"----",list);
            }
            i++;
        }
        return map;
    }


    /**
     * 解析成绩查询的——01界面
     * @param htmlData
     * @return 返回查询成绩所需要的__VIEWSTATE参数
     */
    public static String parse_02_01_score_inquiryHtmlTOMap(Document htmlData){
        HashMap<String,String> map = new HashMap<>();
        Log.d("---","--------------------------------------------");
        String value  = htmlData.select("body").select("form").select("input[name=__VIEWSTATE]")
                .attr("value");
        return value;
    }

    /**
     * 解析成级页面——02——历年成绩
     * @param document
     * @return map键值对，比如年份——{}  课程——{}...
     */
    public static HashMap<String,ArrayList<String>> parse_02_02_score_inquiryHtmlTOMap(Document document){
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        Elements elements = document.select("body").select("div[class=main_box]")
                .select("div[id=divNotPs]").select("table[id=Datagrid1]").select("tbody").select("tr");
        boolean isonceget = true;
        ArrayList<String> key = new ArrayList<>();
        for(Element e:elements){
            if(isonceget){
                Elements onceget = e.select("td");
                for(Element ss : onceget){
                    key.add(ss.text());
                }
                for(String s: key ){
                    map.put(s,new ArrayList<String>());
                }
                isonceget = false;
            }else {
                Elements element = e.select("td");
                for(int i = 0;i < key.size() ; i++){
                    map.get(key.get(i)).add(element.get(i).text());
                }
            }

        }
        return map;
    }
}
