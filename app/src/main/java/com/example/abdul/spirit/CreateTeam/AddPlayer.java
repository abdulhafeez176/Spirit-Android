package com.example.abdul.spirit.CreateTeam;

public class AddPlayer {
    private String name;
    private String userName;

    public AddPlayer(String a, String b){
        name = a;
        userName = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

