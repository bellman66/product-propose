package com.product.propose.domain.wiki.repository.extension;

import com.product.propose.domain.wiki.web.dto.response.WikiSummaryResponse;
import com.product.propose.global.data.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface WikiReadExtenstion {

    WikiSummaryResponse readWikiResponseById(Long wikiId);

    PageResponse readWikiPageResponse(Pageable pageable);
}
