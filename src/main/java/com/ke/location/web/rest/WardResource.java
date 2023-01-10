package com.ke.location.web.rest;

import com.ke.location.entity.SubCounty;
import com.ke.location.entity.Ward;
import com.ke.location.service.WardService;
import com.ke.location.web.rest.dto.CountyDto;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.RestResponse;
import com.ke.location.web.rest.request.SubCountyRequest;
import com.ke.location.web.rest.request.WardRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequestMapping(path = "/api/v1")
public class WardResource {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WardService wardService;
    @PostMapping("/ward")
    public Ward addWard(@RequestBody Ward ward){
        return wardService.addWard(ward);
    }
    @GetMapping("/filter-by-ward-name")
    public ResponseEntity<?> filterByWardNameAndSubCountyId(
            @RequestParam("per_page") int perPage,
            @RequestParam("page") int page,//later we will use security credential
            @RequestParam(name = "name",required = false) String name
    )
    {

        try {
            log.debug(

                   // CountyDto.Id(),
                    name
            );

            ListResponse ward = wardService.filterByNameAndSubCountyId(
                    page,
                    perPage,
                    //userDetails.getCooperative().getId(),
                    name
            );
            //LATER add HTTP headers
            return ResponseEntity.ok().body(ward);
        } catch (Exception e){
            log.error("Error occurred ", e);
            return new ResponseEntity<>(new RestResponse(true, "An Error occurred, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(path = "{name}")
    ResponseEntity<?> findByName(@PathVariable("name") String name) {

        try {
            Optional<Ward> wardOptional = wardService.findByName(name);

            if (wardOptional.isPresent()) {
                Ward ward = wardOptional.get();

                WardRequest response = modelMapper.map(ward, WardRequest.class);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RestResponse(true, "Ward not found"), HttpStatus.NOT_FOUND);

            }
        }
        catch (Exception e) {
            log.error("error ", e);
            return new ResponseEntity<>(new RestResponse(true, "Ward not found, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
