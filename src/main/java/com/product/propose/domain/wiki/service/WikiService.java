package com.product.propose.domain.wiki.service;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;

public interface WikiService {

    Wiki registerWiki(WikiRegisterRequest request);

    WikiResponse readWiki(Long targetId);

    Wiki addPriceRecord(Long wikiId, PriceRecordCreateForm priceRecordCreateForm);

    Wiki updateWiki(Long wikiId, WikiUpdateData wikiUpdateData);
}
