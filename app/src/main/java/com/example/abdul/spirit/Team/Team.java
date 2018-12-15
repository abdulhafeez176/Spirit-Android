package com.example.abdul.spirit.Team;

public class Team {

    private String teamName;
    private String teamContact;
    private String teamLocation;
    private String teamAddress;
    private String teamEmail;
    private int teamId;
    private int teamMembers;


    public Team(String name,String contact, String email, String location, String address, int members){
        teamName = name;
        teamEmail = email;
        teamContact = contact;
        teamLocation = location;
        teamAddress = address;
        teamMembers = members;
    }

    public Team(String teamName, String teamContact, String teamLocation, String teamAddress, String teamEmail, int teamId, int teamMembers) {
        this.teamName = teamName;
        this.teamContact = teamContact;
        this.teamLocation = teamLocation;
        this.teamAddress = teamAddress;
        this.teamEmail = teamEmail;
        this.teamId = teamId;
        this.teamMembers = teamMembers;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamContact() {
        return teamContact;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public String getTeamAddress() {
        return teamAddress;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getTeamMembers() {
        return teamMembers;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamContact(String teamContact) {
        this.teamContact = teamContact;
    }

    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    public void setTeamAddress(String teamAddress) {
        this.teamAddress = teamAddress;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setTeamMembers(int teamMembers) {
        this.teamMembers = teamMembers;
    }
}
