package com.product.propose.domain.main;

import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class init {

    private final DataDefault dataDefault;

    public init(DataDefault dataDefault) {
        this.dataDefault = dataDefault;
    }

    @PostConstruct
    void exec() {
        for (int i=1; i<100; i++) {
            dataDefault.insertWiki(i);
        }
    }

    @Service
    static class DataDefault {

        private final EntityManager em;

        DataDefault(EntityManager em) {
            this.em = em;
        }

        @Transactional
        public void insertWiki(int index) {
            WikiCreateForm wikiCreateForm = new WikiCreateForm("Wiki Bot-" + index);
            PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(22000, 20000, new SaleWay("test2", "", "", "", "", "", "", ""));
            List<String> tagGroup = new ArrayList<>() {{
                add("TestTag1");
                add("TestTag2");
            }};

            WikiCreateData wikiCreateData = new WikiCreateData(wikiCreateForm, priceRecordCreateForm, tagGroup);
            Wiki wiki = Wiki.registerWiki(1L, wikiCreateData);

            em.persist(wiki);
        }
    }
}
