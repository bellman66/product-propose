package com.product.propose.domain.wiki.service;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;

import java.util.List;

public interface WikiService {

    List<Tag> registerTagGroup(List<String> tagGroup);
    Wiki registerWiki(WikiRegisterRequest request);
}
