package com.product.propose.domain.wiki.web.dto.data.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WikiPageData {

    // Wiki
    private String title;

    private Long accountId;
    private int originPrice;
    private int salePrice;

    private String thumbnail;
}
