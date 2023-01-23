package com.example.thutabookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping(value = {"/", "/home"})
    public String layoutTest() {
        return "layout/masterLayout";
    }
}
