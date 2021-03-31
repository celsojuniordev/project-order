package com.br.projectorder.rabbitmq.consumer;

import com.br.projectorder.rabbitmq.AmqpMessageConsumer;
import com.br.projectorder.rabbitmq.model.Message;
import com.br.projectorder.service.DeliveryService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRabbitMQImpl implements AmqpMessageConsumer<Message> {

    private final DeliveryService deliveryService;

    public ConsumerRabbitMQImpl(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
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
