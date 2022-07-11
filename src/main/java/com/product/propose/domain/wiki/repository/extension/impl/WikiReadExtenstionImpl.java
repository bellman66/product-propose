package com.product.propose.domain.wiki.repository.extension.impl;

import com.mysema.commons.lang.CloseableIterator;
import com.product.propose.domain.wiki.entity.QProductImage;
import com.product.propose.domain.wiki.repository.extension.WikiReadExtenstion;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiPageData;
import com.product.propose.domain.wiki.web.dto.response.WikiSummaryResponse;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.data.dto.PageResponse;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.product.propose.domain.wiki.entity.QPriceRecord.priceRecord;
import static com.product.propose.domain.wiki.entity.QProductImage.productImage;
import static com.product.propose.domain.wiki.entity.QWikiTag.wikiTag;
import static com.product.propose.domain.wiki.entity.aggregate.QWiki.wiki;
import static com.product.propose.domain.wiki.entity.reference.QTag.tag;

public class WikiReadExtenstionImpl implements WikiReadExtenstion {

    private final JPAQueryFactory queryFactory;

    public WikiReadExtenstionImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }


    // ===== ===== ===== ===== ===== override method ===== ===== ===== ===== =====

    @Override
    public WikiSummaryResponse readWikiResponseById(Long wikiId) {
        CloseableIterator<WikiSummaryResponse> transform = queryFactory
                .from(wiki)
                .leftJoin(wiki.wikiTagGroup, wikiTag).leftJoin(wikiTag.tag, tag)
                .where(
                        wiki.id.eq(wikiId)
                )
                .setHint(QueryHints.READ_ONLY, true)
                .transform(GroupBy.groupBy(wiki)
                        .iterate(Projections.constructor(WikiSummaryResponse.class,
                                        wiki.title,
                                        GroupBy.list(tag)
                                )
                        )
                );

        CommonAssert.isTrue(transform.hasNext(), ErrorCode.WIKI_NOT_FOUND);
        return transform.next();
    }

    @Override
    public PageResponse readWikiPageResponse(Pageable pageable) {
        LocalDateTime currentDate = LocalDate.now().atStartOfDay();

        JPAQuery<WikiPageData> target = queryFactory
                .select(Projections.constructor(WikiPageData.class,
                        wiki.title,
                        priceRecord.accountId,
                        priceRecord.originPrice,
                        priceRecord.salePrice,
                        productImage.thumbnail
                ))
                .from(wiki)
                .innerJoin(wiki.priceRecordGroup, priceRecord)
                .innerJoin(wiki.imageGroup, productImage)
                .where(priceRecord.recordDate.between(currentDate, currentDate.plusDays(1)));

        List<WikiPageData> content = target
                .orderBy(priceRecord.recordDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return PageResponse.create(PageableExecutionUtils.getPage(content, pageable, target::fetchCount));
    }

    // ===== ===== ===== ===== ===== boolean expression ===== ===== ===== ===== =====
}
