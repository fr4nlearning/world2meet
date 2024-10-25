package com.example.sandav.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseListPageable<T> {

    private List<T> starshipList;
    private Long totalElements;
    private Integer totalPages;
    private Integer number;
    private Integer size;

    public ResponseListPageable(List<T> list, long totalElements, int page, int number, int size) {
        this.starshipList= list;
        this.totalElements= totalElements;
        this.totalPages= page;
        this.number= number;
        this.size= size;
    }
}
