package com.luke.web.tool;

import com.luke.web.tool.exception.AppException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luke on 2018/11/2.
 */
public class LK {
    private static Logger logger = LoggerFactory.getLogger(LK.class) ;

    public static String uuid() throws AppException{
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String vilidateCode(int i) throws AppException{
        StringBuffer sb = new StringBuffer() ;
        String tmp = Math.random()*10 +"";
        for (int j=2 ; j<tmp.length();j++){
            if(Integer.parseInt(tmp.charAt(j)+"")>0){
                return tmp.substring(j,j+i) ;
            }
        }
        return vilidateCode(i) ;
    }

    /**
     * 字符串是空
     * @param str
     * @return
     */
    public static Boolean StrIsEmpty(String str) throws AppException {
        return (str==null||str.trim().equals(""))?true:false ;
    }

    /**
     * 字符串不是空
     * @param str
     * @return
     */
    public static Boolean StrIsNotEmpty(String str) throws AppException{
        return !StrIsEmpty(str) ;
    }

    /**
     * 字符串为空返回默认值，不为空返回本身的trim
     * @param str
     * @param def
     * @return
     */
    public static String StrIsEmptyDo(String str,String def) throws AppException{
        return StrIsEmpty(str)?def:str.trim() ;
    }
    /**
     * 字符串是数字
     * @param str
     * @return
     */
    public static Boolean StrIsNum(String str)throws AppException{
        if(StrIsEmpty(str)) return false ;
        str = str.trim() ;
        if(str.startsWith(".")) return false ;
        Pattern pattern = Pattern.compile("^[\\+\\-]{0,1}[\\d]*[\\.]{0,1}[\\d]*$");
        Matcher matcher = pattern.matcher(str) ;
        return matcher.matches() ;
    }

    /**
     * 字符串是时间
     * @param str
     * @param format
     * @return
     */
    public static Boolean StrIsDate(String str,String format)throws AppException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(str);
            return true ;
        } catch (Exception e) {
            return false ;
        }
    }

    /**
     * 字符串转时间
     * @param str
     * @param format
     * @return
     */
    public static Date StrToDate(String str,String format) throws AppException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            throw AppException.create("请判断是字符串是否时间") ;
        }
    }

    /**
     * 字符串转yyyy-MM-dd
     * @param str
     * @return
     */
    public static Date StrToDate_YMD(String str)throws AppException {
        if(StrIsDate(str, "yyyy-MM-dd")){
            return StrToDate(str,"yyyy-MM-dd") ;
        }else{
            throw AppException.create("请判断是字符串是否年--yyyy-MM-dd");
        }
    }

    /**
     * 字符串转yyyy-MM-dd hh:mm:ss
     * @param str
     * @return
     */
    public static Date StrToDate_YMDHMS(String str)throws AppException {
        if(StrIsDate(str,"yyyy-MM-dd hh:mm:ss")){
            return StrToDate(str,"yyyy-MM-dd hh:mm:ss") ;
        }else{
            throw AppException.create("请判断是字符串是否年月");
        }
    }





    /**
     * 时间转字符串
     * @param date
     * @param format
     * @return
     */
    public static String DateToStr(Date date ,String format)throws AppException{
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 时间转年
     * @param date
     * @return
     */
    public static String DateToStr_Y(Date date)throws AppException{
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    /**
     * 时间转年月
     * @param date
     * @return
     */
    public static String DateToStr_YM(Date date)throws AppException{
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

    /**
     * 时间转年月日
     * @param date
     * @return
     */
    public static String DateToStr_YMD(Date date)throws AppException{
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }



    /**
     * 字符转度数格式
     * @param str
     * @return
     */
    public static String Lens(String str)throws AppException{
        if(StrIsNum(str)){
            if(Float.parseFloat(str)==0)
                return "0" ;
            DecimalFormat df = new DecimalFormat("0.00");
            Float data = Float.parseFloat(str) ;
            if(data<=0){
                return df.format(data) ;
            }else{
                return "+"+df.format(data) ;
            }
        }else{
            throw AppException.create("请正确填写数字") ;
        }
    }

    /**
     * 浮点转度数格式
     * @param data
     * @return
     */
    public static String toLensFormat(Float data) throws AppException{
        String str = data+"" ;
        return Lens(str) ;
    }

    /**
     * 名字转拼音 全拼
     *
     * @param name
     * @return String
     * @author llg
     */
    public static String NameToPingYinLong(String name) throws AppException{
        if (name == null || name.trim().equals(""))
            return "";
        String rt = "";
        HanyuPinyinOutputFormat hypyFormate = new HanyuPinyinOutputFormat();
        hypyFormate.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hypyFormate.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hypyFormate.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < name.length(); i++) {
                String[] ss = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i), hypyFormate);
                if (name.charAt(i)<128||ss == null || ss[0].length() <= 0) {
                    rt += name.charAt(i);
                    continue;
                }
                rt += ss[0].substring(0, 1).toUpperCase() + ss[0].substring(1, ss[0].length());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw AppException.create(e.getMessage()) ;
        }

        return rt;
    }

    /**
     * 名字转拼音 简拼（首字母）
     *
     * @param name
     * @return String
     * @author llg
     */
    public static String NameToPingYinShort(String name) throws AppException{
        if (name == null || name.trim().equals(""))
            return "";
        String rt = "";
        for (int i = 0; i < name.length(); i++) {
            String[] ss = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i));
            if (ss == null || ss[0].length() <= 0) {
                rt += name.charAt(i);
                continue;
            }

            rt += ss[0].substring(0, 1).toUpperCase();
        }
        if (rt.length() >= 36) {
            rt = rt.substring(0, 35);
        }

        return rt;
    }

    /**
     * md5加密（网上找的，应该靠谱）
     *
     * @param s
     * @return String
     * @author llg
     */
    public final static String MD5(String s) throws AppException{
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw AppException.create(e.getMessage()) ;
        }
    }
    /**
     * 时间转年龄
     *
     * @param borthDate
     * @return int
     * @author llg
     */
    public static int BirthdayToAge(Date borthDate)throws AppException {
        String now = DateToStr_Y(new Date());
        String borth = DateToStr_Y(borthDate);
        int rt = Integer.parseInt(now) - Integer.parseInt(borth);
        return rt;
    }

    /**
     * 时间转年龄
     *
     * @param borthDate
     * @return int
     * @author llg
     */
    public static int DateToAge(String borthDate) throws AppException{
        return BirthdayToAge(StrToDate_YMD(borthDate));
    }


    /**
     * 对象是空
     * @param obj
     * @return
     */
    public static Boolean ObjIsNull(Object obj) throws AppException{
        return obj == null?true :false ;
    }

    public static Object ObjIsNullDo(Object obj ,String def) throws AppException{
        if(obj==null)
            return def ;
        else
            return obj ;
    }

    /**
     * 对象不是空
     * @param obj
     * @return
     */
    public static boolean ObjIsNotNull(Object obj) throws AppException{
        return !ObjIsNull(obj) ;
    }

    /**
     *
     * @param list
     * @param discard       丢弃属性
     * @return
     */
    public static List<Map<String,Object>> ListObjToListMap(List<?> list,Map<String,String> discard) throws AppException{
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>(list.size()) ;
        Map<String,Object> map = null ;
        for(Object obj :list){
            map = ObjToMap(obj,discard) ;
            listMap.add(map) ;
        }
        return listMap ;
    }

    /**
     * 对象转为map
     * @param obj
     * @param discard   丢弃属性
     * @return
     */
    public static Map<String, Object> ObjToMap(Object obj,Map<String,String> discard )throws AppException {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<String, Object>(fields.length);
        boolean hasDiscard = discard!=null ;
        try {
            for (Field f : fields) {
                /**判断属性是否被丢弃*/
                if(hasDiscard){
                    if(discard.get(f.getName())!=null)
                        continue;
                }
                f.setAccessible(true);
                map.put(f.getName(), f.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ObjSuperToMap(map,obj.getClass().getSuperclass(),obj,discard);
        return map;
    }
    private static void ObjSuperToMap(Map<String,Object> map,Class clazz,Object obj,Map<String,String> discard) throws AppException{
        if(clazz.equals(Object.class))
            return ;
        else{
            Field[] fields = clazz.getDeclaredFields() ;
            try{
                boolean hasDiscard = discard!=null ;
                for(Field f: fields){
                    /**判断属性是否被丢弃*/
                    if(hasDiscard){
                        if(discard.get(f.getName())!=null)
                            continue;
                    }
                    f.setAccessible(true);
                    map.put(f.getName(),f.get(obj)) ;
                }
            }catch (Exception e){
                e.printStackTrace();
                throw AppException.create(e.getMessage()) ;
            }
            ObjSuperToMap(map, clazz.getSuperclass(), obj,discard);
        }
    }


    /**
     * 为指定的时间添加天数，可以是负数，就是前几天
     * @param time
     * @param num
     * @return
     */
    public static Date AddDay(Date time,int num) throws AppException{
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(time);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime() ;
    }

    /**
     * 对象转json，可以去除对象中的属性
     * @param obj
     * @param removes
     * @return
     * @throws AppException
     */
    public static String ObjToJsonStr(Object obj,String... removes) throws AppException{
        JsonConfig jc = new JsonConfig() ;
        jc.setExcludes(removes);
        JSONObject json = JSONObject.fromObject(obj,jc) ;
        return json.toString() ;
    }

    /**
     * json 字符串转对象
     * @param strJson
     * @param clazz
     * @param <T>
     * @return
     * @throws AppException
     */
    public static <T> T StrJson2Obj(String strJson,Class<T> clazz) throws AppException{
        JSONObject jsonObj = JSONObject.fromObject(strJson) ;
        return (T)JSONObject.toBean(jsonObj,clazz) ;
    }


    /**
     *
     * @param jexlExp 需要执行的代码
     * @param map     执行代码所需要的环境(也就是代码中的变量)
     * @return
     */
    public static Object evalStrCode(String jexlExp,Map<String,Object> map) throws AppException{
        JexlEngine jexl=new JexlEngine();
        Expression e = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for(String key:map.keySet()){
            jc.set(key, map.get(key));
        }
        if(null==e.evaluate(jc)){
            return "";
        }
        return e.evaluate(jc);
    }


}
