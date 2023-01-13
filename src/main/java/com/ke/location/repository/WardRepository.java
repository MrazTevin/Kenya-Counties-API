package com.ke.location.repository;

import com.ke.location.web.rest.dto.WardDto;
import com.ke.location.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WardRepository extends PagingAndSortingRepository<Ward,Long>, QuerydslPredicateExecutor<Ward> {
//    Optional<WardDto> findBySubCountyIdAndId(Long subCountyId, Long id);


    Page<WardDto> findBySubCountyIdAndNameContaining(Long subCountyId, String name, Pageable pageable);

    Page<WardDto> findAllBySubCounty_id(Long subCountyId, Pageable pageable);
}
