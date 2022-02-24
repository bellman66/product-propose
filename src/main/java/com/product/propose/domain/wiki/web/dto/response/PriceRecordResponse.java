package com.product.propose.domain.wiki.web.dto.response;

import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceRecordResponse {

    private Long id;
    private Long accountId;

    private int originPrice;
    private int salePrice;

    private SaleWay saleWay;
    private LocalDateTime recordDate;
}
