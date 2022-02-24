package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.repository.PriceRecordRepository;
import com.product.propose.domain.wiki.service.PriceRecordService;
import com.product.propose.domain.wiki.web.dto.response.PriceRecordResponse;
import com.product.propose.global.data.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PriceRecordServiceImpl implements PriceRecordService {

    private final PriceRecordRepository priceRecordRepository;

    public PriceRecordServiceImpl(PriceRecordRepository priceRecordRepository) {
        this.priceRecordRepository = priceRecordRepository;
    }

    @Override
    public PageResponse getRecordPage(Long wikiId, Pageable pageable) {
        Page<PriceRecordResponse> page = priceRecordRepository.findPageByWikiId(wikiId, pageable);
        return PageResponse.create(page);
    }
}
