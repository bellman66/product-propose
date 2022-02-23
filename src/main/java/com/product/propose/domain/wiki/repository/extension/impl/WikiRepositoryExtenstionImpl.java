package com.product.propose.domain.wiki.repository.extension.impl;

import com.product.propose.domain.wiki.entity.aggregate.QWiki;
import com.product.propose.domain.wiki.entity.reference.QTag;
import com.product.propose.domain.wiki.repository.extension.WikiRepositoryExtenstion;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.product.propose.domain.wiki.entity.QPriceRecord.priceRecord;
import static com.product.propose.domain.wiki.entity.QWikiTag.wikiTag;
import static com.product.propose.domain.wiki.entity.aggregate.QWiki.wiki;
import static com.product.propose.domain.wiki.entity.reference.QTag.tag;

public class WikiRepositoryExtenstionImpl implements WikiRepositoryExtenstion {

    private final JPAQueryFactory queryFactory;

    public WikiRepositoryExtenstionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }


    // ===== ===== ===== ===== ===== override method ===== ===== ===== ===== =====

    @Override
    public WikiResponse findWikiResponseById(Long wikiId) {
        return queryFactory
                .select(Projections.constructor(WikiResponse.class,
                            wiki.title,
                            wiki.priceRecordGroup,
                            wikiTag.tag
                        ))
                .from(wiki)
                .innerJoin(wiki.priceRecordGroup, priceRecord).fetchJoin()
                .innerJoin(wiki.wikiTagGroup, wikiTag)
                .innerJoin(wikiTag.tag, tag).fetchJoin()
                .where(
                        wiki.id.eq(wikiId)
                )
                .fetchOne();
    }

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
