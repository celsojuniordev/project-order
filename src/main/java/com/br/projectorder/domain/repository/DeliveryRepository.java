package com.br.projectorder.domain.repository;

import com.br.projectorder.domain.orm.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {
}
