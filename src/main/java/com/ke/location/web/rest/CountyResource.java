package com.ke.location.web.rest;

import com.ke.location.entity.County;
import com.ke.location.service.CountyService;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.RestResponse;

import com.ke.location.web.rest.request.CountyRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@RestController
@RequestMapping(path = "/api/county")

public class CountyResource {
    @Autowired
    private CountyService countyService;

    private  ModelMapper modelMapper;
//    @PostMapping("/county")
//    ResponseEntity<County> addCounty(@RequestBody County county){
//        log.info("request to add new County");
//
//        County newCounty = countyService.addCounty(county);
//
//        return new ResponseEntity<>(newCounty, HttpStatus.OK);
////        return countyService.addCounty(county);
//    }
    @GetMapping(path = "{county_name}")
    ResponseEntity<?> findBycounty_name(@PathVariable("county_name") String  county_name) {

        try {
            Optional<County> countyOptional = countyService.getCountyByCounty_name(county_name);

            if (countyOptional.isPresent()) {
                County county = countyOptional.get();

                CountyRequest response = modelMapper.map(county, CountyRequest.class);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RestResponse( true, "County not found"), HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            log.error("error ", e);
            return new ResponseEntity<>(new RestResponse(true, "County not found, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//    @GetMapping
//    ResponseEntity<?> getAll(@RequestParam("per_page") Integer perPage,
//                             @RequestParam("page") Integer page,
//                             @RequestParam(name="search", required = false) String search,
//                             @RequestParam(name = "county_id", required = false) Integer countyId) {
//        log.info("Getting all Counties");
//
//        try {
//
//            ListResponse listResponse = countyService.getAllCounties(page, perPage, search, countyId);
//            return new ResponseEntity<>(listResponse, HttpStatus.OK);
//        } catch (Exception e) {
//            log.error("Error ", e);
//            return new ResponseEntity(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
    @GetMapping
    public ResponseEntity<Page<County>> getAllCounties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name="search", required = false) String search) {
        Page<County> counties = countyService.getAllCounties(PageRequest.of(page, size));
        return ResponseEntity.ok().body(counties);
    }
}
