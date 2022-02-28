package com.product.propose.domain.wiki.web.dto.data.integration;

import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceUpdateData {

    private Integer originPrice;
    private Integer salePrice;

    private SaleWay saleWay;
}
