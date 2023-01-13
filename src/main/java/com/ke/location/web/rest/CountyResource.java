package com.ke.location.web.rest;

import com.ke.location.entity.County;
import com.ke.location.service.CountyService;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.RestResponse;
import com.ke.location.web.rest.request.CountyRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@RequestMapping(path = "/api/v1")
public class CountyResource {
    @Autowired
    private CountyService countyService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/county")
    public County addCounty(@RequestBody County county){
        return countyService.addCounty(county);
    }
    @GetMapping(path = "{name}")
    ResponseEntity<?> findByName(@PathVariable("name") String  name) {

        try {
            Optional<County> countyOptional = countyService.getCountyByName(name);

            if (countyOptional.isPresent()) {
                County county = countyOptional.get();

                CountyRequest response = modelMapper.map(county, CountyRequest.class);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RestResponse( true, "County not found"), HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            log.error("error ", e);
            return new ResponseEntity<>(new RestResponse(true, "Cooperative not found, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping
    ResponseEntity<?> getAll(@RequestParam("per_page") int perPage,
                             @RequestParam("page") int page,
                             @RequestParam(name="search", required = false) String search,
                             @RequestParam(name = "county_id", required = false) Long countyId) {
        log.info("Getting all Counties");

        try {

            ListResponse listResponse = countyService.getAllCounties(page, perPage, search, countyId);
            return new ResponseEntity<>(listResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error ", e);
            return new ResponseEntity(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
