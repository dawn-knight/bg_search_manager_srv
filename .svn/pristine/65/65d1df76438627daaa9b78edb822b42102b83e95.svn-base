package com.mbgo.searchmgr.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
/**
 * 
 * @author Administrator
 *
 */
public class AutokeyConvernt {
	public static final String special_split = "#%%#";
	private static IntegerSort _comparator = new IntegerSort();
	private static Pattern pattern = Pattern.compile("[0-9|a-z|A-Z|\\s|\u4e00-\u9fa5]+"); 
//	private static Pattern pattern = Pattern.compile("[0-9]*|[a-z]*|[A-Z]*|[\u4e00-\u9fa5]*"); 

	/**
	 * 用于显示，多个空格合并成�?��空格
	 * @param word
	 * @return
	 */
	public static String withOneSpace(String word) {
		return replace(word, " ");
	}
	
	/**
	 * 多个空格由一个自定义特殊字符代替，用于索�?
	 * @param word
	 * @return
	 */
	public static String withReplaceSpace(String word) {
		return replace(word, special_split);
	}
	
	public static String replace(String w, String sp) {
		String temp = ToDBC(w);
		temp = temp.replaceAll("[^a-zA-Z|^\u4e00-\u9fa5|^\\d|\\s]", "");
		return temp.trim().replaceAll("\\s+", sp);
	}
	
	/**
	 * 全角转半�?
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static String code(String s) {
		String rs = "";
		s = s.toLowerCase();
		if(isNumberAndLetter(s)) {
			return s;
		}
		List<Integer> codes = new ArrayList<Integer>();
		for(int i = 0, len = s.length(); i < len; i ++) {
			int cv = s.codePointAt(i);
			if(cv == 32) {
				continue;
			}
			codes.add(cv);
		}
		Collections.sort(codes, _comparator);
		for(Integer code : codes) {
			rs += String.valueOf(code) + "_";
		}
		return rs;
	}

	public static boolean isValidate(String s) {
		return pattern.matcher(s).matches();
	}
	private static Pattern pattern1 = Pattern.compile("[\\d|\\w]+");
	public static boolean isNumberAndLetter(String s) {
		return pattern1.matcher(s.replaceAll("\\s", "")).matches();
	}
	
	public static void main(String[] args) {
		String s2 = "高跟鞋女";
		String s3 = "高跟女鞋";
		String s4 = "女高跟鞋";
		System.out.println(code(s2));
		System.out.println(code(s3));
		System.out.println(code(s4));
	}
}

class IntegerSort implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}
}
