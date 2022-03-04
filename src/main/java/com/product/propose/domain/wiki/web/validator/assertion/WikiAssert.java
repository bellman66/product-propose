package com.product.propose.domain.wiki.web.validator.assertion;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class WikiAssert extends CommonAssert {

    private static WikiRepository wikiRepository;

    public WikiAssert(WikiRepository wikiRepository) {
        WikiAssert.wikiRepository = wikiRepository;
    }

    public static void exists(Long wikiId) {
        decideException(wikiRepository.existsById(wikiId), ErrorCode.WIKI_NOT_FOUND);
    }

    public static void exists(Wiki wiki) {
        decideException(Objects.nonNull(wiki), ErrorCode.WIKI_NOT_FOUND);
    }

    public static void nonExist(String title) {
        decideException(!wikiRepository.existsByTitle(title), ErrorCode.ALREADY_EXISTS_WIKI);
    }
}
