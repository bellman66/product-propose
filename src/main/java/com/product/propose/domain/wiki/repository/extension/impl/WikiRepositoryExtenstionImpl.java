package com.product.propose.domain.wiki.repository.extension.impl;

import com.mysema.commons.lang.CloseableIterator;
import com.product.propose.domain.wiki.repository.extension.WikiRepositoryExtenstion;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.annotations.QueryHints;

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
        CloseableIterator<WikiResponse> transform = queryFactory
                .from(wiki)
                .join(wiki.wikiTagGroup, wikiTag).join(wikiTag.tag, tag)
                .where(
                        wiki.id.eq(wikiId)
                )
                .setHint(QueryHints.READ_ONLY, true)
                .transform(GroupBy.groupBy(wiki)
                        .iterate(Projections.constructor(WikiResponse.class,
                                        wiki.title,
                                        GroupBy.list(tag)
                                )
                        )
                );

        CommonAssert.isTrue(transform.hasNext(), ErrorCode.WIKI_NOT_FOUND);
        return transform.next();
    }

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
