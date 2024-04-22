package edu.uestc.imdds.Utils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Utils {

    public static <T> List<T> page(List<T> list, int pageIndex, int pageSize) {
        int fromIndex = (pageIndex-1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, list.size());
        return list.subList(fromIndex, toIndex);
    }


    public static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
    public static String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());
    }
    //经度统一到数据库的格式（统一加上180）：
    public static String formatLongitude(String longitude){
        double tmp1 = Double.parseDouble(longitude) + 180.000000;
        String tmp2 = String.valueOf(tmp1);
        return stringFormat(tmp2);
    }
    //纬度统一到数据库的格式（统一加上90）：
    public static String formatLatitude(String latitude){
        double tmp1 = Double.parseDouble(latitude) + 90.00000;
        String tmp2 = String.valueOf(tmp1);
        return stringFormat(tmp2);
    }
    //统一字符串数字的长度位数格式
    public static String stringFormat(String number){
        return new DecimalFormat("000.00000").format(Double.parseDouble(number)).toString();
    }

    //修正经纬度信息，以便正常显示
    public static List<Map<String, Object>> correctLatLng(List<Map<String, Object>> datalist){
        for(int i = 0;i<datalist.size();i++){

            String topLeftLatitude = (String) datalist.get(i).get("topLeftLatitude");
            datalist.get(i).put("topLeftLatitude",correctLatitude(topLeftLatitude));

            String topLeftLongitude = (String) datalist.get(i).get("topLeftLongitude");
            datalist.get(i).put("topLeftLongitude",correctLongitude(topLeftLongitude));

            String topRightLatitude = (String) datalist.get(i).get("topRightLatitude");
            datalist.get(i).put("topRightLatitude",correctLatitude(topRightLatitude));

            String topRightLongitude = (String) datalist.get(i).get("topRightLongitude");
            datalist.get(i).put("topRightLongitude",correctLongitude(topRightLongitude));

            String bottomLeftLatitude = (String) datalist.get(i).get("bottomLeftLatitude");
            datalist.get(i).put("bottomLeftLatitude",correctLatitude(bottomLeftLatitude));

            String bottomLeftLongitude = (String) datalist.get(i).get("bottomLeftLongitude");
            datalist.get(i).put("bottomLeftLongitude",correctLongitude(bottomLeftLongitude));

            String bottomRightLatitude = (String) datalist.get(i).get("bottomRightLatitude");
            datalist.get(i).put("bottomRightLatitude",correctLatitude(bottomRightLatitude));

            String bottomRightLongitude = (String) datalist.get(i).get("bottomRightLongitude");
            datalist.get(i).put("bottomRightLongitude",correctLongitude(bottomRightLongitude));

        }
        return datalist;
    }
    //纬度-90
    public static String correctLatitude(String latitude){
        double tmp = Double.parseDouble(latitude)-90.00000;
        return stringFormat(String.valueOf(tmp));
    }
    //经度-180
    public static String correctLongitude(String longitude){
        double tmp = Double.parseDouble(longitude)-180.00000;
        return stringFormat(String.valueOf(tmp));
    }

    //去除文件的后缀名
    public static String removeExtension(String fname) {
        int pos = fname.lastIndexOf('.');
        if(pos > -1)
            return fname.substring(0, pos);
        else
            return fname;
    }


    //获取当前时间戳
    public static String getTimeMillis(){
       return String.valueOf(System.currentTimeMillis());
    }


    //将一个map转换为bean对象
    public static Object mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
        Object obj = clazz.newInstance();
        if(map != null && map.size() > 0) {
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();       //属性名
                if( propertyName.equals("id")) continue;
                Object value = entry.getValue();
                String setMethodName = "set"
                        + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);
                if(field==null)
                    continue;
                Class<?> fieldTypeClass = field.getType();
                value = convertValType(value, fieldTypeClass);
                try{
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                }catch(NoSuchMethodException e){
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
    /**
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
     *
     * @param clazz 指定的class
     * @param fieldName 字段名称
     * @return Field对象
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if( Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field []declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if(superClass != null) {// 简单的递归一下
            return getClassField(superClass, fieldName);
        }
        return null;
    }

     /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     *
     * @param value Object对象值
     * @param fieldTypeClass 属性的类型
     * @return 转换后的值
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;
        if(Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if(Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if(Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if(Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }


    /**
     * @param str 需要加密的字符串
     * @return String 加密后的字符串
     * 用MD5对string进行加密
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
