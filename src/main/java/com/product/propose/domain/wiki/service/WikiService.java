package com.product.propose.domain.wiki.service;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;

public interface WikiService {

    Wiki registerWiki(WikiRegisterRequest request);

    WikiResponse readWiki(Long targetId);
}
