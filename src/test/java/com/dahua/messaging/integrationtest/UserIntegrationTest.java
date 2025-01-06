package com.dahua.messaging.integrationtest;

import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dao.UserValidationCodeDAO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.dto.UserValidationCodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Integration Test
@SpringBootTest
@AutoConfigureMockMvc //like Postman
class UserIntegrationTest {



    @Autowired private MockMvc mockMvc;
    @Autowired private UserDAO userDAO;
    @Autowired private UserValidationCodeDAO userValidationCodeDAO;

    @BeforeEach
    void cleanUpOldData() { //clean data before test because test ok will be put in database
        this.userDAO.deleteAll();
        this.userValidationCodeDAO.deleteAll();
    }

    @Test
    void testRegister_passwordsNotMatched_returnsErrorMessage() throws Exception { //format: test${target}_${Scenario}_${Expectation}
        String body = """
                {
                    "username": "aaa",
                    "password": "1221212121",
                    "repeatPassword": "12212121219",
                    "email": "kinyeung.huang@gmail.com",
                    "nickname": "nickname",
                    "address": "address",
                    "gender": "FEMALE"
                }
                """;
        this.mockMvc.perform(post("/users/register")
                                        .contentType(MediaType.APPLICATION_JSON )
                                        .content(body))
                .andExpect(status().isBadRequest())       //status code in response is 200. bad is 400
                .andExpect(content().string("Passwords are not matched"));
    }

    @Test
    void testRegister_validRequest_returnsOk() throws Exception {
        String body = """
                {
                    "username": "bbbbb",
                    "password": "1221212121",
                    "repeatPassword": "1221212121",
                    "email": "bbb@gmail.com",
                    "nickname": "nickname",
                    "address": "address",
                    "gender": "FEMALE"
                }
                """;
        this.mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(body))
                .andExpect(status().isOk())       //status code in response is 200. bad is 400
                .andExpect(content().string(""));


        UserDTO userDTO = this.userDAO.selectByUsername("bbbbb");
        assertNotNull(userDTO); // assert userDTO != null;
        assertEquals("bbbbb", userDTO.getUsername());
        assertFalse(userDTO.getValid());

        UserValidationCodeDTO userValidationCodeDTO = this.userValidationCodeDAO.selectByUserId((userDTO.getId()));
        assertEquals(userDTO.getId(), userValidationCodeDTO.getUserId());
        assertEquals(6, userValidationCodeDTO.getValidationCode().length());
    }

    @Test
    void testActivate_validRequest_returnsOk() throws Exception {
        String body = """
                {
                    "username": "activate",
                    "password": "1221212121",
                    "repeatPassword": "1221212121",
                    "email": "activate@gmail.com",
                    "nickname": "nickname",
                    "address": "address",
                    "gender": "FEMALE"
                }
                """;
        this.mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())       //status code in response is 200. bad is 400
                .andExpect(content().string(""));

        UserDTO userDTO = this.userDAO.selectByUsername("activate");
        UserValidationCodeDTO userValidationCodeDTO = this.userValidationCodeDAO.selectByUserId((userDTO.getId()));

        String code = userValidationCodeDTO.getValidationCode();

        String body2 = String.format("""
                {
                    "username": "activate",
                    "validationCode": "%s"
                }
                """, code);

        this.mockMvc.perform(post("/users/activate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body2))
                .andExpect(status().isOk())       //status code in response is 200. bad is 400
                .andExpect(content().string(""));


        UserDTO userDTO2 = this.userDAO.selectByUsername("activate");

        assertEquals(true, userDTO2.getValid());
    }
}
