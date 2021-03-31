package com.br.projectorder.repository;

import com.br.projectorder.domain.orm.Customer;
import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.orm.Request;
import com.br.projectorder.domain.repository.CustomerRepository;
import com.br.projectorder.domain.repository.ProductRepository;
import com.br.projectorder.domain.repository.RequestRepository;
import com.br.projectorder.service.CustomerService;
import com.br.projectorder.service.ProductService;
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
public class RequestRepositoryTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RequestService requestService;

    @Test
    public void AsaveTest() {
        Product product1 = new Product();
        product1.setName("Produto 1");
        product1.setPrice(1000L);

        Product product2 = new Product();
        product2.setName("Produto 2");
        product2.setPrice(1000L);

        productService.save(product1);
        productService.save(product2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);


        Customer customer = new Customer();
        customer.setName("Celso");

        customerService.save(customer);

        Request request = new Request();
        request.setAddress("Rua teste 1");
        request.setProducts(products);
        request.setCustomer(customer);

        requestService.save(request);

        assertThat(request.getFinalPrice()).isEqualTo(2000L);
        assertThat(request.getId()).isEqualTo(1);
    }
}
