package com.br.projectorder.service;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.orm.Request;
import com.br.projectorder.domain.repository.CustomerRepository;
import com.br.projectorder.domain.repository.ProductRepository;
import com.br.projectorder.domain.repository.RequestRepository;
import com.br.projectorder.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Request save(Request request) {
        Optional<Customer> customer = customerRepository.findById(request.getCustomer().getId());
        if(!customer.isPresent()) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        List<Product> products = new ArrayList<>();
        request.getProducts().forEach(it -> {
            Optional<Product> product = productRepository.findById(it.getId());
            if(!product.isPresent()) {
                throw new NotFoundException("Produto "+it.getId()+" não encontrado.");
            }
            products.add(product.get());
        });
        long totalPrice = products.stream().mapToLong(Product::getPrice).sum();
        request.setFinalPrice(totalPrice);
        return requestRepository.save(request);
    }
}
