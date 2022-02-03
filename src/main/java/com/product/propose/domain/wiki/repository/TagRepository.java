package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.reference.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByNameIn(Collection<String> name);
}
