package com.huanwuji.lps.repository;

import com.huanwuji.lps.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
@RepositoryRestResource()
public interface CustomerInfoRepository extends JpaRepository<Customer, String> {
}
