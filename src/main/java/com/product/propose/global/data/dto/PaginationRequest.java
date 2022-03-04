package com.product.propose.global.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {

    @PositiveOrZero
    Integer page;

    @Positive
    @Digits(integer = 2, fraction = 0)
    Integer size;
}
