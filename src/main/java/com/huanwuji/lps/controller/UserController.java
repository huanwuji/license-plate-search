package com.huanwuji.lps.controller;

import com.huanwuji.lps.controller.support.EntityControllerSupport;
import com.huanwuji.lps.domain.User;
import com.huanwuji.lps.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
@RestController
@RequestMapping("/users")
public class UserController extends EntityControllerSupport<User> {
    @Autowired
    public void setRepository(UserRepository userRepository) {
        commonJpaRepository = userRepository;
    }

    @Override
    public Class mainClass() {
        return User.class;
    }
}
