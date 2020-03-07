package com.test_jedis;

import com.test_jedis.config.JedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class BootJedisApplicationTests {

    @Autowired
    private JedisPool pool;
    @Test
    void contextLoads() {
        //TODO:有Bug
        System.out.println(pool); // 测试JedisPool是否连接
        Jedis jedis = pool.getResource();
        jedis.set("name", "guoyujian");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

}
