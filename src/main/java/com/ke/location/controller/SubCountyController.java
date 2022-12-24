package com.ke.location.controller;

import com.ke.location.entity.SubCounty;
import com.ke.location.entity.Ward;
import com.ke.location.service.SubCountyService;
import com.ke.location.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/v1")
public class SubCountyController {
    @Autowired
    private SubCountyService subCountyService;
    @PostMapping("/subCounty")
    public SubCounty addSubCounty(@RequestBody SubCounty subCounty){
        return subCountyService.addSubCounty(subCounty);
    }
}
