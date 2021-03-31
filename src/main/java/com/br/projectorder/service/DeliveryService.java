package com.br.projectorder.service;

import com.br.projectorder.rabbitmq.model.Message;

public interface DeliveryService {

    void saveDelivery(Message message);
}
