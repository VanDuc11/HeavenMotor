package com.example.heaven_motor.model;

public class Users {
    private String id;
    private String passwork;
    private String name;
    private int date;
    private String phone;
    private String CCCD;
    private String address;
    private byte[] img;
    public Users() {
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Users(String id, String passwork, String name, int date, String phone, String CCCD, String address, byte[] img) {
        this.id = id;
        this.passwork = passwork;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.CCCD = CCCD;
        this.address = address;
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }


}
