package com.ke.location.repository;

import com.ke.location.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountyRepository extends PagingAndSortingRepository<County,Long>, QuerydslPredicateExecutor<County> {
    Optional<County> getCountyByName(String name);
}
