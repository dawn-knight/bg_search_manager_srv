package com.mbgo.searchmgr.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.mbgo.searchmgr.core.bean.mbshop.ProductLibAttrKey;

public class CommonUtil {

	public static String getRemainingPath(String requestUri, String mapping, boolean ignoreCase) {
		int index1 = 0;
		int index2 = 0;
		for ( ; (index1 < requestUri.length()) && (index2 < mapping.length()); index1++, index2++) {
			char c1 = requestUri.charAt(index1);
			char c2 = mapping.charAt(index2);
			if (c1 == ';') {
				index1 = requestUri.indexOf('/', index1);
				if (index1 == -1) {
					return null;
				}
				c1 = requestUri.charAt(index1);
			}
			if (c1 == c2) {
				continue;
			}
			if (ignoreCase && (Character.toLowerCase(c1) == Character.toLowerCase(c2))) {
				continue;
			}
			return null;
		}
		if (index2 != mapping.length()) {
			return null;
		}
		if (index1 == requestUri.length()) {
			return "";
		}
		else if (requestUri.charAt(index1) == ';') {
			index1 = requestUri.indexOf('/', index1);
		}
		return (index1 != -1) ? requestUri.substring(index1) : "";
	}
	
	public Properties loadProperties(){
		Properties defaultStrategies = null;
		InputStream stream = null;
		try {
		    stream = this.getClass().getClassLoader().getResourceAsStream("urladdress.properties");
			defaultStrategies = new Properties();
			defaultStrategies.load(stream);
			System.out.println(defaultStrategies.getProperty("searchurl"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return defaultStrategies;
	}
	public Properties loadPropertiesMogo(){
		Properties defaultStrategies = null;
		InputStream stream = null;
		try {
			stream = this.getClass().getClassLoader().getResourceAsStream("mongoDB.properties");
			defaultStrategies = new Properties();
			defaultStrategies.load(stream);
			System.out.println(defaultStrategies.getProperty("searchurl"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return defaultStrategies;
	}
	public static Properties loadPropertiePath(){
		Properties defaultStrategies = null;
		try {
			File file = new File("urladdress.properties");  
			InputStream input = null;  
			input = new FileInputStream(file);  
			defaultStrategies = new Properties();
			defaultStrategies.load(input);
			System.out.println(defaultStrategies.getProperty("searchurl"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return defaultStrategies;
	}
	
	
	public static List<Long> getParametersStartingWith(HttpServletRequest request, String prefix)  
    {  
		List<Long> list = new ArrayList<Long>();
        Enumeration paramNames = request.getParameterNames();  
        while(paramNames != null && paramNames.hasMoreElements())   
        {  
            String paramName = (String)paramNames.nextElement();  
            if("".equals(prefix) || paramName.startsWith(prefix))  
            {  
                String value = request.getParameter(paramName);  
                list.add(Long.parseLong(value));
            }  
        }  
        return list;  
    }
	
	public static ProductLibAttrKey [] getNotDynamic(int total){
		ProductLibAttrKey [] result = new ProductLibAttrKey[6];
		ProductLibAttrKey price = new ProductLibAttrKey();
		price.setAttrKeyId(total+1);
		price.setAttrKeyName("价格");
		price.setAttrKeyCode("price");
		price.setSort(total+6);
		price.setCreateDate(new Date());
		price.setLastUpdateDate(new Date());
		ProductLibAttrKey size = new ProductLibAttrKey();
		size.setAttrKeyId(total+2);
		size.setAttrKeyName("尺寸");
		size.setAttrKeyCode("size");
		size.setSort(total+5);
		size.setCreateDate(new Date());
		size.setLastUpdateDate(new Date());
		ProductLibAttrKey color = new ProductLibAttrKey();
		color.setAttrKeyId(total+3);
		color.setAttrKeyName("颜色");
		color.setAttrKeyCode("color");
		color.setSort(total+4);
		color.setCreateDate(new Date());
		color.setLastUpdateDate(new Date());
		ProductLibAttrKey brand = new ProductLibAttrKey();
		brand.setAttrKeyId(total+4);
		brand.setAttrKeyName("品牌");
		brand.setAttrKeyCode("brand");
		brand.setSort(total+3);
		brand.setCreateDate(new Date());
		brand.setLastUpdateDate(new Date());
		ProductLibAttrKey store = new ProductLibAttrKey();
		store.setAttrKeyId(total+5);
		store.setAttrKeyName("店铺");
		store.setAttrKeyCode("store");
		store.setSort(total+2);
		store.setCreateDate(new Date());
		store.setLastUpdateDate(new Date());
		ProductLibAttrKey discount = new ProductLibAttrKey();
		discount.setAttrKeyId(total+5);
		discount.setAttrKeyName("折扣");
		discount.setAttrKeyCode("discount");
		discount.setSort(total+1);
		discount.setCreateDate(new Date());
		discount.setLastUpdateDate(new Date());
		result[0] = brand;
		result[1] = color;
		result[2] = price;
		result[3] = size;
		result[4] = store;
		result[5] = discount;
    return result;
	}
	
}
