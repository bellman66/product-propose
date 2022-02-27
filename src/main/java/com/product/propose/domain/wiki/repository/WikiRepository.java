package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.extension.WikiRepositoryExtenstion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import javax.persistence.QueryHint;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long>, WikiRepositoryExtenstion {
    @QueryHints(value = @QueryHint(name = READ_ONLY, value = "true"))
    boolean existsByTitle(String title);

    @EntityGraph(attributePaths = {"priceRecordGroup"})
    Wiki findWikiById(Long wikiId);
}
