package com.ke.location.web.rest;

import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;
import com.ke.location.service.CountyService;
import com.ke.location.service.SubCountyService;
import com.ke.location.web.rest.dto.CountyDto;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.RestResponse;

import com.ke.location.web.rest.dto.SubCountyDto;
import com.ke.location.web.rest.request.CountyRequest;
import com.ke.location.web.rest.request.SubCountyRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RequestMapping(path = "/api/v1")
public class SubCountyResource {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubCountyService subCountyService;
    @Autowired
    private CountyService countyService;

    @PostMapping("/subCounty")
    public SubCounty addSubCounty(@RequestBody SubCounty subCounty) {
        return subCountyService.addSubCounty(subCounty);
    }

    @GetMapping("/filter-by-subCounty-name")
    public ResponseEntity<?> filterBySubCountyNameAndCooperativeId(
            @RequestParam("per_page") int perPage,
            @RequestParam("page") int page,//later we will use security credential
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "name", required = false) String name
    ) {

        try {


            ListResponse subCounty = subCountyService.filterByNameAndCountyId(
                    page,
                    perPage,
                    id,
                    name
            );
            //LATER add HTTP headers
            return ResponseEntity.ok().body(subCounty);
        } catch (Exception e) {
            log.error("Error occurred ", e);
            return new ResponseEntity<>(new RestResponse(true, "An Error occurred, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "{id}")
    ResponseEntity<?> findById(@PathVariable("id") Long id,Long countyId) {

        try {
            Optional<SubCountyDto> subCountyOptional = subCountyService.findById(id,countyId);

            if (subCountyOptional.isPresent()) {

                return new ResponseEntity<>(subCountyOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RestResponse(true, "SubCounty not found"), HttpStatus.NOT_FOUND);

            }
        }
        catch (Exception e) {
                log.error("error ", e);
                return new ResponseEntity<>(new RestResponse(true, "SubCounty not found, contact admin"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }
    @GetMapping
    ResponseEntity<?> getAll(@RequestParam("per_page") int perPage,
                             @RequestParam("page") int page,
                             @RequestParam(name="search", required = false) String search,
                             @RequestParam(name = "subCounty_id", required = false) Long subCountyId) {
        log.info("Getting all SubCounties");

        try {

            ListResponse listResponse = subCountyService.getAllSubCounties(page, perPage, search, subCountyId);
            return new ResponseEntity<>(listResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error ", e);
            return new ResponseEntity(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    }
