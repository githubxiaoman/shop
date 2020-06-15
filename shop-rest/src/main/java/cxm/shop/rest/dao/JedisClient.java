package cxm.shop.rest.dao;

/**
 * 
 * @author	xiaoman
 * @Date 2020年2月12日下午9:57:04
 */
public interface JedisClient {
	String set(String key,String value);
	String get(String key);
	
	String hget(String hkey,String key);
	Long hset(String hkey,String key,String value);
	
	long incr(String key);
	long expire(String key,int second);
	long ttl(String key);
	
	long del(String key);
	long hdel(String  hkey,String key);
}
