package com.sergeev.supreme_ruler.controller;

import com.sergeev.supreme_ruler.methodsAPI.CrUDAPI;
import com.sergeev.supreme_ruler.model.Lord;
import com.sergeev.supreme_ruler.model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class ControllerForApp {
    @Autowired
    private CrUDAPI crUDAPI;

    @GetMapping("/referendum")
    public String referendum() {
        return "referendum";
    }

    @GetMapping("/addLord")
    public String getAddLord() {
        return "addLord";
    }

    @PostMapping("/addLord")
    public String postAddLord(HttpServletRequest request) {
        String name = request.getParameter("Name");
        Long age = Long.parseLong(request.getParameter("Age"));
        if (name == null || age < 0) {
            return "fail";
        }
        Lord lord = new Lord(name, age);
        crUDAPI.addLord(lord);
        return "success";
    }

    @GetMapping("/addPlanet")
    public String getAddPlanet() {
        return "addPlanet";
    }

    @PostMapping("/addPlanet")
    public String postAddPlanet(HttpServletRequest request) {
        String planetName = request.getParameter("Name");
        if (planetName == null) {
            return "fail";
        }
        Planet planet = new Planet(planetName);
        crUDAPI.addPlanet(planet);
        return "success";
    }

    @GetMapping("/lordSetPlanet")
    public String lordSetPl() {
        return "lordSetPlanet";
    }

    @PostMapping("/lordSetPlanet")
    public String lordSetPlanet(HttpServletRequest request) {
        Long lordId = Long.parseLong(request.getParameter("lordId"));
        String planetName = request.getParameter("planetName");
        if (lordId < 0 || planetName == null) {
            return "fail";
        }
        crUDAPI.lordSetPlanet(lordId, planetName);
        return "success";
    }

    @GetMapping("/deletePlanet")
    public String delPla() {
        return "deletePlanet";
    }

    @PostMapping("/deletePlanet")
    public String deletePlanet(HttpServletRequest request) {
        Long planetId = Long.parseLong(request.getParameter("planetId"));
        if (planetId < 0) {
            return "fail";
        }
        crUDAPI.deletePlanet(planetId);
        return "success";
    }

    @GetMapping("/findUnemploedLord")
    public String findUnemplLord(Model model) {
        model.addAttribute("Lords", crUDAPI.getAllUnemployedLord());
        return "unemplLord";
    }

    @GetMapping("/top-10yongest")
    public String top10(Model model) {
        model.addAttribute("Top10Lords", crUDAPI.getTenYoungestLord());
        return "top10";
    }
}
