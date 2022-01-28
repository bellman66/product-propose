package com.product.propose.domain.wiki.web.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WikiCreateForm {

    @NotNull
    private int accountId;
    @NotBlank
    private String title;
}
