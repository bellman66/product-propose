package com.product.propose.domain.wiki.web.dto.data.integration;

import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WikiCreateData {

    @NotNull
    private WikiCreateForm wikiCreateForm;
    @NotNull
    private PriceRecordCreateForm priceRecordCreateForm;

    private List<String> tagGroup;

    public String getWikiTitle() {
        return getWikiCreateForm().getTitle();
    }
}
