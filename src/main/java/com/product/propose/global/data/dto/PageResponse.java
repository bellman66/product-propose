package com.product.propose.global.data.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class PageResponse {

    private int totalPageNum;           // 전체 페이지 총 개수
    private int currentPageNum;         // 현재 페이지 위치
    private int currentElementNum;      // 현재 페이지 Space 개수

    private boolean isFirst;            // 첫번째 페이지 확인자
    private boolean isLast;             // 마지막 페이지 확인자

    List<?> content;             // 현재 페이지 컨텐츠

    public static PageResponse create(Page<?> pageValue) {
        return PageResponse.builder()
                .totalPageNum(pageValue.getTotalPages())
                .currentPageNum(pageValue.getNumber())
                .currentElementNum(pageValue.getNumberOfElements())
                .isFirst(pageValue.isFirst())
                .isLast(pageValue.isLast())
                .content(pageValue.getContent())
                .build();
    }
}
