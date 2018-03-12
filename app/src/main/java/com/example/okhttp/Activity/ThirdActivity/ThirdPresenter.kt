package com.example.okhttp.Activity.ThirdActivity

/**
 * Created by 杨豪 on 2018/3/12.
 */
class ThirdPresenter:IThirdPresenter {

    private var view:IThirdVIew? = null
    private var model:IThirdModel? = null

    constructor(view:IThirdVIew){
        this.view = view
        this.model = ThirdModel()
    }


    override fun changeTitle() {
        view!!.changeTitle(model!!.getstudentname())
    }

    override fun showScore() {

    }

    override fun initspinnerData():ArrayList<String> {
        return model!!.getspinnerData()
    }
}