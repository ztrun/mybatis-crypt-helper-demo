package com.huiyadan.crypt.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author huiyadanli
 */
@Mapper
public interface UserMapper {

    User query(Long id);

    @Select("select id, name, phone from user where id = #{id}")
    User queryNoEncrypt(Long id);

    int insert(User user);

    int update(User user);
}
