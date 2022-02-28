package com.product.propose.domain.wiki.web.event.listener;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.repository.TagRepository;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.web.event.TagRegister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisterListener {

    private final WikiRepository wikiRepository;
    private final TagRepository tagRepository;

    public RegisterListener(WikiRepository wikiRepository, TagRepository tagRepository) {
        this.wikiRepository = wikiRepository;
        this.tagRepository = tagRepository;
    }

    /**
    *   @Author : Youn
    *   @Summary : Wiki - Tag 등록 로직
    *   @Param : TagRegister Dto
    *   @Memo : After Commit 정책 - Wiki 등록완료시 발행
    **/
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void RegisterTag(TagRegister tagRegister) {
        List<Tag> tags = getTagList(tagRegister.getTagRegisterGroup());

        Wiki wiki = tagRegister.getWiki();
        tags.forEach(wiki::registerWikiTag);
        wikiRepository.save(wiki);
    }

    /**
    *   @Author : Youn
    *   @Summary : Tag중에 없는 것들을 생성
    *   @Param : List<String> nameList
    *   @Memo : In Query를 통해서 전체 리스트를 가져옴
    **/
    private List<Tag> getTagList(List<String> aTagList) {
        List<Tag> result = new ArrayList<>();

        // 1. Find Exist Tag
        List<Tag> existTags = tagRepository.findAllByNameIn(aTagList);
        result.addAll(existTags);

        // 2. Filtering Exist Tag
        List<String> existTagNames = existTags.stream().map(Tag::getName).collect(Collectors.toList());
        aTagList.removeAll(existTagNames);

        // 3. Collect Create Tags
        List<Tag> createTags = tagRepository.saveAll(
                                aTagList.stream()
                                        .map(Tag::create)
                                        .collect(Collectors.toList()));
        result.addAll(createTags);

        return result;
    }
}
