package com.ke.location.repository;


import com.ke.location.entity.SubCounty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCountyRepository extends PagingAndSortingRepository<SubCounty,Integer>, QuerydslPredicateExecutor<SubCounty> {

    Page<SubCounty> findByWardAndNameAndCountyId(String ward, String name, Integer countyId, PageRequest of);

    Page<SubCounty> findByWardAndName(String ward, String name, PageRequest of);

    Page<SubCounty> findByWardAndCountyId(String ward, Integer countyId, PageRequest of);

    Page<SubCounty> findByNameAndCountyId(String name, Integer countyId, PageRequest of);
    Page<SubCounty> findByNameContaining(String name, Pageable pageable);

    Page<SubCounty> findByWardContaining(String ward, Pageable pageable);

    // Page<SubCountyDto> findByCountyIdAndSubCountyNameAndWardContaining(Integer countyId, String name, String ward, Pageable pageable);
}
