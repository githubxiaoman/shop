package cxm.shop.test;

import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月12日下午12:56:04
 */
public class JedisTest {
	/**
	 * 单击版 jedis
	 *
	 *@date 2020年2月12日下午8:35:14
	*
	 */
	public void testJedis() {
		//创建jedis连接
		Jedis jedis=new Jedis("192.168.43.88",6379);
		//从连接池中获取Jedis对象
		jedis.set("key", "xiaomantest1");
		String string = jedis.get("key1");
		System.out.println(string);
		//调用jedis对象的方法
		//关闭jedis
		jedis.close();
	}
	public void testJedisPool() {
		//创建jedis连接池
		JedisPool pool=new JedisPool("192.168.43.88",6379);
		//从连接池中获取jedis对象
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
		
	}

	
	
	/**
	 * 集群版测试
	 *@date 2020年2月12日下午1:19:35
	 */
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.43.88", 7001));
		nodes.add(new HostAndPort("192.168.43.88", 7002));
		nodes.add(new HostAndPort("192.168.43.88", 7003));
		nodes.add(new HostAndPort("192.168.43.88", 7004));
		nodes.add(new HostAndPort("192.168.43.88", 7005));
		nodes.add(new HostAndPort("192.168.43.88", 7006));
		JedisCluster cluster=new JedisCluster(nodes);
		cluster.set("a", "1000");
		String string = cluster.get("a");
		System.out.println(string);
		cluster.close();
	}
	//整合spring的jedis测试(单击)
	public void testSpringJedis() {
		ApplicationContext app=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool= (JedisPool) app.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	//整合spring的jedis测试(集群)
	public void testSpringJedisCluster() {
		ApplicationContext app=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster=  (JedisCluster) app.getBean("redisClient");
		String string = cluster.get("a");
		System.out.println(string);
		cluster.close();
	}
	
	
	
	
	
	
	
}
