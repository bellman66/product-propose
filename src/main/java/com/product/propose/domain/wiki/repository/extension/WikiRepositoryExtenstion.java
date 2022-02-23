package com.product.propose.domain.wiki.repository.extension;

import com.product.propose.domain.wiki.web.dto.response.WikiResponse;

public interface WikiRepositoryExtenstion {

    WikiResponse findWikiResponseById(Long wikiId);
}
