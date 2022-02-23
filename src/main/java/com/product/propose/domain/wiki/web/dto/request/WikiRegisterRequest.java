package com.product.propose.domain.wiki.web.dto.request;

import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WikiRegisterRequest {

    @NotNull
    private WikiCreateData wikiCreateData;

    public String getWikiTitle() {
        return wikiCreateData.getWikiCreateForm().getTitle();
    }
}
