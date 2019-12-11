package Utils;

import java.math.BigDecimal;

public class MathUtil {
    //去掉末尾为.0的数
    public static String cutZero(String num){
        String string;
        if (num.endsWith(".0")){
//            Lg.e("有0");
//            Lg.e("去掉0",string.substring(0,string.length()-2));
            string = num.substring(0,num.length()-2);
        }else{
            string=num;
        }
        return string;
    }

    //防止强转时崩溃操作
    public static double toD(String string) {
        if (null == string) {
            return 0;
        } else if (string.equals("")) {
            return 0;
        } else {
            try {
                return Double.parseDouble(string);
            }catch (Exception e){
                return 0;
            }
        }
    }
    //防止强转时崩溃操作
    public static int toInt(String string) {
        if (null == string) {
            return 0;
        } else if (string.equals("")) {
            return 0;
        } else {
            try{
                return Integer.parseInt(string);
            }catch (Exception e){
                return 0;
            }
        }
    }
    //去掉小数点以后的数值
    public static String Cut0(String value) {
        try {
            if (value==null||"".equals(value)){
                return "0";
            }
            if (value.contains(".")){
//            String str=Math.rint(Double.parseDouble(value))+"";
                return value.substring(0,value.lastIndexOf("."));
            }else{
                return value;
            }
        }catch (Exception e){
            return "0";
        }

//       Lg.e("TEST",DoubleUtil.Cut0("0.50"));0
//       Lg.e("TEST",DoubleUtil.Cut0("1.50"));2
//       Lg.e("TEST",DoubleUtil.Cut0("1.40"));1
//       Lg.e("TEST",DoubleUtil.Cut0("0.45"));0
    }
    public static double mul(String d1,String d2){
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);
        return bd1.multiply(bd2).doubleValue();
    }

    public static double D2save4(Double d){
        return Double.parseDouble(String.format("%.4f", d));
    }
    public static double D2save5(Double d){
        return Double.parseDouble(String.format("%.5f", d));
    }
//    //四舍五入取整
//    public static double D2saveInt(Double d){
//        return Math.round(d);
//    }
    public static String D2saveInt(Double d){
        return Cut0(Math.round(d)+"");
    }
}
