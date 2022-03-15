package com.product.propose.domain.wiki.entity;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.reference.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "wiki_tag")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WikiTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id")
    private Wiki wiki;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    // ============================================  CREATE  ===================================================

    public static WikiTag create(Wiki wiki, Tag tag) {
        return WikiTag.builder()
                .wiki(wiki)
                .tag(tag)
                .build();
    }

    // ============================================  ETC  ===================================================

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
