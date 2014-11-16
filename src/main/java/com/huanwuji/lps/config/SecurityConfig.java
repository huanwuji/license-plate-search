package com.huanwuji.lps.config;

import com.huanwuji.lps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
@Component
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.userDetailsService(userService);
        // @formatter:on
    }
}