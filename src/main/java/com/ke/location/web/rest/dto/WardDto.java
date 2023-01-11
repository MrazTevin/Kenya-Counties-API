package com.ke.location.web.rest.dto;

public interface WardDto {
    public String getWardName();
    public Long getId();
    public WardView getWard();

    public interface  WardView {

        public Long getId();

        public String getWardName();
    }
}
