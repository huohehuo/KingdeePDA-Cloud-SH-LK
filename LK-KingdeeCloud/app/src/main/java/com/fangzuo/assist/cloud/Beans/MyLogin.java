package com.fangzuo.assist.cloud.Beans;

public class MyLogin extends CommonResponse{
    public String data;

    @Override
    public String toString() {
        return "MyLogin{" +
                "data='" + data + '\'' +
                ", state=" + state +
                ", returnJson='" + returnJson + '\'' +
                '}';
    }
}
