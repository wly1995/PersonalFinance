package com.atguigu.giugufinance.bean;

/**
 * Created by 万里洋 on 2017/3/16.
 */

public class UserInfo {
    /**
     * data : {"imageurl":"http://182.92.5.3:8081/P2PInvest/images/tx.png","iscredit":"true","name":"xiaolongge","phone":"15321970103"}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
