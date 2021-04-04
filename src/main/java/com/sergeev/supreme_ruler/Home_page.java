package com.sergeev.supreme_ruler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home_page {

    @GetMapping("/")
    public String goToHome() {
        return "home";
    }
}
