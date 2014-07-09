package com.x.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理类
 * 
 */
public class StringUtil {
	
    /**
     * 将str将多个分隔符进行切分，
     * 
     * 示例：StringTokenizerUtils.split("1,2;3 4"," ,;");
     * 返回: ["1","2","3","4"]
     * 
     * @param str
     * @param seperators
     * @return
     */
	public static String[] split(String str,String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str,seperators);
		List<Object> result = new ArrayList<Object>();
		
		while(tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[])result.toArray(new String[result.size()]);
	}
	
	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(String... strings) {
		for (String t : strings) {
			if (isEmpty(t)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String... strings) {
		for (String t : strings) {
			if (isEmpty(t)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 非空字符串
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		if (!isEmpty(value)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验为int型
	 * 
	 * @param args
	 * @return true/flase
	 */
	public static boolean isInt(String... args) {
		for (String a : args) {
			try {
				Integer.valueOf(a);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验非int型
	 * 
	 * @param args
	 * @return true/flase
	 */
	public static boolean isNotInt(String... args) {
		if (!isInt(args)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 转化为int
	 * 
	 * @param value
	 * @return 0
	 */
	public static int toInt(String value) {
		int num = 0;
		try {
			num = Integer.parseInt(value);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * 转化为long
	 * 
	 * @param value
	 * @return
	 */
	public static long toLong(String value) {
		long num = 0;
		try {
			num = Long.valueOf(value);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * 防止sql注入
	 * 
	 * @param sql
	 * @return
	 **/
	public static String filterSql(String sql) {
		// .* 0个以上任意'或;字符
		return sql.replaceAll(".*([';]+|(--)+).*", " ");
	}

	/**
	 * 生成随机数 （激活码）
	 * 
	 * @param length
	 *            生成多少位
	 * @return
	 */
	public static String createRandom(int length) {
		String code = "";
		char[] a = new char[length];
		for (int i = 0; i < a.length; ++i) {
			int rand = getRandom();
			rand = a[i] = (char) (rand + 48);
		}
		code = new String(a).substring(0, length);
		return code;
	}

	private static int getRandom() {
		Set<Integer> filter = new HashSet<Integer>();
		for (int i = 58; i < 97; i++) {
			filter.add(i - 48);
		}
		Random r = new Random();
		int i = r.nextInt(122 - 48);
		if (filter.contains(i))
			i = getRandom();
		return i;
	}

	/**
	 * LIST 去重
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}
	
	/**
	 * 保留两位小数,已自带%
	 * @param num
	 * @return
	 */
	public static String NumFormat(float num){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(num*100)+"%";
	}
	
	/**
	 * 全角转半角
	 * 
	 * @param input
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}
	
	/**
	 * String转MAP工具
	 * String格式为：file=task`t=2014-04-03 08:59:50`rid=6958`lv=23`server_id=1`activity_id=1040513`action=3
	 * @param params
	 * @param split 分隔符
	 * @return
	 */
	public static Map<String,Object> stringToMap(String params,String split){
		String[] rs=params.split(split);
		Map<String,Object> map=new HashMap<String, Object>(2);
		for(String ss:rs){
			String[] s1=ss.split("=");
			if(s1.length<2){
				map.put(s1[0], "");
			}else{
				map.put(s1[0], s1[1]);
			}
		}
		return map;
	}
	
	/**
	 * [{key,value}]转换格式
	 * 
	 * @param str
	 *            [{stren_exp,0},{stren_lv,0}]
	 * @return map
	 */
	public static Map<String, String> toMapValue(String str) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.isNotEmpty(str)) {
			String regex = "\\{.*?\\}";
			Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
			Matcher ma = pa.matcher(str);
			String[] param=null;
			while (ma.find()) {
				param=ma.group().replace("{", "").replace("}", "").split(",");
				map.put(param[0], param[1]);
			}
		}
		return map;
	}
	
	/**
	 * 正则表达式比较
	 * @param regex
	 * @param value
	 * @return
	 */
	public static boolean matcher(String regex,String value){
		Pattern pattern= Pattern.compile(regex);

	    Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}
	
	
	public static void main(String[] args) {
//		Map<String,Object> my=stringToMap("file=task`t=2014-04-03 08:59:50`rid=6958`lv=23`server_id=1`activity_id=1040513`action=3", "`");
//		System.out.println(my.get("action"));
		
		System.out.println(matcher("^(|12|13|14|15|18)\\d{9}$", "18923852070"));
		
//		String sql="SELECT a,b,c,d from a";
//		System.out.println(sql.replaceAll("(?i)select.*?from", "select count(*) from"));
	}
}
