package com.br.projectorder.service.impl;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.repository.CustomerRepository;
import com.br.projectorder.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
