package com.ke.location.web.rest.dto;

public interface CountyDto {
    public String getCountyName();
    public Long getId();
    public CountyView getCounty();

    public interface  CountyView {

        public Long getId();

        public String getCountyName();
    }
}
