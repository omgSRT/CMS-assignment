package com.springframework.assignmentCMS.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public ProviderManager providerManager(AuthenticationProvider authenticationProvider){
        return new ProviderManager(authenticationProvider);
    }
    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        String myKey = "981546";
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm
                = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices(myKey, userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        return rememberMe;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(req -> req
                        .requestMatchers("/register", "/loginmember").permitAll()
                        .anyRequest().hasRole("USER"))
                .formLogin(login -> login
                        .loginPage("/loginmember")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/editprofile")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logoutmember")
                        .logoutSuccessUrl("/loginmember")
                        .clearAuthentication(true)
                        .permitAll())
                .rememberMe(remember -> remember
                        .key("981546")
                        .rememberMeParameter("remember-me")
                        .rememberMeServices(rememberMeServices(getUserDetailsService()))
                        .tokenValiditySeconds(3600));
        return http.build();
    }
}

