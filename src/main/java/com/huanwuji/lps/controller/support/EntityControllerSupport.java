package com.huanwuji.lps.controller.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huanwuji.lps.utils.CommonJpaRepository;
import com.huanwuji.lps.utils.search.SearchUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public abstract class EntityControllerSupport<T> {
    protected CommonJpaRepository<T, String> commonJpaRepository;
    protected ObjectMapper objectMapper;

    public EntityControllerSupport() {
        objectMapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> save(@RequestBody T bean) {
        commonJpaRepository.save(bean);
        return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> getItemResource(@PathVariable("id") String id) {
        return new ResponseEntity<T>(commonJpaRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> putItemResource(@PathVariable("id") String id, @RequestBody T bean) {
        commonJpaRepository.save(bean);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemResource(@PathVariable("id") String id) {
        commonJpaRepository.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<?>> search(HttpServletRequest httpRequest, Pageable pageable) {
        Specification specification = SearchUtils.searchProcess(httpRequest, mainClass());
        return new ResponseEntity<Page<?>>(commonJpaRepository.findAll(specification, pageable), HttpStatus.OK);
    }

    public abstract Class mainClass();
}
