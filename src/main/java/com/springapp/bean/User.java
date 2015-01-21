package com.springapp.bean;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    @NotBlank(message = "User name cannot be blank.")
    @Size(max = 30, min = 3, message = "Username must be between 3 and 30 characters long.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters long.")
    private String password;

    @NotBlank
    @Email
    private String email;
    private boolean enabled = false;
    private String authority;


    public User() {

    }

    public User(String username, String password, String email, boolean enabled,
                String authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                '}';
    }
}
