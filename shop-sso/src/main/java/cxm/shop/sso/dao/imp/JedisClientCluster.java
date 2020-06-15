package cxm.shop.sso.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;

import cxm.shop.sso.dao.JedisClient;
import redis.clients.jedis.JedisCluster;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月12日下午10:14:15
 */
public class JedisClientCluster implements JedisClient{
	@Autowired
	private JedisCluster cluster;

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return cluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return cluster.get(key);
	}

	@Override
	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		return cluster.hget(hkey, key);
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		return cluster.hset(hkey, key, value);
	}

	@Override
	public long incr(String key) {
		return cluster.incr(key);
	}

	@Override
	public long expire(String key, int second) {
		return cluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		return cluster.ttl(key);
	}

	@Override
	public long del(String key) {
		return cluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		return cluster.hdel(hkey,key);
	}

}
