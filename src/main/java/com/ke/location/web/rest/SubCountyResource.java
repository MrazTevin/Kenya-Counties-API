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

//    @PostMapping("/subCounty")
//    ResponseEntity< SubCounty>addSubCounty(@RequestBody SubCounty subCounty) {
//        log.info("request to add new subCountyCounty");
//
//        SubCounty newSubCounty = subCountyService.addSubCounty(subCounty);
//
//        return new ResponseEntity<>(newSubCounty, HttpStatus.OK);
//    }

    @GetMapping("/filter-by-subCounty-name")
    public ResponseEntity<?> filterBySubCountyNameAndCountyId(
            @RequestParam("per_page") int perPage,
            @RequestParam("page") int page,
            @RequestParam(name = "id", required = false) Integer countyId,
            @RequestParam(name = "name", required = false) String name
    ) {

        try {


            ListResponse subCounty = subCountyService.filterBySubCountyAndCountyId(
                    page,
                    perPage,
                    countyId,
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

    @GetMapping("/filter-by-ward")
    public ResponseEntity<?> filterByWardAndCountyId(
            @RequestParam("per_page") int perPage,
            @RequestParam("page") int page,//later we will use security credential
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "ward", required = false) String ward
    ) {

        try {


            ListResponse subCounty = subCountyService.filterByWardAndCountyId(
                    page,
                    perPage,
                    id,
                    ward
            );
            //LATER add HTTP headers
            return ResponseEntity.ok().body(subCounty);
        } catch (Exception e) {
            log.error("Error occurred ", e);
            return new ResponseEntity<>(new RestResponse(true, "An Error occurred, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/filter-by-ward-constituencyName")
    public ResponseEntity<?> filterByWardAndConstituencyName(
            @RequestParam("per_page") int perPage,
            @RequestParam("page") int page,//later we will use security credential
            @RequestParam(name = "id", required = false) String name,
            @RequestParam(name = "ward", required = false) String ward
    ) {

        try {


            ListResponse subCounty = subCountyService.filterByWardAndSubCounty(
                    page,
                    perPage,
                    name,
                    ward
            );
            //LATER add HTTP headers
            return ResponseEntity.ok().body(subCounty);
        } catch (Exception e) {
            log.error("Error occurred ", e);
            return new ResponseEntity<>(new RestResponse(true, "An Error occurred, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/filter-by-ward-subCountyName-CountyId")
    public ResponseEntity<?> filterByWardAndSubCountyNameAndCountyId(
            @RequestParam("per_page") int perPage,
            @RequestParam("page") Integer page,//later we will use security credential
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "ward", required = false) String ward
    ) {

        try {


            ListResponse subCounty = subCountyService.filterByWardAndNameAndCountyId(
                    page,
                    perPage,
                    id,
                    name,
                    ward
            );
            //LATER add HTTP headers
            return ResponseEntity.ok().body(subCounty);
        } catch (Exception e) {
            log.error("Error occurred ", e);
            return new ResponseEntity<>(new RestResponse(true, "An Error occurred, contact admin"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @GetMapping(path = "{id}")
//    ResponseEntity<?> findById(@PathVariable("id") Long id,Long countyId) {
//
//        try {
//            Optional<SubCountyDto> subCountyOptional = subCountyService.findById(id,countyId);
//
//            if (subCountyOptional.isPresent()) {
//
//                return new ResponseEntity<>(subCountyOptional.get(), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new RestResponse(true, "SubCounty not found"), HttpStatus.NOT_FOUND);
//
//            }
//        }
//        catch (Exception e) {
//                log.error("error ", e);
//                return new ResponseEntity<>(new RestResponse(true, "SubCounty not found, contact admin"),
//                        HttpStatus.IntegerERNAL_SERVER_ERROR);
//            }
//
//    }
    @GetMapping
    ResponseEntity<?> getAll(@RequestParam("per_page") int perPage,
                             @RequestParam("page") int page,
                             @RequestParam(name="search", required = false) String search,
                             @RequestParam(name = "id", required = false) Integer countyId) {
        log.info("Getting all SubCounties");

        try {

            ListResponse listResponse = subCountyService.getAll(page, perPage, search, countyId);
            return new ResponseEntity<>(listResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error ", e);
            return new ResponseEntity(new RestResponse(true, "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/filter")
    public Page<SubCounty> filterByWardSubCountyNameCountyId(@RequestParam(value = "ward") String ward,
                                                             @RequestParam(value = "name") String name,
                                                             @RequestParam(value = "countyId") Integer countyId,
                                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        return subCountyService.filterByWardSubCountyNameCountyId(ward, name, countyId, page, size);
    }



}
