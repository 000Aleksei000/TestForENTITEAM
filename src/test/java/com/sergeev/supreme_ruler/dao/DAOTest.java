package com.sergeev.supreme_ruler.dao;

import com.sergeev.supreme_ruler.accessing.data.jpa.LordRepository;
import com.sergeev.supreme_ruler.accessing.data.jpa.PlanetRepository;
import com.sergeev.supreme_ruler.model.Lord;
import com.sergeev.supreme_ruler.model.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class DAOTest {

    @Autowired
    DAO DAO;

    @Autowired
    LordRepository lordRepository;

    @Autowired
    PlanetRepository planetRepository;


    @Test
    void addLord() {
        Lord lord = new Lord("Gregory Cligakhghnnnnsdfasf", 4888L);
        DAO.addLord(lord);
        Lord lord1 = lordRepository.findByName(lord.getName()).get(0);
        assertEquals(lord.getName(), lord1.getName());
        assertEquals(lord.getId(), lord1.getId());
        assertEquals(lord.getAge(), lord1.getAge());

    }

    @Test
    void addPlanet() {
        Planet planet = new Planet("SSSR");
        DAO.addPlanet(planet);
        Planet planet1 = planetRepository.findByName(planet.getName()).get(0);
        assertEquals(planet1.getId(), planet.getId());
        assertEquals(planet1.getName(), planet.getName());
    }

    @Test
    void lordSetPlanet() {
          Lord lord = new Lord("Lanister", 44L);
          Planet planet = new Planet("Westeros");
          DAO.addPlanet(planet);
          DAO.addLord(lord);
          Long idLord = lordRepository.findByName(lord.getName()).get(0).getId();
          DAO.lordSetPlanet(idLord, planet.getName());
          Planet planet1 = planetRepository.findByName(planet.getName()).get(0);
          assertEquals(idLord, planet1.getLord().getId());

          Lord lord1 = lordRepository.findByName(lord.getName()).get(0);
          assertFalse(lord1.getPlanetSet().isEmpty());
    }

    @Test
    void deletePlanet() {
        Planet planet = new Planet("Jupiter");
        DAO.addPlanet(planet);
        DAO.deletePlanet(planet.getId());
        Optional<Planet> byId = planetRepository.findById(planet.getId());
        assertTrue(byId.isEmpty());
    }

    @Test
    void getAllUnemployedLord() {
        List<Lord> lords = getLordsList(50);
        List<Planet> planets = getPlanetsList(50);
        for (int i = 0; i < 25; i++) {
            planets.get(i).setLord(lords.get(i));
        }
        for (Lord lord : lords) {
            DAO.addLord(lord);
        }
        List<Lord> allUnemployedLord = DAO.getAllUnemployedLord();
        assertEquals(allUnemployedLord.size(), 26);
    }

    @Test
    void getTenYoungestLord() {
        for (int i = 0; i < 10; i++) {
            DAO.addLord(new Lord("Yongest" + i, 0L));
        }
        List<Lord> tenYoungestLord = DAO.getTenYoungestLord();
        for (Lord lord : tenYoungestLord) {
            assertTrue(lord.getAge() == 0);
        }
    }




    private List<Lord> getLordsList (int quantity){
        List<Lord> lords = new ArrayList<>();
        String lord = "Alex";
        Long age = 0L;
        for (int i = 0; i < quantity; i++) {
            lords.add(new Lord(lord+i, age+i));
        }
        return lords;
    }

    private List<Planet> getPlanetsList(int quantity) {
        List<Planet> planets = new ArrayList<>();
        String planetName = "Saturn";
        for (int i = 0; i < quantity; i++) {
            planets.add(new Planet(planetName+i));
        }
        return planets;
    }
}