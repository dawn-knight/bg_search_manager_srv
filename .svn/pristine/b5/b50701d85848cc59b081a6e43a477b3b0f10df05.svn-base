package com.mbgo.searchmgr.core.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mbgo.searchmgr.core.bean.MgrHotKeyword;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;

/**
 * @author qining
 *
 */
public class TranUtil {
	String collectionsName = "indexmgr_hot_word";
	String dbName = "";
	DBCollection coll = null;
	Mongo m = null;

	public TranUtil() {
		try {
			coll = getDBCollection(collectionsName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBCollection getDBCollection(String collectionName) {
		DBCollection coll = null;
		try {
			Properties properties = new CommonUtil().loadPropertiesMogo();
			String address = properties.getProperty("searchDB.addresses");
			String username = properties.getProperty("searchDB.user");
			String password = properties.getProperty("searchDB.password");
			dbName = properties.getProperty("searchDB.name");
//			m = new Mongo("10.100.200.55", 55555);
			m = new Mongo(address);
			DB db = m.getDB(dbName);
//			if (db.authenticate("search", "bgsearch*()".toCharArray())) {
				if (db.authenticate(username, password.toCharArray())) {
				System.out.println("success");
			}
			coll = db.getCollection(collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return coll;
	}




	// ��ѯ����
	public  List<MgrHotKeyword> queryAll() {
		List<MgrHotKeyword> list = new ArrayList<MgrHotKeyword>();
		BasicDBObject obj = new BasicDBObject();
//		obj.put("sex", "boy");
		DBCursor cursor = coll.find(obj);
		MgrHotKeyword mhk = null;
		int i = 0;
		boolean flag = true;
		System.out.println(cursor.count());
		while (cursor.hasNext() && flag == true) {
			i++;
			if(i>5000){
				if(i<=5500){
			mhk = new MgrHotKeyword();
			String wordCode = (String) cursor.next().get("wordCode");
			Long searchCount = (Long) cursor.next().get("searchCount");
			String keyWord = (String) cursor.next().get("keyWord");
			Long addTime = (Long) cursor.next().get("addTime");
			Long updateTime = (Long) cursor.next().get("updateTime");
			mhk.setWordCode(wordCode);
			mhk.setWord(keyWord);
			mhk.setSearchCount(searchCount);
			mhk.setAddTime(addTime);
			mhk.setLastUpdate(new Date(updateTime));
			list.add(mhk);
				}else{
					flag = false;
				}
			}
		}
		
		cursor.close();
		return list;
	}

	//���Ŵʿ����
	public List getAllHotWord() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		DB db = m.getDB("searchDB");
		Set<String> colls = db.getCollectionNames();
		 DBCollection goods = db.getCollection("indexmgr_hot_word");
		  DBCursor cur = goods.find();
          while (cur.hasNext()) {
        	  list.add(cur.next().toMap());
          }
          
          cur.close();
          return list;
	}
	//ͬ��ʱ�
	public List getAllSameWord() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		DB db = m.getDB("searchDB");
		Set<String> colls = db.getCollectionNames();
		 DBCollection goods = db.getCollection("indexmgr_same_word");
		  DBCursor cur = goods.find();
          System.out.println(cur.count());
          while (cur.hasNext()) {
        	  list.add(cur.next().toMap());
          }
          
          cur.close();
          return list;
	}
	//��Ʒ��ǩ���
	public List getAllTagWord() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		DB db = m.getDB("searchDB");
		Set<String> colls = db.getCollectionNames();
		DBCollection goods = db.getCollection("goods");
		DBCursor cur = goods.find();
		System.out.println(cur.count());
		while (cur.hasNext()) {
			list.add(cur.next().toMap());
		}
		
		cur.close();
		return list;
	}
	
	public int getAllTagCount(){
		DB db = m.getDB("searchDB");
		DBCollection goods = db.getCollection("indexmgr_tag_word");
		DBCursor cur = goods.find();
		return cur.count();
	}
//	//��ǩ����Ϣ
//		public List getAllTagInfo(int pagesize,int total) {
//			List<Map<String,Object>> list = null;
//			DB db = m.getDB("searchDB");
//			DBCollection goods = db.getCollection("indexmgr_tag_word");
//			DBCursor cur = goods.find();
//			DBCursor cursor  = null;
//			if(pagesize < total){
//		    list = new ArrayList<Map<String,Object>>();
//		    cursor = PageUtil.getCursor(cursor, pagesize, total);
//			ExecutorService pool = Executors.newFixedThreadPool(1);
//			 Callable c1 = new BaseThread(cursor);
//		     // ִ�����񲢻�ȡFuture����
//		     Future future = pool.submit(c1);
//		     try {
//				list = (List<Map<String, Object>>) future.get();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			}
//			if(cursor != null){
//			cursor.close();
//			}
//			return list;
//		}
		
		//����������
		public List getAllHotSearch() {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			DB db = m.getDB("searchDB");
			Set<String> colls = db.getCollectionNames();
			DBCollection goods = db.getCollection("indexmgr_hot_keyword");
			DBCursor cur = goods.find();
			System.out.println(cur.count());
			while (cur.hasNext()) {
				list.add(cur.next().toMap());
			}
			
			cur.close();
			return list;
		}
		
		
		       public int getAllBasewordCount(){
		    	    DB db = m.getDB("searchDB");
					DBCollection goods = db.getCollection("indexmgr_base_word");
					DBCursor cur = goods.find();
					return cur.count();
		       }
		
		       public int getAllCount(String tableName){
		    	    DB db = m.getDB("searchDB");
					DBCollection goods = db.getCollection(tableName);
					DBCursor cur = goods.find();
					return cur.count();
		       }
		       
		       /**
		     * @param pagesize ������ҳ��
		     * @param total ������
		     * @return
		     * @throws InterruptedException
		     * @throws ExecutionException
		     */
//		    public List<List<Map<String,Object>>> getAllBaseword(int pagesize,int total) throws InterruptedException, ExecutionException {
//					List<List<Map<String,Object>>> list = new ArrayList<List<Map<String,Object>>>();
//					DB db = m.getDB("searchDB");
//					DBCollection goods = db.getCollection("indexmgr_base_word");
//					DBCursor cur = goods.find();
//					DBCursor cursor  = null;
//				    int page = 2000;
//			        pagesize = 0;
//					while(pagesize < total){
//				    cursor = goods.find().skip(pagesize).limit(page);
//					pagesize = pagesize + 2000;
//					ExecutorService pool = Executors.newFixedThreadPool(1);
//					 Callable c1 = new BaseThread(cursor);
//				     // ִ�����񲢻�ȡFuture����
//				     Future f1 = pool.submit(c1);
//					list.add((List<Map<String,Object>>)f1.get());
//					
//					}
//					cursor.close();
//					return list;
//				}
		       
		    
//		    public List<List<Map<String,Object>>> getAllBaseword(int pagesize,int total) throws InterruptedException, ExecutionException {
//				List<List<Map<String,Object>>> list = new ArrayList<List<Map<String,Object>>>();
//				list.clear();
//				DB db = m.getDB("searchDB");
//				DBCollection goods = db.getCollection("indexmgr_base_word");
//				DBCursor cur = goods.find();
//				DBCursor cursor  = null;
//				if(pagesize < total){
//			    cursor = goods.find().skip(pagesize).limit(10000);
//				ExecutorService pool = Executors.newFixedThreadPool(1);
//				 Callable c1 = new BaseThread(cursor);
//			     // ִ�����񲢻�ȡFuture����
//			     Future f1 = pool.submit(c1);
//				list.add((List<Map<String,Object>>)f1.get());
//				
//				}
//				if(cursor != null){
//				cursor.close();
//				}
//				return list;
//			}
		    //�Զ���ִ�
		    public List<Map<String,Object>> getAllBaseword1(int pagesize,int total) throws InterruptedException, ExecutionException {
				List<Map<String,Object>> list = null;
				DB db = m.getDB("searchDB");
				DBCollection goods = db.getCollection("indexmgr_base_word");
				DBCursor cur = goods.find();
				DBCursor cursor  = null;
				if(pagesize < total){
			    list = new ArrayList<Map<String,Object>>();
			    cursor = goods.find().skip(pagesize).limit(5000);
				ExecutorService pool = Executors.newFixedThreadPool(1);
				 Callable c1 = new BaseThread(cursor);
			     Future future = pool.submit(c1);
			     list = (List<Map<String, Object>>) future.get();
				
				}
				if(cursor != null){
				cursor.close();
				}
				return list;
			}
		    
		    public List<Map<String,Object>> getAllData(int pagesize,int total,String tableName) throws InterruptedException, ExecutionException {
				List<Map<String,Object>> list = null;
				DB db = m.getDB("searchDB");
				DBCollection goods = db.getCollection(tableName);
				DBCursor cursor  = null;
				if(pagesize < total){
			    list = new ArrayList<Map<String,Object>>();
			    cursor = goods.find().skip(pagesize).limit(5000);
				ExecutorService pool = Executors.newFixedThreadPool(1);
				 Callable c1 = new CommonConvert(cursor);
			     Future future = pool.submit(c1);
			     list = (List<Map<String, Object>>) future.get();
				
				}
				if(cursor != null){
				cursor.close();
				}
				return list;
			}
		    
//				/**
//				 * @return
//				 * @throws InterruptedException
//				 * @throws ExecutionException
//				 */
//				public List<List<Map<String,Object>>> getAllBaseword() throws InterruptedException, ExecutionException {
//					List<List<Map<String,Object>>> list = new ArrayList<List<Map<String,Object>>>();
//					DB db = m.getDB("searchDB");
//					DBCollection goods = db.getCollection("indexmgr_base_word");
//					DBCursor cur = goods.find();
//					DBCursor cursor  = null;
//					int page = 2000;
//					int pagesize = 0;
//					while(pagesize < 10000){
//				    cursor = goods.find().skip(pagesize).limit(page);
//					pagesize = pagesize + 2000;
////					listsub = new ArrayList<Map<String,Object>>();
////					while (cursor.hasNext()) {
////					    map = new HashMap<String, Object>();
////						map = cursor.next().toMap();
////						listsub.add(map);
////					}
////					List<Map<String,Object>> listsub1 = null;
////					List<Map<String,Object>> listsub2 = null;
////					List<Map<String,Object>> listsub3 = null;
////					List<Map<String,Object>> listsub4 = null;
////					List<Map<String,Object>> listsub5 = null;
//					ExecutorService pool = Executors.newFixedThreadPool(1);
//					 Callable c1 = new BaseThread(cursor);
////					 Callable c2 = new BaseThread(cursor);
////					 Callable c3 = new BaseThread(cursor);
////					 Callable c4 = new BaseThread(cursor);
////					 Callable c5 = new BaseThread(cursor);
//				     // ִ�����񲢻�ȡFuture����
//				     Future f1 = pool.submit(c1);
//				     // ִ�����񲢻�ȡFuture����
////				     List<Map<String,Object>> listsub1 = (List<Map<String, Object>>) f1.get();
////				     list.add((List<Map<String, Object>>) f1.get());
////				     list.add(listsub2);
////				     list.add(listsub3);
////				     list.add(listsub4);
////				     list.add(listsub5);
//					list.add((List<Map<String,Object>>)f1.get());
//					
//					}
//					cursor.close();
//					return list;
//				}
	// �Զ���ִʱ�
		public List getAllbase() {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			DB db = m.getDB("searchDB");
			Set<String> colls = db.getCollectionNames();
			 DBCollection goods = db.getCollection("indexmgr_industry_word");
			 DBCursor cur = goods.find();
				while (cur.hasNext()) {
					list.add(cur.next().toMap());
				}
				cur.close();
				return list;
		}
	
	public static void main(String[] args) {
		CommonUtil.loadPropertiePath();
	}
	
	     //��ǩ����Ϣ
			public List getAllTagInfo() {
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				DB db = m.getDB("searchDB");
				Set<String> colls = db.getCollectionNames();
				DBCollection goods = db.getCollection("indexmgr_tag_word");
				DBCursor cur = goods.find();
				while (cur.hasNext()) {
					list.add(cur.next().toMap());
				}
				
				cur.close();
				return list;
			}

}
