package com.ke.location.controller.dto;

public interface WardDto {
    public String getSubCountyName();
    public Long getId();
    public WardView getWard();

    public interface  WardView {

        public Long getId();

        public String getWardName();
    }
}
