package com.ke.location.repository;


import com.ke.location.entity.SubCounty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCountyRepository extends JpaRepository<SubCounty,Integer> {


    List<SubCounty> findByCountyId(Integer countyId);


    List<SubCounty> findByCountyIdAndName(Integer countyId, String name);

    List<SubCounty> findByNameContainingIgnoreCaseOrCountyNameContainingIgnoreCaseOrWardContainingIgnoreCase(String name, String ward,String countyName);

    List<SubCounty> findByCountyIdAndNameAndWard(Integer countyId, String name, String ward);
}
