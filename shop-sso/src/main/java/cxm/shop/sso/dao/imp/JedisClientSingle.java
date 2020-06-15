package cxm.shop.sso.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;

import cxm.shop.sso.dao.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月12日下午10:05:07
 */
public class JedisClientSingle implements JedisClient{
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.hset(hkey, key, value);
		jedis.close();
		return string;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.incr(key);
		jedis.close();
		return string;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.expire(key,  second);
		jedis.close();
		return string;
	}

	@Override 
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.ttl(key);
		jedis.close();
		return string;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey,String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey,key);
		jedis.close();
		return result;
	}


}
