package com.product.propose.domain.wiki.web.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageCreateForm {

    private String fileName;

    private String originImgUrl;
    private String mediumImgUrl;
    private String thumbnail;

    private LocalDateTime registerDateTime;
}
