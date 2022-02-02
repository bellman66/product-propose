package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
}
