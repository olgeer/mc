package com.ucsmy.mc.bg.api;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtil {

    //Redis服务器IP
    private static String ADDR = "127.0.0.1";//172.17.21.188

    //Redis的端口号
    private static int PORT = 6379;

    //访问密码
    private static String AUTH = "admin";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = -1;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 8;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = -1;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
            //jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * 同步获取Jedis实例 
     * @return Jedis 
     */  
    public synchronized static Jedis getJedis() {    
        Jedis jedis = null;  
            if (jedisPool != null) {    
                jedis = jedisPool.getResource(); 
            }
         
        return jedis;  
    }    
      
    public static void close(Jedis jedis) {
		try {
			jedisPool.returnResource(jedis);

		} catch (Exception e) {
			if (jedis.isConnected()) {
				jedis.quit();
				jedis.disconnect();
			}
		}
	}  
	/**
	 * 存储REDIS队列 顺序存储
	 * @param byte[] key reids键名
	 * @param byte[] value 键值
	 */
	public static void lpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {

			jedis = RedisUtil.getJedis();
			jedis.lpush(key, value);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			//返还到连接池
			RedisUtil.close(jedis);

		}
	}
	/**
	 * 获取队列数据
	 * @param byte[] key 键名
	 * @return
	 */
	public static byte[] lpop(byte[] key) {

		byte[] bytes = null;
		Jedis jedis = null;
		try {

			jedis =  RedisUtil.getJedis();
			System.out.println("jedis="+jedis.getClass().getName().toString());
			bytes = jedis.lpop(key);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			//返还到连接池
			close(jedis);

		}
		return bytes;
	}

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}