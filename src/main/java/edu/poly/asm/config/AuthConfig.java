package edu.poly.asm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import edu.poly.asm.impl.UserImpl;
import edu.poly.asm.service.SessionService;
import edu.poly.asm.service.UserService;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    @Autowired
    UserService userService;
    @Autowired
    UserImpl userImpl;
    @Autowired
    SessionService sessionService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                // .requestMatchers("/cart/index", "/register/**", "/login/**").permitAll()
                .requestMatchers("/admin/index").hasRole("ADMIN")
                .requestMatchers("/user/index").hasAnyRole("USER", "ADMIN")
                // .requestMatchers("/admin/index").permitAll()
                // .requestMatchers("/user/index").permitAll()
                .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/login/index")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHanler authenticationSuccessHandler() {
        AuthenticationSuccessHanler successHandler = new AuthenticationSuccessHanler();
        successHandler.setSessionService(sessionService); // Thiết lập SessionService
        return successHandler;
    }

    @Bean
    public UserImpl userimpl() {
        return userImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
