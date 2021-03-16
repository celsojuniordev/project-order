package com.br.projectorder.domain.repository;

import com.br.projectorder.domain.orm.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
