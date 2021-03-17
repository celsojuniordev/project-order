package com.br.projectorder.controller;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.orm.Request;
import com.br.projectorder.service.CustomerService;
import com.br.projectorder.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid Request request) {
        return ResponseEntity.ok(requestService.save(request));
    }
}
