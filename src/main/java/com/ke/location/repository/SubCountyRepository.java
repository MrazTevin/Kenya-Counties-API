package com.ke.location.repository;

import com.ke.location.controller.dto.SubCountyDto;
import com.ke.location.entity.County;
import com.ke.location.entity.SubCounty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCountyRepository extends PagingAndSortingRepository<SubCounty,Long>, QuerydslPredicateExecutor<SubCounty> {
    Optional<SubCountyDto> findByCountyIdAndId(Long countyId, Long id);

    Optional<SubCountyDto> findSubCountyById(Long id);

    Optional<SubCountyDto> findSubCountyByName(String name);

    Page<SubCountyDto> findByCountyIdAndNameContaining(Long subCountyId, String name, Pageable pageable);
}
