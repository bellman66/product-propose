package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;
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
    public Wiki registerWiki(WikiRegisterRequest request) {
        WikiAssert.nonExist(request.getWikiTitle());

        // 1. register
        Wiki result = Wiki.registerWiki(request.getWikiCreateData());
        return wikiRepository.save(result);
    }

    @Override
    public WikiResponse readWiki(Long targetId) {
        WikiAssert.isExist(targetId);

        return wikiRepository.findWikiResponseById(targetId);
    }

    @Override
    @Transactional
    public Wiki addPriceRecord(Long wikiId, PriceRecordCreateForm priceRegisterData) {
        // Assert Exist Wiki
        WikiAssert.isExist(wikiId);

        // Find Target Wiki
        Wiki wiki = wikiRepository.findWikiById(wikiId);

        // Register Price Record
        wiki.registerPriceRecord(priceRegisterData);
        return wiki;
    }

    @Override
    @Transactional
    public Wiki updateWiki(Long wikiId, WikiUpdateData wikiUpdateData) {
        // Assert Exist Wiki
        WikiAssert.isExist(wikiId);

        // Find Target Wiki
        Wiki wiki = wikiRepository.findWikiById(wikiId);

        // Update Wiki
        wiki.updateWiki(wikiUpdateData);
        return wiki;
    }
}
