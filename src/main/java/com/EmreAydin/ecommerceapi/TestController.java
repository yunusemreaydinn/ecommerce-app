package com.EmreAydin.ecommerceapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this is a controller for our rest API, so spring has to listen to stuff in here
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
