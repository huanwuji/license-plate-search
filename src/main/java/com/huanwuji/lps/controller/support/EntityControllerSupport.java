package com.huanwuji.lps.controller.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class EntityControllerSupport<T> {
    protected JpaRepository<T, String> defaultJpaRepository;
    protected ObjectMapper objectMapper;

    public EntityControllerSupport() {
        objectMapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<T>> getCollectionResource(Pageable pageable) {
        return new ResponseEntity<Page<T>>(defaultJpaRepository.findAll(pageable), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> save(@RequestBody T bean) {
        defaultJpaRepository.save(bean);
        return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> getItemResource(@PathVariable("id") String id) {
        return new ResponseEntity<T>(defaultJpaRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> putItemResource(@PathVariable("id") String id, @RequestBody T bean) {
        defaultJpaRepository.save(bean);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemResource(@PathVariable("id") String id) {
        defaultJpaRepository.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
