package com.mbgo.searchmgr.core.service.impl;

import java.io.BufferedOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrSearchwordLog;
import com.mbgo.searchmgr.core.bean.SearchwordLog;
import com.mbgo.searchmgr.core.mapper.MgrSearchwordLogMapper;
import com.mbgo.searchmgr.core.service.SearchWordService;

@Service("searchWordService")
public class SearchWordServiceImpl implements SearchWordService{

	@Autowired
	private MgrSearchwordLogMapper searchwordLogMapper;
	

	@Override
	public List<MgrSearchwordLog> selectAndPage(Map<String, Object> map) {
		return searchwordLogMapper.selectAndPage(map);
	}
	@Override
	public List<MgrSearchwordLog> exportWord(Map<String, Object> map) {
		return searchwordLogMapper.exportWord(map);
	}

	@Override
	public int selectPageTotal(Map<String, Object> map) {
		return searchwordLogMapper.selectPageTotal(map);
	}
	private static final String DOWNLOAD_CHARSET = "gb2312";
	public void downLoadSearchword(HttpServletResponse response, List<SearchwordLog> list){
		BufferedOutputStream bos = null;
		try{
			String fileName = "关键字搜索结果_" +"_" + System.currentTimeMillis() + ".csv";
			fileName = URLEncoder.encode(fileName, "GB2312");  
	        fileName = URLDecoder.decode(fileName, "ISO-8859-1");
			bos = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream "); // MIME
			response.setHeader("Location", fileName);
			response.setHeader("Content-disposition", "inline;filename=" + fileName);
			response.setCharacterEncoding(DOWNLOAD_CHARSET);
			bos.write("关键字,原关键字,重组关键字,搜索次数,搜索结果数,添加时间,更新时间,日期\n".getBytes(DOWNLOAD_CHARSET));
			int count = 0;
			for (SearchwordLog searchword : list) {
					String line = searchword.getWord() + "," + searchword.getSrcWord() + "," + searchword.getNewWord() + ","
			                       + searchword.getSearchCount() + "," + searchword.getRsCount() + "," + searchword.getAddTime()+ ","
							       +searchword.getEndTime() + "," + searchword.getDayDate() +"\n";
					bos.write(line.getBytes(DOWNLOAD_CHARSET));
					count ++;
					if(count > 3000) {
						bos.flush();
						count = 0;
					}
				}
			bos.flush();
			bos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
