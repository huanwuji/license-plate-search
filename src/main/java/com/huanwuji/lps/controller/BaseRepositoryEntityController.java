package com.huanwuji.lps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huanwuji.lps.domain.Customer;
import com.huanwuji.lps.domain.User;
import com.huanwuji.lps.repository.CustomerRepository;
import com.huanwuji.lps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/{repository}")
public class BaseRepositoryEntityController {
    private Map<String, JpaRepository> repositories;
    private Map<String, Class> types;
    private ObjectMapper objectMapper;

    @Autowired
    public BaseRepositoryEntityController(
            UserRepository userRepository,
            CustomerRepository customerRepository) {
        repositories = new HashMap<String, JpaRepository>();
        repositories.put("users", userRepository);
        repositories.put("customers", customerRepository);
        types = new HashMap<String, Class>();
        types.put("users", User.class);
        types.put("customers", Customer.class);
        objectMapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCollectionResource(@PathVariable("repository") String repository, Pageable pageable) {
        return new ResponseEntity<Object>(repositories.get(repository).findAll(pageable), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@PathVariable("repository") String repository, @RequestBody String json)
            throws HttpRequestMethodNotSupportedException, IOException {
        repositories.get(repository).save(objectMapper.readValue(json, types.get(repository)));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemResource(@PathVariable("repository") String repository,
                                             @PathVariable("id") String id) {
        return new ResponseEntity<Object>(repositories.get(repository).findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> putItemResource(@PathVariable("repository") String repository,
                                             @PathVariable("id") String id,
                                             @RequestBody String json) throws IOException {

        repositories.get(repository).save(objectMapper.readValue(json, types.get(repository)));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemResource(@PathVariable("repository") String repository,
                                                @PathVariable("id") String id) {
        repositories.get(repository).delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
