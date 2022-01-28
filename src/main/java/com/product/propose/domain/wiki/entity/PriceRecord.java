package com.product.propose.domain.wiki.entity;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
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

    @Embedded
    private SaleWay saleWay;

    @Column(name = "record_date")
    private LocalDateTime recordDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id")
    private Wiki wiki;

    private static PriceRecord createPriceRecord(PriceRecordCreateForm createform) {
        return PriceRecord.builder()
                .accountId(createform.getAccountId())
                .salePrice(createform.getSalePrice())
                .saleWay(createform.getSaleWay())
                .recordDate(LocalDateTime.now())
                .build();
    }

    private void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }
}
