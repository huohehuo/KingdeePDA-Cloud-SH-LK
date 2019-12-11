package com.fangzuo.assist.cloud.Adapter;//package com.fangzuo.assist.Adapter;
//
//import java.io.IOException;
//import java.util.List;
//
//
//import com.fangzuo.assist.Activity.IOTestActivity;
//import com.fangzuo.assist.Beans.OtherInBean;
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonWriter;
//import com.google.zxing.common.StringUtils;
//
//public class OISTypeAdapter extends TypeAdapter<List<OtherInBean.ModelBean.FEntityBean>> {
//
//    @Override
//    public void write(JsonWriter out, Object value) throws IOException {
//        out.beginObject();
//        out.name("FMATERIALID").value(((OtherInBean)value).getModel().getFEntity().get(0).getFMATERIALID().getFNumber());
//        out.name("FLOT").value(((OtherInBean)value).getModel().getFEntity().get(0).getFLOT().getFNumber());
//        out.endObject();
//    }
//
//    @Override
//    public void write(JsonWriter out, List<OtherInBean.ModelBean.FEntityBean> value) throws IOException {
//        out.beginArray();//1 流式数组序列化开始 "]"
//    for(OtherInBean.ModelBean.FEntityBean user:value){
//            writeUser(out,user);//2 流式对象序列化，"{ }"
//}
//out.endArray();//1 流式数组序列化结束 "]"
//
//    }
//    private void writeUser(JsonWriter out ,OtherInBean.ModelBean.FEntityBean bean)throws IOException{
//        out.beginObject();
//        out.name("").value(bean.getFMATERIALID().FNumber);
//        out.endObject();
//        OtherInBean.ModelBean.FEntityBean bean1=new OtherInBean.ModelBean.FEntityBean();//一个大类
//        bean1.FStockId=new OtherInBean.ModelBean.FEntityBean.FSTOCKIDBean();bean1.FStockId.FNumber=beans.get(i).FStorageId;//仓库
//        bean1.FOWNERID=new OtherInBean.ModelBean.FEntityBean.FOWNERIDBean();bean1.FOWNERID.FNumber="100";//货主
//        bean1.FKEEPERTYPEID="BD_KeeperOrg";//保管者类型
//        bean1.FKEEPERID=new OtherInBean.ModelBean.FEntityBean.FKEEPERIDBean();bean1.FKEEPERID.FNumber="100";//保管者
//        bean1.FLOT=new OtherInBean.ModelBean.FEntityBean.FLOTBean();bean1.FLOT.FNumber=beans.get(i).FBatch;//批号
//        bean1.FMATERIALID = new OtherInBean.ModelBean.FEntityBean.FMATERIALIDBean();bean1.FMATERIALID.FNumber=beans.get(i).FMaterialId;//物料编码
//
//        bean1.FQty=beans.get(i).FRealQty;         //实收数量
//        bean1.FUnitID = new OtherInBean.ModelBean.FEntityBean.FUnitIDBean();bean1.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
//        bean1.FSTOCKSTATUSID = new OtherInBean.ModelBean.FEntityBean.FSTOCKSTATUSIDBean();bean1.FSTOCKSTATUSID.FNumber="KCZT01_SYS";//库存状态
//        bean1.FOWNERTYPEID = main.FOwnerTypeIdHead;//货主类型
//        bean1.FOWNERID=new OtherInBean.ModelBean.FEntityBean.FOWNERIDBean();bean1.FOWNERID.FNumber=main.FOwnerIdHead;//货主
////                bean1.FEntryNote="明细备注";
//    }
//
//    @Override
//    public List<OtherInBean.ModelBean.FEntityBean> read(final JsonReader in) throws IOException {
//        final OtherInBean book = new OtherInBean();
//        in.beginObject();
//        while (in.hasNext()) {
//            switch (in.nextName()) {
//                case "FMATERIALID":
//                    book.setModel().setFEntity().get(0).getFMATERIALID().getFNumber();
//                    break;
//                case "title":
////                    book.setTitle(in.nextString());
//                    break;
//                case "authors":
////                    book.setAuthors(in.nextString().split(";"));
//                    break;
//            }
//        }
//        in.endObject();
//
//        return book;
//    }
//
//}
