package com.example.okhttp.Activity.ForthActivity

import java.util.ArrayList

/**
 * Created by 杨豪 on 2018/3/12.
 */
class ForthPresenter:IForthPresenter{

    private var model:IForthModel? = null
    private var view:IForthView? = null

    constructor(view:IForthView){
        this.view = view
        model = ForthModel()
    }

    override fun InitSpinnerData(): ArrayList<String> {
        return model!!.getSpinnerData()
    }

    override fun getkebiaoData():HashMap<String,ArrayList<String>> {
        return model!!.getKebiaoData()
    }
}