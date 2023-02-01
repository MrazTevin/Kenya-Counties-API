package com.ke.location.service;


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

import java.util.Optional;

@Service
@Slf4j
public class SubCountyService {
    @Autowired
    private SubCountyRepository subCountyRepository;

//    public SubCounty addSubCounty(SubCounty subCounty) {
//        return subCountyRepository.save(subCounty);
//    }
////    public Optional<SubCountyDto> findById(Long id, Long countyId) {
////
////        return subCountyRepository.findByCountyIdAndId(countyId, id);
////    }



    public Page<SubCounty> filterByNameAndCountyId(String name, Integer countyId ,int page, int size) {
        return subCountyRepository.findByNameAndCountyId(name, countyId, PageRequest.of(page, size));
    }
    public Page<SubCounty> filterByWardAndName(String ward, String name, int page, int size) {
        return subCountyRepository.findByWardAndName(ward, name, PageRequest.of(page, size));
    }
    public Page<SubCounty> filterByWardAndCountyId(String ward, Integer countyId ,int page, int size) {
        return subCountyRepository.findByWardAndCountyId(ward, countyId, PageRequest.of(page, size));
    }









    public Page<SubCounty> filterByWardAndSubCountyNameCountyId(String ward, String name, Integer countyId, int page, int size) {
        return subCountyRepository.findByWardAndNameAndCountyId(ward, name, countyId, PageRequest.of(page, size));
    }

    public Page<SubCounty> getSubCountiesByName(String name, int page, int size) {
        if (name == null) {
            return subCountyRepository.findAll(PageRequest.of(page, size));
        } else {
            return subCountyRepository.findByNameContaining(name, PageRequest.of(page, size));
        }
    }

    public Page<SubCounty> getByWard(String ward, int page, int size) {
        if (ward == null) {
            return subCountyRepository.findAll(PageRequest.of(page, size));
        } else {
            return subCountyRepository.findByWardContaining(ward, PageRequest.of(page, size));
        }
    }
}
