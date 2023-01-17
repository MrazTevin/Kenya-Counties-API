package com.ke.location.repository;

import com.ke.location.web.rest.dto.SubCountyDto;
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
    //Optional<SubCountyDto> findByCountyIdAndId(Long countyId, Long id);



    Page<SubCountyDto> findByCountyIdAndNameContaining(Integer countyId, String name, Pageable pageable);
    Page<SubCountyDto> findByCountyIdAndNameAndWardContaining(Integer countyId, String name,String ward, Pageable pageable);
    Page<SubCountyDto> findAllByCountyId(Integer countyId, Pageable pageable);

    Page<SubCountyDto> findByCountyIdAndWardContaining(Integer countyId, String ward, Pageable pageable);

    Page<SubCountyDto> findByNameAndWardContaining(String name, String ward, Pageable pageable);

    Page<SubCounty> findByWardAndNameAndCountyId(String ward, String name, Integer countyId, PageRequest of);

    // Page<SubCountyDto> findByCountyIdAndSubCountyNameAndWardContaining(Integer countyId, String name, String ward, Pageable pageable);
}
