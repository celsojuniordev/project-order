package com.br.projectorder.domain.repository;

import com.br.projectorder.domain.orm.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
