package com.product.propose.domain.wiki.entity;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "price_record")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aggregate Id - 가격 등록 Account
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "sale_price")
    private int salePrice;

    @Column(name = "record_date")
    private LocalDateTime recordDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    private PriceWay priceWay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id")
    private Wiki wiki;
}
