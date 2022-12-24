package com.ke.location.controller;

import com.ke.location.entity.County;
import com.ke.location.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/v1")
public class CountyController {
    @Autowired
    private CountyService countyService;
    @PostMapping("/county")
    public County addCounty(@RequestBody County county){
        return countyService.addCounty(county);
    }
}
