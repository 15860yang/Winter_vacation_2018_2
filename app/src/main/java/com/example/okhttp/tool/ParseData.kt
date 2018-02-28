package com.example.okhttp.tool

import android.util.Log

import org.jsoup.nodes.Document

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by 杨豪 on 2018/2/25.
 */

object ParseData {
    /**
     * 解析刚登陆进去的html网页文件
     * @param htmlData
     * @return 返回网页中可点击的链接名称键值对
     */
    fun parse_01_HtmlToMap(htmlData: Document): HashMap<String, String> {
        val map = HashMap<String, String>()
        val elements = htmlData.select("body[class=mainbody]").select("div[id=bodyDiv]")
                .select("div[id=headDiv]").select("ul[class=nav]").select("li[class=top]")
        for (element in elements) {
            val element11 = element.select("a[class=top_link]")
            //            Log.d("-------",element11.toString());
            val s1 = element11[0].attr("href")
            //            Log.d("---------","s1 = "+ s1);
            if (s1.length > 2) {
                val s2 = element11[0].select("span").text()
                map.put(s2, s1)
            } else {
                val li = element.select("ul[class=sub]").select("li")
                Log.d("------", li.toString())
                for (e in li) {
                    var ss1 = e.select("a").attr("onclick")
                    val sss = StringBuilder(ss1)
                    sss.delete(0, 7)
                    sss.deleteCharAt(sss.length - 1)
                    sss.deleteCharAt(sss.length - 1)
                    sss.deleteCharAt(sss.length - 1)
                    ss1 = sss.toString()
                    val ss2 = e.select("a").attr("href")
                    map.put(ss1, ss2)
                }
            }
        }
        return map
    }

    /**
     * 解析上面链接地址
     * @param Url
     * @return 可使用的表单数据键值对
     */
    fun getDataformUrl(Url: String): HashMap<String, String> {
        val Datamap = HashMap<String, String>()
        val i = Url.indexOf("?")
        val s = Url.substring(0, i)
        val s1 = Url.substring(i + 1)

        Datamap.put("firsturl", s)
        val strings1 = s1.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (ss in strings1) {
            val ss1 = ss.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            Datamap.put(ss1[0], ss1[1])
        }
        return Datamap
    }

    /**
     * 解析个人信息页面数据
     * @param htmlpersonData
     * @return 个人信息--名称 键值对
     */
    fun parse_02_personDataHtmlTOMap(htmlpersonData: Document): HashMap<String, String> {
        val map = HashMap<String, String>()
        val elements = htmlpersonData
                .select("body").select("div").select("div").select("tbody").select("tr")
        Log.d("88888888888", "解析完成")
        val strings = arrayOfNulls<String>(6)
        var i = 0
        for (element in elements) {
            val elements1 = element.select("td")
            i = 0
            for (e in elements1) {
                Log.d("-------", e.toString())
                strings[i] = e.select("span").text()
                i++
                if (i >= 6) {
                    break
                }
            }
            map.put(strings[0]!!, strings[1]!!)
            map.put(strings[2]!!, strings[3]!!)
            map.put(strings[4]!!, strings[5]!!)
        }
        return map
    }

    /**
     * 获取其他课表的重要参数__VIEWSTATE
     * @param document
     * @return
     */
    fun parse_02_Stu_per_schHtmlTo__VIEWSTATE(document: Document): String {
        return document.select("body").select("input[name=__VIEWSTATE]").attr("value")
    }


    /**
     * 解析课表页面信息
     * @param htmlData
     * @return 节数和这节啥课的键值对
     */
    fun parse_02_Stu_per_schHtmlToMap(htmlData: Document): HashMap<String, ArrayList<String>> {
        val map = HashMap<String, ArrayList<String>>()
        val elements = htmlData.select("body").select("div").select("span[class=formbox]")

        val elements2 = elements.select("table[class=formlist noprint]").select("table").select("tr")
        val headAll = ArrayList<String>()
        val realyear = ArrayList<String>()
        val realnum = ArrayList<String>()
        var i: Int

        val elem1 = elements2[0].select("td").select("select")

        val sw = elem1[0].select("option[selected=selected]").text()
        for (e in elem1[0].select("option")) {
            realyear.add(e.text())
        }

        val s1 = elem1[1].select("option[selected=selected]").text()
        for (e in elem1[1].select("option")) {
            realnum.add(e.text())
        }
        headAll.add(sw + "学年度" + s1 + "学期学生个人课程表")
        map.put("realtyear", realyear)
        map.put("realnum", realnum)
        map.put("head", headAll)
        val elements1 = elements.select("table[class=blacktab]").select("table").select("tr")
        i = 1
        for (s in elements1) {
            if (i == 3 || i == 5 || i == 7 || i == 9 || i == 11) {
                val elements3 = s.select("td")
                val list = ArrayList<String>()
                for (element in elements3) {
                    list.add(element.text())
                }
                if (i == 3 || i == 7 || i == 11) {
                    list.removeAt(0)
                }
                map.put((i / 2).toString() + "----", list)
            }
            i++
        }
        return map
    }


    /**
     * 解析成绩查询的——01界面
     * @param htmlData
     * @return 返回查询成绩所需要的__VIEWSTATE参数
     */
    fun parse_02_01_score_inquiryHtmlTOMap(htmlData: Document): String {
        val map = HashMap<String, String>()
        Log.d("---", "--------------------------------------------")
        return htmlData.select("body").select("form").select("input[name=__VIEWSTATE]")
                .attr("value")
    }

    /**
     * 解析成级页面——02——历年成绩
     * @param document
     * @return map键值对，比如年份——{}  课程——{}...
     */
    fun parse_02_02_score_inquiryHtmlTOMap(document: Document): ArrayList<ArrayList<String>> {
        val lists = ArrayList<ArrayList<String>>()
        val elements = document.select("body").select("div[class=main_box]")
                .select("div[id=divNotPs]").select("table[id=Datagrid1]").select("tbody").select("tr")
        var isonceget = true
        for (e in elements) {
            if (isonceget) {
                isonceget = false
            } else {
                val element = e.select("td")
                val list = ArrayList<String>()
                for (e1 in element) {
                    list.add(e1.text())
                }
                lists.add(list)
            }
        }

        return lists
    }
}
