package jira.javarush.com.config;

import jira.javarush.com.stub.User;

public class AuthUser extends org.springframework.security.core.userdetails.User{
    private User user;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
