package com.geonho.moneyflow.repository;

import com.geonho.moneyflow.dto.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (username, email, password) VALUES (#{username}, #{email}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(@Param("email") String email);
}
