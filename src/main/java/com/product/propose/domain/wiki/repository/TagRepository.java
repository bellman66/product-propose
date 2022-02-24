package com.product.propose.domain.wiki.repository;

import com.product.propose.domain.wiki.entity.reference.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @QueryHints(value = @QueryHint(name = READ_ONLY, value = "true"))
    List<Tag> findAllByNameIn(Collection<String> name);
}
