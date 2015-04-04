package com.huanwuji.lps.repository.elasticsearch;

import com.huanwuji.lps.domain.ElasticsearchCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
public interface ElasticsearchCustomerRepository extends ElasticsearchRepository<ElasticsearchCustomer, Long> {
    @Query("{\"multi_match\": {\"query\":  \"?0\",\"fields\": [\"id\",\"num\",\"brand\",\"type\",\"carriageNum\",\"engineNum\",\"color\",\"useType\",\"credentialsNum\",\"credentialsName\",\"customerName\",\"address\",\"phone\",\"mobile\",\"registerTime\",\"validTime\",\"effectTime\",\"expireTime\"]}}")
    public Page<ElasticsearchCustomer> findAllFields(String searchText, Pageable pageable);
}