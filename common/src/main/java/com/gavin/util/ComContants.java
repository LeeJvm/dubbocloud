package com.gavin.util;

/**
 *  常量信息
 * Created by 17428 on 2023/4/1.
 */
public class ComContants {


    public final static String projectName = "dubbocloud";


    //内部类：按照内容类别划分，同一类别一个静态内部类

    public  static class  Permission{

        /**
         * 更新权限
         */
        public final static String permission_update_dubbocloud_ris_rating_risk = "system:rcmsris:updateDubbocloudRisRatingRisk";


    }

    /**
     * 数据字典常量
     */
    public  static  class  DictType{

        /**
         * 城市类型
         */
        public static final String  city_type = "dubblcoud_city_ype";

    }


    /**
     * redis 缓存key
     */
    public static  class  RedisKey{

        public static final String REDIS_SETNX_TEXT="REDIS:SETNX:TEXT";

        private static final String LOCK_KEY_IMP_IMPORT = "IMP:IMPORT";

    }




}
