package com.simplefaas.registry.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {


    @RequestMapping("/register")
    public String register(String ip,long tasknum,String chash){


        return "ok";
    }

}
