package com.br.projectorder.domain.orm;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Cliente é obrigatório")
    private Customer customer;

    @OneToMany
    @JoinTable(name="request_product",
            joinColumns={@JoinColumn(name="request_id")},
            inverseJoinColumns={@JoinColumn(name="product_id")})
    @NotNull(message = "Produto é obrigatório")
    private List<Product> products;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    private Long finalPrice;
}
