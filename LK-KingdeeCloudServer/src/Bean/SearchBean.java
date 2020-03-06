package Bean;

public class SearchBean {
    public static String product_for_id="product_for_id";
    public static String product_for_number="product_for_number";
    public static String product_for_barcode="product_for_barcode";
    public static String product_for_like="product_for_like";
    public String searchType;
    public String json;
    public String val1;
    public String val2;
    public String val3;
    public String val4;
    public String val5;
    public String val6;
    public String val7;
    public String val8;
    public String val9;
    public String val10;

    public SearchBean(){}
    public SearchBean(String searchType, String json){
        this.searchType=searchType;
        this.json = json;
    }

    public static class S2Product{
        public String likeOr;
        public String FIsPurchase;
        public String FIsSale;
        public String FIsInventory;
        public String FIsProduce;
        public String FIsSubContract;
        public String FIsAsset;
        public String FOrg;
        public String FVal1;
        public String FVal2;
        public String FVal3;

        public S2Product(){}

    }


}
