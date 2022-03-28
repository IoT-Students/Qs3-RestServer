package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {

    private final String username;
    private final String passw;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") final String username, @JsonProperty("passw") final String passw) {
        this.username = username;
        this.passw = passw;
    }


    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("passw")
    public String getPassword() {
        return passw;
    }
}
