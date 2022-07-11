package com.product.propose.global.factory;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class WikiFactory {

    private static EntityManager em;

    public WikiFactory(EntityManager em) {
        WikiFactory.em = em;
    }

    private static WikiCreateData getDefaultData() {
        WikiCreateForm wikiCreateForm = new WikiCreateForm("TestWiki1");
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(22000, 20000, new SaleWay("test1", "", "", "", "", "", "", ""));
        List<String> tagGroup = new ArrayList<>(){{ add("TestTag1"); add("TestTag2"); }};

        return new WikiCreateData(wikiCreateForm, priceRecordCreateForm, tagGroup);
    }

    public static Wiki create() {
        Wiki wiki = Wiki.create(1L, getDefaultData());
        setPersist(wiki);

        return wiki;
    }


    public static Wiki create(Long accountId) {
        Wiki wiki = Wiki.create(accountId, getDefaultData());
        setPersist(wiki);

        return wiki;
    }

    private static void setPersist(Wiki wiki) {
        em.persist(wiki);
        em.flush();
    }
}
