package com.fangzuo.assist.cloud.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintDataBean;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.PrintTimeBean;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.PrintErrorHistory;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PrintErrorHistoryDao;
import com.fangzuo.greendao.gen.PrintHistoryDao;
import com.orhanobut.hawk.Hawk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zpSDK.zpSDK.zpBluetoothPrinter;

public class CommonUtil {
    /*------------------------------------------------二期打印*/
    //水版打印
    public static void doPrint4P2Shuiban(final zpBluetoothPrinter zpSDK, final PrintHistory bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
//        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
                if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
                {
                    Toast.showText(App.getContext(),"打印机连接失败------");
                    return;
                }
                if (null== zpSDK)return;
                int size=4;
                int size2=3;
                int lineSize=3;
                int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
                for (int i = 0; i < printNum; i++) {

                    zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
                    zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
                    zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
                    /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 220, "货主:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 280, "规格:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 340, "测量宽:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 400, "层数:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 460, "体积:", size, 0, 0, false, false);
                    /*右边数值*/
                    zpSDK.drawText(10, 124, bean.FName,size, 0, 0, false, false);
                    zpSDK.drawText(10, 174, bean.FBatch,size, 0, 0, false, false);
                    zpSDK.drawText(160, 224, bean.FHuoquan==null?"":bean.FHuoquan,size, 0, 0, false, false);
                    zpSDK.drawText(160, 284, bean.FModel,size, 0, 0, false, false);
                    zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
                    String wide = bean.FBWide==null?"":bean.FBWide;
                    if (wide.length()>19){
                        if (wide.length()<38){
                            zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
                            zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
                        }else{
                            zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
                            zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
                            zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
                        }
                    }else{
                        zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
                    }
//            zpSDK.drawText(300, 344, bean.FBWide==null?"":bean.FBWide,size2, 0, 0, false, false);
                    zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
                    zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
//            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
                    zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
                    zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
                    zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
                    zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
                    zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
                    zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.print(0, 1);
                    zpSDK.printerStatus();
                    int a=zpSDK.GetStatus();
                    Lg.e("打印机状态",a);
//                    if(a==-1)
//                    { //"获取状态异常------";
//                        Toast.showText(App.getContext(),"获取状态异常---请重连打印机");
//                    }
//                    if(a==1)
//                    {//"缺纸----------";
//                        Toast.showTextLong(App.getContext(),"打印机缺少纸张---请添加纸张后再操作");
//                    }
//                    if(a==2)
//                    {
//                        //"开盖----------";
//                        Toast.showText(App.getContext(),"请勿打开盖子-----");
//                    }
//                    if(a==0)
//                    {
//                        //"打印机正常-------";
//                        Toast.showText(App.getContext(),"打印成功");
//                    }
                }
                zpSDK.disconnect();
            }
        }).start();

    }

    //原木打印
    public static void doPrint4P2(final zpBluetoothPrinter zpSDK, final PrintHistory bean, final String times) throws Exception {
        Lg.e("打印数据:" + times, bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(context);
                if (!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address)) {
                    Toast.showText(App.getContext(), "打印机连接失败------");
                    return;
                }
                int size = 4;
                int size2 = 3;
                int lineSize = 3;
                int printNum = Integer.parseInt(null == times ? "1" : times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
                for (int i = 0; i < printNum; i++) {
                    zpSDK.pageSetup(550, 400);
                    zpSDK.drawBox(2, 1, 1, 495, 358);
//            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawText(10, 10, bean.FHuoquan == null ? "" : bean.FHuoquan, size, 0, 0, false, false);
                    zpSDK.drawText(10, 60, bean.FName, size, 0, 0, false, false);
                    zpSDK.drawText(10, 110, bean.FBatch, size, 0, 0, false, false);
                    zpSDK.drawQrCode(15, 165, bean.FBarCode, 0, 7, 0);
                    zpSDK.drawLine(2, 10, 155, 495, 155, true);
                    zpSDK.drawBox(2, 215, 155, 495, 358);
                    zpSDK.drawText(222, 158, "L=" + bean.FNum2, size2, 0, 0, false, false);
                    zpSDK.drawText(222, 208, "D=" + bean.FAuxSign, size2, 0, 0, false, false);
                    zpSDK.drawText(222, 258, "V=" + bean.FNum, size2, 0, 0, false, false);
                    zpSDK.drawText(222, 298, bean.FDate, size2, 0, 0, false, false);
//                    if ("1".equals(bean.F_TypeID)) {//立方米版本(已弃用)
//                        zpSDK.drawText(450, 155, "m", size2, 0, 0, false, false);
//                        zpSDK.drawText(450, 205, "cm", size2, 0, 0, false, false);
//                        zpSDK.drawText(450, 255, "m3", size2, 0, 0, false, false);
//                    } else {//2或者其他，为英寸版本
                    zpSDK.drawText(450, 155, "ft", size2, 0, 0, false, false);
                    zpSDK.drawText(450, 205, "in", size2, 0, 0, false, false);
                    zpSDK.drawText(450, 255, "bf", size2, 0, 0, false, false);
//                    }

//            zpSDK.drawText(230, 50, "物料标签", size, 0, 1, false, false);
//            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
//            zpSDK.drawText(10, 120, "货主:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 124, bean.FHuoquan==null?"":bean.FHuoquan,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 174, bean.FBatch,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 224, bean.FName,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 280, "规格:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 284, bean.FModel,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 340, "材积:", size, 0, 0, false, false);
//            zpSDK.drawText(230, 344, bean.FNum==null?"":bean.FNum,size2, 0, 0, false, false);
//            zpSDK.drawText(450, 344, bean.FUnit==null?"":bean.FUnit,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 400, "长度:", size, 0, 0, false, false);
//            zpSDK.drawText(230, 404, bean.FNum2==null?"":bean.FNum2,size2, 0, 0, false, false);
//            //            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(450, 404, "米(m)",size2, 0, 0, false, false);
//            zpSDK.drawText(10, 460, "直径:", size, 0, 0, false, false);
//            zpSDK.drawText(230, 464, bean.FAuxSign==null?"":bean.FAuxSign,size2, 0, 0, false, false);
//            zpSDK.drawText(450, 464, "厘米(cm)",size2, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
//            zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
//            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
//            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
////            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
//            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
//            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.print(0, 1);
//                    int a = zpSDK.GetStatus();
//                    Lg.e("打印机状态", a);
//                    if (a == -1) { //"获取状态异常------";
//                        Toast.showText(App.getContext(), "获取状态异常---请重连打印机");
//                    }
//                    if (a == 1) {//"缺纸----------";
//                        Toast.showTextLong(App.getContext(), "打印机缺少纸张---请添加纸张后再操作");
//                    }
//                    if (a == 2) {
//                        //"开盖----------";
//                        Toast.showText(App.getContext(), "请勿打开盖子-----");
//                    }
//                    if (a == 0) {
//                        //"打印机正常-------";
//                        Toast.showText(App.getContext(), "打印成功");
//                    }
                }
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Out_OK, ""));
                Lg.e("disconnect....");
                zpSDK.disconnect();
                Lg.e("disconnect");
            }
        }).start();
//        }
//            zpSDK.drawBarCode(8, 540, "12345678901234567", 128, true, 3, 60);
//              zpSDK.drawGraphic(90, 70, 0, 0, bmp);
//            zpSDK.drawQrCode(350, 48, "111111111", 0, 6, 0);
//            zpSDK.drawText(90, 48+100, "400-8800-", 2
//                    , 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56, "株洲      张贺", 4, 0, 0, false, false);
//            zpSDK.drawText(250, 48+100+56+56, "经由  株洲", 2, 0, 0, false, false);

//            zpSDK.drawText(100, 48+100+56+56+80, "2015110101079-01-01   广州", 2, 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56+56+80+80, "2015-11-01  23:00    卡班", 2, 0, 0, false, false);

//            zpSDK.drawBarCode(124,48+100+56+56+80+80+80 , "12345678901234567", 128, false, 3, 60);
//            zpSDK.print(0, 0);


    }

    //原木打印
    public static void doPrint4P2ForYC(final zpBluetoothPrinter zpSDK, final PrintHistory bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
//        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
                if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
                {
                    Toast.showText(App.getContext(),"打印机连接失败------");
                    return;
                }

                if (null== zpSDK)return;
                int size=4;
                int size2=3;
                int lineSize=3;
                int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
                for (int i = 0; i < printNum; i++) {
                    zpSDK.pageSetup(550, 400);
                    zpSDK.drawBox(2,1,1,495,358);
//            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawText(10, 10, bean.FHuoquan==null?"":bean.FHuoquan,size, 0, 0, false, false);
                    zpSDK.drawText(10, 60, bean.FName,size, 0, 0, false, false);
                    zpSDK.drawText(10, 110, bean.FBatch,size, 0, 0, false, false);
                    zpSDK.drawQrCode(15, 165, bean.FBarCode, 0, 7, 0);
                    zpSDK.drawLine(2,10,155,495,155,true);
                    zpSDK.drawBox(2,215,155,495,358);
                    zpSDK.drawText(222, 158, "L="+bean.FYmLenght,size2, 0, 0, false, false);
                    zpSDK.drawText(222, 208, "D="+bean.FYmDiameter,size2, 0, 0, false, false);
                    zpSDK.drawText(222, 258, "V="+bean.FVolume,size2, 0, 0, false, false);
                    zpSDK.drawText(222, 298, bean.FDate,size2, 0, 0, false, false);
//            if ("1".equals(bean.F_TypeID)){//立方米版本
//                zpSDK.drawText(450, 155, "m",size2, 0, 0, false, false);
//                zpSDK.drawText(450, 205, "cm",size2, 0, 0, false, false);
//                zpSDK.drawText(450, 255, "m3",size2, 0, 0, false, false);
//            }else{//2或者其他，为英寸版本
                    zpSDK.drawText(450, 155, "ft",size2, 0, 0, false, false);
                    zpSDK.drawText(450, 205, "in",size2, 0, 0, false, false);
                    zpSDK.drawText(450, 255, "bf",size2, 0, 0, false, false);
//            }

//            zpSDK.drawText(230, 50, "物料标签", size, 0, 1, false, false);
//            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
//            zpSDK.drawText(10, 120, "货主:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 124, bean.FHuoquan==null?"":bean.FHuoquan,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 174, bean.FBatch,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 224, bean.FName,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 280, "规格:", size, 0, 0, false, false);
//            zpSDK.drawText(160, 284, bean.FModel,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 340, "材积:", size, 0, 0, false, false);
//            zpSDK.drawText(230, 344, bean.FNum==null?"":bean.FNum,size2, 0, 0, false, false);
//            zpSDK.drawText(450, 344, bean.FUnit==null?"":bean.FUnit,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 400, "长度:", size, 0, 0, false, false);
//            zpSDK.drawText(230, 404, bean.FNum2==null?"":bean.FNum2,size2, 0, 0, false, false);
//            //            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(450, 404, "米(m)",size2, 0, 0, false, false);
//            zpSDK.drawText(10, 460, "直径:", size, 0, 0, false, false);
//            zpSDK.drawText(230, 464, bean.FAuxSign==null?"":bean.FAuxSign,size2, 0, 0, false, false);
//            zpSDK.drawText(450, 464, "厘米(cm)",size2, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
//            zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
//            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
//            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
////            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
//            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
//            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
//            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.print(0, 1);
//            int a=zpSDK.GetStatus();
//            Lg.e("打印机状态",a);
//            if(a==-1)
//            { //"获取状态异常------";
//                Toast.showText(App.getContext(),"获取状态异常---请重连打印机");
//            }
//            if(a==1)
//            {//"缺纸----------";
//                Toast.showTextLong(App.getContext(),"打印机缺少纸张---请添加纸张后再操作");
//            }
//            if(a==2)
//            {
//                //"开盖----------";
//                Toast.showText(App.getContext(),"请勿打开盖子-----");
//            }
//            if(a==0)
//            {
//                //"打印机正常-------";
//                Toast.showText(App.getContext(),"打印机正常-----");
//            }
                }
                zpSDK.disconnect();
//        }
//            zpSDK.drawBarCode(8, 540, "12345678901234567", 128, true, 3, 60);
//              zpSDK.drawGraphic(90, 70, 0, 0, bmp);
//            zpSDK.drawQrCode(350, 48, "111111111", 0, 6, 0);
//            zpSDK.drawText(90, 48+100, "400-8800-", 2
//                    , 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56, "株洲      张贺", 4, 0, 0, false, false);
//            zpSDK.drawText(250, 48+100+56+56, "经由  株洲", 2, 0, 0, false, false);

//            zpSDK.drawText(100, 48+100+56+56+80, "2015110101079-01-01   广州", 2, 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56+56+80+80, "2015-11-01  23:00    卡班", 2, 0, 0, false, false);

//            zpSDK.drawBarCode(124,48+100+56+56+80+80+80 , "12345678901234567", 128, false, 3, 60);
//            zpSDK.print(0, 0);
            }
        }).start();
    }

    //刨光打印
    public static void doPrint4P2Wort(zpBluetoothPrinter zpSDK, List<WortPrintData> bean, String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        if (null== zpSDK)return;
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {

            zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 174, "名称:", size, 0, 0, false, false);
            zpSDK.drawText(10, 228, "总长:", size, 0, 0, false, false);
            zpSDK.drawText(500, 228, "MM", size, 0, 0, false, false);
            zpSDK.drawText(10, 288, "宽:", size, 0, 0, false, false);
            zpSDK.drawText(500, 288, "MM", size, 0, 0, false, false);
            zpSDK.drawText(10, 348, "厚度:", size, 0, 0, false, false);
            zpSDK.drawText(500, 348, "MM", size, 0, 0, false, false);
            zpSDK.drawText(10, 444, "PCS:", size, 0, 0, false, false);
            zpSDK.drawText(10, 504, "M2:", size, 0, 0, false, false);
            /*右边数值*/
            zpSDK.drawText(10, 124, bean.get(0).FBatch,size, 0, 0, false, false);
            zpSDK.drawText(156, 174, bean.get(0).FName,size, 0, 0, false, false);
            String[] split = bean.get(0).FModel.split("x", 3);
            Lg.e("截取长度", split.length);
            Lg.e("截取长度", split);
            if (split.length == 3) {
                zpSDK.drawText(156, 224, split[0],size, 0, 0, false, false);
                zpSDK.drawText(156, 284, split[1],size, 0, 0, false, false);
                zpSDK.drawText(156, 344, split[2],size, 0, 0, false, false);
            }
            zpSDK.drawText(156, 444, MathUtil.Cut0(bean.get(0).FQtySum),size, 0, 0, false, false);
            zpSDK.drawText(156, 504, bean.get(0).FM2Sum,size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, bean.get(0).FUser,size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.print(0, 1);
            zpSDK.pageSetup(668, 900);
            zpSDK.drawText(0, 55, "明细:",size2, 0, 0, false, false);
            zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);

            zpSDK.drawText(90, 90, "L", size, 0, 0, false, false);
            zpSDK.drawText(260, 90, "PCS", size, 0, 0, false, false);
            zpSDK.drawText(420, 90, "M2", size, 0, 0, false, false);
            zpSDK.drawText(0, 123, "______________________________________________", 2, 0, 0, false, false);

            int print_x=150;
            for (int j = 0; j < bean.size(); j++) {
                zpSDK.drawText(90, print_x, bean.get(j).FLenght, 2, 0, 0, false, false);
                zpSDK.drawText(260, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
                zpSDK.drawText(420, print_x, bean.get(j).FM2, 2, 0, 0, false, false);
                print_x+=35;
            }
            zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(90, print_x+8, "汇总:", 2, 0, 0, false, false);
            zpSDK.drawText(260, print_x+8, MathUtil.Cut0(bean.get(0).FQtySum), 2, 0, 0, false, false);
            zpSDK.drawText(420, print_x+8, bean.get(0).FM2Sum, 2, 0, 0, false, false);
            zpSDK.print(0, 1);



        }
//        zpSDK.disconnect();
    }

    //刨光打印
    public static void doPrint4P2Wort(Context context,List<WortPrintData> bean, String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
        if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
        {
            Toast.showText(context,"打印机连接失败------");
            return;
        }
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {

            zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 174, "名称:", size, 0, 0, false, false);
            zpSDK.drawText(10, 228, "总长:", size, 0, 0, false, false);
            zpSDK.drawText(500, 228, "MM", size, 0, 0, false, false);
            zpSDK.drawText(10, 288, "宽:", size, 0, 0, false, false);
            zpSDK.drawText(500, 288, "MM", size, 0, 0, false, false);
            zpSDK.drawText(10, 348, "厚度:", size, 0, 0, false, false);
            zpSDK.drawText(500, 348, "MM", size, 0, 0, false, false);
            zpSDK.drawText(10, 444, "PCS:", size, 0, 0, false, false);
            zpSDK.drawText(10, 504, "M2:", size, 0, 0, false, false);
            /*右边数值*/
            zpSDK.drawText(10, 124, bean.get(0).FBatch,size, 0, 0, false, false);
            zpSDK.drawText(156, 174, bean.get(0).FName,size, 0, 0, false, false);
            String[] split = bean.get(0).FModel.split("x", 3);
            Lg.e("截取长度", split.length);
            Lg.e("截取长度", split);
            if (split.length == 3) {
                zpSDK.drawText(156, 224, split[0],size, 0, 0, false, false);
                zpSDK.drawText(156, 284, split[1],size, 0, 0, false, false);
                zpSDK.drawText(156, 344, split[2],size, 0, 0, false, false);
            }
            zpSDK.drawText(156, 444, MathUtil.Cut0(bean.get(0).FQtySum),size, 0, 0, false, false);
            zpSDK.drawText(156, 504, bean.get(0).FM2Sum,size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, bean.get(0).FUser,size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.print(0, 1);
            zpSDK.pageSetup(668, 900);
            zpSDK.drawText(0, 55, "明细:",size2, 0, 0, false, false);
            zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);

            zpSDK.drawText(90, 90, "L", size, 0, 0, false, false);
            zpSDK.drawText(260, 90, "PCS", size, 0, 0, false, false);
            zpSDK.drawText(420, 90, "M2", size, 0, 0, false, false);
            zpSDK.drawText(0, 123, "______________________________________________", 2, 0, 0, false, false);

            int print_x=150;
            for (int j = 0; j < bean.size(); j++) {
                zpSDK.drawText(90, print_x, bean.get(j).FLenght, 2, 0, 0, false, false);
                zpSDK.drawText(260, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
                zpSDK.drawText(420, print_x, bean.get(j).FM2, 2, 0, 0, false, false);
                print_x+=35;
            }
            zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(90, print_x+8, "汇总:", 2, 0, 0, false, false);
            zpSDK.drawText(260, print_x+8, MathUtil.Cut0(bean.get(0).FQtySum), 2, 0, 0, false, false);
            zpSDK.drawText(420, print_x+8, bean.get(0).FM2Sum, 2, 0, 0, false, false);
            zpSDK.print(0, 1);



        }
        zpSDK.disconnect();
    }

    public static void doPrint433(final Context context, final PrintHistory bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
                if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
                {
                    Toast.showText(context,"打印机连接失败------");
                    Lg.e("打印失败....");
//                    printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
                    return;
                }
                int size=4;
                int size2=3;
                int lineSize=3;
                int printNum = Integer.parseInt(null==times?"1":times);
                //        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
                for (int i = 0; i < printNum; i++) {
                    zpSDK.pageSetup(668, 900);
                    zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.drawText(230, 50, "物料标签", size, 0, 1, false, false);
                    zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawText(10, 120, "货主:", size, 0, 0, false, false);
                    zpSDK.drawText(160, 124, bean.FHuoquan==null?"":bean.FHuoquan,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 180, "名称:", size, 0, 0, false, false);
                    zpSDK.drawText(160, 184, bean.FName,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 230, "批号:", size, 0, 0, false, false);
                    zpSDK.drawText(160, 234, bean.FBatch,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 290, "规格:", size, 0, 0, false, false);
                    zpSDK.drawText(160, 294, bean.FModel,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 350, "M3:", size, 0, 0, false, false);
                    zpSDK.drawText(160, 354, bean.FNum==null?"":bean.FNum,size2, 0, 0, false, false);
//                    zpSDK.drawText(450, 344, bean.FUnit==null?"":bean.FUnit,size2, 0, 0, false, false);
//                    zpSDK.drawText(10, 400, "数量2:", size, 0, 0, false, false);
//                    zpSDK.drawText(230, 404, bean.FNum2==null?"":bean.FNum2,size2, 0, 0, false, false);
//                    zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//                    zpSDK.drawText(10, 460, "辅助标识:", size, 0, 0, false, false);
//                    zpSDK.drawText(360, 464, bean.FAuxSign==null?"":bean.FAuxSign,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
                    zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
                    //            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
                    zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
                    zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
                    zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.print(0, 1);
                    //            zpSDK.printerStatus();
                    //            int a=zpSDK.GetStatus();
                    //            Lg.e("打印机状态",a);
                    //            if(a==-1)
                    //            { //"获取状态异常------";
                    //                Toast.showText(App.getContext(),"获取状态异常---请重连打印机");
                    //            }
                    //            if(a==1)
                    //            {//"缺纸----------";
                    //                Toast.showTextLong(App.getContext(),"打印机缺少纸张---请添加纸张后再操作");
                    //            }
                    //            if(a==2)
                    //            {
                    //                //"开盖----------";
                    //                Toast.showText(App.getContext(),"请勿打开盖子-----");
                    //            }
                    //            if(a==0)
                    //            {
                    //                //"打印机正常-------";
                    //                Toast.showText(App.getContext(),"打印机正常-----");
                    //            }
                }
                zpSDK.disconnect();

            }
        }).start();

//        }
//            zpSDK.drawBarCode(8, 540, "12345678901234567", 128, true, 3, 60);
//              zpSDK.drawGraphic(90, 70, 0, 0, bmp);
//            zpSDK.drawQrCode(350, 48, "111111111", 0, 6, 0);
//            zpSDK.drawText(90, 48+100, "400-8800-", 2
//                    , 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56, "株洲      张贺", 4, 0, 0, false, false);
//            zpSDK.drawText(250, 48+100+56+56, "经由  株洲", 2, 0, 0, false, false);

//            zpSDK.drawText(100, 48+100+56+56+80, "2015110101079-01-01   广州", 2, 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56+56+80+80, "2015-11-01  23:00    卡班", 2, 0, 0, false, false);

//            zpSDK.drawBarCode(124,48+100+56+56+80+80+80 , "12345678901234567", 128, false, 3, 60);
//            zpSDK.print(0, 0);
    }


    /*------------------------------------------------一期打印*/

    public static void doPrint(final Context context, final PrintHistory bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
            zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
                if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
                {
                    Toast.showText(context,"打印机连接失败------");
                    Lg.e("打印失败....");
//                    printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
                    return;
                }
            int size=4;
            int size2=3;
            int lineSize=3;
            int printNum = Integer.parseInt(null==times?"1":times);
    //        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
            for (int i = 0; i < printNum; i++) {
                zpSDK.pageSetup(668, 900);
                zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);

                zpSDK.drawText(230, 50, "物料标签", size, 0, 1, false, false);
                zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
                zpSDK.drawText(10, 120, "货主:", size, 0, 0, false, false);
                zpSDK.drawText(160, 124, bean.FHuoquan==null?"":bean.FHuoquan,size2, 0, 0, false, false);
                zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
                zpSDK.drawText(160, 174, bean.FBatch,size2, 0, 0, false, false);
                zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
                zpSDK.drawText(160, 224, bean.FName,size2, 0, 0, false, false);
                zpSDK.drawText(10, 280, "规格:", size, 0, 0, false, false);
                zpSDK.drawText(160, 284, bean.FModel,size2, 0, 0, false, false);
                zpSDK.drawText(10, 340, "数量1:", size, 0, 0, false, false);
                zpSDK.drawText(230, 344, bean.FNum==null?"":bean.FNum,size2, 0, 0, false, false);
                zpSDK.drawText(450, 344, bean.FUnit==null?"":bean.FUnit,size2, 0, 0, false, false);
                zpSDK.drawText(10, 400, "数量2:", size, 0, 0, false, false);
                zpSDK.drawText(230, 404, bean.FNum2==null?"":bean.FNum2,size2, 0, 0, false, false);
                zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
                zpSDK.drawText(10, 460, "辅助标识:", size, 0, 0, false, false);
                zpSDK.drawText(360, 464, bean.FAuxSign==null?"":bean.FAuxSign,size2, 0, 0, false, false);
                zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
                zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
                zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
                zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
                zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
    //            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
                zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
                zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
                zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
                zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
                zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
                zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

                zpSDK.print(0, 1);
    //            zpSDK.printerStatus();
    //            int a=zpSDK.GetStatus();
    //            Lg.e("打印机状态",a);
    //            if(a==-1)
    //            { //"获取状态异常------";
    //                Toast.showText(App.getContext(),"获取状态异常---请重连打印机");
    //            }
    //            if(a==1)
    //            {//"缺纸----------";
    //                Toast.showTextLong(App.getContext(),"打印机缺少纸张---请添加纸张后再操作");
    //            }
    //            if(a==2)
    //            {
    //                //"开盖----------";
    //                Toast.showText(App.getContext(),"请勿打开盖子-----");
    //            }
    //            if(a==0)
    //            {
    //                //"打印机正常-------";
    //                Toast.showText(App.getContext(),"打印机正常-----");
    //            }
            }
            zpSDK.disconnect();

        }
    }).start();

//        }
//            zpSDK.drawBarCode(8, 540, "12345678901234567", 128, true, 3, 60);
//              zpSDK.drawGraphic(90, 70, 0, 0, bmp);
//            zpSDK.drawQrCode(350, 48, "111111111", 0, 6, 0);
//            zpSDK.drawText(90, 48+100, "400-8800-", 2
//                    , 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56, "株洲      张贺", 4, 0, 0, false, false);
//            zpSDK.drawText(250, 48+100+56+56, "经由  株洲", 2, 0, 0, false, false);

//            zpSDK.drawText(100, 48+100+56+56+80, "2015110101079-01-01   广州", 2, 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56+56+80+80, "2015-11-01  23:00    卡班", 2, 0, 0, false, false);

//            zpSDK.drawBarCode(124,48+100+56+56+80+80+80 , "12345678901234567", 128, false, 3, 60);
//            zpSDK.print(0, 0);
    }
    public static void doPrint(zpBluetoothPrinter zpSDK, PrintHistory bean,String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        if (null== zpSDK)return;
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {
            zpSDK.pageSetup(668, 900);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);

            zpSDK.drawText(230, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(10, 120, "货主:", size, 0, 0, false, false);
            zpSDK.drawText(160, 124, bean.FHuoquan==null?"":bean.FHuoquan,size2, 0, 0, false, false);
            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(160, 174, bean.FBatch,size2, 0, 0, false, false);
            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
            zpSDK.drawText(160, 224, bean.FName,size2, 0, 0, false, false);
            zpSDK.drawText(10, 280, "规格:", size, 0, 0, false, false);
            zpSDK.drawText(160, 284, bean.FModel,size2, 0, 0, false, false);
            zpSDK.drawText(10, 340, "数量1:", size, 0, 0, false, false);
            zpSDK.drawText(230, 344, bean.FNum==null?"":bean.FNum,size2, 0, 0, false, false);
            zpSDK.drawText(450, 344, bean.FUnit==null?"":bean.FUnit,size2, 0, 0, false, false);
            zpSDK.drawText(10, 400, "数量2:", size, 0, 0, false, false);
            zpSDK.drawText(230, 404, bean.FNum2==null?"":bean.FNum2,size2, 0, 0, false, false);
            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
            zpSDK.drawText(10, 460, "辅助标识:", size, 0, 0, false, false);
            zpSDK.drawText(360, 464, bean.FAuxSign==null?"":bean.FAuxSign,size2, 0, 0, false, false);
            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

            zpSDK.print(0, 1);
//            zpSDK.printerStatus();
//            int a=zpSDK.GetStatus();
//            Lg.e("打印机状态",a);
//            if(a==-1)
//            { //"获取状态异常------";
//                Toast.showText(App.getContext(),"获取状态异常---请重连打印机");
//            }
//            if(a==1)
//            {//"缺纸----------";
//                Toast.showTextLong(App.getContext(),"打印机缺少纸张---请添加纸张后再操作");
//            }
//            if(a==2)
//            {
//                //"开盖----------";
//                Toast.showText(App.getContext(),"请勿打开盖子-----");
//            }
//            if(a==0)
//            {
//                //"打印机正常-------";
//                Toast.showText(App.getContext(),"打印机正常-----");
//            }
        }

//        }
//            zpSDK.drawBarCode(8, 540, "12345678901234567", 128, true, 3, 60);
//              zpSDK.drawGraphic(90, 70, 0, 0, bmp);
//            zpSDK.drawQrCode(350, 48, "111111111", 0, 6, 0);
//            zpSDK.drawText(90, 48+100, "400-8800-", 2
//                    , 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56, "株洲      张贺", 4, 0, 0, false, false);
//            zpSDK.drawText(250, 48+100+56+56, "经由  株洲", 2, 0, 0, false, false);

//            zpSDK.drawText(100, 48+100+56+56+80, "2015110101079-01-01   广州", 2, 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56+56+80+80, "2015-11-01  23:00    卡班", 2, 0, 0, false, false);

//            zpSDK.drawBarCode(124,48+100+56+56+80+80+80 , "12345678901234567", 128, false, 3, 60);
//            zpSDK.print(0, 0);
    }
    public static void doPrintOut(final zpBluetoothPrinter zpSDK, final PrintHistory bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(context);
                if (!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address)) {
                    Toast.showText(App.getContext(), "打印机连接失败------");
                    return;
                }


//        if (null== zpSDK)return;
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {
            zpSDK.pageSetup(668, 900);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);

            zpSDK.drawText(230, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(10, 120, "货主:", size, 0, 0, false, false);
            zpSDK.drawText(160, 124, bean.FHuoquan==null?"":bean.FHuoquan,size2, 0, 0, false, false);
            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(160, 174, bean.FBatch,size2, 0, 0, false, false);
            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
            zpSDK.drawText(160, 224, bean.FName,size2, 0, 0, false, false);
            zpSDK.drawText(10, 280, "规格:", size, 0, 0, false, false);
            zpSDK.drawText(160, 284, bean.FModel,size2, 0, 0, false, false);
            zpSDK.drawText(10, 340, "数量1:", size, 0, 0, false, false);
            zpSDK.drawText(230, 344, bean.FNum==null?"":bean.FNum,size2, 0, 0, false, false);
            zpSDK.drawText(450, 344, bean.FUnit==null?"":bean.FUnit,size2, 0, 0, false, false);
            zpSDK.drawText(10, 400, "数量2:", size, 0, 0, false, false);
            zpSDK.drawText(230, 404, bean.FNum2==null?"":bean.FNum2,size2, 0, 0, false, false);
            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
            zpSDK.drawText(10, 460, "辅助标识:", size, 0, 0, false, false);
            zpSDK.drawText(360, 464, bean.FAuxSign==null?"":bean.FAuxSign,size2, 0, 0, false, false);
            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawQrCode(10, 560, bean.FBarCode, 0, 11, 0);
            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, bean.FWaveHouse==null?"":bean.FWaveHouse,size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, bean.FDate,size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);

            zpSDK.print(0, 1);
//            zpSDK.printerStatus();
//            int a=zpSDK.GetStatus();
//            Lg.e("打印机状态",a);
//            if(a==-1)
//            { //"获取状态异常------";
//                Toast.showText(App.getContext(),"获取状态异常---请重连打印机");
//            }
//            if(a==1)
//            {//"缺纸----------";
//                Toast.showTextLong(App.getContext(),"打印机缺少纸张---请添加纸张后再操作");
//            }
//            if(a==2)
//            {
//                //"开盖----------";
//                Toast.showText(App.getContext(),"请勿打开盖子-----");
//            }
//            if(a==0)
//            {
//                //"打印机正常-------";
//                Toast.showText(App.getContext(),"打印机正常-----");
//            }
            }
                Lg.e("disconnect....");
                zpSDK.disconnect();
                Lg.e("disconnect");
            }
        }).start();

//        }
//            zpSDK.drawBarCode(8, 540, "12345678901234567", 128, true, 3, 60);
//              zpSDK.drawGraphic(90, 70, 0, 0, bmp);
//            zpSDK.drawQrCode(350, 48, "111111111", 0, 6, 0);
//            zpSDK.drawText(90, 48+100, "400-8800-", 2
//                    , 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56, "株洲      张贺", 4, 0, 0, false, false);
//            zpSDK.drawText(250, 48+100+56+56, "经由  株洲", 2, 0, 0, false, false);

//            zpSDK.drawText(100, 48+100+56+56+80, "2015110101079-01-01   广州", 2, 0, 0, false, false);
//            zpSDK.drawText(100, 48+100+56+56+80+80, "2015-11-01  23:00    卡班", 2, 0, 0, false, false);

//            zpSDK.drawBarCode(124,48+100+56+56+80+80+80 , "12345678901234567", 128, false, 3, 60);
//            zpSDK.print(0, 0);
    }


    //箱码调拨单打印
    public static void doPrint4P1DBBoxCode(final zpBluetoothPrinter zpSDK, final Context context, final List<WortPrintData> bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
                PrintErrorHistoryDao printHistoryDao= GreenDaoManager.getmInstance(context).getDaoSession().getPrintErrorHistoryDao();
                if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
                {
                    Toast.showText(context,"打印机连接失败------");
                    Lg.e("打印失败....");
                    printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
                    return;
                }
                int size=4;
                int size2=3;
                int lineSize=3;
                int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
                for (int i = 0; i < printNum; i++) {

                    zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
                    zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
                    zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
                    /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 124, "分包号:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 174, "货主:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 228, "名称:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 288, "批号:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 348, "宽度:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 444, "PCS:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 504, "M3:", size, 0, 0, false, false);
                    /*右边数值*/
                    zpSDK.drawText(186, 124, printNum+"-"+(i+1),size, 0, 0, false, false);
                    zpSDK.drawText(156, 174, LocDataUtil.getOrg(bean.get(0).FOWNERID,"id").FNote,size, 0, 0, false, false);
//            String[] split = bean.get(0).FModel.split("x", 3);
//            Lg.e("截取长度", split.length);
//            Lg.e("截取长度", split);
//            if (split.length == 3) {
                    zpSDK.drawText(156, 224, bean.get(0).FName,size, 0, 0, false, false);
                    zpSDK.drawText(156, 284, bean.get(0).FBatch,size, 0, 0, false, false);
                    zpSDK.drawText(156, 344, bean.get(0).FWide,size, 0, 0, false, false);
//            }
                    zpSDK.drawText(156, 444, bean.get(0).FQtySum,size, 0, 0, false, false);
                    zpSDK.drawText(156, 504, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolSum))+"",size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
                    zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
                    zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
                    zpSDK.drawText(380, 640, Hawk.get(Info.AutoLogin,""),size2, 0, 0, false, false);
                    zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
                    zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.print(0, 1);

                }
                zpSDK.disconnect();
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,bean.get(0).FBoxCode));
                printHistoryDao.deleteInTx(printHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(bean.get(0).FBoxCode)).build().list());
                //打印成功
            }
        }).start();

    }

    //箱码打印
    public static void doPrint4P1BoxCodeReBox(Context context, List<PrintDataBean> bean, String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
        PrintErrorHistoryDao printHistoryDao= GreenDaoManager.getmInstance(context).getDaoSession().getPrintErrorHistoryDao();
        if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
        {
            Toast.showText(context,"打印机连接失败------");
            Lg.e("打印失败....");
            printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
            return;
        }
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {

            zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 124, "分包号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 174, "货主:", size, 0, 0, false, false);
            zpSDK.drawText(10, 228, "名称:", size, 0, 0, false, false);
            zpSDK.drawText(10, 288, "车号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 348, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 444, "PCS:", size, 0, 0, false, false);
            zpSDK.drawText(10, 504, "M2/M3:", size, 0, 0, false, false);
            /*右边数值*/

            zpSDK.drawText(186, 124, bean.get(0).FFBaoNo,size, 0, 0, false, false);

            zpSDK.drawText(156, 174, bean.get(0).FHuozhuNote,size, 0, 0, false, false);
//            String[] split = bean.get(0).FModel.split("x", 3);
//            Lg.e("截取长度", split.length);
//            Lg.e("截取长度", split);
//            if (split.length == 3) {
                zpSDK.drawText(156, 224, bean.get(0).FName,size, 0, 0, false, false);
                zpSDK.drawText(156, 284, bean.get(0).FCarNo,size, 0, 0, false, false);
                zpSDK.drawText(156, 344, bean.get(0).FBatch,size, 0, 0, false, false);
//            }
            zpSDK.drawText(156, 444, bean.get(0).FQtyAll,size, 0, 0, false, false);
            zpSDK.drawText(156, 504, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"",size, 0, 0, false, false);
            //当为true时，箱码条码打印时会拼接递增
            zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);


            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, bean.get(0).FMaker,size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.print(0, 1);
            //打印最后一份明细
            if ((i+1)==printNum){
                List<PrintTimeBean> printTimeBeanList = new ArrayList<>();
                if (bean.size()>18){
                    if (bean.size()>36){
                        printTimeBeanList.add(new PrintTimeBean(0,18));
                        printTimeBeanList.add(new PrintTimeBean(18,35));
                        printTimeBeanList.add(new PrintTimeBean(35,bean.size()));
                    }else{
                        printTimeBeanList.add(new PrintTimeBean(0,18));
                        printTimeBeanList.add(new PrintTimeBean(18,bean.size()));
                    }
                }else{
                    printTimeBeanList.add(new PrintTimeBean(0,bean.size()));
                }

                for (int k = 0; k < printTimeBeanList.size(); k++) {
                    zpSDK.pageSetup(668, 900);
                    zpSDK.drawText(0, 55, "明细标签:",size2, 0, 0, false, false);
                    zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.drawText(0, 90, "物料明细", size2, 0, 0, false, false);
                    zpSDK.drawText(0, 130, "物料名称:", size2, 0, 0, false, false);
                    zpSDK.drawText(156, 130, bean.get(0).FName, size2, 0, 0, false, false);

                    zpSDK.drawText(0, 170, "等级", size2, 0, 0, false, false);
                    zpSDK.drawText(140, 170, "规格", size2, 0, 0, false, false);
                    zpSDK.drawText(320, 170, "PCS", size2, 0, 0, false, false);
                    zpSDK.drawText(470, 170, "M3", size2, 0, 0, false, false);
                    zpSDK.drawText(0, 190, "______________________________________________", 2, 0, 0, false, false);

                    int print_x=220;
                    for (int j = printTimeBeanList.get(k).FStart; j < printTimeBeanList.get(k).FEnd; j++) {
                        zpSDK.drawText(10, print_x, bean.get(j).FLev, 2, 0, 0, false, false);
                        zpSDK.drawText(140, print_x, bean.get(j).FModel, 2, 0, 0, false, false);
                        zpSDK.drawText(320, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
                        zpSDK.drawText(470, print_x, bean.get(j).FVol, 2, 0, 0, false, false);
                        print_x+=35;
                    }
                    if ((k+1) ==printTimeBeanList.size() ){
                        zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
                        zpSDK.drawText(0, print_x+8, "合计:", 2, 0, 0, false, false);
                        zpSDK.drawText(320, print_x+8, bean.get(0).FQtyAll, 2, 0, 0, false, false);
                        zpSDK.drawText(470, print_x+8, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"", 2, 0, 0, false, false);
                    }else{
                        zpSDK.drawText(0, print_x+10, "__________________下页继续__________________", 2, 0, 0, false, false);
                    }
                    //            zpSDK.drawText(420, print_x+8, bean.get(0).FMaker, 2, 0, 0, false, false);
                    zpSDK.print(0, 1);
                }


            }


        }
        zpSDK.disconnect();
        //打印成功
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,bean.get(0).FBoxCode));
        printHistoryDao.deleteInTx(printHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(bean.get(0).FBoxCode)).build().list());

    }

    //打印模板编号 2 混包新增
    public static void doPrint4P1BoxCode4BoxReAdd(Context context, List<PrintDataBean> bean, String times,boolean autoCode) throws Exception{
        Lg.e("打印数据:"+times,bean);
        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
        PrintErrorHistoryDao printHistoryDao= GreenDaoManager.getmInstance(context).getDaoSession().getPrintErrorHistoryDao();
        if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
        {
            Toast.showText(context,"打印机连接失败------");
            Lg.e("打印失败....");
            printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
            return;
        }
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {

            zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);

//            zpSDK.drawText(10, 124, "分包号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 154, "货主:", size, 0, 0, false, false);
            zpSDK.drawText(10, 208, "名称:", size, 0, 0, false, false);
            zpSDK.drawText(10, 268, "车号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 328, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 424, "PCS:", size, 0, 0, false, false);
            zpSDK.drawText(10, 484, "M2/M3:", size, 0, 0, false, false);
            /*右边数值*/
//            if (autoCode){
//                zpSDK.drawText(186, 124, printNum+"-"+(i+1),size, 0, 0, false, false);
//            }else{
//                zpSDK.drawText(186, 124, bean.get(0).FFBaoNum,size, 0, 0, false, false);
//            }
            zpSDK.drawText(156, 154, bean.get(0).FHuozhuNote,size, 0, 0, false, false);
//            String[] split = bean.get(0).FModel.split("x", 3);
//            Lg.e("截取长度", split.length);
//            Lg.e("截取长度", split);
//            if (split.length == 3) {
            zpSDK.drawText(156, 204, bean.get(0).FName,size, 0, 0, false, false);
            zpSDK.drawText(156, 264, bean.get(0).FCarNo,size, 0, 0, false, false);
            zpSDK.drawText(156, 324, bean.get(0).FBatch,size, 0, 0, false, false);
//            }
            zpSDK.drawText(156, 424, bean.get(0).FQtyAll,size, 0, 0, false, false);
            zpSDK.drawText(156, 484, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"",size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            //当为true时，箱码条码打印时会拼接递增
//            if (autoCode){
//                if (i==0){
//                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
//                }else{
//                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode+(i+1), 0, 11, 0);
//                }
//            }else{
                zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
//            }

            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, bean.get(0).FMaker,size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.print(0, 1);
            //打印最后一份明细
            if ((i+1)==printNum){
                List<PrintTimeBean> printTimeBeanList = new ArrayList<>();
                if (bean.size()>18){
                    if (bean.size()>36){
                        printTimeBeanList.add(new PrintTimeBean(0,18));
                        printTimeBeanList.add(new PrintTimeBean(18,35));
                        printTimeBeanList.add(new PrintTimeBean(35,bean.size()));
                    }else{
                        printTimeBeanList.add(new PrintTimeBean(0,18));
                        printTimeBeanList.add(new PrintTimeBean(18,bean.size()));
                    }
                }else{
                    printTimeBeanList.add(new PrintTimeBean(0,bean.size()));
                }
                for (int k = 0; k < printTimeBeanList.size(); k++) {
                    zpSDK.pageSetup(668, 900);
                    zpSDK.drawText(0, 55, "明细标签"+printTimeBeanList.size()+"/"+(k+1),size2, 0, 0, false, false);
                    zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.drawText(0, 90, "物料明细", size2, 0, 0, false, false);
                    zpSDK.drawText(0, 130, "物料名称:", size2, 0, 0, false, false);
                    zpSDK.drawText(156, 130, bean.get(0).FName, size2, 0, 0, false, false);

                    zpSDK.drawText(0, 170, "等级", size2, 0, 0, false, false);
                    zpSDK.drawText(140, 170, "规格", size2, 0, 0, false, false);
                    zpSDK.drawText(320, 170, "PCS", size2, 0, 0, false, false);
                    zpSDK.drawText(470, 170, "M2/M3", size2, 0, 0, false, false);
                    zpSDK.drawText(0, 190, "______________________________________________", 2, 0, 0, false, false);

                    int print_x=220;
                    for (int j = printTimeBeanList.get(k).FStart; j < printTimeBeanList.get(k).FEnd; j++) {
                        zpSDK.drawText(10, print_x, bean.get(j).FLev, 2, 0, 0, false, false);
                        zpSDK.drawText(140, print_x, bean.get(j).FModel, 2, 0, 0, false, false);
                        zpSDK.drawText(320, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
                        zpSDK.drawText(470, print_x, bean.get(j).FVol, 2, 0, 0, false, false);
                        print_x+=35;
                    }
                    if ((k+1) ==printTimeBeanList.size() ){
                        zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
                        zpSDK.drawText(0, print_x+8, "合计:", 2, 0, 0, false, false);
                        zpSDK.drawText(320, print_x+8, bean.get(0).FQtyAll, 2, 0, 0, false, false);
                        zpSDK.drawText(470, print_x+8, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"", 2, 0, 0, false, false);
                    }else{
                        zpSDK.drawText(0, print_x+10, "__________________下页继续__________________", 2, 0, 0, false, false);
                    }
                    //            zpSDK.drawText(420, print_x+8, bean.get(0).FMaker, 2, 0, 0, false, false);
                    zpSDK.print(0, 1);
                }

            }


        }
        zpSDK.disconnect();
        //打印成功
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,bean.get(0).FBoxCode));
        printHistoryDao.deleteInTx(printHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(bean.get(0).FBoxCode)).build().list());

    }
    //打印模板编号 2-1 方料入库(采购订单下推外购入库)与2差别（隐藏PCS，不打印明细信息）
    public static void doPrint4P1BoxCode4BoxReAdd32(Context context, List<PrintDataBean> bean, String times,boolean autoCode) throws Exception{
        Lg.e("打印数据:"+times,bean);
        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
        PrintErrorHistoryDao printHistoryDao= GreenDaoManager.getmInstance(context).getDaoSession().getPrintErrorHistoryDao();
        if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
        {
            Toast.showText(context,"打印机连接失败------");
            Lg.e("打印失败....");
            printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
            return;
        }
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {

            zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);

//            zpSDK.drawText(10, 124, "分包号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 154, "货主:", size, 0, 0, false, false);
            zpSDK.drawText(10, 208, "名称:", size, 0, 0, false, false);
            zpSDK.drawText(10, 268, "车号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 328, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 424, "PCS:", size, 0, 0, false, false);
            zpSDK.drawText(10, 424, "M2/M3:", size, 0, 0, false, false);
            /*右边数值*/
//            if (autoCode){
//                zpSDK.drawText(186, 124, printNum+"-"+(i+1),size, 0, 0, false, false);
//            }else{
//                zpSDK.drawText(186, 124, bean.get(0).FFBaoNum,size, 0, 0, false, false);
//            }
            zpSDK.drawText(156, 154, bean.get(0).FHuozhuNote,size, 0, 0, false, false);
//            String[] split = bean.get(0).FModel.split("x", 3);
//            Lg.e("截取长度", split.length);
//            Lg.e("截取长度", split);
//            if (split.length == 3) {
            zpSDK.drawText(156, 204, bean.get(0).FName,size, 0, 0, false, false);
            zpSDK.drawText(156, 264, bean.get(0).FCarNo,size, 0, 0, false, false);
            zpSDK.drawText(156, 324, bean.get(0).FBatch,size, 0, 0, false, false);
//            }
//            zpSDK.drawText(156, 424, bean.get(0).FQtyAll,size, 0, 0, false, false);
            zpSDK.drawText(156, 424, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"",size, 0, 0, false, false);
//            zpSDK.drawText(156, 484, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"",size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            //当为true时，箱码条码打印时会拼接递增
//            if (autoCode){
//                if (i==0){
//                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
//                }else{
//                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode+(i+1), 0, 11, 0);
//                }
//            }else{
            zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
//            }

            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, bean.get(0).FMaker,size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.print(0, 1);
//            //打印最后一份明细
//            if ((i+1)==printNum){
//                //计算明细数量，多出指定数量自动打印第二张
//                List<PrintTimeBean> printTimeBeanList = new ArrayList<>();
//                if (bean.size()>18){
//                    if (bean.size()>36){
//                        printTimeBeanList.add(new PrintTimeBean(0,18));
//                        printTimeBeanList.add(new PrintTimeBean(18,35));
//                        printTimeBeanList.add(new PrintTimeBean(35,bean.size()));
//                    }else{
//                        printTimeBeanList.add(new PrintTimeBean(0,18));
//                        printTimeBeanList.add(new PrintTimeBean(18,bean.size()));
//                    }
//                }else{
//                    printTimeBeanList.add(new PrintTimeBean(0,bean.size()));
//                }
//                for (int k = 0; k < printTimeBeanList.size(); k++) {
//                    zpSDK.pageSetup(668, 900);
//                    zpSDK.drawText(0, 55, "明细标签"+printTimeBeanList.size()+"/"+(k+1),size2, 0, 0, false, false);
//                    zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);
//
//                    zpSDK.drawText(0, 90, "物料明细", size2, 0, 0, false, false);
//                    zpSDK.drawText(0, 130, "物料名称:", size2, 0, 0, false, false);
//                    zpSDK.drawText(156, 130, bean.get(0).FName, size2, 0, 0, false, false);
//
//                    zpSDK.drawText(0, 170, "等级", size2, 0, 0, false, false);
//                    zpSDK.drawText(140, 170, "规格", size2, 0, 0, false, false);
//                    zpSDK.drawText(320, 170, "M2/M3", size2, 0, 0, false, false);
////                    zpSDK.drawText(470, 170, "PCS", size2, 0, 0, false, false);
//                    zpSDK.drawText(0, 190, "______________________________________________", 2, 0, 0, false, false);
//
//                    int print_x=220;
//                    for (int j = printTimeBeanList.get(k).FStart; j < printTimeBeanList.get(k).FEnd; j++) {
//                        zpSDK.drawText(10, print_x, bean.get(j).FLev, 2, 0, 0, false, false);
//                        zpSDK.drawText(140, print_x, bean.get(j).FModel, 2, 0, 0, false, false);
//                        zpSDK.drawText(320, print_x, bean.get(j).FVol, 2, 0, 0, false, false);
////                        zpSDK.drawText(470, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
//                        print_x+=35;
//                    }
//                    if ((k+1) ==printTimeBeanList.size() ){
//                        zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
//                        zpSDK.drawText(0, print_x+8, "合计:", 2, 0, 0, false, false);
//                        zpSDK.drawText(320, print_x+8, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"", 2, 0, 0, false, false);
////                        zpSDK.drawText(470, print_x+8, bean.get(0).FQtyAll, 2, 0, 0, false, false);
//                    }else{
//                        zpSDK.drawText(0, print_x+10, "__________________下页继续__________________", 2, 0, 0, false, false);
//                    }
//                    //            zpSDK.drawText(420, print_x+8, bean.get(0).FMaker, 2, 0, 0, false, false);
//                    zpSDK.print(0, 1);
//                }
//
//            }


        }
        zpSDK.disconnect();
        //打印成功
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,bean.get(0).FBoxCode));
        printHistoryDao.deleteInTx(printHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(bean.get(0).FBoxCode)).build().list());

    }

    //箱码打印
    public static void doPrint4P1BoxCode(Context context, List<PrintDataBean> bean, String times,boolean autoCode) throws Exception{
        Lg.e("打印数据:"+times,bean);
        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
        PrintErrorHistoryDao printHistoryDao= GreenDaoManager.getmInstance(context).getDaoSession().getPrintErrorHistoryDao();
        if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
        {
            Toast.showText(context,"打印机连接失败------");
            Lg.e("打印失败....");
            printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
            return;
        }
        int size=4;
        int size2=3;
        int lineSize=3;
        int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
        for (int i = 0; i < printNum; i++) {

            zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
            zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
            zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
            /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 124, "分包号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 174, "货主:", size, 0, 0, false, false);
            zpSDK.drawText(10, 228, "名称:", size, 0, 0, false, false);
            zpSDK.drawText(10, 288, "车号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 348, "批号:", size, 0, 0, false, false);
            zpSDK.drawText(10, 444, "PCS:", size, 0, 0, false, false);
            zpSDK.drawText(10, 504, "M2/M3:", size, 0, 0, false, false);
            /*右边数值*/
            if (autoCode){
                zpSDK.drawText(186, 124, printNum+"-"+(i+1),size, 0, 0, false, false);
            }else{
                zpSDK.drawText(186, 124, bean.get(0).FFBaoNum,size, 0, 0, false, false);
            }
            zpSDK.drawText(156, 174, bean.get(0).FHuozhuNote,size, 0, 0, false, false);
//            String[] split = bean.get(0).FModel.split("x", 3);
//            Lg.e("截取长度", split.length);
//            Lg.e("截取长度", split);
//            if (split.length == 3) {
            zpSDK.drawText(156, 224, bean.get(0).FName,size, 0, 0, false, false);
            zpSDK.drawText(156, 284, bean.get(0).FCarNo,size, 0, 0, false, false);
            zpSDK.drawText(156, 344, bean.get(0).FBatch,size, 0, 0, false, false);
//            }
            zpSDK.drawText(156, 444, bean.get(0).FQtyAll,size, 0, 0, false, false);
            zpSDK.drawText(156, 504, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"",size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
            //当为true时，箱码条码打印时会拼接递增
            if (autoCode){
                if (i==0){
                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
                }else{
                    zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode+(i+1), 0, 11, 0);
                }
            }else{
                zpSDK.drawQrCode(10, 560, bean.get(0).FBoxCode, 0, 11, 0);
            }

            zpSDK.drawText(300, 560, "仓位:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 560, "",size2, 0, 0, false, false);
            zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
            zpSDK.drawText(380, 640, bean.get(0).FMaker,size2, 0, 0, false, false);
            zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
            zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
            zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
            zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
            zpSDK.print(0, 1);
            //打印最后一份明细
            if ((i+1)==printNum){
                List<PrintTimeBean> printTimeBeanList = new ArrayList<>();
                if (bean.size()>18){
                    if (bean.size()>36){
                        printTimeBeanList.add(new PrintTimeBean(0,18));
                        printTimeBeanList.add(new PrintTimeBean(18,35));
                        printTimeBeanList.add(new PrintTimeBean(35,bean.size()));
                    }else{
                        printTimeBeanList.add(new PrintTimeBean(0,18));
                        printTimeBeanList.add(new PrintTimeBean(18,bean.size()));
                    }
                }else{
                    printTimeBeanList.add(new PrintTimeBean(0,bean.size()));
                }
                for (int k = 0; k < printTimeBeanList.size(); k++) {
                    zpSDK.pageSetup(668, 900);
                    zpSDK.drawText(0, 55, "明细标签"+printTimeBeanList.size()+"/"+(k+1),size2, 0, 0, false, false);
                    zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);

                    zpSDK.drawText(0, 90, "物料明细", size2, 0, 0, false, false);
                    zpSDK.drawText(0, 130, "物料名称:", size2, 0, 0, false, false);
                    zpSDK.drawText(156, 130, bean.get(0).FName, size2, 0, 0, false, false);

                    zpSDK.drawText(0, 170, "等级", size2, 0, 0, false, false);
                    zpSDK.drawText(140, 170, "规格", size2, 0, 0, false, false);
                    zpSDK.drawText(320, 170, "PCS", size2, 0, 0, false, false);
                    zpSDK.drawText(470, 170, "M2/M3", size2, 0, 0, false, false);
                    zpSDK.drawText(0, 190, "______________________________________________", 2, 0, 0, false, false);

                    int print_x=220;
                    for (int j = printTimeBeanList.get(k).FStart; j < printTimeBeanList.get(k).FEnd; j++) {
                        zpSDK.drawText(10, print_x, bean.get(j).FLev, 2, 0, 0, false, false);
                        zpSDK.drawText(140, print_x, bean.get(j).FModel, 2, 0, 0, false, false);
                        zpSDK.drawText(320, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
                        zpSDK.drawText(470, print_x, bean.get(j).FVol, 2, 0, 0, false, false);
                        print_x+=35;
                    }
                    if ((k+1) ==printTimeBeanList.size() ){
                        zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
                        zpSDK.drawText(0, print_x+8, "合计:", 2, 0, 0, false, false);
                        zpSDK.drawText(320, print_x+8, bean.get(0).FQtyAll, 2, 0, 0, false, false);
                        zpSDK.drawText(470, print_x+8, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"", 2, 0, 0, false, false);
                    }else{
                        zpSDK.drawText(0, print_x+10, "__________________下页继续__________________", 2, 0, 0, false, false);
                    }
                    //            zpSDK.drawText(420, print_x+8, bean.get(0).FMaker, 2, 0, 0, false, false);
                    zpSDK.print(0, 1);
                }

            }


        }
        zpSDK.disconnect();
        //打印成功
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,bean.get(0).FBoxCode));
        printHistoryDao.deleteInTx(printHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(bean.get(0).FBoxCode)).build().list());

    }

    public static boolean isReView=false;
    public static void doPrint4P1BoxCode2(final zpBluetoothPrinter zpSDK, final Context context, final List<PrintDataBean> bean, final String times,boolean forReview) throws Exception {
        isReView = forReview;
        doPrint4P1BoxCode2(zpSDK,context,bean,times);
    }
    //箱码打印
    public static void doPrint4P1BoxCode2(final zpBluetoothPrinter zpSDK, final Context context, final List<PrintDataBean> bean, final String times) throws Exception{
        Lg.e("打印数据:"+times,bean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
                PrintErrorHistoryDao printHistoryDao= GreenDaoManager.getmInstance(context).getDaoSession().getPrintErrorHistoryDao();
                if(!zpSDK.connect(Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", "")).address))
                {
//                    Toast.showText(context,"打印机连接失败------");
                    Lg.e("打印失败....");
                    printHistoryDao.insertInTx(new PrintErrorHistory(bean.get(0).FBoxCode,CommonUtil.getTimeLong(true),"0"));
                    if (!isReView)EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,"获取箱码成功"));
                    return;
                }
                int size=4;
                int size2=3;
                int lineSize=3;
                int printNum = Integer.parseInt(null==times?"1":times);
//        int printNum = Integer.parseInt(Hawk.get(Config.PrintNum,"2"));
                for (int i = 0; i < printNum; i++) {

                    zpSDK.pageSetup(668, 900);
//            zpSDK.drawBox(1,1,1,668,888);
                    zpSDK.drawText(0, 5, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawText(200, 50, "物料标签", size, 0, 1, false, false);
                    zpSDK.drawText(0, 90, "______________________________________________", 2, 0, 0, false, false);
                    /*左边*/
//            zpSDK.drawText(10, 220, "品名:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 170, "批号:", size, 0, 0, false, false);
//            zpSDK.drawText(10, 124, "批号:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 124, "仓库:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 174, "货主:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 228, "名称:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 288, "批号:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 348, "宽度:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 408, "包数:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 468, "PCS:", size, 0, 0, false, false);
                    zpSDK.drawText(10, 528, "M2/M3:", size, 0, 0, false, false);
                    /*右边数值*/
                    zpSDK.drawText(156, 124, bean.get(0).FStorage,size, 0, 0, false, false);
                    zpSDK.drawText(156, 174, bean.get(0).FHuozhuNote,size, 0, 0, false, false);
//            String[] split = bean.get(0).FModel.split("x", 3);
//            Lg.e("截取长度", split.length);
//            Lg.e("截取长度", split);
//            if (split.length == 3) {
                    zpSDK.drawText(156, 224, bean.get(0).FName,size, 0, 0, false, false);
                    if (bean.get(0).FBatch.length()>17){
                        zpSDK.drawText(156, 284, bean.get(0).FBatch,size2, 0, 0, false, false);
                    }else{
                        zpSDK.drawText(156, 284, bean.get(0).FBatch,size, 0, 0, false, false);
                    }
                    zpSDK.drawText(156, 344, bean.get(0).FWide,size, 0, 0, false, false);
//            }
                    zpSDK.drawText(156, 404, bean.get(0).FBaoNum,size, 0, 0, false, false);
                    zpSDK.drawText(156, 464, bean.get(0).FQtyAll,size, 0, 0, false, false);
                    zpSDK.drawText(156, 524, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"",size, 0, 0, false, false);

//            zpSDK.drawText(500, 284, "MM",size, 0, 0, false, false);
//            String wide = bean.FBWide==null?"":bean.FBWide;
//            if (wide.length()>19){
//                if (wide.length()<38){
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,wide.length()),size2, 0, 0, false, false);
//                }else{
//                    zpSDK.drawText(180, 344, wide.substring(0,19),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 368, wide.substring(19,38),size2, 0, 0, false, false);
//                    zpSDK.drawText(180, 392, wide.substring(38,wide.length()),size2, 0, 0, false, false);
//                }
//            }else{
//                zpSDK.drawText(180, 344, wide,size2, 0, 0, false, false);
//            }
////            zpSDK.drawText(500, 344, "MM",size2, 0, 0, false, false);
//            zpSDK.drawText(160, 404, bean.FCeng==null?"":bean.FCeng,size, 0, 0, false, false);
////            zpSDK.drawText(450, 404, bean.FUnitAux==null?"":bean.FUnitAux,size2, 0, 0, false, false);
//            zpSDK.drawText(160, 464, bean.FVolume==null?"":bean.FVolume,size, 0, 0, false, false);
//            zpSDK.drawText(500, 464, "M3",size, 0, 0, false, false);
//            zpSDK.drawText(10, 500, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.drawQrCode(10, 570, bean.get(0).FBoxCode, 0, 11, 0);
                    zpSDK.drawText(300, 570, "仓位:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 570, "",size2, 0, 0, false, false);
                    zpSDK.drawText(300, 640, "录入:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 640, bean.FSaveIn==null?"":bean.FSaveIn,size2, 0, 0, false, false);
                    zpSDK.drawText(380, 640, bean.get(0).FMaker,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 720, "审核:",size2, 0, 0, false, false);
//            zpSDK.drawText(380, 720, bean.FCheck==null?"":bean.FCheck,size2, 0, 0, false, false);
                    zpSDK.drawText(300, 790, "日期:",size2, 0, 0, false, false);
                    zpSDK.drawText(380, 790, getTime(true),size2, 0, 0, false, false);
                    zpSDK.drawText(10, 850, "______________________________________________", 2, 0, 0, false, false);
                    zpSDK.print(0, 1);
                    //li
                    List<PrintTimeBean> printTimeBeanList = new ArrayList<>();
                    Lg.e("bean.size"+bean.size(),bean);
                    if (bean.size()>18){
                        if (bean.size()>36){
                            printTimeBeanList.add(new PrintTimeBean(0,18));
                            printTimeBeanList.add(new PrintTimeBean(18,35));
                            printTimeBeanList.add(new PrintTimeBean(35,bean.size()));
                        }else{
                            printTimeBeanList.add(new PrintTimeBean(0,18));
                            printTimeBeanList.add(new PrintTimeBean(18,bean.size()));
                        }
                    }else{
                        printTimeBeanList.add(new PrintTimeBean(0,bean.size()));
                    }
                    Lg.e("printTimeBeanList",printTimeBeanList);
                    for (int k = 0; k < printTimeBeanList.size(); k++) {
                        zpSDK.pageSetup(668, 900);
                        zpSDK.drawText(0, 55, "明细标签:",size2, 0, 0, false, false);
                        zpSDK.drawText(0, 65, "______________________________________________", 2, 0, 0, false, false);

                        zpSDK.drawText(0, 90, "物料明细", size2, 0, 0, false, false);
                        zpSDK.drawText(0, 130, "物料名称:", size2, 0, 0, false, false);
                        zpSDK.drawText(156, 130, bean.get(0).FName, size2, 0, 0, false, false);

                        zpSDK.drawText(0, 170, "等级", size2, 0, 0, false, false);
                        zpSDK.drawText(140, 170, "规格", size2, 0, 0, false, false);
                        zpSDK.drawText(320, 170, "PCS", size2, 0, 0, false, false);
                        zpSDK.drawText(470, 170, "M2/M3", size2, 0, 0, false, false);
                        zpSDK.drawText(0, 190, "______________________________________________", 2, 0, 0, false, false);

                        int print_x=220;
                        for (int j = printTimeBeanList.get(k).FStart; j < printTimeBeanList.get(k).FEnd; j++) {
                            zpSDK.drawText(10, print_x, bean.get(j).FLev, 2, 0, 0, false, false);
                            zpSDK.drawText(140, print_x, bean.get(j).FModel, 2, 0, 0, false, false);
                            zpSDK.drawText(320, print_x, bean.get(j).FQty, 2, 0, 0, false, false);
                            zpSDK.drawText(470, print_x, bean.get(j).FVol, 2, 0, 0, false, false);
                            print_x+=35;
                        }
                        if ((k+1) ==printTimeBeanList.size() ){
                            zpSDK.drawText(0, print_x+10, "______________________________________________", 2, 0, 0, false, false);
                            zpSDK.drawText(0, print_x+8, "合计:", 2, 0, 0, false, false);
                            zpSDK.drawText(320, print_x+8, bean.get(0).FQtyAll, 2, 0, 0, false, false);
                            zpSDK.drawText(470, print_x+8, MathUtil.D2save4(MathUtil.toD(bean.get(0).FVolAll))+"", 2, 0, 0, false, false);
                        }else{
                            zpSDK.drawText(0, print_x+10, "__________________下页继续__________________", 2, 0, 0, false, false);
                        }
                        //            zpSDK.drawText(420, print_x+8, bean.get(0).FMaker, 2, 0, 0, false, false);
                        zpSDK.print(0, 1);
                    }


                }
                zpSDK.disconnect();
                if (!isReView)EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.PrintBox_OK,bean.get(0).FBoxCode));
                printHistoryDao.deleteInTx(printHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(bean.get(0).FBoxCode)).build().list());

            }
        }).start();

    }



    /*条码规则：
    物料条码.16位数

    批次条码:大于22位
                批次和备注  11位到空格是批次 后三位备注
    批次条码:大于16小于20位
               批次和备注  11位到空格是批次*/

    /*if (CommonUtil.ScanBack(code).size()>0){
            List<String> list = CommonUtil.ScanBack(code);
            edNum.setText(list.get(1));
            ScanBarCode(list.get(0));
        }*/
    public static List<String> ScanBack(String code) {
        List<String> list = new ArrayList<>();
        if (code.contains("/")) {
            String[] split = code.split("/", 3);
            Log.e("code:", split.length + "");
            if (split.length == 3) {
                String fcode = split[0];
                if (fcode.length() > 12) {
                    try {
                        String barcode = fcode.substring(0, 12);
                        list.add(barcode);
                        String num = fcode.substring(12, fcode.length());
                        list.add(num);
                        list.add(split[1]);
                        return list;
                    } catch (Exception e) {
                        Toast.showText(App.getContext(), "条码有误");
                        return new ArrayList<>();
                    }
                } else {
                    Toast.showText(App.getContext(), "条码有误");
                    return new ArrayList<>();
                }
            } else {
                Toast.showText(App.getContext(), "条码有误");
                return new ArrayList<>();
            }
        } else {
            Toast.showText(App.getContext(), "条码有误");
            return new ArrayList<>();
        }

//        List<String> list = new ArrayList<>();
//        if (code.length()>22){
//            String barcode = code.substring(0, 12);
//            list.add(barcode);
//            return list;
//        }else if (code.length()>16 && code.length()<20){
//            String barcode = code.substring(0, 12);
//            list.add(barcode);
//            return list;
//        }else{
//            return new ArrayList<>();
//        }

        // 大于12位的条码  前面12是条形码 后面为数量
        //角标以 0 未开始获取
//        if (code.length()>12){
//            try {
//                String barcode = code.substring(0, 12);
//                list.add(barcode);
//                String num = code.substring(12, code.length());
//                list.add(num);
//                return list;
//            } catch (Exception e) {
//                Toast.showText(App.getContext(), "条码有误");
//                return new ArrayList<>();
//            }
//        }else{
//            Toast.showText(App.getContext(), "条码有误");
//            return new ArrayList<>();
//        }
    }

    //生成单据编号（生成的单据编号只用作查找，不具备时间效应）
    public static long createOrderCode(Activity activity) {
        Long ordercode = 0l;
        ShareUtil share = ShareUtil.getInstance(activity.getApplicationContext());
        if (share.getOrderCode(activity) == 0) {
            ordercode = Long.parseLong(getTimeLong(false) + "001");
            share.setOrderCode(activity, ordercode);
        } else {
            //当不是当天时，生成新的单据，重新计算
//            if (String.valueOf(share.getOrderCode(activity)).contains(getTime(false))) {
                ordercode = share.getOrderCode(activity);
//            } else {
//                ordercode = Long.parseLong(getTimeLong(false) + "001");
//                share.setOrderCode(activity, ordercode);
//            }
        }
//        Log.e("生成新的单据:", ordercode + "");
        return ordercode;
    }
    //生成单据编号（生成的单据编号只用作查找，不具备时间效应）
    public static long createOrderCode(int activity) {
        Long ordercode = 0l;
        ShareUtil share = ShareUtil.getInstance(App.getContext());
        if (share.getOrderCode(activity) == 0) {
            ordercode = Long.parseLong(getTimeLong(false) + "001");
            share.setOrderCode(activity, ordercode);
        } else {
            //当不是当天时，生成新的单据，重新计算
//            if (String.valueOf(share.getOrderCode(activity)).contains(getTime(false))) {
            ordercode = share.getOrderCode(activity);
//            } else {
//                ordercode = Long.parseLong(getTimeLong(false) + "001");
//                share.setOrderCode(activity, ordercode);
//            }
        }
//        Log.e("生成新的单据:", ordercode + "");
        return ordercode;
    }
    public static long createBoxCode(String activity) {
        Long ordercode = 0l;
        ShareUtil share = ShareUtil.getInstance(App.getContext());
        if (share.getOrderCode(activity) == 0) {
            ordercode = Long.parseLong(getTimeLong(false) + "001");
            share.setOrderCode(activity, ordercode);
        } else {
            //当不是当天时，生成新的单据，重新计算
//            if (String.valueOf(share.getOrderCode(activity)).contains(getTime(false))) {
            ordercode = share.getOrderCode(activity);
//            } else {
//                ordercode = Long.parseLong(getTimeLong(false) + "001");
//                share.setOrderCode(activity, ordercode);
//            }
        }
//        Log.e("生成新的单据:", ordercode + "");
        return ordercode;
    }

    /**
     * //获取报数信息
     * @param activity
     * @param up        true 递增
     * @return
     */
    public static int getCountOffNumber(int activity,boolean up){
        int num =Hawk.get(Info.CountOffNumber+activity,1);
        if (up){
            num++;
            Hawk.put(Info.CountOffNumber+activity,num);
        }
        return num;
    }

    //生成单据编号（生成的单据编号只用作查找，不具备时间效应）下推单时使用
    public static long createOrderCode(String activity) {
        Long ordercode = 0l;
        ShareUtil share = ShareUtil.getInstance(App.getContext());
        if (share.getOrderCode(activity) == 0) {
            ordercode = Long.parseLong(getTimeLong(false) + "001");
            share.setOrderCode(activity, ordercode);
        } else {
            //当不是当天时，生成新的单据，重新计算
//            if (String.valueOf(share.getOrderCode(activity)).contains(getTime(false))) {
            ordercode = share.getOrderCode(activity);
//            } else {
//                ordercode = Long.parseLong(getTimeLong(false) + "001");
//                share.setOrderCode(activity, ordercode);
//            }
        }
//        Log.e("生成新的单据:", ordercode + "");
        return ordercode;
    }

    //获取当前账套ID，用于区分账套数据
    public static String getAccountID(){
//        Lg.e("得到账套ID",Hawk.get(Config.Cloud_ID, ""));
        return Hawk.get(Config.Cloud_ID, "");
    }

    public static String getTime(boolean b) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd" : "yyyyMMdd");
        Date curDate = new Date();
        Log.e("date", curDate.toString());
        String str = format.format(curDate);
        return str;
    }
    public static String getTime2Fen() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }
    public static String getTimeLong(boolean b) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd-HH-mm-ss" : "yyyyMMddHHmmss");
        Date curDate = new Date();
        Log.e("date", curDate.toString());
        String str = format.format(curDate);
        return str;
    }

    //用于页面中DataBinding判断是否开启批次并且处理
    public static boolean isOpen(String string) {
        if (null != string && "1".equals(string)) {
            return true;
        } else {
            return false;
        }
    }

    //更新软件
    public static void installApk(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }
        Lg.e("获得文件路径："+apkPath);

        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            Lg.e(">=24时");
//            Log.v(TAG,"7.0以上，正在安装apk...");
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context,
                    "com.fangzuo.assist.cloud.provider",
//                    BuildConfig.APPLICATION_ID + ".provider",
                    file);
//            Uri apkUri = FileProvider.getUriForFile(context, "com.fangzuo.assist.fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            Lg.e("<24时");
//            Log.v(TAG,"7.0以下，正在安装apk...");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }


    //是否开启库存管理
    public static boolean isAllowFStore(String string) {
        if (null != string && "1".equals(string)) {
            return true;
        } else {
            return false;
        }
    }

    //读取本地的txt文件
    public static String getString4Version() {
        InputStreamReader inputStreamReader = null;
                String lineTxt = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory()+"/ScanAppVision.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                while ((lineTxt = br.readLine()) != null) {
                    Lg.e("读取txt:"+lineTxt);
                    //保存版本号
                    Hawk.put(Config.Apk_Version,lineTxt);
                    System.out.println(lineTxt);
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineTxt;
    }
    //读取本地的txt文件
    public static String getString() {
        InputStreamReader inputStreamReader = null;
        String lineTxt = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory()+"/ScanAppVisionExplain.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                while ((lineTxt = br.readLine()) != null) {
                    Lg.e("读取txt:"+lineTxt);
                    //保存版本号
//                    Hawk.put(Config.Apk_Version,lineTxt);
                    System.out.println(lineTxt);
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineTxt;
    }
    //解密加密的时间
    public static String dealTime(String timemd){
//        Lg.e("加密的日期："+timemd);
        StringBuffer buffer = new StringBuffer()
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(0)+"")+1))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(1)+"")+2))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(2)+"")+3))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(3)+"")+4))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(4)+"")+5))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(5)+"")+6))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(6)+"")+7))
                .append(timemd.charAt(Integer.parseInt(Config.Key.charAt(7)+"")+8));
        Lg.e("解析日期："+buffer.toString());
        return buffer.toString();
    }

    //androidmanifest中获取版本名称
    public static String getVersionName() {
        PackageManager packageManager = App.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    App.getContext().getPackageName(), 0);

            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            System.out.println("versionName=" + versionName + ";versionCode="
                    + versionCode);

            return versionName;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return "";
    }

    public static String getTimesecond() {
        Date curDate = new Date();
        Long time = curDate.getTime();
        return time + "";
    }

//标准销售订单  对应 销售出库单的单据类型 XSCKD01_SYS
//寄售销售订单  对应 销售出库单的单据类型 XSCKD02_SYS
//分销购销订单  对应 销售出库单的单据类型 XSCKD04_SYS
//VMI销售订单   对应 销售出库单的单据类型 XSCKD05_SYS
//现销订单      对应 销售出库单的单据类型 XSCKD06_SYS
    public static String getSaleOutBillType(String saleoder){
        String backData="";
//        if (null==saleoder||"".equals(saleoder)){
//            backData="XSCKD01_SYS";
//            return backData;
//        }
        switch (saleoder==null||"".equals(saleoder)?"":saleoder){
            case "标准销售订单":
                backData="XSCKD01_SYS";
                break;
            case "寄售销售订单":
                backData="XSCKD02_SYS";
                break;
            case "分销购销订单":
                backData="XSCKD04_SYS";
                break;
            case "VMI销售订单":
                backData="XSCKD05_SYS";
                break;
            case "现销订单":
                backData="XSCKD06_SYS";
                break;
            case "":
                backData="XSCKD01_SYS";
                break;
        }
        return backData;
    }

    public static String getSaleOutHuoZhuType(String saleoder){
        String backData="";
//        if (null==saleoder||"".equals(saleoder)){
//            backData="XSCKD01_SYS";
//            return backData;
//        }
        switch (saleoder==null||"".equals(saleoder)?"":saleoder){
            case "标准销售订单":
                backData="BD_OwnerOrg";
                break;
            case "寄售销售订单":
                backData="BD_OwnerOrg";
                break;
            case "分销购销订单":
                backData="BD_OwnerOrg";
                break;
            case "VMI销售订单":
                backData="BD_Supplier";
                break;
            case "现销订单":
                backData="BD_OwnerOrg";
                break;
            case "":
                backData="BD_OwnerOrg";
                break;
        }
        return backData;
    }
}




















/*
String str = "abc,12,3yy98,0";
String[]  strs=str.split(",");//以，截取   或者\\^
for(int i=0,len=strs.length;i<len;i++){
    System.out.println(strs[i].toString());



 sb.substring(2);//索引号 2 到结尾

String barcode = code.substring(0, 12);//第一位到第十一位

3.通过StringUtils提供的方法
StringUtils.substringBefore(“dskeabcee”, “e”);
/结果是：dsk/
这里是以第一个”e”，为标准。

StringUtils.substringBeforeLast(“dskeabcee”, “e”)
结果为：dskeabce
这里以最后一个“e”为准。
* */