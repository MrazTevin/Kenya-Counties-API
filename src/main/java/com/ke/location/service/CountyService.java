package com.ke.location.service;

import com.ke.location.entity.County;
import com.ke.location.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyService {
    @Autowired
    private CountyRepository countyRepository;

    public County addCounty(County county) {
        return countyRepository.save(county);
    }
}
