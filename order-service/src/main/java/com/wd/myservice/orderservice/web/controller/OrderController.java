package com.wd.myservice.orderservice.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @GetMapping("login")
    public Object login() {
        return "order  aaaa";
    }

}
