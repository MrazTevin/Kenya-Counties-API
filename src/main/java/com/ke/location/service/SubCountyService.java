package com.ke.location.service;


import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;



import com.ke.location.repository.SubCountyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubCountyService {
    @Autowired
    private SubCountyRepository subCountyRepository;


    public List<SubCounty> getSubCountiesByCountyId(Integer countyId) {
        return subCountyRepository.findByCountyId(countyId);
    }
    public List<SubCounty> getAllWards() {
        return subCountyRepository.findAll();
    }
//    public List<SubCounty> getWardsByName(String name) {
//        return subCountyRepository.findByName(name);
//    }
public List<SubCounty> filterByCountyAndSubcountyAndWardAndSearch(Integer countyId, String name, String ward, String search) {
    if (countyId == null && name == null && ward == null && search == null) {
        return subCountyRepository.findAll();
    }
    if (countyId != null && name == null && ward == null && search == null) {
        return subCountyRepository.findByCountyId(countyId);
    }
    if (countyId != null && name != null && ward == null && search == null) {
        return subCountyRepository.findByCountyIdAndName(countyId, name);
    }
    if (countyId != null && name != null && ward != null && search == null) {
        return subCountyRepository.findByCountyIdAndNameAndWard(countyId, name, ward);
    }
    return subCountyRepository.findByNameContainingIgnoreCaseOrCountyNameContainingIgnoreCaseOrWardContainingIgnoreCase(search, search, search);
}

}
