package com.example.okhttp.Activity.SecondActivity

/**
 * Created by 杨豪 on 2018/3/12.
 */
class SecondPresenter:ISecondPresenter ,ISecondPresenter.Listener{

    var model:ISecondModel? = null
    var view:ISecondView? = null
    constructor(view:ISecondView){
        this.model = SecondModel(this)
        this.view  = view
    }


    override fun getstudentData(): HashMap<String, String> {
        return model!!.getstudentData()
    }

    //改变主题
    override fun changeTitle() {
        view!!.chagetitle(model!!.getTitle())
    }

    override fun showstudentData() {
        view!!.showData(model!!.getstudentData())
    }

    override fun getScore() {
        model!!.getScore()
    }

    override fun getSocreOk() {
        view!!.StartThirdActivity()
    }

    override fun getStu_per_schedules() {
        model!!.getStu_per_schedules()
    }

    override fun getStu_per_schedulesOk() {
        view!!.StartForthActivity()
    }
}