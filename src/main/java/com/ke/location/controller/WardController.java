package com.ke.location.controller;

import com.ke.location.entity.Ward;
import com.ke.location.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/v1")
public class WardController {
    @Autowired
    private WardService wardService;
    @PostMapping("/ward")
    public Ward addWard(@RequestBody Ward ward){
        return wardService.addWard(ward);
    }
}
