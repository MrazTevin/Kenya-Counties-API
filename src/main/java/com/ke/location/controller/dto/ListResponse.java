package com.ke.location.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListResponse {

    private List<?> data;
    private int totalPages;
    private int totalItemsInPage;
    private long totalElements;
}
