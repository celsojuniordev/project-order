package com.br.projectorder.domain.repository;

import com.br.projectorder.domain.orm.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
}
