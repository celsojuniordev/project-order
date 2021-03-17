package com.br.projectorder.rabbitmq;

public interface AmqpMessage<T> {

    void producer(T t);

}
