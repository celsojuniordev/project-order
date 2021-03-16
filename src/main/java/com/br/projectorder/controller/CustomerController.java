package com.br.projectorder.controller;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.aspectj.util.LangUtil.isEmpty;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {

        if (isEmpty(customer.getName())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        return ResponseEntity.ok(service.save(customer));
    }
}
