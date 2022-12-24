package com.ke.location.repository;

import com.ke.location.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends  JpaRepository<County,Long> {
}
