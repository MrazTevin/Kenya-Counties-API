package com.ke.location.web.rest.dto;

public interface SubCountyDto {
    public String getName();
    public Integer getId();
    public  String getWard();
    public SubCountyView getSubCounty();

    public interface  SubCountyView {

        public Integer getId();
        public String getWard();

        public String getName();
    }
}
