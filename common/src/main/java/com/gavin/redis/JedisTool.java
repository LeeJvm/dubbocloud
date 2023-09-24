package com.gavin.redis;


import com.gavin.util.SerializaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.Collections;


/**
 * 加锁
 *
 * 解锁
 *
 * 超时:过期时间解决
 * 单节点的分布式锁不具备可重入性
 *  https://www.jianshu.com/p/47fd7f86c848
 */

@Component
public class JedisTool {

    //将jedis抽出来封装，然后调用后close
    @Autowired
    JedisPool jedisPool;



    private static final String LOCKSUCCESS = "OK";
    private static final String SETKEYWAY="NX";//仅在k-v不存在的时候设置
    private static final String EXPIRETIME = "PX";//设置到期时间
    private static final long TIMEOUT =600000L;  //十分钟



    /**
     * 加锁方法
     * @param lockId
     * @param lockKey
     * @return
     */
    public   boolean getLock(String lockId,String lockKey) {
        /**
         * NX：原来没有key值才会成功
         * NX=Only set the key if it does not already exist；XX=Only set the key if it already exist
         * EX：单位是秒
         * expire time units: EX = seconds; PX = milliseconds
         * 此参数方法返回：OK
         * lockId:可以是线程id
         */
        Jedis jedis = jedisPool.getResource();
        long start = System.currentTimeMillis();
        //设定获取锁的超时时间
        try {
            while (true) {
                String result = jedis.set(lockKey, lockId, SETKEYWAY, EXPIRETIME, TIMEOUT);
                if (LOCKSUCCESS.equals(result)) {
                    return true;
                }
                //给多一个获取锁时间
             /*   long costTime = System.currentTimeMillis() - start;
                if (costTime>=TIMEOUT) {
                     return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                return false;

            }
        }
        finally {
            jedis.close();
        }
    }

    /**
     * 解锁方法
     * @param id
     * @param lockKey
     * @return
     */
    public  boolean releaseLock(String id,String lockKey) {
      //  long id = Thread.currentThread().getId();
        Jedis jedis = jedisPool.getResource();
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";

        try {
            Object result = jedis.eval(script, Collections.singletonList(lockKey),
                    Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        }finally {
            jedis.close();
        }

    }


    /**
     * setKey
     * 无过期时间，无并发控制
     * @param key
     * @param value
     */


    public  void setKey(final  String key,final  String value) {
        byte[] bytes = SerializaUtil.serialzeKey(value);
        jedisPool.getResource().set(key, String.valueOf(bytes));

    }

    /**
     * 获取key
     * @param key
     */
    public String getKey(final String key) {
        return jedisPool.getResource().get(key);

    }

    /**
     * 通过key删除
     * @param id
     */
    public void deleteObject(final Object id) {
        jedisPool.getResource().del(String.valueOf(id));
    }










}
