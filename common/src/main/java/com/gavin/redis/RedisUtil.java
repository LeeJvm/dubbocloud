//package com.gavin.redis;
//
//import org.omg.CORBA.OBJ_ADAPTER;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
///**
// *  提供操作redis的所有方法：
// *  列出常用方法
// *  1，set方法，过期时间
// *  2，
// *
// *
// * Created by 17428 on 2023/3/26.
// */
//
//@Component
//public class RedisUtil {
//
//
//    @Autowired
//    RedisTemplate<String, Object> redisTemplate;
//
//
//    /**
//     * 设置key-value，同时设置过期时间
//     * @param key
//     * @param value
//     * @param expireTime
//     */
//    public void setex(String key,Object value ,long expireTime) {
//        redisTemplate.opsForValue().set(key,value,expireTime);
//    }
//
//    /**
//     * 设置key-value，不过期
//     * @param key
//     * @param value
//     */
//    public void set(String key, Object value) {
//        redisTemplate.opsForValue().set(key,value);
//    }
//
//    /**
//     * 如果不存在则写入
//     * 可做分布式锁
//     * expireTime需要超过业务处理时间，在业务处理异常、超时后释放锁；业务代码需要考虑重跑的逻辑严谨性
//     * 获取锁，业务逻辑正常跑完后，需要释放锁，调用del key操作
//     *
//     * @param key
//     * @param value
//     * @param expireTime
//     * @return
//     */
//    public Boolean setnx(String key, Object value,long expireTime) {
//        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime,null);
//
//    }
//
//
//    public void del(String key) {
//        redisTemplate.delete(key);
//    }
//
//
//
//
//    /**
//     * 获取value
//     * @param key
//     * @return
//     */
//    public Object get(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public Long incr(String key) {
//        return redisTemplate.opsForValue().increment(key);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
//