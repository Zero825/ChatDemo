package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    private static final String INDEX = "web/index.html";

    @RequestMapping("/")
    public String getIndex() {
        return INDEX;
    }

}
