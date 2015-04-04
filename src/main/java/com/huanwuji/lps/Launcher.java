package com.huanwuji.lps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description
 * Date 2014/10/7
 *
 * @author huanwuji
 */
@EnableAutoConfiguration
@ComponentScan
public class Launcher {
    private static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        logger.info("begin start");
        ConfigurableApplicationContext context = SpringApplication.run(Launcher.class);
        logger.info("start complete");
//        ClassPathXmlApplicationContext elasticsearchContext = ElasticsearchLauncher.start(args);
//        CustomerController customerController = context.getBean(CustomerController.class);
//        ElasticsearchCustomerRepository elasticsearchCustomerRepository = elasticsearchContext.getBean(ElasticsearchCustomerRepository.class);
//        customerController.setElasticsearchCustomerRepository(elasticsearchCustomerRepository);
    }
}
