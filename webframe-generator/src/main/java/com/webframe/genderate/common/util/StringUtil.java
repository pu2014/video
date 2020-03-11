package com.webframe.genderate.common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 */
public class StringUtil {

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().equals("") || obj.equals("null") || obj.equals("NULL") ? true : false;
	}

	
	/**  基本数据类型的转换    ***/
	
	/**
	 * 将空类型转为字符串型
	 * 
	 * @param obj
	 * @return
	 */
	public static String null2String(Object obj) {
		return obj == null || obj.equals("null") || obj.equals("NULL") ? "" : obj.toString();
	}

	
	/**
	 * 转为Long类型，空值返回null
	 * 
	 * @param obj
	 * @return
	 */
	public static Long strToLong(Object obj) {
		return isEmpty(obj) ? null : Long.parseLong(obj.toString());
	}

	/**
	 * 转为Int类型
	 * 
	 * @param str
	 * @return
	 */
	public static Integer strToInt(Object obj) {
		return isEmpty(obj) ? null : Integer.parseInt(obj.toString());
	}

	/**
	 * 转为Float类型
	 * 
	 * @param str
	 * @return
	 */
	public static Float strToFloat(Object obj) {
		return isEmpty(obj) ? null : Float.parseFloat(obj.toString());
	}

	/**
	 * 转为Double类型
	 * 
	 * @param str
	 * @return
	 */
	public static Double strToDouble(Object obj) {
		return isEmpty(obj) ? null : Double.parseDouble(obj.toString());
	}
	
	public static Boolean strToBoolean(Object obj){
		return isEmpty(obj) ? false : Boolean.parseBoolean(obj.toString());
	}
	
	public static Character strToChar(Object obj){
		return isEmpty(obj) ? ' ' : (Character)obj;
	}
	
	public static Byte strToByte(Object obj){
		return isEmpty(obj) ? 0 :Byte.parseByte(obj.toString());
	}
	
	public static Short strToShort(Object obj){
		return isEmpty(obj) ? 0 : Short.parseShort(obj.toString());
	}

	/**
	 * 转为long类型
	 * 
	 * @param str
	 * @return
	 */
	public static long str2Long(Object obj) {
		return isEmpty(obj) ? 0 : Long.parseLong(obj.toString());
	}

	/**
	 * 转为int类型
	 * 
	 * @param str
	 * @return
	 */
	public static int str2Int(Object obj) {
		return isEmpty(obj) ? 0 : Integer.parseInt(obj.toString());
	}

	/**
	 * 转为float类型
	 * 
	 * @param str
	 * @return
	 */
	public static float str2Float(Object obj) {
		return isEmpty(obj) ? 0 : Float.parseFloat(obj.toString());
	}

	/**
	 * 转为double类型
	 * 
	 * @param str
	 * @return
	 */
	public static double str2Double(Object obj) {
		return isEmpty(obj) ? 0 : Double.parseDouble(obj.toString());
	}
	
	public static boolean str2Boolean(Object obj){
		return isEmpty(obj) ? false : Boolean.parseBoolean(obj.toString());
	}
	
	public static char str2Char(Object obj){
		return isEmpty(obj) ? ' ' : (Character)obj;
	}
	
	public static byte str2Byte(Object obj){
		return isEmpty(obj) ? 0 :Byte.parseByte(obj.toString());
	}
	
	public static short str2Short(Object obj){
		return isEmpty(obj) ? 0 : Short.parseShort(obj.toString());
	}

	
	/**
	 * 数组转为 ('a','b')，空返回('') 
	 * 
	 * @param names
	 * @return
	 */
	public static String getStrsplit(String[] names) {
		String rs = "('')";
		if (names != null && !"".equals(names)) {
			StringBuilder result = new StringBuilder("(");
			for (String string : names) {
				result.append("'" + string.toString() + "',");
			}
			rs = result.substring(0, result.length() - 1) + ")";
		}
		return rs;
	}

	/**
	 * 数组链表转为 ('a','b') ，空返回('')
	 * 
	 * @param names
	 * @return
	 */
	public static String getStrsplit(List<?> names) {
		String rs = "('')";
		if (names != null && !names.isEmpty()) {
			StringBuilder result = new StringBuilder("(");
			for (Object o : names) {
				result.append("'" + o.toString() + "',");
			}
			rs = result.substring(0, result.length() - 1) + ")";
		}
		return rs;
	}

	public static StringBuffer string2Json(String s) {       
        StringBuffer sb = new StringBuffer ();       
        for (int i=0; i<s.length(); i++) {     
            char c = s.charAt(i);       
            switch (c) {       
            case '\"':       
                sb.append("\\\"");       
                break;       
            case '\\':       
                sb.append("\\\\");       
                break;       
            case '/':       
                sb.append("\\/");       
                break;       
            case '\b':       
                sb.append("\\b");       
                break;       
            case '\f':       
                sb.append("\\f");       
                break;       
            case '\n':       
                sb.append("\\n");       
                break;       
            case '\r':       
                sb.append("\\r");       
                break;       
            case '\t':       
                sb.append("\\t");       
                break;       
            default:       
                sb.append(c);       
            }  
        }  
        return sb;       
    }
	
	
	public static String getLikeStr(String str){
		return getLikeStr(str,"");
	}
	
	public static String getLikeStr(String str,String type){
		if(type.isEmpty()){
			str = "%" + str + "%";
		}else if(type.equals("L")){
			str = "%" + str;
		}else if(type.equals("R")){
			str = str + "%";
		}
		return str;
	}
	
	
	public static String splitData(String str, String strStart, String strEnd) {  
        String tempStr;         
        tempStr = str.substring(str.indexOf(strStart), str.lastIndexOf(strEnd)); 
        tempStr = tempStr.replace(strStart, "");
        return tempStr.trim();         
    }
	
	
	public static String split(String str, String strStart, String strEnd) {  
			int si = str.indexOf(strStart);
			if(si > 0){
				int ei = str.indexOf(strEnd);
				if(ei >0){
					String t = str.substring(si+strStart.length(), ei);
			        return t.trim();  
				}
			}
	        return null;
	              
	    }
	
	
	/**
	 * 判断是否包含非法字符
	 * 
	 * @return
	 */
	public static Boolean includeUnLowChar(String str) {
		String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t|and|or";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	public static void main(String []args) {
		String str = "1234andfdafa";
		boolean result = includeUnLowChar(str);
		System.out.println(result);
	}
}
