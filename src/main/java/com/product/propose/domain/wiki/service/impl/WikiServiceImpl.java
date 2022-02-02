package com.product.propose.domain.wiki.service.impl;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;

    public WikiServiceImpl(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }


    @Override
    public List<Tag> registerTagGroup(List<String> tagGroup) {
        return null;
    }

    @Override
    @Transactional
    public Wiki registerWiki(WikiRegisterRequest request) {

        // 1. register Tag
//        List<Tag> tags = registerTagGroup(request.getTagGroup());

        // 2. register
       Wiki result = Wiki.registerWiki(request.getWikiCreateData());
        return wikiRepository.save(result);
    }
}
