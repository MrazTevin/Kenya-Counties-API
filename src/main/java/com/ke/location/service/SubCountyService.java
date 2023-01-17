package com.ke.location.service;

import com.ke.location.entity.QSubCounty;
import com.ke.location.entity.SubCounty;
import com.ke.location.web.rest.dto.ListResponse;
import com.ke.location.web.rest.dto.SubCountyDto;


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

//    public SubCounty addSubCounty(SubCounty subCounty) {
//        return subCountyRepository.save(subCounty);
//    }
////    public Optional<SubCountyDto> findById(Long id, Long countyId) {
////
////        return subCountyRepository.findByCountyIdAndId(countyId, id);
////    }



    @Transactional(readOnly = true)
    public ListResponse filterBySubCountyAndCountyId(int page, int perPage, Integer countyId, String name) {
        log.debug("Request to filter sub counties given countyId : {}, sub county name : {}", name

        );
        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage = subCountyRepository.findByCountyIdAndNameContaining(countyId, name, pageable);
        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }
    @Transactional(readOnly = true)
    public ListResponse filterByWardAndCountyId(int page, int perPage, Integer countyId, String ward) {
        log.debug("Request to filter sub counties given countyId : {}, ward: {}", ward

        );
        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage = subCountyRepository.findByCountyIdAndWardContaining(countyId, ward, pageable);
        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }
    @Transactional(readOnly = true)
    public ListResponse filterByWardAndSubCounty(int page, int perPage, String name, String ward) {
        log.debug("Request to filter sub counties given constiuencyName : {}, ward: {}", ward

        );
        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage = subCountyRepository.findByNameAndWardContaining(name, ward, pageable);
        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }
    @Transactional(readOnly = true)
    public ListResponse filterByWardAndNameAndCountyId(int page, int perPage, Integer countyId, String name,String ward) {
        log.debug("Request to filter wards given countyId : {}, sub county name : {} ward :{}",ward

        );
        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage = subCountyRepository.findByCountyIdAndNameAndWardContaining(countyId, name,ward, pageable);
        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }



    public ListResponse getAll(int page, int perPage, String search, Integer countyId) {

        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<SubCountyDto> subCountyPage;
        if (search != null && !search.isEmpty()) {

            QSubCounty qSubCounty = QSubCounty.subCounty;

            subCountyPage = subCountyRepository.findBy(qSubCounty.county.id.eq(countyId).andAnyOf(qSubCounty.name.containsIgnoreCase(search)), q -> q.sortBy(sort).as(SubCountyDto.class).page(pageable));
        } else {
            subCountyPage = subCountyRepository.findAllByCountyId(countyId, pageable);
        }

        return new ListResponse(subCountyPage.getContent(), subCountyPage.getTotalPages(), subCountyPage.getNumberOfElements(), subCountyPage.getTotalElements());
    }




    public Page<SubCounty> filterByWardSubCountyNameCountyId(String ward, String name, Integer countyId, int page, int size) {
        return subCountyRepository.findByWardAndNameAndCountyId(ward, name, countyId, PageRequest.of(page, size));
    }

}
