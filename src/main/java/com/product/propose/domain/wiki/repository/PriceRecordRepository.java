package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.PriceRecord;
import com.product.propose.domain.wiki.repository.extension.PriceRecordRepositoryExtenstion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRecordRepository extends JpaRepository<PriceRecord, Long>, PriceRecordRepositoryExtenstion {

}
