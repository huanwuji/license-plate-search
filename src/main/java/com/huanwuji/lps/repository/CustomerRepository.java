package com.huanwuji.lps.repository;

import com.huanwuji.lps.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
