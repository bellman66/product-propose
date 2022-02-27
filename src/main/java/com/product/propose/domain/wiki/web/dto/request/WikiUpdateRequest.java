package com.product.propose.domain.wiki.web.dto.request;

import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WikiUpdateRequest {

    @NotNull
    private WikiUpdateData wikiUpdateData;
}
