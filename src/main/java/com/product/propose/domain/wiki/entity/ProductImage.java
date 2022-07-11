package com.product.propose.domain.wiki.entity;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.web.dto.data.ProductImageCreateForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "product_image")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority")
    private int priority;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "origin_img_url")
    private String originImgUrl;
    @Column(name = "medium_img_url")
    private String mediumImgUrl;
    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "register_date_time")
    private LocalDateTime registerDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id")
    private Wiki wiki;

    // ============================================  CREATE  ===================================================

    public static ProductImage create(ProductImageCreateForm createForm) {
        return ProductImage.builder()
                .fileName(createForm.getFileName())
                .originImgUrl(createForm.getOriginImgUrl())
                .mediumImgUrl(createForm.getMediumImgUrl())
                .thumbnail(createForm.getThumbnail())
                .registerDateTime(createForm.getRegisterDateTime())
                .build();
    }

    // ============================================  ETC  ===================================================

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }
}
