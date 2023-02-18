package jira.javarush.com.sociallogin.oidc;

import jira.javarush.com.config.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class CustomOidcUser implements OidcUser {
    private final OidcUser oidcUser;
    private final AuthUser authUser;

    public CustomOidcUser(@NonNull OidcUser oidcUser, @NonNull AuthUser authUser) {
        this.oidcUser = oidcUser;
        this.authUser = authUser;
    }

    @Override
    public Map<String, Object> getClaims() {
        return oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcUser.getIdToken();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authUser.getAuthorities();
    }

    @Override
    public String getName() {
        return oidcUser.getName();
    }
}