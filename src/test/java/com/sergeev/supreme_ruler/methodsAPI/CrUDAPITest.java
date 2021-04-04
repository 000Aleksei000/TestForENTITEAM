package com.sergeev.supreme_ruler.methodsAPI;

import com.sergeev.supreme_ruler.accessingdatajpa.LordRepository;
import com.sergeev.supreme_ruler.accessingdatajpa.PlanetRepository;
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
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class CrUDAPITest {

    @Autowired
    CrUDAPI crUDAPI;

    @Autowired
    LordRepository lordRepository;

    @Autowired
    PlanetRepository planetRepository;


    @Test
    void addLord() {
        Lord lord = new Lord("Gregory Cligakhghnnnnsdfasf", 4888L);
        crUDAPI.addLord(lord);
        Lord lord1 = lordRepository.findByName(lord.getName()).get(0);
        assertEquals(lord.getName(), lord1.getName());
        assertEquals(lord.getId(), lord1.getId());
        assertEquals(lord.getAge(), lord1.getAge());

    }

    @Test
    void addPlanet() {
        Planet planet = new Planet("SSSR");
        crUDAPI.addPlanet(planet);
        Planet planet1 = planetRepository.findByName(planet.getName()).get(0);
        assertEquals(planet1.getId(), planet.getId());
        assertEquals(planet1.getName(), planet.getName());
    }

    @Test
    void lordSetPlanet() {
          Lord lord = new Lord("Lanister", 44L);
          Planet planet = new Planet("Westeros");
          crUDAPI.addPlanet(planet);
          crUDAPI.addLord(lord);
          Long idLord = lordRepository.findByName(lord.getName()).get(0).getId();
          crUDAPI.lordSetPlanet(idLord, planet.getName());
          Planet planet1 = planetRepository.findByName(planet.getName()).get(0);
          assertEquals(idLord, planet1.getLord().getId());

          Lord lord1 = lordRepository.findByName(lord.getName()).get(0);
          assertFalse(lord1.getPlanetSet().isEmpty());
    }

    @Test
    void deletePlanet() {
        Planet planet = new Planet("Jupiter");
        crUDAPI.addPlanet(planet);
        crUDAPI.deletePlanet(planet.getId());
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
            crUDAPI.addLord(lord);
        }
        List<Lord> allUnemployedLord = crUDAPI.getAllUnemployedLord();
        assertEquals(allUnemployedLord.size(), 26);
    }

    @Test
    void getTenYoungestLord() {
        for (int i = 0; i < 10; i++) {
            crUDAPI.addLord(new Lord("Yongest" + i, 0L));
        }
        List<Lord> tenYoungestLord = crUDAPI.getTenYoungestLord();
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