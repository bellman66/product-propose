package com.product.propose.domain.wiki.web.dto.data;

import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceRecordCreateForm {

    @Positive
    private int accountId;
    @Positive
    private int salePrice;

    @NotNull
    SaleWay saleWay;
}
