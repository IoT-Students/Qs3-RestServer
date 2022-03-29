package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    final private String loginStatus;
    final private int userID;
    final private String role;
    final private String name;
    final private String email;

    public LoginResponse(@JsonProperty("loginStatus")  String loginStatus, @JsonProperty("userID") int userID, @JsonProperty("role") String role,@JsonProperty("name") String name,@JsonProperty("email") String email) {
        this.loginStatus = loginStatus;
        this.userID = userID;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    @JsonProperty("loginStatus")
    public String getLoginStatus() {
        return loginStatus;
    }

    @JsonProperty("userID")
    public int getUserID() {
        return userID;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
}
