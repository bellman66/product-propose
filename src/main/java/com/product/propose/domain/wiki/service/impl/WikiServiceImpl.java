package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;

    public WikiServiceImpl(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }


    @Override
    @Transactional
    public Wiki registerWiki(WikiRegisterRequest request) {
        // 1. register
       Wiki result = Wiki.registerWiki(request.getWikiCreateData());
       return wikiRepository.save(result);
    }

    @Override
    public Wiki readWiki(Long targetId) {
        return wikiRepository.findById(targetId).orElseThrow(() -> new CommonException(ErrorCode.WIKI_NOT_FOUND));
    }
}
