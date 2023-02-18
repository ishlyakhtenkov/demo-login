package jira.javarush.com.config;

import jira.javarush.com.stub.UserServiceStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().cors()
                .and().formLogin(withDefaults())
                .oauth2Login();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailService(UserServiceStub service) {
        return email -> new AuthUser(service.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + email + "' was not found")));
    }
}