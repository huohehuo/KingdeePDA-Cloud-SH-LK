package com.fangzuo.assist.cloud.Beans;

public class RegisterBean {
    public String Register_code;
    public String type;
    public String val1;
    public String val2;
    public String val3;
    public String val4;
    public String val5;

    public RegisterBean(String Register_code, String val1,String val2,String val3) {
        this.Register_code = Register_code;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }
    public RegisterBean() {
    }
}
