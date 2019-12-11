package com.fangzuo.assist.cloud.Utils;


import com.fangzuo.assist.cloud.Dao.DryingGetData;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//JSON回单组装类，用于拼接键值对（不用GSON是由于排列键值对相对麻烦，而此api需要对键值对进行先后排序才能生效，so do this）
public class JsonDealUtils {
    /*-----------------------------------------------------------二期单据----------------------------------------------*/
    //-------------------------------------------------------------------------------------采购入库下推生产领料的JSON拼接（二期单据）
    public static String JSonP2Cgrk2PrG(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FSTAFFNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FSecActualQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("FAppQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FBaseAppQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FSecActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FStockActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(0).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }


    //采购入库下推生产领料的JSON拼接（二期单据）
    public static String JSonP2Cgrk2PrG4P1(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FSTAFFNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FSecActualQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("FAppQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FBaseAppQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FSecActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FStockActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(0).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //产品入库的JSON拼接(二期单据)
    public static String JSonP2PrIS(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//入库组织
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
//                addObject(inObject,"FCurrId","FNUMBER","1");
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);//货主
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
//                    addObject(jsonfz,"FAUXPROPID__FF100003","FNumber","");
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);

//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
//                    jsonAr.put("FBaseRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
                    jsonAr.put("F_FFF_Integer5",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNumber","KCZT01_SYS");
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(i).FOwnerIdHead);
                    addObject(jsonAr,"FWorkShopId1","FNumber",beans.get(j).FWorkShopId1);
                    addObject(jsonAr,"FKeeperID","FNUMBER",mains.get(i).FStockOrgId);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //产品入库的JSON拼接(二期单据)
    public static String JSonP2PrIS4Wort(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//入库组织
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
//                addObject(inObject,"FCurrId","FNUMBER","1");
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);//货主
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
//                    addObject(jsonfz,"FAUXPROPID__FF100003","FNumber","");
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);

//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
//                    jsonAr.put("FBaseRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
                    jsonAr.put("F_FFF_Integer5",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNumber","KCZT01_SYS");
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(i).FOwnerIdHead);
                    addObject(jsonAr,"FWorkShopId1","FNumber",beans.get(j).FWorkShopId1);
                    addObject(jsonAr,"FKeeperID","FNUMBER",mains.get(i).FStockOrgId);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //生产领料的JSON拼接
    public static String JSonP2PrG(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FSTAFFNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FActualQty",beans.get(j).FVolume);
//                    jsonAr.put("FSecActualQty",beans.get(j).FVolume);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("FAppQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FBaseAppQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FSecActualQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FStockActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(0).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //烘干板领料的JSON拼接(箱码出库)
    public static String JSonP2DryingGet(List<T_main> mains, Map<String,List<DryingGetData>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FSTAFFNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                Lg.e("11111111");
                JSONArray jsonArray = new JSONArray();
                List<DryingGetData> beans = map.get(mains.get(i).FOrderId+"");
                Lg.e("22222");
                for (int j = 0; j < beans.size(); j++) {
                Lg.e("23333333");
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FNumber);
                    JSONObject jsonfz = new JSONObject();
//                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
//                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
//                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageNumber);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FActualQty",beans.get(j).FQty);
//                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
//                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
//                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
//                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
//                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
//                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(i).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitNumber);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //水版产品入库的JSON拼接
    public static String JSonP2PrIS_SuiBan(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//入库组织
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
//                addObject(inObject,"FCurrId","FNUMBER","1");
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);//货主
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
//                    addObject(jsonfz,"FAUXPROPID__FF100003","FNumber","");
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);

//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);
                    if (beans.get(j).activity==Config.WgDryingInStoreActivity){
//                        jsonAr.put("FRealQty",MathUtil.D2save5(MathUtil.toD(beans.get(j).FRealQty)*0.00236)+"");
//                        jsonAr.put("FRealQty",MathUtil.D2save5(MathUtil.mul(beans.get(j).FRealQty,"0.00236")));
                        Lg.e("凭借1",beans.get(j).FRealQty);
                        Lg.e("凭借2",MathUtil.D2save5_33(beans.get(j).FRealQty)+"");
                        jsonAr.put("FRealQty",MathUtil.D2save5_33(beans.get(j).FRealQty));
                    }else{
                        jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    }
//                    jsonAr.put("FBaseRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
                    jsonAr.put("F_FFF_Integer5",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNumber","KCZT01_SYS");
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(i).FOwnerIdHead);
                    addObject(jsonAr,"FWorkShopId1","FNumber",beans.get(j).FWorkShopId1);
                    addObject(jsonAr,"FKeeperID","FNUMBER",mains.get(i).FStockOrgId);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    private void P1______________d(){}
    /*-----------------------------------------------------------------------------------------------  一期单据  ----------------------------------------------*/
    //生产领料(箱码出库)
    public static String JSonP1PrGet4Box(List<T_main> mains, Map<String,List<DryingGetData>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FSTAFFNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("F_FFF_Text6",Hawk.get(Info.AutoLogin,""));
                Lg.e("11111111");
                JSONArray jsonArray = new JSONArray();
                List<DryingGetData> beans = map.get(mains.get(i).FOrderId+"");
                Lg.e("22222");
                for (int j = 0; j < beans.size(); j++) {
                    Lg.e("23333333");
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FNumber);
                    JSONObject jsonfz = new JSONObject();
//                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
//                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
//                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageNumber);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FActualQty",beans.get(j).FQty);
//                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
//                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
//                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
//                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
//                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
//                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(i).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitNumber);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //生产领料下推产品入库的JSON拼接
    public static String JSonPG2PrIS(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//入库组织
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"F_FFF_Base","FNUMBER",mains.get(i).FPurchaserId);//采购员
                addObject(inObject,"F_FFF_Base","FNUMBER",mains.get(i).FPurchaserId);//采购员
//                addObject(inObject,"FCurrId","FNUMBER","1");
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);//货主
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("F_FFF_Text2",mains.get(i).FWlCompany);//物流公司
                inObject.put("F_FFF_Text3",mains.get(i).FCarBoxNo);//车厢好
                inObject.put("F_FFF_Amount",mains.get(i).FFreight);//运费
                inObject.put("F_FFF_Text5",mains.get(i).FYaoNo);//窑号
                inObject.put("F_FFF_Text4",mains.get(i).FPassNo);//出门证
                inObject.put("F_FFF_Text6", Hawk.get(Info.AutoLogin,""));

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(0).FOwnerIdHead);
                    addObject(jsonAr,"FWorkShopId1","FNumber",beans.get(j).FWorkShopId1);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
//                    addObject(jsonfz,"FAUXPROPID__FF100003","FNumber","");
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);

//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNumber","KCZT01_SYS");
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperID","FNUMBER",mains.get(0).FStockOrgId);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //-------------------------------------------------------------------------------------采购入库下推生产领料的JSON拼接（一期单据）
    public static String JSonP1Cgrk2PrG(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId",   "FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text2",mains.get(i).FWlCompany);//物流公司
                inObject.put("F_FFF_Text3",mains.get(i).FCarBoxNo);//车厢好
                inObject.put("F_FFF_Amount",mains.get(i).FFreight);//运费
                inObject.put("F_FFF_Text5",mains.get(i).FYaoNo);//窑号
                inObject.put("F_FFF_Text4",mains.get(i).FPassNo);//出门证
                inObject.put("F_FFF_Text6",Hawk.get(Info.AutoLogin,""));


                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNUMBER","KCZT01_SYS");
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FSecActualQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("FAppQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FBaseAppQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FSecActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FStockActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("F_FFF_Integer",beans.get(j).FYmLenght);
                    jsonAr.put("F_FFF_Integer1",beans.get(j).FYmDiameter);
//                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
//                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
//                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(0).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //-------------------------------------------------------------------------------------箱码调拨单的JSON拼接(箱码出库)
    public static String JSonP1DB4Box(List<T_main> mains, Map<String,List<DryingGetData>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                inObject.put("FTransferDirect",mains.get(i).FDBDirection);//调拨方向
                inObject.put("FTransferBizType",mains.get(i).FDBType);//调拨类型

                addObject(inObject,"FStockOutOrgId","FNUMBER",mains.get(i).FStockOrgId);//调出库存组织
                addObject(inObject,"FOwnerOutIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
//                inObject.put("FOwnerTypeOutIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//调入库存组织
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHeadIn);//调入货主
//                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHeadIn);//调入货主类型
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);//
                inObject.put("FNote",mains.get(i).FNot);//
                inObject.put("F_FFF_TEXT",mains.get(i).FWlCompany);//物流公司
                inObject.put("F_FFF_TEXT1",mains.get(i).FCarBoxNo);//车厢好
                inObject.put("F_FFF_DECIMAL",mains.get(i).FFreight);//运费
                inObject.put("F_FFF_TEXT2",mains.get(i).FYaoNo);//窑号
//                inObject.put("F_FFF_Text4",mains.get(i).FPassNo);//出门证
                inObject.put("F_FFF_Text3",Hawk.get(Info.AutoLogin,""));

                JSONArray jsonArray = new JSONArray();
                List<DryingGetData> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    Lg.e("23333333");
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FNumber);
//                    JSONObject jsonfz = new JSONObject();
//                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
//                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
//                    jsonAr.put("FAuxPropId",jsonfz);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitNumber);
                    jsonAr.put("FQty",beans.get(j).FQty);//调拨数量
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);//调出批号
                    addObject(jsonAr,"FDestLot","FNumber",beans.get(j).FBatch);//调入批号
                    addObject(jsonAr,"FSrcStockId","FNumber",mains.get(i).FStorageOutId);//调出仓库
                    addObject(jsonAr,"FDestStockId","FNumber",mains.get(i).FStorageInId);//调入仓库
                    addObject(jsonAr,"FSrcStockLocId","FNumber",mains.get(i).FWaveHouseOutId);//调出仓位
                    addObject(jsonAr,"FDestStockLocId","FNumber",mains.get(i).FWaveHouseInId);//调入仓位
//                    jsonAr.put("FOwnerTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出货主类型
                    addObject(jsonAr,"FOwnerOutId","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入货主类型
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(i).FOwnerIdHeadIn);//调入货主


                    addObject(jsonAr,"FKeeperOutId","FNumber",mains.get(i).FStockOrgId);//调出保管者
                    addObject(jsonAr,"FKeeperId","FNumber",mains.get(i).FPurchaseOrgId);//调入保管者
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入保管者类型
//                    jsonAr.put("FKeeperTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出保管者类型
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);


//                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FNumber);
//                    JSONObject jsonfz = new JSONObject();
////                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
////                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
////                    jsonAr.put("FAuxpropId",jsonfz);
//                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageNumber);
//                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
//                    jsonAr.put("FActualQty",beans.get(j).FQty);
////                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
////                    jsonAr.put("F_FFF_Decimal",beans.get(j).FYmLenght);
////                    jsonAr.put("F_FFF_Decimal1",beans.get(j).FYmDiameter);
////                    jsonAr.put("F_FFF_Integer3",beans.get(j).FBWide);
////                    jsonAr.put("F_FFF_Integer4",beans.get(j).FBThick);
////                    jsonAr.put("F_FFF_Integer2",beans.get(j).FBLenght);
////                    jsonAr.put("F_FFF_Text1",beans.get(j).FLevel);
////                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
//                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(i).FStockOrgId);
//                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitNumber);
////                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FBillEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //采购入库的JSON拼接
    public static String JSonPIS(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FPurchaseOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);
                addObject(inObject,"FStockDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FPurchaseDeptId","FNUMBER",mains.get(i).FPurchaseDeptId);
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPurchaserId","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);
                inObject.put("FDate",mains.get(i).FDate);
//                inObject.put("FStockDirect","GENERAL");
                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
                JSONObject stockObject = new JSONObject();
                stockObject.put("FPriceTimePoint",mains.get(i).FPriceTimePoint);
                addObject(stockObject,"FSettleOrgId","FNUMBER",mains.get(i).FSettleOrgId);
                addObject(stockObject,"FSettleCurrId","FNUMBER",mains.get(i).FSettleCurrId);
                inObject.put("FInStockFin",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FRemainInStockUnitId","FNumber",beans.get(j).FRemainInStockUnitId);
                    addObject(jsonAr,"FPriceUnitID","FNumber",beans.get(j).FPriceUnitID);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FRemainInStockQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FGiveAway",beans.get(j).FIsFree);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FInStockEntry",jsonArray);
                outOfModel.put(inObject);
            }

            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //-----------------------------------------------------------------------------------------------采购订单下推外购入库单的JSON拼接
    public static String JSonCgOrder2Wgrk(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//收料组织
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FPurchaseOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//采购组织
                addObject(inObject,"FDemandOrgId","FNUMBER",mains.get(i).FNeedOrgId);//需求组织
//                addObject(inObject,"FOwnerCustomerID","FNUMBER",mains.get(i).FPurchaseOrgId);//财务货主客户
//                addObject(inObject,"FStockDeptId","FNUMBER","02.01.02");//mains.get(i).FDepartmentNumber
                addObject(inObject,"FPurchaseDeptId","FNUMBER",mains.get(i).FPurchaseDeptId);
//                addObject(inObject,"FStockerId","FNUMBER","024");//mains.get(i).FStockerNumber
                addObject(inObject,"FPurchaserId","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("F_FFF_Remarks",mains.get(i).FNot);
                inObject.put("F_FFF_Text4",Hawk.get(Info.AutoLogin,""));
//                inObject.put("FStockDirect","GENERAL");
                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
                JSONObject stockObject = new JSONObject();
                stockObject.put("FPriceTimePoint",mains.get(i).FPriceTimePoint);
                addObject(stockObject,"FSettleOrgId","FNUMBER",mains.get(i).FSettleOrgId);//结算
                addObject(stockObject,"FSettleCurrId","FNUMBER",mains.get(i).FSettleCurrId);
                inObject.put("FInStockFin",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FRemainInStockUnitId","FNumber",beans.get(j).FRemainInStockUnitId);
                    jsonAr.put("FEntryTaxRate",beans.get(j).FTaxRate);
                    addObject(jsonAr,"FPriceUnitID","FNumber",beans.get(j).FPriceUnitID);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    jsonAr.put("FPrice","2.403846");
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    if (j > 0 && beans.get(j).FEntryID.equals(beans.get(j-1).FEntryID)) {
                        jsonAr.put("FMustQty","0");
                    }else{
                        jsonAr.put("FMustQty",beans.get(j).FRemainInStockQty);
                    }
//                    jsonAr.put("FMustQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FTaxPrice",beans.get(j).FTaxPrice);
                    jsonAr.put("FTaxNetPrice",beans.get(j).FTaxPrice);
//                    jsonAr.put("FRemainInStockQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FGiveAway",beans.get(j).FIsFree);
                    jsonAr.put("FSRCBillNo",mains.get(i).FSoorDerno);
                    jsonAr.put("FSoorDerno",mains.get(i).FSoorDerno);
                    jsonAr.put("FSRCBILLTYPEID","PUR_PurchaseOrder");
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FInStockEntry_Link_FRuleId","PUR_PurchaseOrder-STK_InStock");
                        jsonAr2.put("FInStockEntry_Link_FSTableId","0");
                        jsonAr2.put("FInStockEntry_Link_FSTableName","t_PUR_POOrderEntry");
                        jsonAr2.put("FInStockEntry_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FInStockEntry_Link_FSId",beans.get(j).FEntryID);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FInStockEntry_Link",jsonA2);
                }
                inObject.put("FInStockEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //-----------------------------------------------------------------------------------------------采购订单的JSON拼接
    public static String JSonPuO(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FPurchaseOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);
                addObject(inObject,"FPurchaseDeptId","FNUMBER",mains.get(i).FPurchaseDeptId);
                addObject(inObject,"FPurchaserId","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FDate",mains.get(i).FDate);
                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
                JSONObject stockObject = new JSONObject();
                addObject(stockObject,"FPriceTimePoint","FNUMBER",mains.get(i).FPriceTimePoint);
                addObject(stockObject,"FExchangeTypeId","FNUMBER","HLTX01_SYS");
                stockObject.put("FExchangeRate","1");
                inObject.put("FPOOrderFinance",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FPriceUnitId","FNumber",beans.get(j).FPriceUnitID);
                    addObject(jsonAr,"FUnitId","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FGiveAway",beans.get(j).FIsFree);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FPOOrderEntry",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //-----------------------------------------------------------------------------------------------其他入库的JSON拼接
    public static String JSonOIS(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNOTE",mains.get(i).FNot);
                inObject.put("FStockDirect","GENERAL");
                addObject(inObject,"FSUPPLIERID","FNUMBER",mains.get(i).FSupplierId);
                addObject(inObject,"FDEPTID","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FSTOCKERID","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FACCEPTANCE","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("F_FFF_Text1",Hawk.get(Info.AutoLogin,""));
                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = new ArrayList<>();
                beans.addAll(map.get(mains.get(i).FOrderId+""));
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMATERIALID","FNUMBER",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockId","FNUMBER",beans.get(j).FStorageId);
                    jsonAr.put("FOWNERTYPEID",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOWNERID","FNUMBER",mains.get(i).FOwnerIdHead);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    jsonAr.put("FEntryNote",beans.get(j).FNote);
//                    jsonAr.put("FKEEPERTYPEID","BD_KeeperOrg");
                    jsonAr.put("FQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FKEEPERID","FNUMBER",mains.get(i).FStockOrgId);
                    addObject(jsonAr,"FLOT","FNUMBER",beans.get(j).FBatch);
                    addObject(jsonAr,"FUnitID","FNUMBER",beans.get(j).FUnitID);
                    addObject(jsonAr,"FSTOCKSTATUSID","FNUMBER","KCZT01_SYS");
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //-----------------------------------------------------------------------------------------------其他出库的JSON拼接
    public static String JSonOOS(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FPickOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);
                addObject(inObject,"FDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNote",mains.get(i).FNot);
//                inObject.put("FStockDirect","GENERAL");
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FCustId","FNUMBER",mains.get(i).FCustomerID);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("F_FFF_Text1",Hawk.get(Info.AutoLogin,""));
                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNUMBER",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockId","FNUMBER",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNUMBER",beans.get(j).FBatch);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    jsonAr.put("FQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNUMBER",beans.get(j).FUnitID);
                    addObject(jsonAr,"FStockStatusId","FNUMBER","KCZT01_SYS");
                    jsonAr.put("FOwnerTypeId",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(i).FOwnerIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //----------------------------------------------------------------------------------------------销售出库的JSON拼接
    public static String JSonSaleOut(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FSaleOrgId","FNumber",mains.get(i).FStockOrgId);//销售组织
                addObject(inObject,"FCustomerID","FNumber",mains.get(i).FCustomerID);//客户
//                addObject(inObject,"FSaleDeptID","FNUMBER",mains.get(i).FDepartmentNumber);//销售部门
                addObject(inObject,"FSalesManID","FNUMBER",mains.get(i).FStockerNumber);//销售员
                addObject(inObject,"FStockOrgId","FNumber",mains.get(i).FStockOrgId);//库存组织
                addObject(inObject,"FDeliveryDeptID","FNumber",mains.get(i).FDepartmentNumber);//发货部门
                addObject(inObject,"FStockerID","FNumber",mains.get(i).FStockerNumber);//仓管员
//                addObject(inObject,"FStockerID","FNumber",mains.get(i).FStockerNumber);
//                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNumber",mains.get(i).FOwnerIdHead);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNote",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).FWlCompany);
                inObject.put("FCARRIAGENO",mains.get(i).FCarBoxNo);
                inObject.put("F_FFF_Text1",Hawk.get(Info.AutoLogin,""));
//                inObject.put("F_FFF_Decimal",mains.get(i).FFreight);
                JSONObject stockObject = new JSONObject();
                addObject(stockObject,"FSettleOrgID","FNUMBER",mains.get(i).FSettleOrgId);
                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);
//                inObject.put("FSoorDerno",mains.get(i).FSoorDerno);
                inObject.put("SubHeadEntity",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialID","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockID","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FOwnerID","FNumber",mains.get(i).FOwnerIdHead);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FMustQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FBaseMustQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FARNOTJOINQTY",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
                    jsonArray.put(jsonAr);
                    }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //----------------------------------------------------------------------------------------------销售订单下推销售出库的JSON拼接
    public static String JSonSaleOrder2SaleOut(List<T_main> mains, Map<String,List<T_Detail>> map){
        Lg.e("进入json拼接");
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                if (null==beans || beans.size()<=0){//若是没明细，直接跳过
                    continue;
                }
                Lg.e("jsonMain:",mains.get(i));
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FSaleOrgId","FNumber",mains.get(i).FStockOrgId);
                addObject(inObject,"FOwnerIdHead","FNumber",mains.get(i).FOwnerIdHead);
                if ("XSCKD05_SYS".equals(mains.get(i).FBillTypeID)){
                    addObject(inObject,"FSettleOrgID","FNumber",mains.get(i).FPurchaseOrgId);//0530本来是以FPurchaseOrgId
                }else{
                    addObject(inObject,"FSettleOrgID","FNumber",mains.get(i).FOwnerIdHead);
                }
                addObject(inObject,"FStockOrgId","FNumber",mains.get(i).FPurchaseOrgId);
                addObject(inObject,"FStockerID","FNumber",mains.get(i).FStockerNumber);
                addObject(inObject,"FDeliveryDeptID","FNumber",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FCustomerID","FNumber",mains.get(i).FCustomerID);
                addObject(inObject,"FSaleDeptID","FNumber",mains.get(i).FPurchaseDeptId);
                addObject(inObject,"FSalesManID","FNumber",mains.get(i).FPurchaserId);
                inObject.put("F_FFF_Combo",mains.get(i).FFieldMan);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNote",mains.get(i).FNot);
                inObject.put("F_FFF_Text1",Hawk.get(Info.AutoLogin,""));
                JSONObject stockObject = new JSONObject();
                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);
//                addObject(stockObject,"FOwnerSupplierID","FNUMBER",mains.get(i).FOwnerIdHead);
//                inObject.put("FSoorDerno",mains.get(i).FSoorDerno);
                inObject.put("SubHeadEntity",stockObject);

                JSONArray jsonArray = new JSONArray();

                Lg.e("jsonDetail:",beans);
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialID","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockID","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    if (j > 0 && beans.get(j).FEntryID.equals(beans.get(j-1).FEntryID)) {
                        jsonAr.put("FMustQty","0");
                    }else{
                        jsonAr.put("FMustQty",beans.get(j).FRemainInStockQty);
                    }
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    jsonAr.put("FBaseMustQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FARNOTJOINQTY",beans.get(j).FRealQty);

                    jsonAr.put("FTaxPrice",beans.get(j).FTaxPrice);
                    addObject(jsonAr,"FOwnerID","FNumber",beans.get(j).FHuoZhuNumber);
                    jsonAr.put("FSOEntryId",beans.get(j).FSOEntryId);
                    jsonAr.put("FSrcBillNo",mains.get(i).FSoorDerno);
                    jsonAr.put("FSoorDerno",mains.get(i).FSoorDerno);
                    jsonAr.put("FSrcType","SAL_SaleOrder");
                    jsonAr.put("FIsFree","1".equals(beans.get(j).FIsGift));
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","SaleOrder-OutStock");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_SAL_ORDERENTRY");
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);

//                        jsonAr2.put("FEntity_Link_FBaseUnitQtyOld",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FBaseUnitQty",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FSALBASEQTYOld",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FSALBASEQTY",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FAuxUnitQtyOld",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FAuxUnitQty",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FLnkAmount",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FLnk1Amount",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FSOEntryId",beans.get(0).FSOEntryId);
//                        jsonAr2.put("FEntity_Link_FSoorDerno",mains.get(0).FSoorDerno);
//                        jsonAr2.put("FEntity_Link_FSrcType","SAL_SaleOrder");
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FEntity_Link",jsonA2);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //发货通知单下推销售出库单的JSON拼接
    public static String JSonSendMsg2SaleOut(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FDeliveryDeptID","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FStockerID","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FSalesManID","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNote",mains.get(i).FDate);
                addObject(inObject,"FCustomerID","FNUMBER",mains.get(i).FCustomerID);
                JSONObject stockObject = new JSONObject();
                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);
//                inObject.put("FSoorDerno",mains.get(i).FSoorDerno);
                inObject.put("SubHeadEntity",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialID","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockID","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FMustQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FBaseMustQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FARNOTJOINQTY",beans.get(j).FRealQty);
                    jsonAr.put("FSOEntryId",beans.get(j).FSOEntryId);
                    jsonAr.put("FSrcBillNo",mains.get(0).FSoorDerno);
                    jsonAr.put("FSoorDerno",mains.get(0).FSoorDerno);
                    jsonAr.put("FSrcType","SAL_SaleOrder");
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","DeliveryNotice-OutStock");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_SAL_DELIVERYNOTICEENTRY");
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);

//                        jsonAr2.put("FEntity_Link_FSOEntryId",beans.get(0).FSOEntryId);
//                        jsonAr2.put("FEntity_Link_FSoorDerno",mains.get(0).FSoorDerno);
//                        jsonAr2.put("FEntity_Link_FSrcType","SAL_SaleOrder");
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FEntity_Link",jsonA2);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //销售订单的JSON拼接
    public static String JSonSaleOrder(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FSaleOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FSaleDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FSalerId","FNUMBER",mains.get(i).FPurchaserId);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNote",mains.get(i).FDate);
                addObject(inObject,"FCustId","FNUMBER",mains.get(i).FCustomerID);
//                JSONObject stockObject = new JSONObject();
//                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);
//                inObject.put("SubHeadEntity",stockObject);
                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FSOStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FQty",beans.get(j).FRealQty);
                    jsonAr.put("FARNOTJOINQTY",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FSaleOrderEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //生产领料的JSON拼接
    public static String JSonPrG(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//发料组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FPickerId","FSTAFFNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FWorkShopId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("F_FFF_Text6",Hawk.get(Info.AutoLogin,""));

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FSecActualQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
//                    jsonAr.put("FAppQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FBaseAppQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FSecActualQty",beans.get(j).FRemainInStockQty);
                    jsonAr.put("FStockActualQty",beans.get(j).FRemainInStockQty);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(i).FStockOrgId);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //产品入库的JSON拼接
    public static String JSonPrIS(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillType","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//入库组织
                addObject(inObject,"FPrdOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//生产组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
//                addObject(inObject,"FCurrId","FNUMBER","1");
                inObject.put("FOwnerTypeId0",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerId0","FNUMBER",mains.get(i).FOwnerIdHead);//货主
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FDescription",mains.get(i).FNot);
                inObject.put("F_FFF_Text",mains.get(i).F_FFF_Text);
                inObject.put("F_FFF_Text6", Hawk.get(Info.AutoLogin,""));

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FOwnerId","FNumber",mains.get(0).FOwnerIdHead);
                    addObject(jsonAr,"FWorkShopId1","FNumber",beans.get(j).FWorkShopId1);
//                    addObject(jsonfz,"FAUXPROPID__FF100003","FNumber","");
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);

//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    jsonAr.put("FProductNo",beans.get(j).FProductNo);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNumber","KCZT01_SYS");
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperID","FNUMBER",mains.get(0).FStockOrgId);
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //销售订单下推销售退货单的JSON拼接
    public static String JSonSaleOrder2SaleOutBack(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                inObject.put("FOrderNo",mains.get(i).FSoorDerno);
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FSaleOrgId","FNUMBER",mains.get(i).FStockOrgId);
                inObject.put("FDate",mains.get(i).FDate);
                addObject(inObject,"FSaledeptid","FNUMBER",mains.get(i).FPurchaseDeptId);
                addObject(inObject,"FStockDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FSalesManId","FNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FRetcustId","FNUMBER",mains.get(i).FCustomerID);
                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
                JSONObject stockObject = new JSONObject();
                addObject(stockObject,"FExchangeTypeId","FNUMBER","HLTX01_SYS");
                stockObject.put("FExchangeRate","1");
                addObject(stockObject,"FSettleOrgId","FNUMBER",mains.get(i).FSettleOrgId);
                addObject(stockObject,"FSettleCurrId","FNUMBER",mains.get(i).FSettleCurrId);
                inObject.put("SubHeadEntity",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
                    jsonAr.put("FSOEntryId",beans.get(j).FSOEntryId);
                    addObject(jsonAr,"FReturnType","FNumber",beans.get(j).FBackType);
                    jsonAr.put("FDeliveryDate",beans.get(j).FBackDate);
                    jsonAr.put("FOwnerTypeId","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerId","FNumber","100");
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","SaleOrder-SalReturnStock");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_SAL_ORDERENTRY");
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FEntity_Link",jsonA2);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //销售出库单下推销售退货单的JSON拼接
    public static String JSonSaleOut2SaleOutBack(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                inObject.put("FOrderNo",mains.get(i).FSoorDerno);
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FSaleOrgId","FNUMBER",mains.get(i).FStockOrgId);
                inObject.put("FDate",mains.get(i).FDate);
                addObject(inObject,"FSaledeptid","FNUMBER",mains.get(i).FPurchaseDeptId);
                addObject(inObject,"FStockDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                addObject(inObject,"FSalesManId","FNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FRetcustId","FNUMBER",mains.get(i).FCustomerID);
                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
                JSONObject stockObject = new JSONObject();
                addObject(stockObject,"FExchangeTypeId","FNUMBER","HLTX01_SYS");
                stockObject.put("FExchangeRate","1");
                addObject(stockObject,"FSettleOrgId","FNUMBER",mains.get(i).FSettleOrgId);
                addObject(stockObject,"FSettleCurrId","FNUMBER",mains.get(i).FSettleCurrId);
                inObject.put("SubHeadEntity",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
                    jsonAr.put("FSOEntryId",beans.get(j).FSOEntryId);
                    addObject(jsonAr,"FReturnType","FNumber",beans.get(j).FBackType);
                    jsonAr.put("FDeliveryDate",beans.get(j).FBackDate);
                    jsonAr.put("FOwnerTypeId","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerId","FNumber","100");
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","OutStock-SalReturnStock");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_SAL_OUTSTOCKENTRY");
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FEntity_Link",jsonA2);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //退货通知单下推销售退货单的JSON拼接
    public static String JSonBackMsg2SaleOutBack(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("F_FFF_Combo",mains.get(i).FFieldMan);
                inObject.put("F_FFF_Remarks",mains.get(i).FNot);
                inObject.put("F_FFF_Text",Hawk.get(Info.AutoLogin,""));
//                inObject.put("FOrderNo",mains.get(i).FSoorDerno);
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FSaleOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);
                addObject(inObject,"FRetcustId","FNUMBER",mains.get(i).FCustomerID);
                addObject(inObject,"FSaledeptid","FNUMBER",mains.get(i).FPurchaseDeptId);
                addObject(inObject,"FSalesManId","FNUMBER",mains.get(i).FPurchaserId);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);
                addObject(inObject,"FStockDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
//                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
                JSONObject stockObject = new JSONObject();
//                addObject(stockObject,"FExchangeTypeId","FNUMBER","HLTX01_SYS");//汇率类型
//                stockObject.put("FExchangeRate","1");//汇率
                addObject(stockObject,"FSettleOrgId","FNUMBER",mains.get(i).FSettleOrgId);
                addObject(stockObject,"FSettleCurrId","FNUMBER",mains.get(i).FSettleCurrId);
                inObject.put("SubHeadEntity",stockObject);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    if (j > 0 && beans.get(j).FEntryID.equals(beans.get(j-1).FEntryID)) {
//                        jsonAr.put("FMustQty","0");
//                        jsonAr.put("FSalBaseNum","0");
//                        jsonAr.put("FStockBaseDen","0");
                    }else{
                        jsonAr.put("FMustQty",beans.get(j).FRemainInStockQty);
//                        jsonAr.put("FSalBaseNum",beans.get(j).FRemainInStockQty);
//                        jsonAr.put("FStockBaseDen",beans.get(j).FRemainInStockQty);
                    }
                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
//                    addObject(jsonAr,"FStockstatusId","FNumber",beans.get(j).FStorageId);
                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
                    jsonAr.put("FSOEntryId",beans.get(j).FSOEntryId);
                    jsonAr.put("FSrcBillTypeID","SAL_RETURNNOTICE");
                    jsonAr.put("FSrcBillNo",mains.get(0).FSoorDerno);

                    addObject(jsonAr,"FReturnType","FNumber",beans.get(j).FBackType);//THLX01_SYS
                    jsonAr.put("FDeliveryDate",beans.get(j).FBackDate);
                    jsonAr.put("FTaxPrice",beans.get(j).FTaxPrice);
//                    jsonAr.put("FOwnerTypeId","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerId","FNumber",beans.get(j).FHuoZhuNumber);
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","SalReturnNotice-SalReturnStock");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_SAL_RETURNNOTICEENTRY");//return_notice_entry
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);
//                        jsonAr2.put("FEntity_Link_FBaseunitQtyOld",beans.get(j).FRemainInStockQty);
//                        jsonAr2.put("FEntity_Link_FBaseunitQty",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FSalBaseQtyOld",beans.get(j).FRemainInStockQty);
//                        jsonAr2.put("FEntity_Link_FSalBaseQty",beans.get(j).FRealQty);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FEntity_Link",jsonA2);
                }
                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //分布式调入单的JSON拼接
    public static String JSonFDin(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgID","FNUMBER",mains.get(i).FStockOrgId);//调入库存组织
                addObject(inObject,"FStockOutOrgID","FNUMBER",mains.get(i).FStockOrgId);//调出库存组织
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FTransferDirect",mains.get(i).FDate);//调拨方向
                inObject.put("FTransferBizType",mains.get(i).FDate);//调拨类型
                inObject.put("FBizType",mains.get(i).FDate);//业务类型
                inObject.put("FOwnerTypeOutIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FOwnerOutIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
                addObject(inObject,"FSTOCKERID","FNUMBER",mains.get(i).FStockerNumber);//仓管员

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialID","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FSrcStockID","FNumber",beans.get(j).FStorageOutId);
                    addObject(jsonAr,"FDestStockID","FNumber",beans.get(j).FStorageInId);
                    JSONObject SrcLoc = new JSONObject();
                    addObject(SrcLoc,"FSRCSTOCKLOCID__FF100003","FNumber",beans.get(j).FWaveHouseOutId);
                    addObject(SrcLoc,"FSRCSTOCKLOCID__FF100012","FNumber",beans.get(j).FWaveHouseOutId);
                    jsonAr.put("FSRCStockLocId",SrcLoc);
                    JSONObject DestLoc = new JSONObject();
                    addObject(DestLoc,"FDESTSTOCKLOCID__FF100003","FNumber",beans.get(j).FWaveHouseInId);
                    addObject(DestLoc,"FDESTSTOCKLOCID__FF100012","FNumber",beans.get(j).FWaveHouseInId);
                    jsonAr.put("FDestStockLocId",DestLoc);
                    addObject(jsonAr,"FLOT","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FKeeperTypeOutID",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperOutID","FNumber",mains.get(i).FOwnerIdHead);
                    jsonAr.put("FKeeperTypeID",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperID","FNumber",mains.get(i).FOwnerIdHead);
                    jsonAr.put("FOwnerTypeID","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerID","FNumber","100");
                    jsonAr.put("FOwnerTypeOutID","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerOutID","FNumber","100");
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","SalReturnNotice-SalReturnStock");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_SAL_RETURNNOTICEENTRY");
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FSTKTRSINENTRY_Link",jsonA2);
                }
                inObject.put("FSTKTRSINENTRY",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //分布式调出单的JSON拼接
    public static String JSonFDout(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgID","FNUMBER",mains.get(i).FStockOrgId);//调入库存组织
                addObject(inObject,"FStockInOrgID","FNUMBER",mains.get(i).FStockOrgId);//调出库存组织
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FTransferDirect",mains.get(i).FDate);//调拨方向
                inObject.put("FTransferBizType",mains.get(i).FDate);//调拨类型
                inObject.put("FBizType",mains.get(i).FDate);//调拨类型
                inObject.put("FOwnerTypeInIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FOwnerInIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
                addObject(inObject,"FSTOCKERID","FNUMBER",mains.get(i).FStockerNumber);//仓管员
                addObject(inObject,"FSUPPLIERID","FNumber",mains.get(i).FSupplierId);
                addObject(inObject,"FCustID","FNumber",mains.get(i).FCustomerID);

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialID","FNumber",beans.get(j).FMaterialId);
                    addObject(jsonAr,"FSrcStockID","FNumber",beans.get(j).FStorageOutId);
                    addObject(jsonAr,"FDestStockID","FNumber",beans.get(j).FStorageInId);
                    JSONObject SrcLoc = new JSONObject();
                    addObject(SrcLoc,"FSRCSTOCKLOCID__FF100003","FNumber",beans.get(j).FWaveHouseOutId);
                    addObject(SrcLoc,"FSRCSTOCKLOCID__FF100012","FNumber",beans.get(j).FWaveHouseOutId);
                    jsonAr.put("FSrcStockLocId",SrcLoc);
                    JSONObject DestLoc = new JSONObject();
                    addObject(DestLoc,"FDESTSTOCKLOCID__FF100003","FNumber",beans.get(j).FWaveHouseInId);
                    addObject(DestLoc,"FDESTSTOCKLOCID__FF100012","FNumber",beans.get(j).FWaveHouseInId);
                    jsonAr.put("FDestStockLocId",DestLoc);
                    addObject(jsonAr,"FLOT","FNumber",beans.get(j).FBatch);
                    jsonAr.put("FQty",beans.get(j).FRealQty);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    addObject(jsonAr,"FBaseUnitID","FNumber",beans.get(j).FUnitID);

                    jsonAr.put("FKeeperTypeOutID",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperOutID","FNumber",mains.get(i).FOwnerIdHead);
                    jsonAr.put("FKeeperTypeID",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FKeeperID","FNumber",mains.get(i).FOwnerIdHead);
                    jsonAr.put("FOwnerTypeID","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerID","FNumber","100");
                    jsonAr.put("FOwnerTypeOutID","BD_OwnerOrg");
                    addObject(jsonAr,"FOwnerInID","FNumber","100");
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FEntity_Link_FRuleId","STK_TRANSFERAPPLY-STK_TRANSFEROUT");
                        jsonAr2.put("FEntity_Link_FSTableId","0");
                        jsonAr2.put("FEntity_Link_FSTableName","T_STK_STKTRANSFERAPPENTRY");
                        jsonAr2.put("FEntity_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FEntity_Link_FSId",beans.get(j).FEntryID);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FSTKTRSOUTENTRY_Link",jsonA2);
                }
                inObject.put("FSTKTRSOUTENTRY",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //销售退货单的JSON拼接
    public static String JSonPD(List<T_main> mainsd, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill","true");
            JSONArray outOfModel = new JSONArray();//Model
            List<T_Detail> beans = map.get("PD");
            for (int i = 0; i < beans.size(); i++) {
                JSONObject inObject = new JSONObject();
                addObject(inObject,"FBillTypeID","FNUMBER",Info.BT_PD);
                inObject.put("FBillNo","");
                inObject.put("FName","普通盘点方案");//盘点方案名称
                addObject(inObject,"FStockOrgId","FNUMBER","100");
                inObject.put("FDocumentStatus","");//状态
                inObject.put("FNote","备注");//备注
                inObject.put("FCloseStatus","");//关闭状态
                inObject.put("FCheckQtyDefault","");//实盘数默认值
                addObject(inObject,"FMaterialId","FNUMBER",beans.get(i).FMaterialId);
                addObject(inObject,"FStockId","FNUMBER",beans.get(i).FStorageId);


//                addObject(inObject,"FSaleOrgId","FNUMBER",mains.get(i).FStockOrgId);
//                inObject.put("FDate",mains.get(i).FDate);
//                addObject(inObject,"FSaledeptid","FNUMBER",mains.get(i).FPurchaseDeptId);
//                addObject(inObject,"FStockDeptId","FNUMBER",mains.get(i).FDepartmentNumber);
//                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
//                addObject(inObject,"FSalesManId","FNUMBER",mains.get(i).FPurchaserId);
//                addObject(inObject,"FRetcustId","FNUMBER",mains.get(i).FCustomerID);
//                addObject(inObject,"FSupplierId","FNUMBER",mains.get(i).FSupplierId);
//
//                JSONObject stockObject = new JSONObject();
//                addObject(stockObject,"FExchangeTypeId","FNUMBER","HLTX01_SYS");
//                stockObject.put("FExchangeRate","1");
//                addObject(stockObject,"FSettleOrgId","FNUMBER",mains.get(i).FSettleOrgId);
//                addObject(stockObject,"FSettleCurrId","FNUMBER",mains.get(i).FSettleCurrId);
//                inObject.put("SubHeadEntity",stockObject);

//                JSONArray jsonArray = new JSONArray();
//                List<T_Detail> beans = map.get("PD");
//                for (int j = 0; j < beans.size(); j++) {
//                    JSONObject jsonAr = new JSONObject();
//                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
//                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
//                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
//                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
//                    jsonAr.put("FRealQty",beans.get(j).FRealQty);
//                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    jsonAr.put("FIsFree",beans.get(j).FIsFree);
//                    addObject(jsonAr,"FReturnType","FNumber",beans.get(j).FBackType);
//                    jsonAr.put("FDeliveryDate",beans.get(j).FBackDate);
//                    jsonAr.put("FOwnerTypeId","BD_OwnerOrg");
//                    addObject(jsonAr,"FOwnerId","FNumber","100");
//                    jsonArray.put(jsonAr);
//                }
//                inObject.put("FEntity",jsonArray);
                outOfModel.put(inObject);
            }
            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //调拨单P2的JSON拼接
    public static String JSonDB4P2(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                inObject.put("FTransferDirect",mains.get(i).FDBDirection);//调拨方向
                inObject.put("FTransferBizType",mains.get(i).FDBType);//调拨类型

                addObject(inObject,"FStockOutOrgId","FNUMBER",mains.get(i).FStockOrgId);//调出库存组织
                addObject(inObject,"FOwnerOutIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
//                inObject.put("FOwnerTypeOutIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//调入库存组织
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHeadIn);//调入货主
//                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHeadIn);//调入货主类型

                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);//
                inObject.put("FNote",mains.get(i).FNot);//
//                addObject(inObject,"FDeliveryDeptID","FNUMBER",mains.get(i).FDepartmentNumber);//
//                addObject(inObject,"FSalesManID","FNUMBER",mains.get(i).FPurchaserId);//
//                inObject.put("FDate",mains.get(i).FDate);//
//                addObject(inObject,"FCustomerID","FNUMBER",mains.get(i).FCustomerID);//
//                JSONObject stockObject = new JSONObject();//
//                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);//
////                inObject.put("FSoorDerno",mains.get(i).FSoorDerno);//
//                inObject.put("SubHeadEntity",stockObject);//

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxPropId",jsonfz);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FQty",beans.get(j).FRealQty);//调拨数量
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);//调出批号
                    addObject(jsonAr,"FDestLot","FNumber",beans.get(j).FBatch);//调入批号
//                    jsonAr.put("FOwnerTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出货主类型
                    addObject(jsonAr,"FOwnerOutId","FNUMBER",mains.get(0).FOwnerIdHead);//调出货主
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入货主类型
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(0).FOwnerIdHeadIn);//调入货主


                    addObject(jsonAr,"FKeeperOutId","FNumber",mains.get(0).FStockOrgId);//调出保管者
                    addObject(jsonAr,"FKeeperId","FNumber",mains.get(0).FPurchaseOrgId);//调入保管者
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入保管者类型
//                    jsonAr.put("FKeeperTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出保管者类型
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);

                    addObject(jsonAr,"FSrcStockId","FNumber",beans.get(j).FStorageOutId);//调出仓库
                    addObject(jsonAr,"FDestStockId","FNumber",beans.get(j).FStorageInId);//调入仓库
                    addObject(jsonAr,"FSrcStockLocId","FNumber",beans.get(j).FWaveHouseOutId);//调出仓位
                    addObject(jsonAr,"FDestStockLocId","FNumber",beans.get(j).FWaveHouseInId);//调入仓位
                    jsonArray.put(jsonAr);
                }
                inObject.put("FBillEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //调拨单的JSON拼接
    public static String JSonDB(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                inObject.put("FTransferDirect",mains.get(i).FDBDirection);//调拨方向
                inObject.put("FTransferBizType",mains.get(i).FDBType);//调拨类型

                addObject(inObject,"FStockOutOrgId","FNUMBER",mains.get(i).FStockOrgId);//调出库存组织
                addObject(inObject,"FOwnerOutIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
//                inObject.put("FOwnerTypeOutIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//调入库存组织
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHeadIn);//调入货主
//                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHeadIn);//调入货主类型

                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);//
                inObject.put("FNote",mains.get(i).FNot);//
                inObject.put("F_FFF_Text",mains.get(i).FWlCompany);
                inObject.put("F_FFF_Text1",mains.get(i).FCarBoxNo);
                inObject.put("F_FFF_Text3",Hawk.get(Info.AutoLogin,""));

//                addObject(inObject,"FDeliveryDeptID","FNUMBER",mains.get(i).FDepartmentNumber);//
//                addObject(inObject,"FSalesManID","FNUMBER",mains.get(i).FPurchaserId);//
//                inObject.put("FDate",mains.get(i).FDate);//
//                addObject(inObject,"FCustomerID","FNUMBER",mains.get(i).FCustomerID);//
//                JSONObject stockObject = new JSONObject();//
//                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);//
////                inObject.put("FSoorDerno",mains.get(i).FSoorDerno);//
//                inObject.put("SubHeadEntity",stockObject);//

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxPropId",jsonfz);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FQty",beans.get(j).FRealQty);//调拨数量
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);//调出批号
                    addObject(jsonAr,"FDestLot","FNumber",beans.get(j).FBatch);//调入批号
//                    jsonAr.put("FOwnerTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出货主类型
                    addObject(jsonAr,"FOwnerOutId","FNUMBER",mains.get(0).FOwnerIdHead);//调出货主
//                    jsonAr.put("FOwnerTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入货主类型
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(0).FOwnerIdHeadIn);//调入货主


                    addObject(jsonAr,"FKeeperOutId","FNumber",mains.get(0).FStockOrgId);//调出保管者
                    addObject(jsonAr,"FKeeperId","FNumber",mains.get(0).FPurchaseOrgId);//调入保管者
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入保管者类型
//                    jsonAr.put("FKeeperTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出保管者类型
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);

                    addObject(jsonAr,"FSrcStockId","FNumber",beans.get(j).FStorageOutId);//调出仓库
                    addObject(jsonAr,"FDestStockId","FNumber",beans.get(j).FStorageInId);//调入仓库
                    addObject(jsonAr,"FSrcStockLocId","FNumber",beans.get(j).FWaveHouseOutId);//调出仓位
                    addObject(jsonAr,"FDestStockLocId","FNumber",beans.get(j).FWaveHouseInId);//调入仓位
                    jsonArray.put(jsonAr);
                }
                inObject.put("FBillEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //调拨申请单下推直接调拨单的JSON拼接
    public static String JSonDBApply2DB(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                inObject.put("FTransferDirect",mains.get(i).FDBDirection);//调拨方向
                inObject.put("FTransferBizType",mains.get(i).FDBType);//调拨类型

                addObject(inObject,"FStockOutOrgId","FNUMBER",mains.get(i).FStockOrgId);//调出库存组织
                inObject.put("FOwnerTypeOutIdHead",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                addObject(inObject,"FOwnerOutIdHead","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FPurchaseOrgId);//调入库存组织
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHeadIn);//调入货主类型
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHeadIn);//调入货主

                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);//
                inObject.put("FNote",mains.get(i).FNot);//
                inObject.put("F_FFF_Text3",Hawk.get(Info.AutoLogin,""));
//                addObject(inObject,"FDeliveryDeptID","FNUMBER",mains.get(i).FDepartmentNumber);//
//                addObject(inObject,"FSalesManID","FNUMBER",mains.get(i).FPurchaserId);//
//                inObject.put("FDate",mains.get(i).FDate);//
//                addObject(inObject,"FCustomerID","FNUMBER",mains.get(i).FCustomerID);//
//                JSONObject stockObject = new JSONObject();//
//                addObject(stockObject,"FSettleCurrID","FNUMBER",mains.get(i).FSettleCurrId);//
////                inObject.put("FSoorDerno",mains.get(i).FSoorDerno);//
//                inObject.put("SubHeadEntity",stockObject);//

                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxPropId",jsonfz);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    jsonAr.put("FQty",beans.get(j).FRealQty);//调拨数量
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);//调出批号
                    addObject(jsonAr,"FDestLot","FNumber",beans.get(j).FBatch);//调入批号
                    jsonAr.put("FOwnerTypeOutId",mains.get(i).FOwnerTypeIdHead);//调出货主类型
                    addObject(jsonAr,"FOwnerOutId","FNUMBER",mains.get(i).FOwnerIdHead);//调出货主
                    jsonAr.put("FOwnerTypeId",mains.get(i).FOwnerTypeIdHeadIn);//调入货主类型
                    addObject(jsonAr,"FOwnerId","FNUMBER",mains.get(i).FOwnerIdHeadIn);//调入货主


                    addObject(jsonAr,"FKeeperOutId","FNumber",mains.get(i).FStockOrgId);//调出保管者
                    addObject(jsonAr,"FKeeperId","FNumber",mains.get(i).FPurchaseOrgId);//调入保管者
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHeadIn);//调入保管者类型
//                    jsonAr.put("FKeeperTypeOutId",mains.get(0).FOwnerTypeIdHead);//调出保管者类型
//                    addObject(jsonAr,"FBaseUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FSecUnitId","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber",beans.get(j).FUnitID);

                    addObject(jsonAr,"FSrcStockId","FNumber",beans.get(j).FStorageOutId);//调出仓库
                    addObject(jsonAr,"FDestStockId","FNumber",beans.get(j).FStorageInId);//调入仓库
                    addObject(jsonAr,"FSrcStockLocId","FNumber",beans.get(j).FWaveHouseOutId);//调出仓位
                    addObject(jsonAr,"FDestStockLocId","FNumber",beans.get(j).FWaveHouseInId);//调入仓位
                    jsonArray.put(jsonAr);
                    JSONArray jsonA2 = new JSONArray();
                    for (int k = 0; k < 1; k++) {
                        JSONObject jsonAr2 = new JSONObject();
                        jsonAr2.put("FBillEntry_Link_FRuleId","StkTransferApply-StkTransferDirect");
                        jsonAr2.put("FBillEntry_Link_FSTableId","0");
                        jsonAr2.put("FBillEntry_Link_FSTableName","T_STK_STKTRANSFERAPPENTRY");//return_notice_entry
                        jsonAr2.put("FBillEntry_Link_FSBillId",beans.get(j).FID);
                        jsonAr2.put("FBillEntry_Link_FSId",beans.get(j).FEntryID);
//                        jsonAr2.put("FEntity_Link_FBaseunitQtyOld",beans.get(j).FRemainInStockQty);
//                        jsonAr2.put("FEntity_Link_FBaseunitQty",beans.get(j).FRealQty);
//                        jsonAr2.put("FEntity_Link_FSalBaseQtyOld",beans.get(j).FRemainInStockQty);
//                        jsonAr2.put("FEntity_Link_FSalBaseQty",beans.get(j).FRealQty);
                        jsonA2.put(jsonAr2);
                    }
                    jsonAr.put("FBillEntry_Link",jsonA2);
                }
                inObject.put("FBillEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //盘盈的JSON拼接
    public static String JSonPYing(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//库存组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FDeptId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNoteHead",mains.get(i).FNot);
                inObject.put("F_FFF_Text",Hawk.get(Info.AutoLogin,""));
                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    jsonAr.put("FOwnerTypeId",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOwnerid","FNumber",beans.get(j).FOwnerId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    addObject(jsonAr,"FStockStatusId","FNUMBER","KCZT01_SYS");
                    jsonAr.put("FAcctQty",beans.get(j).FRemainInStockQty);//帐存数量
//                    jsonAr.put("FBaseAcctQty",beans.get(j).FRealQty);
//                    jsonAr.put("FGainQty",beans.get(j).FRemainInStockQty);//盘盈数量
                    jsonAr.put("FCountQty",beans.get(j).FRealQty);//盘点数量
//                    jsonAr.put("FBaseCountQty",beans.get(j).FRealQty);
//                    jsonAr.put("FBaseGainQty",beans.get(j).FRemainInStockQty);
                    addObject(jsonAr,"FKeeperId","FNumber",mains.get(i).FStockOrgId);//默认为库存组织
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
//                    addObject(jsonAr,"FKeeperId","FNUMBER",mains.get(0).FStockOrgId);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FBillEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }
    //盘亏的JSON拼接
    public static String JSonPKui(List<T_main> mains, Map<String,List<T_Detail>> map){
        JSONObject outOjbect = new JSONObject();
        try {
//            outOjbect.put("IsEntryBatchFill",true);
            JSONArray outOfModel = new JSONArray();//Model
            for (int i = 0; i < mains.size(); i++) {
                JSONObject inObject = new JSONObject();
                inObject.put("FBillNo","");
                addObject(inObject,"FBillTypeID","FNUMBER",mains.get(i).FBillTypeID);
                addObject(inObject,"FStockOrgId","FNUMBER",mains.get(i).FStockOrgId);//库存组织
                addObject(inObject,"FStockerId","FNUMBER",mains.get(i).FStockerNumber);
                inObject.put("FOwnerTypeIdHead",mains.get(i).FOwnerTypeIdHead);
                addObject(inObject,"FOwnerIdHead","FNUMBER",mains.get(i).FOwnerIdHead);
                addObject(inObject,"FDeptId","FNumber",mains.get(i).FDepartmentNumber);
                inObject.put("FDate",mains.get(i).FDate);
                inObject.put("FNoteHead",mains.get(i).FNot);
                inObject.put("F_FFF_Text",Hawk.get(Info.AutoLogin,""));
                JSONArray jsonArray = new JSONArray();
                List<T_Detail> beans = map.get(mains.get(i).FOrderId+"");
                for (int j = 0; j < beans.size(); j++) {
                    JSONObject jsonAr = new JSONObject();
                    addObject(jsonAr,"FMaterialId","FNumber",beans.get(j).FMaterialId);
                    jsonAr.put("FOwnerTypeId",mains.get(i).FOwnerTypeIdHead);
                    addObject(jsonAr,"FOwnerid","FNumber",beans.get(j).FOwnerId);
                    JSONObject jsonfz = new JSONObject();
                    addObject(jsonfz,"FAUXPROPID__FF100001","FNumber",beans.get(j).ActualModel);
                    addObject(jsonfz,"FAUXPROPID__FF100002","FNumber",beans.get(j).AuxSign);
                    jsonAr.put("FAuxpropId",jsonfz);
                    addObject(jsonAr,"FStockStatusId","FNUMBER","KCZT01_SYS");
                    addObject(jsonAr,"FLot","FNumber",beans.get(j).FBatch);
                    addObject(jsonAr,"FUnitID","FNumber",beans.get(j).FUnitID);
//                    addObject(jsonAr,"FExtAuxUnitId","FNumber","m3");
                    jsonAr.put("FCountQty",beans.get(j).FRealQty);//盘点数量
                    addObject(jsonAr,"FStockId","FNumber",beans.get(j).FStorageId);
                    jsonAr.put("FAcctQty",beans.get(j).FQuantity);//帐存数量
//                    jsonAr.put("FLossQty",beans.get(j).FRemainInStockQty);//盘亏数量
//                    jsonAr.put("FExtSecAcctQty",beans.get(j).FQuantity);//帐存数量（辅单位
//                    jsonAr.put("FExtAuxUnitQty",beans.get(j).FRealQty);//帐存数量（辅单位
//                    jsonAr.put("FExtSecLOSSQty",beans.get(j).FRemainInStockQty);//盘亏数量
                    addObject(jsonAr,"FKeeperId","FNumber",mains.get(i).FStockOrgId);//默认为库存组织
//                    jsonAr.put("FKeeperTypeId",mains.get(0).FOwnerTypeIdHead);
                    jsonArray.put(jsonAr);
                }
                inObject.put("FBillEntry",jsonArray);
                outOfModel.put(inObject);
            }


            outOjbect.put("Model",outOfModel);
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return outOjbect.toString();
    }

    //调拨单的JSON拼接（提交和审核的json）
    public static String JSonDB_Check(String id){
                JSONObject inObject = new JSONObject();
        try {
//                String[] strings = new String[1];
//                strings[0]=id;
                inObject.put("CreateOrgId",0);
                JSONArray array = new JSONArray();//Model
                array.put(id);
                inObject.put("Numbers",array);
                inObject.put("Ids","");
//                inObject.put("InterationFlags","");

        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return inObject.toString();
    }

    //调拨单的JSON拼接（提交和审核的json）
    public static String JSon_Submit_Audit(List<String> id){
        JSONObject inObject = new JSONObject();
        try {
//                String[] strings = new String[1];
//                strings[0]=id;
            inObject.put("CreateOrgId",0);
            JSONArray array = new JSONArray();//Model
            for (String string:id) {
                array.put(string);
            }
            inObject.put("Numbers",array);
            inObject.put("Ids","");
//                inObject.put("InterationFlags","");

        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return inObject.toString();
    }

    //调拨单的JSON拼接（提交和审核的json）
    public static String JSon_View(List<String> id,String org,String ids){
        JSONObject inObject = new JSONObject();
        try {
//                String[] strings = new String[1];
//                strings[0]=id;
//            inObject.put("CreateOrgId",org);
            JSONArray array = new JSONArray();//Model
            for (String string:id) {
                array.put(string);
            }
//            inObject.put("Numbers",array);
            inObject.put("Number",org);
//            inObject.put("Ids",ids);
//                inObject.put("InterationFlags","");

        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("解析错误："+e.toString());
        }
        return inObject.toString();
    }


    //创建对象中的对象
    private static void addObject(JSONObject jsonObject,String key,String keyInKey,String value) throws JSONException {
        JSONObject jsonO = new JSONObject();
        jsonO.put(keyInKey,value);
        jsonObject.put(key,jsonO);
    }
}

