package com.huanwuji.lps.controller.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huanwuji.lps.utils.CommonJpaRepository;
import com.huanwuji.lps.utils.search.SearchUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public abstract class EntityControllerSupport<T, ID extends Serializable> {
    protected CommonJpaRepository<T, ID> commonJpaRepository;
    protected ObjectMapper objectMapper;

    public EntityControllerSupport(CommonJpaRepository commonJpaRepository) {
        objectMapper = new ObjectMapper();
        this.commonJpaRepository = commonJpaRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> save(@RequestBody T bean) {
        commonJpaRepository.save(bean);
        return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> getItemResource(@PathVariable("id") ID id) {
        return new ResponseEntity<T>((T) commonJpaRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity putItemResource(@PathVariable("id") String id, @RequestBody T bean) {
        commonJpaRepository.save(bean);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemResource(@PathVariable("id") ID id) {
        commonJpaRepository.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> search(HttpServletRequest httpRequest, Pageable pageable, @RequestHeader(value = "x-page-request", required = false) Boolean isPageRequest) {
        Specification specification = SearchUtils.searchProcess(httpRequest, mainClass());
        if (BooleanUtils.isNotFalse(isPageRequest)) {
            return new ResponseEntity(commonJpaRepository.findAll(specification, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity(commonJpaRepository.findAll(specification), HttpStatus.OK);
        }
    }

    public abstract Class mainClass();
}