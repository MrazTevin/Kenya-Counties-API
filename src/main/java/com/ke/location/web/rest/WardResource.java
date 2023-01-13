package com.ke.location.web.rest;

import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;
import com.ke.location.entity.Ward;
import com.ke.location.service.WardService;
import com.ke.location.web.rest.dto.CountyDto;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.RestResponse;
import com.ke.location.web.rest.dto.WardDto;
import com.ke.location.web.rest.request.SubCountyRequest;
import com.ke.location.web.rest.request.WardRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.ke.location.entity.QCounty.county;

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
            @RequestParam("page") int page,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(name = "name",required = false) String name

    )
    {

        try {


            ListResponse ward = wardService.filterByNameAndSubCountyId(
                    page,
                    perPage,
                     id,
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
//    @GetMapping(path = "{id}")
//    ResponseEntity<?> findById(@PathVariable("id") Long id,Long subCountyId) {
//
//        try {
//            Optional<WardDto> wardOptional = wardService.findById(id,subCountyId);
//
//            if (wardOptional.isPresent()) {
//
//                return new ResponseEntity<>(wardOptional.get(), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new RestResponse(true, "Ward not found"), HttpStatus.NOT_FOUND);
//
//            }
//        }
//        catch (Exception e) {
//            log.error("error ", e);
//            return new ResponseEntity<>(new RestResponse(true, "Ward not found, contact admin"),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
    @GetMapping
    ResponseEntity<?> getAll(@RequestParam("per_page") int perPage,
                             @RequestParam("page") int page,
                             @RequestParam(name="search", required = false) String search,
                             @RequestParam(name = "ward_id", required = false) Long wardId) {
        log.info("Getting all Wards");

        try {

            ListResponse listResponse = wardService.getAllWards(page, perPage, search, wardId);
            return new ResponseEntity<>(listResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error ", e);
            return new ResponseEntity(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
