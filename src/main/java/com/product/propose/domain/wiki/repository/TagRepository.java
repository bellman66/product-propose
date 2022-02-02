package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.reference.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
