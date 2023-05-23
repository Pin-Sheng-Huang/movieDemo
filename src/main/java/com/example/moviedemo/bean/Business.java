package com.example.moviedemo.bean;

/**
 * @program: movieDemo
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-05-07 18:57
 * @LastEditTime: 2023-05-07 18:57
 */

public class Business extends User{
    //店名称
    private String shopName;
    //地址
    private String address;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
