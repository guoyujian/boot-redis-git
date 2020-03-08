package com.test_jedis.service;

import com.test_jedis.po.User;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2020/3/7 19:52
 * @Created by 11599
 */
public interface UserService {
    /**
     * Redis有什么命令，Jedis就有什么方法
     * 用户输入一个key，先判断在Redis中是否存在，
     * 若存在则直接返回，若不存在则到mysql中查询数据并赋值给Redis
     * @param key
     * @return
     */
    public String getString(String key);

    /**
     * 操作Redis Hash类型
     * 用户输入一个id，根据id查询用户信息，
     * 先查redis，后查mysql，返回结果对象。
     * @param id
     * @return
     */
    public User selectById(String id);
}
