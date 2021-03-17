package com.br.projectorder.rabbitmq;

import com.br.projectorder.service.DeliveryService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMQ implements AmqpMessage<Message> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.exchenge.producer}")
    private String exchenge;

    @Autowired
    private DeliveryService deliveryService;

    @Override
    public void producer(Message message) {
        try{
            rabbitTemplate.convertAndSend(exchenge, queue, message);
        } catch (Exception ex){
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }

    @RabbitListener(queues = "rk.producer.request-send")
    public void consumer(Message message) {
        try {
            deliveryService.saveDelivery(message);
        }catch (Exception ex){
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }

}
