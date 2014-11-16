package com.huanwuji.lps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
@Configuration
public class AuthenticationSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().anonymous().and().formLogin()
                .loginPage("/login").failureUrl("/login?error").and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().exceptionHandling()
                .accessDeniedPage("/access?error");
        // @formatter:on
    }

}