package io.littlehorse.quickstart.services;

import io.littlehorse.sdk.worker.LHTaskMethod;

public class NotiUser {

    @LHTaskMethod("noti-user")
    public String notiUser(){

        return "Send notification to user";
    }

    @LHTaskMethod("noti-user-failed")
    public String notiUserFail(){
        return "payment Failed";
    }


}
