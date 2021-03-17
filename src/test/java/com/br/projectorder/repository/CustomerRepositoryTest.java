package com.br.projectorder.repository;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.repository.CustomerRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void AsaveTest() {
        Customer customer = new Customer();
        customer.setName("Celso");

        customerRepository.save(customer);

        assertThat(customer.getName()).isEqualTo("Celso");
        assertThat(customer.getId()).isEqualTo(1);
    }
}
