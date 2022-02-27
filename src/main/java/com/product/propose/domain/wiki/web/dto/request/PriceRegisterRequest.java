package com.product.propose.domain.wiki.web.dto.request;

import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceRegisterRequest {

    @NotNull
    private PriceRecordCreateForm priceRecordCreateForm;
}
