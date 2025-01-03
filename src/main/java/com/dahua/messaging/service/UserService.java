package com.dahua.messaging.service;

import java.util.Date;
import java.util.regex.Pattern;

import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.enumeration.Gender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public void register(String username,
                         String password,
                         String repeatPassword,
                         String nickname,
                         String address,
                         String email,
                         Gender gender) throws Exception {

        // validation
        if (!password.equals(repeatPassword)) {
            throw new Exception();
        }
        if (!isValidEmail(email)) {
            throw new Exception();
        }
        if (!isValidString(username) || !isValidString(nickname)) {
            throw new Exception();
        }
        if (!isValidString(password) || password.length() < 8) {
            throw new Exception();
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
}
