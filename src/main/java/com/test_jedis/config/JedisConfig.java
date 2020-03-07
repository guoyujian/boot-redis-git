package com.test_jedis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @Classname JedisConfig
 * @Description TODO
 * @Date 2020/3/6 13:20
 * @Created by 11599
 */
@Configuration  //相当于xml配置文件
public class JedisConfig {
    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    @Value("${spring.redis.host}") // 从公共的配置文件中读取数据
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean //相当于Spring IoC <bean id="jedisPool" class="...JedisPool">
    public JedisPool jedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxTotal(maxActive);

        JedisPool pool = new JedisPool(config, host, port, timeout, password);

        logger.info("Jedis连接成功:"+host+"\t"+port);
        return pool;
    }

}
