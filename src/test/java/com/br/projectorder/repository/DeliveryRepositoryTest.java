package com.br.projectorder.repository;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.orm.Request;
import com.br.projectorder.domain.repository.CustomerRepository;
import com.br.projectorder.domain.repository.ProductRepository;
import com.br.projectorder.rabbitmq.model.Message;
import com.br.projectorder.service.DeliveryService;
import com.br.projectorder.service.RequestService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class DeliveryRepositoryTest {

    @Autowired
    private DeliveryService deliveryService;

    @Test
    public void AsaveTest() {
        Message message = new Message();
        message.setAddress("Endereço 1");
        message.setRequestId(1);

        deliveryService.saveDelivery(message);

        assertThat(message.getAddress()).isEqualTo("Endereço 1");
        assertThat(message.getRequestId()).isEqualTo(1);
    }
}
