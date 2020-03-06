package com.fangzuo.assist.cloud.Utils;


import android.text.TextUtils;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.SettingList;
import com.fangzuo.assist.cloud.R;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

/**
 * Created by NB on 2017/7/28.
 */

public class GetSettingList {
    public static ArrayList<SettingList> getList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList(App.getContext().getResources().getString(R.string.down_set), R.mipmap.download));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.Wifi_set),R.mipmap.wifi));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.voice_set),R.mipmap.sound));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.updata_app),R.mipmap.update));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.server_set),R.mipmap.tomcat));
//        items.add(new SettingList(App.getContext().getResources().getString(R.string.print_set),R.mipmap.test));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.net_set),R.mipmap.test));
        return items;
    }
    //区分一期二期的项目列表
    public static ArrayList<SettingList> getPurchaseList(String[] ary) {
        ArrayList<SettingList> items = new ArrayList<>();

        //最终返回的选项
        ArrayList<SettingList> backItems = new ArrayList<>();

            items.add(new SettingList("201",App.getContext().getResources().getString(R.string.saleout),Config.SaleOutActivity,R.mipmap.chuku));
            items.add(new SettingList("5",App.getContext().getResources().getString(R.string.dh_instore1),Config.DhInActivity,R.mipmap.sellinout));
            items.add(new SettingList("6",App.getContext().getResources().getString(R.string.dh_instore2),Config.DhIn2Activity,R.mipmap.sellinout));
            items.add(new SettingList("7",App.getContext().getResources().getString(R.string.purchase_instore),Config.PdCgOrder2WgrkActivity,R.mipmap.ruku));//实际是：采购订单下推外购入库
            items.add(new SettingList("202",App.getContext().getResources().getString(R.string.other_out_in_instore),Config.HwOut3Activity,R.mipmap.chuku));
            items.add(new SettingList("203",App.getContext().getResources().getString(R.string.db_order),Config.DBActivity,R.mipmap.diaobo));
            items.add(new SettingList("204",App.getContext().getResources().getString(R.string.tb_mananger1),Config.TbGetActivity,R.mipmap.sellinout));
            items.add(new SettingList("205",App.getContext().getResources().getString(R.string.tb_mananger2),Config.TbGet2Activity,R.mipmap.sellinout));
            items.add(new SettingList("206",App.getContext().getResources().getString(R.string.tb_mananger3),Config.TbGet3Activity,R.mipmap.sellinout));
            items.add(new SettingList("207",App.getContext().getResources().getString(R.string.gb_mananger),Config.GbGetActivity,R.mipmap.sellinout));
            items.add(new SettingList("22",App.getContext().getResources().getString(R.string.py_instore),Config.PYingActivity,R.mipmap.sellinout));
            items.add(new SettingList("208",App.getContext().getResources().getString(R.string.pk_instore),Config.PkuiActivity,R.mipmap.sellinout));
            items.add(new SettingList("25",App.getContext().getResources().getString(R.string.production_get),Config.ProductGetActivity,R.mipmap.chuku));
            items.add(new SettingList("26",App.getContext().getResources().getString(R.string.production_instore),Config.ProductInStoreActivity,R.mipmap.purchaseinstorage));
            items.add(new SettingList("27",App.getContext().getResources().getString(R.string.scan),Config.ScanProductActivity,R.mipmap.scan));
            items.add(new SettingList("28",App.getContext().getResources().getString(R.string.print_tips_before),Config.PrintBeforeDataActivity,R.mipmap.printmain));
            items.add(new SettingList("29",App.getContext().getResources().getString(R.string.print_tips),Config.PrintHistoryActivity,R.mipmap.printmain));
            items.add(new SettingList("30","采购入库单下推生产领料单",Config.P1PdCgrk2ProductGetActivity,R.mipmap.chuku));
            items.add(new SettingList("36","水板采购",Config.P1PdProductGet2CprkActivity,R.mipmap.ruku));//0917生产领料单下推产品入库单(整单装箱)
            items.add(new SettingList("31","箱码补打",Config.PrintBoxCode4P1Activity,R.mipmap.printmain));
            items.add(new SettingList("32","拆箱单",Config.SplitBoxP1Activity,R.mipmap.sellinout));
            items.add(new SettingList("33","码拍领料",Config.ProductGet4BoxActivity,R.mipmap.chuku));//0917生产领料单
            items.add(new SettingList("34","码拍入窑",Config.P1PdProductGet2Cprk2Activity,R.mipmap.ruku));//0917生产领料单下推产品入库单(重新装箱)
            items.add(new SettingList("35","箱码调拨单",Config.DB4P1BoxActivity,R.mipmap.diaobo));
            items.add(new SettingList("37","混包追加",Config.BoxReBoxP1Activity,R.mipmap.diaobo));
            items.add(new SettingList("38","混包新增",Config.BoxReAddP1Activity,R.mipmap.diaobo));
            items.add(new SettingList("39","方料入库",Config.FLInStoreP1Activity,R.mipmap.diaobo));
            items.add(new SettingList("216","批号调整",Config.BatchChangeLv1,R.mipmap.sellinout));
            items.add(new SettingList("215","等级调整",Config.LvChangeLv1,R.mipmap.sellinout));
            items.add(new SettingList("214","规格调整",Config.ModelChangeLv1,R.mipmap.sellinout));
            items.add(new SettingList("55","调拨(客户在途)",Config.DBClientActivity,R.mipmap.diaobo));
            items.add(new SettingList("56","调拨(在途仓库)",Config.DBStorageActivity,R.mipmap.diaobo));
            items.add(new SettingList("213","拆包理货",Config.SplitBoxLv1,R.mipmap.sellinout));
            items.add(new SettingList("212","纵刨车间",Config.ZbChejianLv1,R.mipmap.sellinout));
            items.add(new SettingList("211","刨光二车间",Config.Bg2ChejianLv1,R.mipmap.sellinout));
            items.add(new SettingList("210","刨光一车间",Config.Bg1ChejianLv1,R.mipmap.sellinout));
            items.add(new SettingList("209","成品外购入库",Config.CpWgInLv1,R.mipmap.sellinout));
//        for (int i=0; i<items.size();i++){
////            Log.e("test","定位ary:"+ary[i]);
////            Log.e("test","定位items:"+items.get(i).tag);
//            //根据ary的值，遍历list符合的item并添加
//            for (int j=0;j<ary.length;j++){
//                if (TextUtils.equals(items.get(i).tag,ary[j])){
//                    Lg.e("加入单据",items.get(i));
//                    backItems.add(items.get(i));
//                }
//            }
//        }

//        backItems.add(new SettingList("217","整包领料(通用)",Config.ZbGetActivity,R.mipmap.sellinout));
//        backItems.add(new SettingList("218","整包入库(通用)",Config.ZbInActivity,R.mipmap.sellinout));
        backItems.add(new SettingList("218","外购入库单",Config.OutsourcingInActivity,R.mipmap.sellinout));
        backItems.add(new SettingList("219","销售订单下推销售出库",Config.PdSaleOrder2SaleOutActivity,R.mipmap.sellinout));
        backItems.add(new SettingList("220","采购订单下推采购入库",Config.PdCgOrder2WgrkActivity,R.mipmap.sellinout));
        backItems.add(new SettingList("220","采购订单下推委外入库",Config.PdCgOrder2WwrkActivity,R.mipmap.sellinout));
//        backItems.add(new SettingList("220","其他入库",Config.OtherInStoreActivity,R.mipmap.sellinout));
//        backItems.add(new SettingList("220","其他出库",Config.OtherOutStoreActivity,R.mipmap.sellinout));

        return backItems;



//        items.add(new SettingList("简单生产领料",R.mipmap.chuku));
//        items.add(new SettingList("简单产品入库",R.mipmap.purchaseinstorage));
//        items.add(new SettingList("代存业务",R.mipmap.sellinout));
//        items.add(new SettingList("库存查询",R.mipmap.sellinout));
//        items.add(new SettingList("采购入库",R.mipmap.purchaseorder));
//        items.add(new SettingList("其他入库",R.mipmap.ruku));
//        items.add(new SettingList("其他出库",R.mipmap.chuku));
//        items.add(new SettingList("单据下推",R.mipmap.sellout));
//        items.add(new SettingList("盘点",R.mipmap.pandian));
        //-------------
//        items.add(new SettingList("采购订单",R.mipmap.purchaseorder));
//        items.add(new SettingList("销售订单",R.mipmap.saleorder));
//        items.add(new SettingList("盘点",R.mipmap.pandian));
//        for (int i=0; i<items.size();i++){
//            Log.e("test","定位ary:"+ary[i]);
//            Log.e("test","定位items:"+items.get(i).tag);
            //根据ary的值，遍历list符合的item并添加
//            for (int j=0;j<ary.length;j++){
//                if (TextUtils.equals(items.get(i).tag,ary[j])){
//                    Log.e("test","222加入："+items.get(i).toString());
//                    backItems.add(items.get(i));
//                }
//            }
//        }
//        return items;
    }

    //二期的项目列表
    public static ArrayList<SettingList> getOrderList(int type) {
        ArrayList<SettingList> items = new ArrayList<>();
        if (type==1){
            items.add(new SettingList(App.getContext().getResources().getString(R.string.p2_cgrk2production_get),Config.P2PdCgrk2ProductGetActivity,R.mipmap.ruku));
            items.add(new SettingList("",1,0));
            items.add(new SettingList("",1,0));
            items.add(new SettingList(App.getContext().getResources().getString(R.string.p2_production_instore2),Config.P2ProductionInStore2Activity,R.mipmap.ruku));
            items.add(new SettingList(App.getContext().getResources().getString(R.string.product_get_p2),Config.ProductGet4P2Activity,R.mipmap.chuku));
            items.add(new SettingList("",1,0));
            items.add(new SettingList(App.getContext().getResources().getString(R.string.product_instore4p2),Config.ProductInStore4P2Activity,R.mipmap.ruku));
            items.add(new SettingList("板材调拨",Config.DB4P2Activity,R.mipmap.diaobo));
            items.add(new SettingList("",1,0));
            items.add(new SettingList(App.getContext().getResources().getString(R.string.print_tips),Config.PrintHistory4P2Activity,R.mipmap.printmain));
            items.add(new SettingList("","码拍领料",Config.ProductGet4BoxP2Activity,R.mipmap.chuku));//0917生产领料单
            items.add(new SettingList("","混包新增",Config.BoxReAddP2Activity,R.mipmap.chuku));//0917生产领料单
            items.add(new SettingList("","组织间调拨/箱码",Config.DBCopy2P2Activity,R.mipmap.chuku));//组织间调拨/箱码  复制与一期20191121
            items.add(new SettingList("","原木领料2",Config.ProductGet4P24PihaoActivity,R.mipmap.chuku));

        }else if (type==2){
            items.add(new SettingList("水板调拨",Config.ShuiBanDB4P2Activity,R.mipmap.diaobo));
            items.add(new SettingList("水板拆托出库",Config.ShuiBanGetActivity,R.mipmap.chuku));
            items.add(new SettingList("",1,0));
            items.add(new SettingList("烘干板入库",Config.DryingInStoreActivity,R.mipmap.ruku));
            items.add(new SettingList("水板板材码拍",Config.ProductInStore4P2MpActivity,R.mipmap.ruku));
            items.add(new SettingList("调拨入窑",Config.DBInKiln4P2Activity,R.mipmap.chuku));
            items.add(new SettingList("出窑领料",Config.OutKilnGetActivity,R.mipmap.chuku));
            items.add(new SettingList("外购烘干板入库",Config.WgDryingInStoreActivity,R.mipmap.chuku));
        }else if (type==3){
            items.add(new SettingList("烘干板领料",Config.ShuiBanGet2Activity,R.mipmap.chuku));
            items.add(new SettingList("刨光板码托",Config.DryingGetActivity,R.mipmap.chuku));
//            items.add(new SettingList("烘干板领料",0,R.mipmap.printmain));
            items.add(new SettingList(App.getContext().getResources().getString(R.string.wort_instore),Config.WortInStore4P2Activity,R.mipmap.ruku));
            items.add(new SettingList(App.getContext().getResources().getString(R.string.boxcode_print),Config.PrintBoxCodeActivity,R.mipmap.printmain));
            items.add(new SettingList("拆箱",Config.SplitBoxP2Activity,R.mipmap.diaobo));
        }else{
            items.add(new SettingList("成品加工领料",Config.CpGetActivityDlg,R.mipmap.sellinout));
            items.add(new SettingList("成品加工入库",Config.CpInActivityDlg,R.mipmap.sellinout));
            items.add(new SettingList("标签补打",Config.PrintHistoryActivity,R.mipmap.printmain));
            items.add(new SettingList("期初补打",Config.PrintBeforeDataActivity,R.mipmap.printmain));
            items.add(new SettingList("样板出库",Config.YbOut4P2Activity,R.mipmap.chuku));
        }
//            items.add(new SettingList(App.getContext().getResources().getString(R.string.p2_production_instore),Config.P2ProductionInStoreActivity,R.mipmap.printmain));


//        for (int i=0; i<items.size();i++){
//            Log.e("test","定位ary:"+ary[i]);
//            Log.e("test","定位items:"+items.get(i).tag);
        //根据ary的值，遍历list符合的item并添加
//            for (int j=0;j<ary.length;j++){
//                if (TextUtils.equals(items.get(i).tag,ary[j])){
//                    Log.e("test","222加入："+items.get(i).toString());
//                    backItems.add(items.get(i));
//                }
//            }
//        }
        return items;
    }



    public static ArrayList<SettingList> getSaleList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList("简单生产领料",R.mipmap.chuku));
        items.add(new SettingList("简单产品入库",R.mipmap.purchaseinstorage));
        items.add(new SettingList("扫一扫",R.mipmap.scan));
//        items.add(new SettingList("挑板入库",R.mipmap.saleorder));
//        items.add(new SettingList("到柜入库",R.mipmap.sellinout));
//        items.add(new SettingList("简单生产入库",R.mipmap.sellout));
//        items.add(new SettingList("生产领料",R.mipmap.chuku));
        return items;
    }
    public static ArrayList<SettingList> getStorageList() {
        ArrayList<SettingList> items = new ArrayList<>();
//        items.add(new SettingList("盘点",R.mipmap.pandian));
//        items.add(new SettingList("调拨",R.mipmap.diaobo));
//        items.add(new SettingList("其他入库",R.mipmap.ruku));
//        items.add(new SettingList("其他出库",R.mipmap.chuku));
        return items;
    }

    public static ArrayList<SettingList> GetPushDownList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList("采购订单下推外购入库单",R.mipmap.pandian));
        items.add(new SettingList("销售订单下推销售出库单",R.mipmap.pandian));
        items.add(new SettingList("销售订单下推销售退货单",R.mipmap.diaobo));
        items.add(new SettingList("销售出库单下推销售退货单",R.mipmap.ruku));
        items.add(new SettingList("发货通知单下推销售出库单",R.mipmap.chuku));
        items.add(new SettingList("退货通知单下推销售退货单",R.mipmap.pandian));
        items.add(new SettingList("调拨申请单下推分布式调入单",R.mipmap.diaobo));
        items.add(new SettingList("调拨申请单下推分布式调出单",R.mipmap.ruku));

//        items.add(new SettingList("生产任务单下推生产领料",R.mipmap.pandian));
//        items.add(new SettingList("采购订单下推收料通知单",R.mipmap.pandian));
//        items.add(new SettingList("销售订单下推发料通知单",R.mipmap.pandian));
//        items.add(new SettingList("生产任务单下推生产汇报单",R.mipmap.pandian));
//        items.add(new SettingList("汇报单下推产品入库",R.mipmap.pandian));
//        items.add(new SettingList("销售出库单验货",R.mipmap.pandian));
//        items.add(new SettingList("发货通知生成调拨单",R.mipmap.pandian));
//        items.add(new SettingList("产品入库验货",R.mipmap.pandian));
        return items;
    }
}
