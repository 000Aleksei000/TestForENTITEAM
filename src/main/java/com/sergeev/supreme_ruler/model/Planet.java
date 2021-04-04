package com.sergeev.supreme_ruler.model;


import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Planet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "lord_id")
    private Lord lord;


    public Planet() {

    }

    public Planet(String name) {
        this.name = name;
    }


    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        lord.getPlanetSet().add(this);
        this.lord = lord;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return id == planet.id && Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", Lord=" + lord +
                '}';
    }
}
