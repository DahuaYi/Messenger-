package com.dahua.messaging.dto;

    public class UserValidationCodeDTO {

        private int id;
        private int userId; //foreign key 用于跟user table关联 UserDTO.id == UserValidationCodeDTO.userID
        private String validationCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getValidationCode() {
            return validationCode;
        }

        public void setValidationCode(String validationCode) {
            this.validationCode = validationCode;
        }

        @Override
        public String toString() {
            return "UserValidationCodeDTO{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", validationCode='" + validationCode + '\'' +
                    '}';
        }
    }
