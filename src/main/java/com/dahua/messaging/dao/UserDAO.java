package com.dahua.messaging.dao;


import com.dahua.messaging.dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
}
