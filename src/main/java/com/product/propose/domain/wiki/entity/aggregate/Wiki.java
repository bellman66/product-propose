package com.product.propose.domain.wiki.entity.aggregate;

import com.product.propose.domain.wiki.entity.PriceRecord;
import com.product.propose.domain.wiki.entity.WikiTag;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.event.TagRegister;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "wiki")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wiki extends AbstractAggregateRoot<Wiki> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aggregate Id - 위키 수정 Account
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "wiki", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PriceRecord> priceRecordGroup = new ArrayList<>();

    @OneToMany(mappedBy = "wiki", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<WikiTag> wikiTagGroup = new ArrayList<>();

    private static Wiki createWiki(WikiCreateForm createForm) {
        return Wiki.builder()
                .accountId(createForm.getAccountId())
                .title(createForm.getTitle())
                .build();
    }

    public static Wiki registerWiki(WikiCreateData registerData) {
        Wiki result = createWiki(registerData.getWikiCreateForm());

        // 프라이스 등록
        result.addPriceRecordGroup(PriceRecord.createPriceRecord(registerData.getPriceRecordCreateForm()));

        // Event - Tag
        result.eventWikiTagGroup(registerData.getTagGroup());
        return result;
    }

    public void registerWikiTagGroup(Tag tag) {
        wikiTagGroup.add(WikiTag.createrWikiTag(this, tag));
    }

    private void addPriceRecordGroup(PriceRecord priceRecord) {
        priceRecord.setWiki(this);
        priceRecordGroup.add(priceRecord);
    }

    private void eventWikiTagGroup(List<String> tagRegisterGroup) {
        registerEvent(TagRegister.createTagRegister(this, tagRegisterGroup));
    }
}
