package jira.javarush.com.sociallogin.oauth2;

import jira.javarush.com.config.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class CustomOAuth2User implements OAuth2User {
    private final OAuth2User oauth2User;
    private final AuthUser authUser;

    public CustomOAuth2User(@NonNull OAuth2User oauth2User, @NonNull AuthUser authUser) {
        this.oauth2User = oauth2User;
        this.authUser = authUser;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authUser.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }
}