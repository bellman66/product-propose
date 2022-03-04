package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.PriceUpdateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.dto.response.WikiSummaryResponse;
import com.product.propose.domain.wiki.web.validator.assertion.WikiAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;

    public WikiServiceImpl(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    @Override
    @Transactional
    public Wiki registerWiki(Long accountId, WikiCreateData createData) {
        WikiAssert.nonExist(createData.getWikiTitle());

        // 1. register
        Wiki result = Wiki.registerWiki(accountId, createData);
        return wikiRepository.save(result);
    }

    @Override
    public WikiSummaryResponse readWiki(Long targetId) {
        WikiAssert.exists(targetId);

        return wikiRepository.readWikiResponseById(targetId);
    }

    @Override
    @Transactional
    public Wiki addPriceRecord(Long wikiId, Long accountId, PriceRecordCreateForm priceRegisterData) {
        // Assert Exist Wiki
        Wiki wiki = wikiRepository.findWikiById(wikiId);
        WikiAssert.exists(wiki);

        // Register Price Record
        wiki.registerPriceRecord(accountId, priceRegisterData);
        return wikiRepository.save(wiki);
    }

    @Override
    @Transactional
    public Wiki updateWiki(Long wikiId, WikiUpdateData wikiUpdateData) {
        // Get & Assertion
        Wiki wiki = wikiRepository.findWikiById(wikiId);
        WikiAssert.exists(wiki);

        // Update Wiki
        wiki.update(wikiUpdateData);
        return wikiRepository.save(wiki);
    }

    @Override
    @Transactional
    public Wiki updatePriceRecord(Long wikiId, Long recordId, Long accountId, PriceUpdateData updateData) {
        // Get & Assertion
        Wiki wiki = wikiRepository.findWikiById(wikiId);
        WikiAssert.exists(wiki);

        // PriceRecord
        wiki.updatePriceRecord(recordId, accountId, updateData);
        return wikiRepository.save(wiki);
    }
}
