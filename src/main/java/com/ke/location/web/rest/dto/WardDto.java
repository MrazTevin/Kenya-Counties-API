package com.ke.location.web.rest.dto;

public interface WardDto {
    public String getName();
    public Long getId();
    public WardView getWard();

    public interface  WardView {

        public Long getId();

        public String getName();
    }
}
