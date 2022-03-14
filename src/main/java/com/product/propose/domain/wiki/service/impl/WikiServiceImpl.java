package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.ProductImageCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.PriceUpdateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.dto.mapper.WikiMapper;
import com.product.propose.domain.wiki.web.dto.response.WikiSummaryResponse;
import com.product.propose.domain.wiki.web.validator.assertion.WikiAssert;
import com.product.propose.global.utils.upload.ImageBBUploadUtil;
import com.product.propose.global.utils.upload.UploadUtils;
import com.product.propose.global.utils.upload.dto.ImageInfoDto;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;


@Service
@Transactional(readOnly = true)
public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;
    private final UploadUtils uploadUtils;

    public WikiServiceImpl(WikiRepository wikiRepository, ImageBBUploadUtil imageBBUploadUtil) {
        this.wikiRepository = wikiRepository;
        this.uploadUtils = imageBBUploadUtil;
    }

    @Override
    public Wiki getTargetWiki(Long wikiId) {
        Wiki wiki = wikiRepository.findWikiById(wikiId);
        WikiAssert.exists(wiki);
        return wiki;
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
        Wiki wiki = getTargetWiki(wikiId);

        // Register Price Record
        wiki.registerPriceRecord(accountId, priceRegisterData);
        return wikiRepository.save(wiki);
    }

    @Override
    @Transactional
    public Wiki updateWiki(Long wikiId, WikiUpdateData wikiUpdateData) {
        // Get & Assertion
        Wiki wiki = getTargetWiki(wikiId);

        // Update Wiki
        wiki.update(wikiUpdateData);
        return wikiRepository.save(wiki);
    }

    @Override
    @Transactional
    public Wiki updatePriceRecord(Long wikiId, Long recordId, Long accountId, PriceUpdateData updateData) {
        // Get & Assertion
        Wiki wiki = getTargetWiki(wikiId);

        // PriceRecord
        wiki.updatePriceRecord(recordId, accountId, updateData);
        return wikiRepository.save(wiki);
    }

    @Override
    @Transactional
    public Wiki updateWikiImage(Long wikiId, MultipartFile image) {
        Wiki wiki = getTargetWiki(wikiId);

        // Upload Image
        ImageInfoDto imageInfo = uploadUtils.uploadImage(image);
        ProductImageCreateForm createForm = WikiMapper.INSTANCE.ImageInfoToProductImage(imageInfo);

        // Update Wiki Image
        wiki.registerProductImage(createForm);
        return wikiRepository.save(wiki);
    }
}
