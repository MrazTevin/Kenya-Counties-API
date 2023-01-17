package com.ke.location.web.rest;

import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;
import com.ke.location.service.CountyService;
import com.ke.location.service.SubCountyService;
import com.ke.location.web.rest.dto.CountyDto;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.RestResponse;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(path = "/api/subCounty")
public class SubCountyResource {

    private ModelMapper modelMapper;

    @Autowired
    private SubCountyService subCountyService;
    @Autowired
    private CountyService countyService;

    @GetMapping("/filter-subCounty-countyId")
    public Page<SubCounty> filterBySubCountyAndCountyId(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "countyId", required = false) Integer countyId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return subCountyService.filterByNameAndCountyId(name, countyId, page, size);
    }

    @GetMapping("/filter-ward-countyId")
    public Page<SubCounty> filterByWardAndCountyId(@RequestParam(value = "ward", required = false) String ward, @RequestParam(value = "countyId", required = false) Integer countyId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return subCountyService.filterByWardAndCountyId(ward, countyId, page, size);
    }

    @GetMapping("/filter-ward-subCounty")
    public Page<SubCounty> filterByWardAndSubCountyName(@RequestParam(value = "ward", required = false) String ward, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return subCountyService.filterByWardAndName(ward, name, page, size);
    }


    @GetMapping("/filter")
    public Page<SubCounty> filterByWardSubCountyNameCountyId(@RequestParam(value = "ward", required = false) String ward, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "countyId", required = false) Integer countyId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return subCountyService.filterByWardAndSubCountyNameCountyId(ward, name, countyId, page, size);
    }


}
