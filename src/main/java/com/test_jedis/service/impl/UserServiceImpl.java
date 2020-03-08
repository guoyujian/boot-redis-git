package com.test_jedis.service.impl;

import com.test_jedis.po.User;
import com.test_jedis.service.UserService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public User selectById(String id) {
        User user = null;
        String key ="user:"+id; // 实体类名：id
        //得到jedis对象
        Jedis jedis = jedisPool.getResource();

        if(jedis.exists(key)){
            logger.info("查询的时Redis数据");
            Map<String, String> map = jedis.hgetAll(key);
            user.setId(map.get("id"));
            user.setName(map.get("name"));
            user.setAge(Integer.parseInt(map.get("age")));
        } else {
            //…从Mysql中取数
            user.setId("1");
            user.setName("gyj");
            user.setAge(25);
            logger.info("查询的时MySql数据");
            //存入Redis
            Map<String, String> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("name", user.getName());
            map.put("age", user.getAge()+"");
            jedis.hmset(key, map);
            logger.info("存入Redis");
        }

        //关闭Jedis对象
        jedis.close();

        return user;
    }

}
