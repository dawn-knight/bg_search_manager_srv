package com.mbgo.searchmgr.core.util;

public class MorePara {
	
	public static String paraConvert(String ... para){
		StringBuilder tempSb = new StringBuilder("");
		String paraValue ="";
		if(para.length > 0){
			for (String str : para) {
				tempSb.append(str).append(",");
			}
			 paraValue = tempSb.substring(0,tempSb.length() - 1);
		}
		return paraValue;
	}

}
