package com.geonho.moneyflow.repository;

import com.geonho.moneyflow.dto.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    void save(User user);
    User findByEmail(@Param("email") String email);
}
