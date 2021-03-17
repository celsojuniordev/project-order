package com.br.projectorder.repository;

import com.br.projectorder.domain.orm.Product;
import com.br.projectorder.domain.repository.ProductRepository;
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
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void AsaveTest() {
        Product product = new Product();
        product.setName("Produto 1");
        product.setPrice(1000L);

        productRepository.save(product);

        assertThat(product.getName()).isEqualTo("Produto 1");
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getPrice()).isEqualTo(1000L);
    }
}
