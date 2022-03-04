package com.product.propose.domain.wiki.repository.extension.impl;

import com.product.propose.domain.wiki.repository.extension.PriceRecordReadExtenstion;
import com.product.propose.domain.wiki.web.dto.response.PriceRecordResponse;
import com.product.propose.global.data.dto.PageResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.product.propose.domain.wiki.entity.QPriceRecord.priceRecord;
import static com.product.propose.domain.wiki.entity.aggregate.QWiki.wiki;


public class PriceRecordReadExtenstionImpl implements PriceRecordReadExtenstion {

    private final JPAQueryFactory queryFactory;

    public PriceRecordReadExtenstionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }

    // ===== ===== ===== ===== ===== override method ===== ===== ===== ===== =====

    @Override
    public PageResponse readPageByWikiId(Long wikiId, Pageable pageable) {
        JPAQuery<PriceRecordResponse> recordJPAQuery = queryFactory
                .select(Projections.constructor(PriceRecordResponse.class,
                            priceRecord.id,
                            priceRecord.accountId,
                            priceRecord.originPrice,
                            priceRecord.salePrice,
                            priceRecord.saleWay,
                            priceRecord.recordDate
                        )
                )
                .from(priceRecord)
                .innerJoin(priceRecord.wiki, wiki)
                .where(wiki.id.eq(wikiId));

        List<PriceRecordResponse> fetch = recordJPAQuery
                .orderBy(priceRecord.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return PageResponse.create(PageableExecutionUtils.getPage(fetch, pageable, recordJPAQuery::fetchCount));
    }

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
