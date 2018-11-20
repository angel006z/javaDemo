package com.meida.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/** 
 * 缓存管理
 */
public class CacheManager {
	
	/**
	 * key的值唯一
	 */
	public static final String strFormDict="strFormDict";
	
	/**
	 * 所有的缓存信息
	 */
	public static final String findAllEstate="findAllEstate";
	
	/**
	 * 后台缓存集合
	 */
	private static HashMap cacheMap=new HashMap<>();
	
	
	private CacheManager(){
		super();
	}
	
	/**
	 * 获取布尔值的缓存
	 * @param key
	 * @return
	 */
	public static boolean getSimpleFlag(String key){
		try {
			return (boolean) cacheMap.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取布尔值的缓存
	 * @param key
	 * @return
	 */
	public static long getServerStartdt(String key){
		try {
			return (long) cacheMap.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 设置布尔值的缓存
	 * @param key
	 * @param flag
	 * @return
	 */
	public synchronized static boolean setSimpleFlag(String key,boolean flag){
		if(flag && getSimpleFlag(key)){
			return false;
		}else {
			cacheMap.put(key, flag);
			return true;
		}
	}
	
	/**
	 * 设置布尔值缓存
	 * @param key
	 * @param serverbegrundt
	 * @return
	 */
	public synchronized static boolean setSimpleFlag(String key,long serverbegrundt){
		if(cacheMap.get(key)==null){
			cacheMap.put(key, serverbegrundt);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 得到缓存，同步静态方法
	 * @param key
	 * @return
	 */
	private synchronized static Cache getCache(String key){
		return (Cache) cacheMap.get(key);
	}
	
	/**
	 * 判断是否存在一个缓存
	 * @param key
	 * @return
	 */
	private synchronized static boolean hasCache(String key){
		return cacheMap.containsKey(key);
	}
	
	/**
	 * 清楚所有缓存
	 */
	public synchronized static void clearAll(){
		cacheMap.clear();
	}
	
	
	/**
	 * 清除某类一特定缓存,通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配
	 * @param type
	 */
	public synchronized static void clearAll(String type){
		Iterator i=cacheMap.entrySet().iterator();
		String key;
        ArrayList<String> arr = new ArrayList<String>();
        try {
			while(i.hasNext()){
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
			    key = (String) entry.getKey();
			    if(key.startsWith(type)){
			    	arr.add(key);
			    }
			}
			for(int k =0;k <arr.size();k++){
				clearOnly(arr.get(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 清楚指定缓存
	 * @param key
	 */
	private static void clearOnly(String key) {
		cacheMap.remove(key);
	}
	
	/**
	 * 载入缓存
	 * @param key
	 * @param obj
	 */
	public synchronized static void putCache(String key,Cache obj){
		cacheMap.put(key, obj);
	}
	
	/**
	 * 获取缓存信息
	 * @param key
	 * @return
	 */
	public static Cache getCacheInfo(String key){
		if(hasCache(key)){
			Cache cache=getCache(key);
			if(cacheExpired(cache)){
				cache.setExpired(true);
				clearOnly(key);
			}
			return cache;
		}else {
			return null;
		}
	}
	
	/**
	 * 载入缓存信息
	 * @param key
	 * @param obj
	 * @param dt
	 * @param expired
	 */
	public static void putCacheInfo(String key,Cache obj,long dt,boolean expired){
		Cache cache=new Cache();
		cache.setKey(key);
		cache.setTimeout(dt+System.currentTimeMillis());
		cache.setValue(obj);
		cache.setExpired(expired);
		cacheMap.put(key, cache);
	}
	
	/**
	 * 重写载入缓存信息方法
	 * @param key
	 * @param obj
	 * @param dt
	 */
	public static void putCacheInfo(String key,Cache obj,long dt){
		Cache cache=new Cache();
		cache.setKey(key);
		cache.setTimeout(dt+System.currentTimeMillis());
		cache.setValue(obj);
		cache.setExpired(false);
		cacheMap.put(key, cache);
	}
	
	/**
	 * 判断是否终止
	 * @param cache
	 * @return
	 */
	public static boolean cacheExpired(Cache cache){
		if(cache==null){
			return false;
		}
		long nowDt=System.currentTimeMillis();//系统当前毫秒数
		long cacheDt=cache.getTimeout();//缓存内过期的毫秒数
		if(cacheDt <= 0||cacheDt >nowDt){//过期时间小于等于零，或者过期时间大于当前时间，则为false;
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 获取指定的类型大小
	 * @param type
	 * @return
	 */
	public static int getCacheSize(String type){
		int k =0;
		Iterator i=cacheMap.entrySet().iterator();
		String key;
		try {
			while(i.hasNext()){
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
			    key = (String) entry.getKey();
			    if (key.indexOf(type) != -1) { //如果匹配则删除掉
			        k++;
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
	
	/**
	 * 获取缓存对象中的所有键值名称
	 * @return
	 */
	public static ArrayList<String> getCacheAllkey(){
		ArrayList a=new ArrayList();
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
			    java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
			    a.add((String) entry.getKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return a;
		}
	}
	
	/**
	 * 获取缓存对象中指定类型 的键值名称
	 * @param type
	 * @return
	 */
	public static ArrayList<String> getCacheListkey(String type){
		ArrayList a=new ArrayList();
		String key;
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
			key = (String) entry.getKey();
			if (key.indexOf(type) != -1) {
			     a.add(key);
			 }
    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return a;
		}
	}

}
