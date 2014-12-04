package com.huanwuji.lps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.solr.repository.config.EnableSolrRepositories;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Description
 * Date 2014/10/7
 *
 * @author huanwuji
 */
@EnableAutoConfiguration
@ComponentScan
//@EnableSolrRepositories
//@EnableSolrRepositores(basePackages={"com.acme.solr"}, multicoreSupport=true)
//@EnableJpaRepositories(basePackages = "com.huanwuji.lps.repository")
//@EnableGlobalMethodSecurity
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
