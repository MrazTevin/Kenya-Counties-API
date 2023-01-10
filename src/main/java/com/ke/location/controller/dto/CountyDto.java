package com.ke.location.controller.dto;

public interface CountyDto {
    public String getCountyName();
    public Long getId();
    public CountyView getCounty();

    public interface  CountyView {

        public Long getId();

        public String getCountyName();
    }
}
