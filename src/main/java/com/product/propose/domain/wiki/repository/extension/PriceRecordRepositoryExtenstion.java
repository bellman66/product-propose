package com.product.propose.domain.wiki.repository.extension;

import com.product.propose.domain.wiki.web.dto.response.PriceRecordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PriceRecordRepositoryExtenstion {

    Page<PriceRecordResponse> findPageByWikiId(Long wikiId, Pageable pageable);
}
