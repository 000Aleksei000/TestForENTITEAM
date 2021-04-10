package com.sergeev.supreme_ruler.dao;

import com.sergeev.supreme_ruler.accessing.data.jpa.LordRepository;
import com.sergeev.supreme_ruler.accessing.data.jpa.PlanetRepository;
import com.sergeev.supreme_ruler.model.Lord;
import com.sergeev.supreme_ruler.model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DAO {
    @Autowired
    private LordRepository lordRepository;

    @Autowired
    private PlanetRepository planetRepository;


    public void addLord(Lord lord) {
        if(lord.getName() == null || lord.getAge()<0){
            throw new NullPointerException("Убедитесь в корректности данных.");
        }
        lordRepository.save(lord);
    }

    public void addPlanet(Planet planet) {
        if(planet.getName() == null){
            throw new NullPointerException("Убедитесь в корректности данных.");
        }
        planetRepository.save(planet);
    }

    public void lordSetPlanet(Long lordId, String planetName) { //назначить повелителя управлять планетой

        if (lordId == null || planetName == null) {
            throw new NullPointerException("Убедитесь в корректности данных.");
        }
        Lord lord = lordRepository.findById(lordId).get();
        List<Planet> planets = planetRepository.findByName(planetName);
        for (Planet planet : planets) {
            planet.setLord(lord);
            lordRepository.save(lord);
            //planetRepository.save(planet);
        }
    }

    public void deletePlanet(Long planetId) {
        planetRepository.deleteById(planetId);
    }

    public List <Lord> getAllUnemployedLord() {
        List<Lord> result = new ArrayList<>();
        Iterable<Lord> allLord = lordRepository.findAll();
        for(Lord lord : allLord){
            Set<Planet> planetSet = lord.getPlanetSet();
            if (planetSet.isEmpty()) {
                result.add(lord);
            }
        }
        return result;
    }

    public List<Lord> getTenYoungestLord() {
        List<Lord> result = new ArrayList<>();
        Set<Long> ageSet = new TreeSet<>();
        Iterable<Lord> allLord = lordRepository.findAll();
        for(Lord lord : allLord){
            ageSet.add(lord.getAge());
        }
        if(ageSet.size()<10){
            for (Lord lord : allLord) {
                result.add(lord);
            }
            return result;
        }
        else {
            int i = 0;
            for (Long age : ageSet) {
                if(i==10){
                    break;
                }
                System.out.println(age);
                List<Lord> byAge = lordRepository.findByAge(age);
                for (Lord lord : byAge) {
                    result.add(lord);
                    i++;
                    if(i==10){
                        break;
                    }
                }
            }
        }
        return result;

    }
}
