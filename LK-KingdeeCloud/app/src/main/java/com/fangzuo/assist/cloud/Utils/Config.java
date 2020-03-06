package com.fangzuo.assist.cloud.Utils;

import com.orhanobut.hawk.Hawk;

public class Config {
    //用于崩溃信息错误信息回执的服务器地址：
//    public static final String Error_Url = "http://192.168.0.115:8083/Assist/GetLogMessage";
    public static final String Error_Url = "http://148.70.108.65:8080/LogAssist/GetLogMessage";
    public static final String Apk_Url = "http://148.70.108.65:8080/AppFile/Cloud/app-debug.apk";
    public static String getApk_Url(){//判断一期二期的apk下载地址
        if ("1".equals(Hawk.get(Config.PDA_Project_Type,"1"))){
            return "http://148.70.108.65:8080/AppFile/SHLK/app-debug.apk";
        }else{
            return "http://148.70.108.65:8080/AppFile/Cloud/p2/app-debug.apk";
        }
    }
    public static String getApk_Version(){
        if ("1".equals(Hawk.get(Config.PDA_Project_Type,"1"))){
            return "http://148.70.108.65:8080/AppFile/Cloud/version.txt";
        }else{
            return "http://148.70.108.65:8080/AppFile/Cloud/p2/version.txt";
        }
    }
    public static final String Apk_Version = "http://148.70.108.65:8080/AppFile/Cloud/version.txt";
    public static String Company="通用Cloud版";
    public static String User_Permit="User_Permit";
    public static String DataBase="DataBase";
    public static String SaveTime="SaveTime";//用于保存使用截止日期
    public static String Key="01235679";//用于保存使用截止日期（需要web端的key与之相同,并且不能倒序，只能递增的数字）
    public static String PDA_IMIE="PDA_IMIE";//用于保存注册码(注册文件最终显示的注册码)
    public static String PDA_Language="PDA_Language";//用于保存语言
    public static String PDA_RegisterCode="PDA_RegisterCode";//用于保存注册码
    public static String PDA_MsgAndIMIE="PDA_MsgAndIMIE";//用于保存手机IMIE码
    public static String PDA_RegisterMaxNum="PDA_RegisterMaxNum";//用于保存注册码
    public static String PDA_Project_Type="PDA_Project_Type";//项目一期二期区分
    public static final String Jingji_Max="Jingji_Max";//保存径级的最大值（在通用设置里面可以自由设置大小）
    public static final String Fragment_Tag="Fragment_Tag";//用于重定位最后退出时的菜单页面


    public static final String Cloud_Url = "http://sanger.gnway.cc:8090/K3Cloud/";
    public static final String Cloud_ID = "DataBaseID";
    //回单的接口key
    public static final String C_Draft =        "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Draft.common.kdsvc";//暂存
    public static final String C_Save =         "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc";//保存
    public static final String C_BatcnSave =    "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.BatchSave.common.kdsvc";//批量保存
    public static final String C_View =         "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.View.common.kdsvc";
    public static final String C_Submit =       "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Submit.common.kdsvc";
    public static final String C_Audit =        "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc";
    public static final String C_UnAudit =      "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.UnAudit.common.kdsvc";
    public static final String C_StatusConvert ="Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.StatusConvert.common.kdsvc";
    public static final String C_Login =        "Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";
    public static final String C_Delete =        "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Delete.common.kdsvc";


    public static final String DATABASESETTING = "master";
    public static final String OBJ_BLUETOOTH="key_Bluetooth_object";
    public static final String PDA = "PDA";
    public static final String PrintNum = "PrintNum";
    public static final String[] PDA_Type = {"请选择设备型号","G02A设备", "8000设备", "5000设备","M60","手机端"};

    //用于接口回调的判断------------------------------------------
    public static final String IO_type_Test="IO_type_Test";
    public static final String IO_type_TestDataBase="IO_type_TestDataBase";
    public static final String IO_type_SetProp="IO_type_SetProp";
    public static final String IO_type_connectToSQL="IO_type_connectToSQL";


    //用于Presenter下载表的type区分
    public static final int Data_Bibie           =1;    //币别表
    public static final int Data_Department      =2;    //部门表
    public static final int Data_Employee        =3;    //职员表
    public static final int Data_WaveHouse       =4;    //仓位表
    public static final int Data_InstorageNums   =5;    //库存表
    public static final int Data_Storage         =6;    //仓库表
    public static final int Data_Unit            =7;    //单位表
    public static final int Data_BarCodes        =8;    //条码表":
    public static final int Data_Suppliers       =9;    //供应商表"
    public static final int Data_PayTypes        =10;   //结算方式表
    public static final int Data_Product         =11;   //商品资料表
    public static final int Data_User            =12;   //用户信息表
    public static final int Data_Client          =13;   //客户信息表
    public static final int Data_GoodsDepartments=14;   //交货单位"
    public static final int Data_PurchaseMethod  =15;   //销售/采购方式表
    public static final int Data_yuandanType     =16;   //源单类型"
    public static final int Data_Wanglaikemu     =17;   //往来科目"
    public static final int Data_PriceMethods    =18;   //价格政策"
    public static final int Data_InStoreType     =19;   //入库类型"
    public static final int Data_Company         =20;   //公司信息表
    public static final int Data_Product_Type    =21;   //物料类别


    public static final String Text_Log    ="Text_Log1";   //物料类别

    public static final int PushDownMTActivity             =10001;
    public static final int PushDownPOActivity             =10002;
    public static final int PushDownSNActivity             =10003;
    public static final int ShouLiaoTongZhiActivity        =10004;
    public static final int OutsourcingOrdersISActivity    =10005;
    public static final int OutsourcingOrdersOSActivity    =10006;
    public static final int ProducePushInStoreActivity     =10007;
    public static final int ShengchanrenwudanxiatuilingliaoActivity =10008;
    public static final int CGDDPDSLTZDActivity                     =10009;
    public static final int XSDDPDFLTZDActivity                     =10010;
    public static final int SCRWDPDSCHBDActivity                    =10011;
    public static final int HBDPDCPRKActivity                       =10012;
    public static final int OutCheckGoodsActivity                   =10013;
    public static final int FHTZDDBActivity                         =10014;
    public static final int PurchaseInStorageActivity               =10016;
    public static final int ProductInStorageActivity                =10017;
//    public static final int OtherInStoreActivity                    =10018;
    public static final int SoldOutActivity                         =10019;
    public static final int ProduceAndGetActivity                   =10020;
//    public static final int OtherOutStoreActivity                   =10021;
//    public static final int PurchaseOrderActivity                   =10022;
//    public static final int SaleOrderActivity                       =10023;
//    public static final int PDActivity                              =10024;


    public static final String Lock                              ="Lock";//用于判断是否锁定表头的string
    public static final String OrderNo                              ="OrderNo";//用于锁定表头业务单号的key
    public static final String Note                              ="Note";//用于锁定表头备注的key
    public static final String Supplier                              ="Supplier";//用于锁定表头备注的key
    public static final String Client                              ="Client";//用于锁定表头备注的key
    public static final String SupplierHz                              ="SupplierHz";//用于锁定表头备注的key
    public static final String SupplierHzDetail                              ="SupplierHzDetail";//用于锁定表头备注的key
    public static final String ClientHz                              ="ClientHz";//用于锁定表头备注的key
    public static final String ClientHzDetail                              ="ClientHzDetail";//用于锁定表头备注的key

    public static final int OutsourcingInActivity                   =20000;//外购入库单

    public static final int PurchaseInStoreActivity                 =10025;//采购入库
    public static final int ProductInStoreActivity                  =10026;//产品入库
    public static final int ProductGetActivity                      =10027;//生产领料
    public static final int SaleOutActivity                         =10028;//销售出库
    public static final int OtherInStoreActivity                    =10029;//其他入库
    public static final int OtherOutStoreActivity                   =10030;//其他出库
    public static final int SaleOrderActivity                       =10031;//销售订单
    public static final int PurchaseOrderActivity                   =10032;//采购订单
    public static final int PdCgOrder2WgrkActivity                  =10033;//采购订单下推外购入库单
    public static final int PdCgOrder2WwrkActivity                  =1003302;//采购订单下推委外入库单
    public static final int PdSaleOrder2SaleOutActivity             =10034;//销售订单下推销售出库单
    public static final int PdSaleOrder2SaleOut4BoxActivity         =100342;//销售订单下推销售出库单
    public static final int PdSaleOrder2SaleOut2Activity            =100341;//VMI销售订单下推销售出库单
    public static final int PdSaleOrder2SaleBackActivity            =10035;//销售订单下推销售退货单
    public static final int PdSaleOut2SaleBackActivity              =10036;//销售出库单下推销售退货单
    public static final int PdSendMsg2SaleOutActivity               =10037;//发货通知单下推销售出库单
    public static final int PdBackMsg2SaleBackActivity              =10038;//退货通知单下推销售退货单
    public static final int Db2FDinActivity                         =10039;//调拨申请单下推分布式调入单
    public static final int Db2FDoutActivity                        =10040;//调拨申请单下推分布式调出单
    public static final int PDActivity                              =10041;//盘点单
    public static final int DBActivity                              =10042;//组织内调拨单
    public static final int DBCopy2P2Activity                       =100422;//组织内调拨单  复制与一期20191121
    public static final int DB2Activity                             =100421;//跨组织调拨单
    public static final int DgInActivity                            =10044;//到柜入库
    public static final int SimpleInActivity                        =10045;//简单生产入库
    public static final int TbGetActivity                           =10046;//挑板领料1
    public static final int TbGet2Activity                          =100462;//挑板领料2
    public static final int TbGet3Activity                          =100463;//挑板领料3
    public static final int TbInActivity                            =10047;//挑板入库1
    public static final int TbIn2Activity                           =100472;//挑板入库2
    public static final int TbIn3Activity                           =100473;//挑板入库3
    public static final int GbGetActivity                           =10048;//改板领料
    public static final int GbInActivity                            =10049;//改板入库
    public static final int DcOutActivity                           =10050;//代存出库
    public static final int DcInActivity                            =10051;//代存入库
    public static final int DhInActivity                            =10052;//到货入库1
    public static final int DhIn2Activity                           =100521;//到货入库2
    public static final int YbOutActivity                           =10053;//样板出库
    public static final int HwIn3Activity                           =10054;//第三方货物入库
    public static final int HwOut3Activity                          =10055;//第三方货物出库
    public static final int PYingActivity                           =10056;//盘盈入库
    public static final int PkuiActivity                            =10057;//盘亏入库
    public static final int PkuiVMIActivity                         =10058;//VMI盘亏入库
    public static final int PdDbApply2DBActivity                    =10059;//调拨申请单下推直接调拨单
    public static final int PdDbApply2DB4VMIActivity                =10060;//VMI调拨申请单下推直接调拨单
    public static final int PdOutApply2OtherOutActivity             =10061;//出库申请单下推其他出库单1
    public static final int PrintHistoryActivity                    =100612;//条码补打
    public static final int PrintBeforeDataActivity                 =100613;//期初条码补打
    public static final int ScanProductActivity                     =100614;//扫一扫
    public static final int P1PdCgrk2ProductGetActivity             =1006302;//采购入库单下推简单生产领料（一期）
    public static final int P1PdProductGet2CprkActivity             =1006303;//水板采购/生产领料单下推产品入库单（一期）整单装箱
    public static final int P1PdProductGet2Cprk2Activity            =1006304;//码拍入窑/生产领料单下推产品入库单（一期）重新装箱
    public static final int PrintBoxCode4P1Activity                 =10075;//箱码补打（一期）
    public static final int SplitBoxP1Activity                      =10076;//拆箱（一期）
    public static final int ProductGet4BoxActivity                  =1007102;//码拍领料/生产领料单
    public static final int DB4P1BoxActivity                        =10077;//箱码调拨单
    public static final int BoxReBoxP1Activity                      =10078;//混包追加
    public static final int BoxReAddP1Activity                      =10079;//混包新增
    public static final int FLInStoreP1Activity                     =10080;//方料入库
    public static final int BatchChangeLv1                          =10081;//方料入库
    public static final int ChangeGetActivity                       =10046001;//调整领料(整包)---挑板领料1
    public static final int ChangeInActivity                        =10047001;//调整入库(整包)---挑板入库1
    public static final int LvChangeLv1                             =10082;//方料入库
    public static final int ChangeLvGetActivity                     =10046002;//调整领料(整包)---挑板领料1
    public static final int ChangeLvInActivity                      =10047002;//调整入库(整包)---挑板入库1------------------
    public static final int ModelChangeLv1                          =10083;//方料入库
    public static final int ChangeModelGetActivity                  =10046003;//调整领料(整包)---挑板领料1
    public static final int ChangeModelInActivity                   =10047003;//调整入库(整包)---挑板入库1
    public static final int DBClientActivity                        =10042001;//调拨(客户在途)--组织内调拨单
    public static final int DBStorageActivity                       =10042002;//调拨(在途仓库)--组织内调拨单
    public static final int SplitBoxLv1                             =10084;//拆包理货
    public static final int SplitBoxGetActivity                     =10046004;//拆包领料(整包)---挑板领料1
    public static final int SplitBoxInActivity                      =10047004;//拆包入库(整包)---挑板入库1
    public static final int SplitBoxHunInActivity                   =10079005;//拆包入库(混包)--混包新增
    public static final int SplitBoxDiGetActivity                   =100710204;//拆包领料(混包)--码拍领料/生产领料单
    public static final int ZbChejianLv1                            =10085;//纵刨车间
    public static final int ZbCheJianInActivity                     =10047005;//整包入库---挑板入库1
    public static final int ZbCheJianHunInActivity                  =10079001;//混包入库--混包新增
    public static final int ZbCheJianDiGetActivity                  =100710201;//底领料(混包)--码拍领料/生产领料单
    public static final int ZbCheJianDiZGetActivity                 =10046005;//底领料(整包)---挑板领料1
    public static final int Bg1ChejianLv1                           =10086;//刨光一车间
    public static final int Bg1CheJianInActivity                    =10047006;//整包入库---挑板入库1
    public static final int Bg1CheJianHunInActivity                 =10079002;//混包入库--混包新增
    public static final int Bg1CheJianDiGetActivity                 =100710202;//底领料--码拍领料/生产领料单
    public static final int Bg2ChejianLv1                           =10087;//刨光二车间
    public static final int Bg2CheJianInActivity                    =10047007;//整包入库---挑板入库1
    public static final int Bg2CheJianHunInActivity                 =10079003;//混包入库--混包新增
    public static final int Bg2CheJianDiGetActivity                 =100710203;//底领料--码拍领料/生产领料单
    public static final int CpWgInLv1                               =10088;//成品外购入库
    public static final int CpWgInActivity                          =10047008;//外购入库(整包)---挑板入库1
    public static final int CpWgHunInActivity                       =10079004;//外购入库(混包)--混包新增

    public static final int Tb1HunInActivity                       =10079009;//挑板入库1(混包)--混包新增
    public static final int Tb2HunInActivity                       =10079010;//挑板入库2(混包)--混包新增
    public static final int Tb3HunInActivity                       =10079011;//挑板入库3(混包)--混包新增
    public static final int Tb1DiGetActivity                       =100710205;//挑板领料1(混包)--码拍领料/生产领料单
    public static final int Tb2DiGetActivity                       =100710206;//挑板领料2(混包)--码拍领料/生产领料单
    public static final int Tb3DiGetActivity                       =100710207;//挑板领料3(混包)--码拍领料/生产领料单
    public static final int GbDiGetActivity                        =100710208;//改板领料(混包)---码拍领料/生产领料单
    public static final int GbHunInActivity                        =10079006;//改板入库(混包)--混包新增
    public static final int ZbInActivity                            =10047009;//一级选择--挑板入库1
    public static final int ZbIn1Activity                            =10047010;//简单生产入库--挑板入库1
    public static final int ZbIn2Activity                            =10047011;//简单生产入库--挑板入库1
    public static final int ZbIn3Activity                            =10047012;//简单生产入库--挑板入库1
    public static final int ZbIn4Activity                            =10047013;//简单生产入库--挑板入库1
    public static final int ZbIn5Activity                            =10047014;//简单生产入库--挑板入库1
    public static final int ZbGetActivity                          =10046006;//一级选择--挑板领料1
    public static final int ZbGet1Activity                          =10046007;//简单生产领料--挑板领料1
    public static final int ZbGet2Activity                          =10046008;//简单生产领料--挑板领料1
    public static final int ZbGet3Activity                          =10046009;//简单生产领料--挑板领料1
    public static final int ZbGet4Activity                          =10046010;//简单生产领料--挑板领料1
    public static final int ZbGet5Activity                          =10046011;//简单生产领料--挑板领料1



    /*---------------------------------------------------二期单据----------------------------------------*/
    public static final int P2ProductionInStoreActivity             =10062;//简单生产入库（二期）
    public static final int P2ProductionInStore2Activity            =1006202;//简单生产入库（二期）
    public static final int P2PdCgrk2ProductGetActivity             =10063;//采购入库单下推简单生产领料（二期）
    public static final int ProductGet4P2Activity                   =1002702;//原木领料
    public static final int ProductGet4P24PihaoActivity             =1002704;//原木领料
    public static final int ProductInStore4P2Activity               =1002602;//水板板材入库--水板入库
    public static final int ProductInStore4P2MpActivity             =1002603;//水板板材码拍(copy水板板材入库)
    public static final int WortInStore4P2Activity                  =10064;//刨光干板入库
    public static final int PrintBoxCodeActivity                    =10065;//箱码补打
    public static final int DB4P2Activity                           =10066;//板材调拨-水板调拨
    public static final int DryingInStoreActivity                   =10067;//烘干板入库
    public static final int WgDryingInStoreActivity                 =1006702;//外购烘干板入库
    public static final int ShuiBanGetActivity                      =10068;//水板拆托出库（水板领料)
    public static final int ShuiBanGet2Activity                     =1006803;//烘干板领料（水板拆托出库)
    public static final int OutKilnGetActivity                      =1006802;//出窑领料(copy水板拆托出库)
    public static final int ShuiBanDB4P2Activity                    =10069;//水板调拨
    public static final int DBInKiln4P2Activity                     =1006902;//调拨入窑(copy水板调拨)
    public static final int SplitBoxP2Activity                      =10070;//拆箱
    public static final int DryingGetActivity                       =10071;//刨光板码托
    //成品车间
    public static final int PrintHistory4P2Activity                 =10072;//条码补打
    public static final int CpGetActivityDlg                        =10073;//成品加工领料(用于弹框)
    public static final int CpInActivityDlg                         =10074;//成品加工入库(用于弹框)
    public static final int YbOut4P2Activity                        =1005302;//样板出库(copy一期样板出库)
    public static final int WorkOrgGet4P2Activity                   =1002703;//业务组织领料(copy一期简单生产领料)
    public static final int SupplierGet4P2Activity                  =1005502;//供应商领料(copy一期第三方货物出库)
    public static final int WorkOrgIn4P2Activity                    =1002604;//业务组织入库(copy一期简单生产入库)
    public static final int SupplierIn4P2Activity                   =1005402;//供应商入库(copy一期第三方货物入库)
    public static final int ProductGet4BoxP2Activity                =1007103;//码拍领料/生产领料单
    public static final int BoxReAddP2Activity                      =10079012;//混包新增


    public static boolean isInStoreOrder(int activity){
        boolean res=false;
//        switch (activity){
//            case ChangeInActivity:
//
//                res=true;
//                break;
//        }

        return res;
    }
}
