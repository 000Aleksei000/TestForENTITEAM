package com.sergeev.supreme_ruler.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Lord {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long age;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "planet_id")
    private Set<Planet> planetSet = new HashSet<>();

    public Lord() {
    }

    public Lord(String name, Long age) {
        this.name = name;
        this.age = age;
    }


    public Set<Planet> getPlanetSet() {
        return planetSet;
    }

    public void addPlanet(Planet planet){
        planet.setLord(this);
        planetSet.add(planet);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lord)) return false;
        Lord lord = (Lord) o;
        return id == lord.id && Objects.equals(name, lord.name) && Objects.equals(age, lord.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "Lord{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
