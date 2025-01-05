package com.dahua.messaging.dao;

import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.dto.UserValidationCodeDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//This file is to (ACTION) put information into the Database
@Mapper
@Repository
public interface UserValidationCodeDAO { // DAO = data access object

    @Insert("INSERT INTO user_validation_code (user_id, validation_code)" +
            "VALUES (#{userId}, #{validationCode})")
    void insert(UserValidationCodeDTO userValidationCodeDTO);

    @Select("SELECT * FROM user_validation_code WHERE user_id = #{userId}")
    UserValidationCodeDTO selectByUserId(int userId);

    @Delete("DELETE FROM user_validation_code WHERE id = #{id}")
    void delete(int id);

}
