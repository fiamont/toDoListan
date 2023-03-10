package se.sofiamontgomery.todolistan.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import se.sofiamontgomery.todolistan.user.UserService;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    private final AppPasswordConfig bcrypt;
    private final UserService userService;

    @Autowired
    public AppSecurityConfig(AppPasswordConfig bcrypt, UserService userService) {
        this.bcrypt = bcrypt;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests( requests -> {
                        requests
                                .requestMatchers("/", "/error", "/static/**", "/login", "/logout", "/about","/register").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated();
                        }

                )
                .formLogin( login -> {
                            login.loginPage("/login");
                        }
                )
                .rememberMe( rememberMe -> {
                        rememberMe
                                .rememberMeParameter("remember-me")
                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                                .key("SomeSecureKey")
                                .userDetailsService(userService);
                        }
                )
                .logout( logout -> {
                    logout
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .deleteCookies("remember-me", "JSESSIONID");
                })
                .authenticationProvider(authenticationOverride());

        return http.build();
    }

    public DaoAuthenticationProvider authenticationOverride() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(bcrypt.bcryptPasswordEncoder());

        return provider;
    }
}
