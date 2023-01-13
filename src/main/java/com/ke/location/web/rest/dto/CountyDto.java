package com.ke.location.web.rest.dto;

public interface CountyDto {
    public String getName();
    public Long getId();
    public CountyView getCounty();

    public interface  CountyView {

        public Long getId();

        public String getName();
    }
}
