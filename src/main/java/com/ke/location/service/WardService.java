package com.ke.location.service;

import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.WardDto;

import com.ke.location.entity.QWard;
import com.ke.location.entity.Ward;

import com.ke.location.repository.WardRepository;
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
public class WardService {
    @Autowired
    private WardRepository wardRepository;

    public Ward addWard(Ward ward) {
        return wardRepository.save(ward);
    }

    public Optional<WardDto> findBySubCountyIdAndId(Long id, Long subCountyId) {

        return wardRepository.findBySubCountyIdAndId(subCountyId, id);
    }

    public Optional<WardDto> findById(Long id) {

        return wardRepository.findWardById(id);
    }
    public Optional<Ward> findByName(String name) {

        return wardRepository.findWardByName(name);
    }


    @Transactional(readOnly = true)
    public ListResponse filterByNameAndSubCountyId(int page, int perPage, Long subCountyId, String name) {
        log.debug("Request to filter wards given subCountyId : {}, subCounty name : {}", name

        );
        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<WardDto> wardPage = wardRepository.findBySubCountyIdAndNameContaining(subCountyId, name, pageable);
        return new ListResponse(wardPage.getContent(), wardPage.getTotalPages(), wardPage.getNumberOfElements(), wardPage.getTotalElements());
    }


    public ListResponse getAllWards(int page, int perPage, String search, Long subCountyId) {

        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<WardDto> wardPage;
        if (search != null && !search.isEmpty()) {

            QWard qWard = QWard.ward;

            wardPage = wardRepository.findBy(qWard.subCounty.id.eq(subCountyId).andAnyOf(qWard.name.containsIgnoreCase(search)), q -> q.sortBy(sort).as(WardDto.class).page(pageable));
        } else {
            wardPage = wardRepository.findAllBysubCounty_id(subCountyId, pageable);
        }

        return new ListResponse(wardPage.getContent(), wardPage.getTotalPages(), wardPage.getNumberOfElements(), wardPage.getTotalElements());
    }

}
