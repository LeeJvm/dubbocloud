/*
package com.gavin.redis;

import com.gavin.util.ComContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

*/
/**
 *
 * ====================此方案被弃用=========================================
 *
 * 1，jedis封装类
 * 2，使用方法：
 * 调用此工具类，getJedis获取jedis对象；
 * 通过jedis对象调用操作redis的方法；
 * 操作完成后调用close释放jedis连接
 *
 *
 *
 * Created by 17428 on 2023/5/1.
 *//*

@Component
public class JedisUtils {

    @Autowired
    JedisPool jedisPool ;

    */
/**
     * 获取Jedis资源
     *//*

    public Jedis getJedis(){
        return jedisPool.getResource();
    }
    */
/**
     * 释放Jedis连接
     *//*

    public void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

    */
/**
     * 分布式锁：加锁
     *
     * @param lockName
     * @param value
     * @param expireTime
     * @param jedis
     * @return
     *//*

    public synchronized boolean  jedisLock(String lockName,String value,long expireTime,Jedis jedis) {
        Long jedisLock = jedis.setnx(lockName, value);
        if (jedisLock == 1) {
            jedis.expire(ComContants.RedisKey.REDIS_SETNX_TEXT, (int) expireTime);
            return true;
        }
        return false;
    }

    */
/**
     * 分布式锁：释放锁
     *
     * @param lockName
     * @param jedis
     * @return
     *//*

    public boolean releaseLock(String lockName,Jedis jedis) {
        Long del = jedis.del(lockName);
        if (del==1) {
            return true;
        }
        return false;
    }







    public static void main(String[] args) {
        JedisUtils jedisUtils = new JedisUtils();
        Jedis jedis = jedisUtils.getJedis();
        Long jedisLock = jedis.setnx(ComContants.RedisKey.REDIS_SETNX_TEXT, "分布式锁");
        if (jedisLock == 1) {
            jedis.expire(ComContants.RedisKey.REDIS_SETNX_TEXT, 10);
        }

    }



}
*/
