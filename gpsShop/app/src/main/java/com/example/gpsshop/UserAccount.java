package com.example.gpsshop;

/*
    사용자 계정 정보 모델 클래스
 */
public class UserAccount {

    private String idToken;     // firebase Uid (고유 토큰정보)
    private String emailId;     // 이메일 아이디
    private String password;    // 비밀번호
    private String name;        // 성명
    private String age;            // 나이
    private String tel;         // 전화번호

    public UserAccount() { } // firebase 사용시 빈 생성자를 만들어줘야함


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
