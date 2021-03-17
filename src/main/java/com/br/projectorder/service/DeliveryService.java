package com.br.projectorder.service;

import com.br.projectorder.domain.orm.Delivery;
import com.br.projectorder.domain.repository.DeliveryRepository;
import com.br.projectorder.rabbitmq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void saveDelivery(Message message) {

        Delivery delivery = new Delivery();
        delivery.setAddress(message.getAddress());
        delivery.setRequestId(message.getRequestId());

        deliveryRepository.save(delivery);
    }
}
