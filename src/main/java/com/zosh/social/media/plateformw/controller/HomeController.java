package com.zosh.social.media.plateformw.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {


    @GetMapping
    public String homeControllerHandler(){
        return "this is Home controller";
    }
    @GetMapping("/home")
    public String homeControllerHandler2(){
        return "this is Home controller2";
    }

    //@PostMapping
    //@DeleteMapping
    //@PutMapping
}
