package com.br.projectorder.service.impl;

import com.br.projectorder.domain.orm.Delivery;
import com.br.projectorder.domain.repository.DeliveryRepository;
import com.br.projectorder.rabbitmq.model.Message;
import com.br.projectorder.service.DeliveryService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void saveDelivery(Message message) {

        Delivery delivery = new Delivery();
        delivery.setAddress(message.getAddress());
        delivery.setRequestId(message.getRequestId());

        deliveryRepository.save(delivery);
    }
}
