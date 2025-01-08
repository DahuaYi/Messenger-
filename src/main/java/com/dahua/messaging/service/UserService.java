package com.dahua.messaging.service;

import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dao.UserValidationCodeDAO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.dto.UserValidationCodeDTO;
import com.dahua.messaging.enumeration.Gender;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//This class is user's information logic,
// mainly use for validating formats of user's information
@Service
public class UserService {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Autowired private UserDAO userDAO;
    @Autowired private UserValidationCodeDAO userValidationCodeDAO;
    @Autowired private EmailService emailService;

    public void register(String username,
                         String password,
                         String repeatPassword,
                         String nickname,
                         String address,
                         String email,
                         Gender gender) throws Exception {

        // validation
        if (!password.equals(repeatPassword)) {
            throw new Exception("Passwords are not matched");
        }
        if (!isValidEmail(email)) {
            throw new Exception("Email is not valid");
        }
        if (!isValidString(username) || !isValidString(nickname)) {
            throw new Exception("Username or nickname is not valid");
        }
        if (!isValidString(password) || password.length() < 8) {
            throw new Exception("Password length is not valid");
        }

        if (this.userDAO.selectByUsername(username) != null) {
            throw new Exception("Username already exist");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setNickname(nickname);
        userDTO.setAddress(address);
        userDTO.setEmail(email);
        userDTO.setGender(gender);
        userDTO.setRegisterTime(new Date());
        userDTO.setValid(false);

        this.userDAO.insert(userDTO); //Put information to database

        UserDTO insertedUserDTO = this.userDAO.selectByUsername(username); //从数据库取回用户

        String validationCode = String.format("%06d", new Random().nextInt(1000000)); //创6位数code

        UserValidationCodeDTO userValidationCodeDTO = new UserValidationCodeDTO(); //创一个validationCode 用户的暂存
        userValidationCodeDTO.setUserId(insertedUserDTO.getId()); //暂时绑定用户的id 用于绑定code和id
        userValidationCodeDTO.setValidationCode(validationCode); //Code绑定用户 用于之后扔进validationCode的table

        this.userValidationCodeDAO.insert(userValidationCodeDTO);// 拿到了ID和validation code之后 insert到数据库里

        // send email to "email"

        String title = "Welcome to Our Service!";
        String content = "This is your 6 digits validation codes: " + validationCode;
//        emailService.sendEmail(email, title, content);
        //EmailService is now off

    }

    private static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    private static boolean isValidString(String str) {

        return str != null && str.length() >= 4;
    }

    public void activate(String username, String validationCode) throws Exception{
        UserDTO userDTO = this.userDAO.selectByUsername(username);
        System.out.println(userDTO);
        if (userDTO == null) {
            throw new Exception();
        }

        UserValidationCodeDTO userValidationCodeDTO = this.userValidationCodeDAO.selectByUserId(userDTO.getId());
        System.out.println(userValidationCodeDTO);

        if (userValidationCodeDTO == null) {
            throw new Exception();
        }

        if (!validationCode.equals(userValidationCodeDTO.getValidationCode())) {
            throw new Exception();
        }

        this.userDAO.updateToValid(userDTO.getId());
        this.userValidationCodeDAO.delete(userValidationCodeDTO.getId());


    }

    public void resendValidationCode(String email) throws Exception{
        UserDTO userDTO = this.userDAO.selectByEmail(email);

        String validationCode = String.format("%06d", new Random().nextInt(1000000));
        this.userValidationCodeDAO.deleteByUserId(userDTO.getId()); //delete previous code

        UserValidationCodeDTO userValidationCodeDTO = new UserValidationCodeDTO();
        userValidationCodeDTO.setId(userDTO.getId());
        userValidationCodeDTO.setValidationCode(validationCode);

        this.userValidationCodeDAO.insert(userValidationCodeDTO); //insert validation code into table

        emailService.sendEmail(email, "Validation Code", validationCode);


    }

    public String login(String username, String password) throws Exception {
        UserDTO userDTO = this.userDAO.selectByUsername(username);
        if (!password.equals((userDTO.getPassword()))) {
            throw new Exception("Wrong password");
        }

        String loginToken = RandomStringUtils.randomAlphanumeric(60);
        this.userDAO.login(userDTO.getId(), loginToken, new Date());

        return loginToken;
    }

    public void logout(String loginToken) throws Exception {
        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);

        if (userDTO == null) {
            throw new Exception("Invalid login token");
        }

        this.userDAO.logout(userDTO.getId());



    }
}
