package com.lw.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.lw.common.config.Global;

public class RedisPool {
    

    private JedisPool jedisPool = null;// 非切片连接池

    private String ip = Global.getConfig("redis_ip"); 
    
    private int port = Integer.parseInt(Global.getConfig("redis_port")); 
    
    private RedisPool() {
        initialPool();
    }

    private static class RedisClientSub{
        private final static RedisPool redisPool = new RedisPool();
    }
    
    public static Jedis getJedis(){
        return RedisClientSub.redisPool.jedisPool.getResource();
    }
    
    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(true);
        // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");

        //是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);
        config.setJmxNamePrefix("pool");
        
        //是否启用后进先出, 默认true
        config.setLifo(true);
        
        //最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        
        //最大连接数, 默认8个
        config.setMaxTotal(20);
        
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(60000);
         
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);

        //最小空闲连接数, 默认0
        config.setMinIdle(0);
        
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);
        
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
        config.setSoftMinEvictableIdleTimeMillis(1800000);
         
        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
         
        //在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);
         
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(-1);
        System.out.println(ip);
        jedisPool = new JedisPool(config, ip,port);
         
    }
 
     public static void main(String[] args) {
         Jedis jedis =  RedisPool.getJedis();
         jedis.setex("key", 10, "value");
         try {
             while (jedis.get("key") != null) {
                 System.out.println(jedis.get("key"));
                 Thread.sleep(1000);
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         jedis.close();
    }
}
