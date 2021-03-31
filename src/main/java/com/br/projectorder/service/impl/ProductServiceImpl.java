package com.br.projectorder.service.impl;

import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.repository.ProductRepository;
import com.br.projectorder.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
