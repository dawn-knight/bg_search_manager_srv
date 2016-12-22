package com.mbgo.searchmgr.core.util;

import com.mongodb.DBCursor;

public class PageUtil {

	
	public static DBCursor getCursor(DBCursor cursor,int pagesize,int nowsize){
		  return cursor.skip(pagesize).limit(nowsize);
	}
	
}
