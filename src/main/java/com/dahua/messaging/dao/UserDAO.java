package com.dahua.messaging.dao;


import com.dahua.messaging.dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

//This file is to (ACTION) put information into the Database
@Mapper
@Repository
public interface UserDAO { // DAO = data access object

    @Insert("INSERT INTO user (username, nickname, email, password, address, gender, register_time, is_valid) " +
            "VALUES (#{username}, #{nickname}, #{email}, #{password}, #{address}, #{gender}, #{registerTime}, #{isValid})")
    void insert(UserDTO userDTO); // userDTO.username

    @Select("SELECT * FROM user WHERE username=#{username}")
    UserDTO selectByUsername(String username);

    @Select("SELECT * FROM user WHERE email=#{email}")
    UserDTO selectByEmail(String email);

    @Update("UPDATE user SET is_valid = 1 WHERE id = #{userId}")
    void updateToValid(int userId);

    @Delete("DELETE FROM user")
    void deleteAll();

    @Update("UPDATE user SET login_token = #{loginToken}, last_login_time = #{lastLoginTime} WHERE id = #{id}")
    void login(int id, String loginToken, Date lastLoginTime);

    @Select("SELECT * FROM user WHERE login_token = #{loginToken}")
    UserDTO selectByLoginToken(String loginToken);

    @Update("UPDATE user SET login_token = null, last_login_time = null WHERE id = #{id}")
    void logout(int id);

    @Select("SELECT * FROM user WHERE id=#{id}")
    UserDTO selectById(int id);
}
