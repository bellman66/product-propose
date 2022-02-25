package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.repository.PriceRecordRepository;
import com.product.propose.domain.wiki.service.PriceRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PriceRecordServiceImpl implements PriceRecordService {

    private final PriceRecordRepository priceRecordRepository;

    public PriceRecordServiceImpl(PriceRecordRepository priceRecordRepository) {
        this.priceRecordRepository = priceRecordRepository;
    }

}
