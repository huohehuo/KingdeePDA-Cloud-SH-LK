package com.fangzuo.assist.cloud.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wly on 2016/11/26.
 */
public class ShareUtil {
    private static final String FILE_NAME = "ASSIST";
    public static ShareUtil mInstance;
    private Context mContext=null;
    private final SharedPreferences shared;
    private final SharedPreferences.Editor editor;


    public synchronized static ShareUtil getInstance(Context mContext){
        if(mInstance == null)
            mInstance = new ShareUtil(mContext);
        return mInstance;
    }

    public ShareUtil(Context context) {
        this.mContext = context;
        shared = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = shared.edit();
    }


    //获取单据的单据编号：key为activity的类名字
    public void setOrderCode(Activity activity, long orderCode){
        editor.putLong(activity.getClass().getSimpleName()+CommonUtil.getAccountID(),orderCode).apply();
    }
    public long getOrderCode(Activity activity){
        return shared.getLong(activity.getClass().getSimpleName()+CommonUtil.getAccountID(),0);
    }
    //根据activity的tag，获取单据编号
    public void setOrderCode(int activity, long orderCode){
        editor.putLong(activity+CommonUtil.getAccountID(),orderCode).apply();
    }
    //根据activity的tag，获取单据编号
    public void setOrderCode(String activity, long orderCode){
        editor.putLong(activity+CommonUtil.getAccountID(),orderCode).apply();
    }
    public long getOrderCode(int activity){
        return shared.getLong(activity+CommonUtil.getAccountID(),0);
    }
    public long getOrderCode(String activity){
        return shared.getLong(activity+CommonUtil.getAccountID(),0);
    }
    public void setBooleam(String key,boolean val){
        editor.putBoolean(key,val);
        editor.apply();
    }
    public boolean getBoolean(String key){
        return shared.getBoolean(key,false);
    }

    public void setDatabaseIp(String ip){
        editor.putString("databaseIp",ip);
        editor.apply();
    }
    public String getDatabaseIp(){
        return shared.getString("databaseIp","");
    }
    public void setPROGOrderCode(long PISOrderCode){
        editor.putLong("getPROGOrderCode",PISOrderCode).apply();
    }

    public long getPROGOrderCode(){
        return shared.getLong("getPROGOrderCode",0);
    }

    public void setDatabasePort(String port){
        editor.putString("DatabasePort",port);
        editor.apply();
    }
    public String getDatabasePort(){
        return shared.getString("DatabasePort","");
    }


    public void setDataBaseUser(String DataBaseUser) {
        editor.putString("DataBaseUser", DataBaseUser);
        editor.apply();
    }
    public String getDataBaseUser(){
        return shared.getString("DataBaseUser","");
    }


    public void setDataBasePass(String DataBasePass){
        editor.putString("DataBasePass",DataBasePass);
        editor.apply();
    }
    public String getDataBasePass(){
        return shared.getString("DataBasePass","");
    }

    public void setDataBase(String DataBase){
        editor.putString("DataBase",DataBase);
        editor.apply();
    }
    public String getDataBase(){
        return shared.getString("DataBase","");
    }

    public void setVersion(String version){
        editor.putString("version",version);
        editor.apply();
    }
    public String getVersion(){
       return shared.getString("version","");
    }


    public void setUserName(String UserName){
        editor.putString("UserName",UserName);
        editor.apply();
    }
    public String getUserName(){
        return shared.getString("UserName","");
    }

    public void setUserID(String setUserID){
        editor.putString("setUserID",setUserID);
        editor.apply();
    }
    public String getsetUserID(){
        return shared.getString("setUserID","");
    }

    public void setRegisterState(boolean b){
        editor.putBoolean("RegisterState",b);
        editor.apply();
    }
    public boolean getRegisterState(){
        return shared.getBoolean("RegisterState",false);
    }

    public void setServerIP(String ip){
        editor.putString("serverip",ip).apply();
    }

    public String getServerIP(){
        return shared.getString("serverip","");
    }

    public void setServerPort(String port){
        editor.putString("serverport",port).apply();
    }

    public String getServerPort(){
        return shared.getString("serverport","");
    }

    public String getServerUrl(){
        return "http://"+getServerIP()+":"+getServerPort()+"/Assist/";
    }

    public void setMAC(String mac){
        editor.putString("MAC",mac).apply();
    }

    public String getMAC(){
        return shared.getString("MAC","");
    }


    public void  clear(){
        editor.clear().apply();
    }

}
