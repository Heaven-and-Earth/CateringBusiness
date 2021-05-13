package com.cbm.utils;

/**
 * @ClassName AccountStatus
 * @Description TODO
 * @date 2021/5/11 1:19
 * @Version 1.0
 */
/**
 * 账号状态
 */
public class AccountStatus {


    private  int status;
    private  String  desc;

    AccountStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
