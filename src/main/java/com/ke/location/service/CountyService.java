package com.ke.location.service;



import com.ke.location.entity.SubCounty;
import com.ke.location.web.rest.dto.CountyDto;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.entity.County;
import com.ke.location.entity.QCounty;
import com.ke.location.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyService {
    @Autowired
    private CountyRepository countyRepository;


    public List<County> getAllCounties() {
        return countyRepository.findAll();
    }


}
