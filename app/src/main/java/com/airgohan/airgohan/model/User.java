package com.airgohan.airgohan.model;

public class User {
    private static final String TAG = User.class.getSimpleName();
    private final User self = this;

    private int mId;
    private String mGender;
    private String mName;
    private int mAge;
    private String mAddress;
    private String mEmail;
    private String mPassword;

    public User(int id, String gender, String name, int age, String address, String email, String password) {
        mId = id;
        mGender = gender;
        mName = name;
        mAge = age;
        mAddress = address;
        mEmail = email;
        mPassword = password;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}