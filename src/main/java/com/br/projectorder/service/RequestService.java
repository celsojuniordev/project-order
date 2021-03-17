package com.br.projectorder.service;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.orm.Request;
import com.br.projectorder.domain.repository.CustomerRepository;
import com.br.projectorder.domain.repository.ProductRepository;
import com.br.projectorder.domain.repository.RequestRepository;
import com.br.projectorder.exception.NotFoundException;
import com.br.projectorder.rabbitmq.Message;
import com.br.projectorder.rabbitmq.AmqpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AmqpMessage<Message> amqpMessage;

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
        requestRepository.save(request);

        Message message = new Message();
        message.setAddress(request.getAddress());
        message.setRequestId(request.getId());
        amqpMessage.producer(message);

        return request;
    }
}
