package com.fangzuo.assist.cloud.Beans;

/**
 * Created by NB on 2017/7/28.
 */

public class SettingList {
    public int ImageResourse;
    public String tv;
    public String tag;
    public int activity;
    public SettingList(){}
    public SettingList(String tv,int Image){
        this.ImageResourse = Image;
        this.tv=tv;
    }
    public SettingList(String tv,String tag,int Image){
        this.ImageResourse = Image;
        this.tv=tv;
        this.tag = tag;
    }
    public SettingList(String tv,int activity,int Image){
        this.ImageResourse = Image;
        this.tv=tv;
        this.activity = activity;
    }
    public SettingList(String tag,String tv,int activity,int Image){
        this.tag = tag;
        this.ImageResourse = Image;
        this.tv=tv;
        this.activity = activity;
    }
}
