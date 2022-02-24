package com.product.propose.domain.wiki.service;

import com.product.propose.global.data.dto.PageResponse;
import org.springframework.data.domain.Pageable;


public interface PriceRecordService {

    PageResponse getRecordPage(Long wikiId, Pageable pageable);
}
