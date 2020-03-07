package com.test_jedis;

import com.test_jedis.config.JedisConfig;
import com.test_jedis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class BootJedisApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private JedisPool pool;

    @Test
    void contextLoads() {
        System.out.println(pool); // 测试JedisPool是否连接
    }
    @Test
    /**
     * 模拟Jedis操作Redis String类型的数据
     */
    void t1(){
        String value = userService.getString("name");
        System.out.println(value);
    }

}
