package com.mbgo.searchmgr.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.service.GoodsTagsService;

public class ImportExcel {

	public static List<MgrGoodsTags> parseExcel(String path,String [] para) throws Exception{
		DecimalFormat df = new DecimalFormat("0");  
		List<MgrGoodsTags> list = new ArrayList<MgrGoodsTags>(); 
		  MgrGoodsTags goodTags = null;
		    String code = "";
		   File file = new File(path);
		   InputStream input = new FileInputStream(file);
		   XSSFWorkbook xwb = new XSSFWorkbook(input);
		   //InputStream DesXls=new FileInputStream("Result.xls");
		   HSSFWorkbook wb=new HSSFWorkbook();
		   HSSFSheet xsheet = wb.createSheet("sheet1");
		   // ��ȡ��һ�±������  
		   XSSFSheet sheet = xwb.getSheetAt(0);  
		   // ���� row��cell  
		   XSSFRow row;  
		   String cell;
		   // ѭ���������е�����  
		   for(int tagNum = 0; tagNum < para.length;tagNum++){
		   for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {  
		       row = sheet.getRow(i);
		       HSSFRow xrow=xsheet.createRow(i);
		       for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {  
		    	goodTags = new MgrGoodsTags();   
//		        code = row.getCell(0).toString(); 
//		        code = row.getCell(i).getNumericCellValue()+"";
		        code = df.format(row.getCell(0).getNumericCellValue());  
		        goodTags.setGoodsSn(code);
		        goodTags.setTagWord(para[tagNum]);
		        goodTags.setAddTime(new Date());
		        goodTags.setIsLimited(1);
		        goodTags.setFlag(1);
		        list.add(goodTags);
		       } 
		   }
	}
		   return list;
	}
}
