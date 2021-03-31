package com.br.projectorder.rabbitmq;

public interface AmqpMessageConsumer<T> {

    void consumer(T t);

}
