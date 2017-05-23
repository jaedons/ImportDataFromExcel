package com.fjhw.util;

public class StringUtil {
	public static String trim(String str){
		if(str==null) return "" ;
		return str.trim();
	}
	public static String removeEnter(String str){
		str = trim(str);
		str = str.replace("\n", "").replace("\t","").replace("\r","");
		return str;
	}
	/**È¥³ý¿Õ¸ñ*/
	public static String removeBlank(String str){
		str = trim(str);
		return str.replace(" ", "");
	}
}
