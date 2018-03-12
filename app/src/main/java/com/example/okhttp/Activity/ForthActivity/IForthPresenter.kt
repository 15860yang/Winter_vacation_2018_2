package com.example.okhttp.Activity.ForthActivity

import java.util.ArrayList

/**
 * Created by 杨豪 on 2018/3/12.
 */
interface IForthPresenter {
    fun InitSpinnerData(): ArrayList<String>
    fun getkebiaoData():HashMap<String,ArrayList<String>>
}