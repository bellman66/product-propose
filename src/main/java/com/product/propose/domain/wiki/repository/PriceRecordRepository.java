package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.PriceRecord;
import com.product.propose.domain.wiki.repository.extension.PriceRecordReadExtenstion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRecordRepository extends JpaRepository<PriceRecord, Long>, PriceRecordReadExtenstion {

}
