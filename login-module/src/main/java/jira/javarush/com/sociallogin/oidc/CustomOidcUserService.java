package jira.javarush.com.sociallogin.oidc;

import jira.javarush.com.config.AuthUser;
import jira.javarush.com.stub.UserServiceStub;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOidcUserService extends OidcUserService {
    private final UserServiceStub service;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String email = oidcUser.getEmail();
        //TODO create new user if not exist
        return new CustomOidcUser(oidcUser, new AuthUser(service.getByEmail(email).get()));
    }
}