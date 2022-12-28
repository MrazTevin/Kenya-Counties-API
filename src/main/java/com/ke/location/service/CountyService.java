package com.ke.location.service;

import com.ke.location.controller.dto.CountyDto;
import com.ke.location.controller.dto.ListResponse;
import com.ke.location.entity.County;
import com.ke.location.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountyService {
    @Autowired
    private CountyRepository countyRepository;

    public County addCounty(County county) {
        return countyRepository.save(county);
    }
    public Optional<County> getCountyByName(String name) {
        Optional<County> county = countyRepository.getCountyByName(name);

        if (county.isPresent()) {
            return county;
        } else {
            throw new IllegalStateException("county with name " + name + " does not exist");
        }
    }

    public ListResponse getAllCounties(int page, int perPage, String search, String name) {

        page = page - 1;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, perPage, sort);

        Page<CountyDto> countyPage;
        if (search != null && !search.isEmpty()) {
            QCounty qCounty = QCounty.county;
            countyPage = countyRepository.findBy(qCounty.name.eq(countyName).andAnyOf(qCounty.countyName.containsIgnoreCase(search))), q -> q.sortBy(sort).as(CountyDto.class).page(pageable));

            return new ListResponse(countyPage.getContent(), countyPage.getTotalPages(), countyPage.getNumberOfElements(), countyPage.getTotalElements());
        } else {

            Page<County> countyPage1 = countyRepository.findAll(pageable);

            return new ListResponse(countyPage1.getContent(), countyPage1.getTotalPages(), countyPage1.getNumberOfElements(), countyPage1.getTotalElements());
        }


    }
}
