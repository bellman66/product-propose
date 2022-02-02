package com.product.propose.domain.wiki.web.event.listener;

import com.product.propose.domain.wiki.entity.WikiTag;
import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.repository.TagRepository;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.web.event.TagRegister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

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

    @Async
    @TransactionalEventListener
    public void RegisterTag(TagRegister tagRegister) {
        System.out.println(" hello Tag ");

        List<Tag> tags = tagRepository.saveAll(tagRegister.getTagRegisterGroup()
                .stream()
                .map(Tag::createTag)
                .collect(Collectors.toList()));
        tagRepository.saveAll(tags);

        Wiki wiki = tagRegister.getWiki();
        for (Tag tag:tags) {
            wiki.registerWikiTagGroup(tag);
        }
        wikiRepository.save(wiki);
    }
}
