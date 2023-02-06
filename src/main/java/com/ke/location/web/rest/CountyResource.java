package com.ke.location.web.rest;

import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;
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

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@CrossOrigin(origins = "/api1/county")
@RequestMapping(path = "/api1/county")

public class CountyResource {
    @Autowired
    private CountyService countyService;

    @GetMapping
    public ResponseEntity<List<County>> getAllCounties() {
        List<County> counties = countyService.getAllCounties();
        return ResponseEntity.ok().body(counties);
    }

}
