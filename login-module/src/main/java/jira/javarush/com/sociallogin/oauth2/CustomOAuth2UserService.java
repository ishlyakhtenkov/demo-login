package jira.javarush.com.sociallogin.oauth2;

import jira.javarush.com.config.AuthUser;
import jira.javarush.com.stub.Role;
import jira.javarush.com.stub.User;
import jira.javarush.com.stub.UserServiceStub;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserServiceStub service;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        if (email == null) {
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT, "Your GitHub account does not provide a public email", ""));
        }
        String name = oAuth2User.getAttribute("name") == null ? oAuth2User.getAttribute("name") : oAuth2User.getAttribute("login");
        return new CustomOAuth2User(oAuth2User, new AuthUser(service.getByEmail(email).orElseGet(() ->
                service.create(new User(null, name, email, "social_password", true, Set.of(Role.USER))))));
    }
}
