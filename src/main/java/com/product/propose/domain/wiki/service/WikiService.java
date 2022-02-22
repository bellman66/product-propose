package com.product.propose.domain.wiki.service;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;

public interface WikiService {

    Wiki registerWiki(WikiRegisterRequest request);

    Wiki readWiki(Long targetId);
}
