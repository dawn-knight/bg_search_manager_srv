package com.mbgo.searchmgr.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.mongodb.DBCursor;

public class BaseThread implements Callable{

	private DBCursor cursor;

    public DBCursor getCursor() {
		return cursor;
	}


	public void setCursor(DBCursor cursor) {
		this.cursor = cursor;
	}


	public BaseThread(DBCursor cursor) {
		this.cursor = cursor;
	}
	
	 public Object call() throws Exception {
	    	List<Map<String,Object>> listsub = new ArrayList<Map<String,Object>>();
//	    	Map<String,Object> map = null;
	    	while(cursor.hasNext()){
	    			listsub.add(cursor.next().toMap());
	    	}
	    	return listsub;
     }
	 

}

