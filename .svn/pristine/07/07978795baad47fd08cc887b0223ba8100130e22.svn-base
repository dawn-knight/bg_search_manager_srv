package com.mbgo.searchmgr.core.util;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MgrDicKeyword;

public class PageJson {

	public static String ListConvertJson(int total,  List<MgrDicKeyword> listPage){
		StringBuilder builder = new StringBuilder("");
		builder.append("{total:").append(total).append(",");
		for (MgrDicKeyword keyword : listPage) {
			builder.append("rows:").append("[{");
			builder.append("id:").append(keyword.getId()).append(",");
			builder.append("word:").append(keyword.getWord()).append(",");
			builder.append("addTime:").append(keyword.getAddTime()).append(",");
			builder.append("lastUpdate:").append(keyword.getLastUpdate()).append(",");
			builder.append("weight:").append(keyword.getWeight()).append("},");
		}
		return builder.toString();
		
	}
}
