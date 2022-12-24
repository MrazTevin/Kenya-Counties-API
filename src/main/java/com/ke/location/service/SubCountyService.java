package com.ke.location.service;

import com.ke.location.entity.SubCounty;

import com.ke.location.repository.SubCountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCountyService {
    @Autowired
    private SubCountyRepository subCountyRepository;

    public SubCounty addSubCounty(SubCounty subCounty) {
        return subCountyRepository.save(subCounty);
    }
}
