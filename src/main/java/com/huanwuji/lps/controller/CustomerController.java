package com.huanwuji.lps.controller;

import com.huanwuji.lps.controller.support.EntityControllerSupport;
import com.huanwuji.lps.domain.Customer;
import com.huanwuji.lps.domain.ElasticsearchCustomer;
import com.huanwuji.lps.repository.elasticsearch.ElasticsearchCustomerRepository;
import com.huanwuji.lps.repository.jpa.CustomerRepository;
import com.huanwuji.lps.utils.lang.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description
 * Date 2014/10/9
 *
 * @author huanwuji
 */
@RestController
@RequestMapping("/customers")
public class CustomerController extends EntityControllerSupport<Customer, Long> {
    @Autowired
    private ElasticsearchCustomerRepository elasticsearchCustomerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<Page<?>> solrSearch(String searchText, Pageable pageable) {
        Page<ElasticsearchCustomer> page;
        if (StringUtils.isEmpty(searchText)) {
            page = elasticsearchCustomerRepository.findAll(pageable);
        } else {
            page = elasticsearchCustomerRepository.findAllFields(searchText, pageable);
        }
        return new ResponseEntity<Page<?>>(page, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> save(@RequestBody Customer bean) {
        bean.setId(bean.getNum() + "_" + bean.getEngineNum() + "_" + bean.getCredentialsNum());
        saveElasticsearchCustomer(bean);
        return super.save(bean);
    }

    @Override
    public ResponseEntity putItemResource(@PathVariable("id") String id, @RequestBody Customer bean) {
        saveElasticsearchCustomer(bean);
        return super.putItemResource(id, bean);
    }

    private void saveElasticsearchCustomer(Customer bean) {
        ElasticsearchCustomer elasticsearchCustomer = new ElasticsearchCustomer();
        BeanUtils.copyProperties(bean, elasticsearchCustomer, "registerTime", "validTime", "effectTime", "expireTime");
        elasticsearchCustomer.setRegisterTime(DateTools.format(bean.getRegisterTime()));
        elasticsearchCustomer.setValidTime(DateTools.format(bean.getValidTime()));
        elasticsearchCustomer.setEffectTime(DateTools.format(bean.getEffectTime()));
        elasticsearchCustomer.setExpireTime(DateTools.format(bean.getExpireTime()));
        elasticsearchCustomerRepository.save(elasticsearchCustomer);
    }

    @Override
    public ResponseEntity<?> deleteItemResource(@PathVariable("id") Long id) {
        elasticsearchCustomerRepository.delete(id);
        if (commonJpaRepository.exists(id)) {
            return super.deleteItemResource(id);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public Class mainClass() {
        return Customer.class;
    }
}
