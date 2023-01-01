package com.ke.location.service;

import com.ke.location.controller.dto.ListResponse;
import com.ke.location.controller.dto.SubCountyDto;
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

    public SubCounty addSubCounty(SubCounty subCounty) {
        return subCountyRepository.save(subCounty);
    }
    public Optional<SubCountyDto> findByCountyIdAndId(Long id, Long countyId) {

        return subCountyRepository.findByCountyIdAndId(countyId, id);
    }

    public Optional<SubCountyDto> findById(Long id) {

        return subCountyRepository.findSubCountyById(id);
    }
    public Optional<SubCountyDto> findByName(String name) {

        return subCountyRepository.findSubCountyByName(name);
    }

    @Transactional(readOnly = true)
    public ListResponse filterByNameAndCountyId(int page, int perPage, Long countyId, String name) {
        log.debug("Request to filter sub counties given countyId : {}, sub county name : {}", name

        );
        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage = subCountyRepository.findByCountyIdAndNameContaining(countyId, name, pageable);
        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }


    public ListResponse getAllSubCounties(int page, int perPage, String search, Long countyId) {

        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage;
        if (search != null && !search.isEmpty()) {

            QSubCounty qSubCounty = QSubCounty.subCounty;

            subCountyPage = subCountyRepository.findBy(qSubCounty.county.id.eq(countyId).andAnyOf(qSubCounty.name.containsIgnoreCase(search)), q -> q.sortBy(sort).as(SubCountyDto.class).page(pageable));
        } else {
            subCountyPage = subCountyRepository.findAllByCounty_id(countyId, pageable);
        }

        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }

}
