package com.test_jedis.po;

import lombok.Data;

/**
 * @Classname User
 * @Description TODO
 * @Date 2020/3/6 13:04
 * @Created by 11599
 */
@Data //相当于getter，setter方法，若要生效，除了引入lombok包，IDEA还需要安装Lombok插件
public class User {
    private String id;
    private String name;
    private Integer age;

}
