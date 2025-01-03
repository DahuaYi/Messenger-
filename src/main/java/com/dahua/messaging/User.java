package com.dahua.messaging;

public class User {
    private String name;
    private int age;
    private Detail detail;

    public static class Detail {
        private String driverLicenseNo;
        private String dob;

        public String getDriverLicenseNo() {
            return driverLicenseNo;
        }

        public void setDriverLicenseNo(String driverLicenseNo) {

            System.out.println("DLN: " + driverLicenseNo);
            this.driverLicenseNo = driverLicenseNo;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Set name: " + name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
