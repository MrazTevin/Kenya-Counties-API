package com.ke.location.web.rest;


import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;
import com.ke.location.service.CountyService;
import com.ke.location.service.SubCountyService;




import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api1/subCounty")
public class SubCountyResource {

    private ModelMapper modelMapper;

    @Autowired
    private SubCountyService subCountyService;


    @GetMapping("/{countyId}")
    public ResponseEntity<List<SubCounty>> getSubCountiesByCountyId(@PathVariable Integer countyId) {
        List<SubCounty> subCounties = subCountyService.getSubCountiesByCountyId(countyId);
        return ResponseEntity.ok().body(subCounties);
    }

    @GetMapping
    public ResponseEntity<List<SubCounty>> getAllWards() {
        List<SubCounty> wards = subCountyService.getAllWards();
        return ResponseEntity.ok().body(wards);
    }
//    @GetMapping("{name}")
//    public ResponseEntity<List<SubCounty>> getWardsByName(@PathVariable String name) {
//        List<SubCounty> wards = subCountyService.getWardsByName(name);
//        return ResponseEntity.ok().body(wards);
//    }
@GetMapping("/filter")
public ResponseEntity<List<SubCounty>> filterByCountyAndSubcountyAndWardAndSearch(
        @RequestParam(required = false) Integer countyId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String ward,
        @RequestParam(required = false) String search) {
    List<SubCounty> filteredWards = subCountyService.filterByCountyAndSubcountyAndWardAndSearch(countyId, name, ward, search);
    return new ResponseEntity<>(filteredWards, HttpStatus.OK);
}


}
