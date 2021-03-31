package com.br.projectorder.service.impl;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.orm.Request;
import com.br.projectorder.domain.repository.CustomerRepository;
import com.br.projectorder.domain.repository.ProductRepository;
import com.br.projectorder.domain.repository.RequestRepository;
import com.br.projectorder.exception.NotFoundException;
import com.br.projectorder.rabbitmq.AmqpMessageProducer;
import com.br.projectorder.rabbitmq.model.Message;
import com.br.projectorder.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final AmqpMessageProducer<Message> amqpMessage;

    public RequestServiceImpl(ProductRepository productRepository, RequestRepository requestRepository, CustomerRepository customerRepository, AmqpMessageProducer<Message> amqpMessage) {
        this.productRepository = productRepository;
        this.requestRepository = requestRepository;
        this.customerRepository = customerRepository;
        this.amqpMessage = amqpMessage;
    }

    @Override
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

        sendMessage(request);

        return request;
    }

    private void sendMessage(Request request) {
        Message message = new Message();
        message.setAddress(request.getAddress());
        message.setRequestId(request.getId());
        amqpMessage.producer(message);
    }
}
