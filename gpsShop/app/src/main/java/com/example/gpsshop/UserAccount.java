package com.example.gpsshop;

/**
 * 사용자 계정 정보 모델 클래스
 */
public class UserAccount {
    private String idToken; // 고유 토큰
    private String email;
    private String Pwd;
    private String Name;
    private String Age;


    public  UserAccount() {}

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email= email; }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

   /** public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
    **/
}
