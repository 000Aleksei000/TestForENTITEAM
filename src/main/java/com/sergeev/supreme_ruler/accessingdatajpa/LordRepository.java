package com.sergeev.supreme_ruler.accessingdatajpa;

import com.sergeev.supreme_ruler.model.Lord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LordRepository extends CrudRepository<Lord, Long> {
    List<Lord> findByName(String name);

    List <Lord> findByAge(Long age);

}
