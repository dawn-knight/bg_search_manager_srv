package com.mbgo.searchmgr.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mbgo.searchmgr.core.bean.MgrSearchwordLog;
import com.mbgo.searchmgr.core.bean.SearchwordLog;

public class ConvertUtil {

	public static String dataToString(Date date){
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
          return format.format(date);
	}
	public static Long dateConvert(String time){
		if(null == time || time.equals("")){
			return 0l;
		}
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		
        Long addTime = null;
	try {
		addTime = format.parse(time).getTime();
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
       return addTime;
	}
	public static Date dateTodate(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
	    Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String [] convertArray(String para){
	       String [] ids =  para.split(",");
	       return ids;
	}
	public static Long [] convertLongArray(String para){
		String [] ids =  para.split(",");
		Long [] lid = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			lid[i] = Long.parseLong(ids[i]);
		}
		return lid;
	}
	public static List<Long> convertLongList(String para){
		String [] ids =  para.split(",");
		List<Long> list = new ArrayList<Long>();
		for (String id : ids) {
	      list.add(Long.parseLong(id));		
		}
		return list;
	}
	public static List<SearchwordLog> longToDate(List<MgrSearchwordLog> listPage) throws Exception{
		List<SearchwordLog> result = new ArrayList<SearchwordLog>();
		SearchwordLog tempLog = null;
		for (MgrSearchwordLog msl : listPage) {
			tempLog = new SearchwordLog();
			tempLog.setId(msl.getId());
			tempLog.setWord(msl.getWord());
			tempLog.setSrcWord(msl.getSrcWord());
			String nword = msl.getNewWord();
			if(null == nword){
			tempLog.setNewWord("");
			}else{
			tempLog.setNewWord(msl.getNewWord());
			}
			tempLog.setSearchCount(msl.getSearchCount());
			tempLog.setRsCount(msl.getRsCount());
			Long tempAt = msl.getAddTime();
			Long tempEt = msl.getAddTime();
			Date da = new Date(tempAt);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dmd = new SimpleDateFormat("yyyy-MM-dd");
			String cat = df.format(da);
			String cet = df.format(tempEt);
			tempLog.setAddTime(cat);
			tempLog.setEndTime(cet);
			Date dd = msl.getDayDate();
			tempLog.setDayDate(dmd.format(dd));
			result.add(tempLog);
		}
		return result;
	}
}
