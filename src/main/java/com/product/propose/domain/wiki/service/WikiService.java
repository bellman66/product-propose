package com.product.propose.domain.wiki.service;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.PriceUpdateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.dto.response.WikiSummaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface WikiService {

    Wiki getTargetWiki(Long wikiId);

    Wiki registerWiki(Long accountId, WikiCreateData request);

    WikiSummaryResponse readWiki(Long targetId);

    Wiki addPriceRecord(Long wikiId, Long accountId, PriceRecordCreateForm priceRecordCreateForm);

    Wiki updateWiki(Long wikiId, WikiUpdateData wikiUpdateData);

    Wiki updatePriceRecord(Long wikiId, Long recordId, Long accountId, PriceUpdateData priceUpdateData);

    Wiki updateWikiImage(Long wikiId, MultipartFile[] image);
}
