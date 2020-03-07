package com.test_jedis.service.impl;

import com.test_jedis.service.UserService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2020/3/7 19:55
 * @Created by 11599
 */

@Service
//@Log ==  private Logger logger = LoggerFactory.getLogger(JedisConfig.class);
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private JedisPool jedisPool;  //Jedis连接池


    @Override
    public String getString(String key) {
        String val= null;
        //得到jedis对象
        Jedis jedis = jedisPool.getResource();

        if(jedis.exists(key)){ // redis中存在key
            logger.info("查询Redis中的数据。");
            return jedis.get(key);

        }else{ //Redis中不存在key
            logger.info("查询的是MySql中的数据");
            val = "MySql_Value";
            jedis.set(key, val);

        }
        jedis.close();
        return val;

    }
}
