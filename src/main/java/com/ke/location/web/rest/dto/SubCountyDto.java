package com.ke.location.web.rest.dto;

public interface SubCountyDto {
    public String getName();
    public Long getId();
    public SubCountyView getSubCounty();

    public interface  SubCountyView {

        public Long getId();

        public String getName();
    }
}
