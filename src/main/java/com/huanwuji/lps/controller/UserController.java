//package com.huanwuji.lps.controller;
//
//import com.huanwuji.lps.domain.User;
//import com.huanwuji.lps.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Description
// * Date 2014/10/9
// *
// * @author huanwuji
// */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping
//    @Secured("ROLE_ADMIN")
//    @Transactional(readOnly = true)
//    public Page<User> search(int page, int size) {
//        PageRequest pageRequest = new PageRequest(page - 1, size);
//        return userRepository.findAll(pageRequest);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
//    @Secured("ROLE_ADMIN")
//    @Transactional
//    public void save(User user) {
//        userRepository.save(user);
//    }
//}
