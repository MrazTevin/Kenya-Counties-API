package com.ke.location.web.rest.dto;

public interface CountyDto {
    public String getName();
    public Integer getId();
    public CountyView getCounty();

    public interface  CountyView {

        public Integer getId();

        public String getName();
    }
}
