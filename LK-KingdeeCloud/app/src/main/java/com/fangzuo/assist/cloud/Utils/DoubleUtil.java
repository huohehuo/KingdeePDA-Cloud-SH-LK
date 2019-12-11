package com.fangzuo.assist.cloud.Utils;

import java.math.BigDecimal;

public class DoubleUtil {

    /**
     * 对double数据进行取精度.
     * @param value  double数据.
     * @param scale  精度位数(保留的小数位数).
     * @param roundingMode  精度取值方式.
     *  ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。

    2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。

    3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。

    4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。

    5、 HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。

    6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。

    7、 HAIL_EVEN：银行家舍入法。
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale,
                               int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    //保留4为小数四舍五入,new BigDecimal(String)避免了0.745四舍五入变成0.74，不用new BigDecimal(double)
    public static double Cut4(String value) {
        if (null==value || "".equals(value)){
            value = "0";
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
    //保留4为小数四舍五入,new BigDecimal(String)避免了0.745四舍五入变成0.74，不用new BigDecimal(double)
    public static double CutTo0(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
    //舍掉小数取整:Math.floor(3.5)=3
    //四舍五入取整:Math.rint(3.5)=4
    //进位取整:Math.ceil(3.1)=4

    //切除小数点后面的所有数据
   public static String Cut0(String value) {
        if (value==null||"".equals(value)){
            return "0";
        }
        String str=Math.rint(Double.parseDouble(value))+"";
        return str.substring(0,value.lastIndexOf("."));
//       Lg.e("TEST",DoubleUtil.Cut0("0.50"));0
//       Lg.e("TEST",DoubleUtil.Cut0("1.50"));2
//       Lg.e("TEST",DoubleUtil.Cut0("1.40"));1
//       Lg.e("TEST",DoubleUtil.Cut0("0.45"));0
    }


    /**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
    public static double sum(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }


    /**
     * double 相减
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }

    /**
     * double 乘法
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }
    public static double mulX10(String d1){
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal("10");
        return bd1.multiply(bd2).doubleValue();
    }
    /**
     * double 除法
     * @param d1
     * @param d2
     * @param scale 四舍五入 小数点位数
     * @return
     */
    public static double div(double d1,double d2,int scale){
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理
        if (d2<=0)return 0;
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide
                (bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    //除以10
    public static double divC10(String d1,int scale){
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理

        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal("10");
        return bd1.divide
                (bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
