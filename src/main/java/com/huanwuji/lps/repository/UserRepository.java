package com.huanwuji.lps.repository;

import com.huanwuji.lps.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
public interface UserRepository extends JpaRepository<User, String> {
}
