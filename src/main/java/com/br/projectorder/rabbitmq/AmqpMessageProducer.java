package com.br.projectorder.rabbitmq;

public interface AmqpMessageProducer<T> {

    void producer(T t);

}
