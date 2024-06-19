package com.QuanLyChungCu_v2.QuanLyChungCu.security;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.security.service.UserDetailsImpl;
import com.QuanLyChungCu_v2.QuanLyChungCu.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;


    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) ->
                        auth
//                                .requestMatchers("/rooms/**").hasAuthority("ROLE_ADMIN")
//                                .requestMatchers("/api/test/admin").hasAuthority("ROLE_ADMIN")
//                                .requestMatchers("/api/test/user").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
//                                .requestMatchers("/", "/home").permitAll()
                                .anyRequest().authenticated())


                .formLogin(formlogin -> formlogin
                                .loginPage("/login")
                                .loginProcessingUrl("/j_spring_security_login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .permitAll()
                                .successHandler(loginSuccessHandler)
//                                .successHandler((request, response, authentication) -> {
//                                    // Manually handle the redirection to the root page
//                                    response.sendRedirect("/");
//                                })
                )

                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll())
                .rememberMe(rm ->
                        rm.tokenValiditySeconds(7 * 24 * 60 * 60)
                                .key("AbcdefghiJklmNoPqRstUvXyz"))
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/access-denied"));

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetailsImpl user = new UserDetailsImpl((UserEntity) User.builder().username("admin").password("admin").build());
//        return new InMemoryUserDetailsManager(user);
//    }
}
