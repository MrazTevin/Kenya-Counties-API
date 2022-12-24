package com.ke.location.repository;

import com.ke.location.entity.SubCounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCountyRepository extends JpaRepository<SubCounty,Long> {
}
