package com.huanwuji.lps.controller;

import com.huanwuji.lps.controller.support.EntityControllerSupport;
import com.huanwuji.lps.domain.Customer;
import com.huanwuji.lps.repository.CustomerRepository;
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
@RequestMapping("/customers")
public class CustomerController extends EntityControllerSupport<Customer> {

    @Autowired
    public void setDefaultJpaRepository(CustomerRepository customerRepository) {
        this.defaultJpaRepository = customerRepository;
    }
}
