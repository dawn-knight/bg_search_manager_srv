package com.mbgo.searchmgr.core.util;

import java.util.HashMap;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrMenu;

public class CacheManager {
	private static Map<Long,MgrMenu> cacheMenuMap = new HashMap<Long,MgrMenu>();
	
	public static void setMenu(Long parentid,MgrMenu menu){
		cacheMenuMap.put(parentid, menu);
	}

}
