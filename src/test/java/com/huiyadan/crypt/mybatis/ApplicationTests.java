package com.huiyadan.crypt.mybatis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    private Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {
        // 新增
        User u1 = new User();
        u1.setId(1L);
        u1.setName("李二狗");
        u1.setPhone("1390000001");
        userMapper.insert(u1);

        // 查询数据库实际新增的内容（加密后的内容）
        User u2 = userMapper.queryNoEncrypt(1L);
        logger.info("新增后数据库中存储的手机号:{}", u2.getPhone());
        Assertions.assertNotEquals(u1.getPhone(), u2.getPhone());

        // 查询（自动解密）
        User u3 = userMapper.query(1L);
        Assertions.assertEquals(u1.getPhone(), u3.getPhone());

        // 更新
        User u4 = new User();
        u4.setId(1L);
        u4.setPhone("1390000002");
        userMapper.update(u4);

        // 查询数据库实际新增的内容（加密后的内容）
        User u5 = userMapper.queryNoEncrypt(1L);
        logger.info("更新后数据库中存储的手机号:{}", u5.getPhone());
        Assertions.assertNotEquals(u2.getPhone(), u5.getPhone());

        // 查询（自动解密）
        User u6 = userMapper.query(1L);
        Assertions.assertEquals(u4.getPhone(), u6.getPhone());
    }

}
