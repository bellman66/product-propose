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
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.upload.ImageBBUploadUtil;
import com.product.propose.global.utils.upload.UploadUtils;
import com.product.propose.global.utils.upload.dto.ImageInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;


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
    @Transactional
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
        Wiki result = Wiki.create(accountId, createData);
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
    public Wiki updateWikiImage(Long wikiId, MultipartFile[] images) {
        CommonAssert.isTrue(!ObjectUtils.isEmpty(images), ErrorCode.FILE_PATH_NOT_FOUND);
        Wiki wiki = getTargetWiki(wikiId);

        // Upload Image
        Arrays.stream(images)
                .map(uploadUtils::uploadImage)
                .map(WikiMapper.INSTANCE::ImageInfoToProductImage)
                .forEach(wiki::registerProductImage);
        return wikiRepository.save(wiki);
    }
}
