package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.PriceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRecordRepository extends JpaRepository<PriceRecord, Long> {

    @Query(value = "select pr from PriceRecord pr join fetch pr.wiki")
    Page<PriceRecord> findByWikiId(@Param("wikiId") Long wikiId, Pageable pageable);
}
