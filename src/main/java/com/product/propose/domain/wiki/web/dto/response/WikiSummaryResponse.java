package com.product.propose.domain.wiki.web.dto.response;

import com.product.propose.domain.wiki.entity.reference.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WikiSummaryResponse {

    // Wiki
    private String title;

    // PriceRecord
    private List<Tag> tagGroup;
}
