package com.ke.location.service;

import com.ke.location.entity.SubCounty;
import com.ke.location.entity.Ward;
import com.ke.location.repository.SubCountyRepository;
import com.ke.location.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WardService {
    @Autowired
    private WardRepository wardRepository;

    public Ward addWard(Ward ward){
        return wardRepository.save(ward);
    }
}
