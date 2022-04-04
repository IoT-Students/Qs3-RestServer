package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class for holding the login response.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    final private String loginStatus;
    private int userID;
    private String role;
    private String name;
    private String lastName;
    private String email;
    private String JWToken;

    /**
     * The constructor for the login response if it is successful
     * @param loginStatus The status of the login was successful, true
     * @param userID The userId of the user who logged in
     * @param role The role of the user who logged in
     * @param name The name of the user who logged in
     * @param email The email of the user who logged in
     * @param lastName The lastname of the user who logged in
     */
    public LoginResponse(@JsonProperty("loginStatus")  String loginStatus, @JsonProperty("userID") int userID, @JsonProperty("role") String role,@JsonProperty("name") String name,@JsonProperty("email") String email,@JsonProperty("lastName") String lastName) {
        this.loginStatus = loginStatus;
        this.userID = userID;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * The constructor for creating a response if the login fails
     * @param loginStatus fail
     */
    public LoginResponse(@JsonProperty("loginStatus") String loginStatus) {
        this.loginStatus = loginStatus;
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

    public String getJWToken() {
        return JWToken;
    }

    public void setJWToken(String JWToken) {
        this.JWToken = JWToken;
    }

    public String getLastName() {
        return lastName;
    }
}
