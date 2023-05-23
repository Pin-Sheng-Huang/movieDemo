package com.example.moviedemo.bean;

/**
 * @program: movieDemo
 * @description:用户类(客户和商家的父类)
 * @Creator: 阿昇
 * @CreateTime: 2023-05-07 18:51
 * @LastEditTime: 2023-05-07 18:51
 */

public class User {
    private String loginName;//假名 不重复
    private String UserName;//真名
    private String Password;//密码
    private String Sex;//性别
    private String Phone;//手机
    private double AccountMoney;//金额

    public User() {
    }

    public User(String loginName, String userName, String password, String sex, String phone, double accountMoney) {
        this.loginName = loginName;
        UserName = userName;
        Password = password;
        Sex = sex;
        Phone = phone;
        AccountMoney = accountMoney;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public double getAccountMoney() {
        return AccountMoney;
    }

    public void setAccountMoney(double accountMoney) {
        AccountMoney = accountMoney;
    }
}
