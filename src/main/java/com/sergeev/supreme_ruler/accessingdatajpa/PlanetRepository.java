package com.sergeev.supreme_ruler.accessingdatajpa;

import com.sergeev.supreme_ruler.model.Planet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanetRepository extends CrudRepository<Planet, Long> {
    List<Planet> findByName(String name);

    void deletePlanetByName(String name);

}
