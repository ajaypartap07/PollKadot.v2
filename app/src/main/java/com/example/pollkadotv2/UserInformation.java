package com.example.pollkadotv2;

public class UserInformation {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String address;

    public UserInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
