package com.product.propose.domain.wiki.web.event;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagRegister {

    private Wiki wiki;
    private List<String> tagRegisterGroup;

    public static TagRegister createTagRegister(Wiki wiki, List<String> tagRegisterGroup) {
        TagRegister result = new TagRegister();
        result.wiki = wiki;
        result.tagRegisterGroup = tagRegisterGroup;
        return result;
    }
}
