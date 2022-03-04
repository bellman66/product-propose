package com.product.propose.domain.wiki.entity.aggregate;

import com.product.propose.domain.wiki.entity.PriceRecord;
import com.product.propose.domain.wiki.entity.WikiTag;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.PriceUpdateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.event.TagRegister;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
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
    private Long accountId;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "wiki", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PriceRecord> priceRecordGroup = new ArrayList<>();

    @OneToMany(mappedBy = "wiki", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<WikiTag> wikiTagGroup = new ArrayList<>();

    private static Wiki create(Long accountId, WikiCreateForm createForm) {
        return Wiki.builder()
                .accountId(accountId)
                .title(createForm.getTitle())
                .build();
    }

    /**
    *   @Author : Youn
    *   @Summary : Main - 위키 등록
    *   @Param : WikiCreateData
    *   @Memo : 태그의 경우 이벤틀 로직으로 구성 ( 리스너 참조 )
    **/
    public static Wiki registerWiki(Long accountId, WikiCreateData registerData) {
        Wiki result = create(accountId, registerData.getWikiCreateForm());

        // 프라이스 등록
        result.addPriceRecordGroup(PriceRecord.create(accountId, registerData.getPriceRecordCreateForm()));

        // Event - Tag 등록
        result.eventWikiTagGroup(registerData.getTagGroup());
        return result;
    }

    public void update(WikiUpdateData updateData) {
        this.title = updateData.getTitle();
    }

    /**
    *   @Author : Youn
    *   @Summary : 가격 정책 추가
    *   @Param : PriceRecordCreateForm
    **/
    public void registerPriceRecord(Long accountId, PriceRecordCreateForm priceRecordCreateForm) {
        addPriceRecordGroup(PriceRecord.create(accountId, priceRecordCreateForm));
    }

    public void updatePriceRecord(Long recordId, Long accountId, PriceUpdateData updateData) {
        PriceRecord target = getPriceRecordGroup().stream()
                .filter(priceRecord -> priceRecord.getId().equals(recordId) && priceRecord.getAccountId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new CommonException(ErrorCode.PRICE_RECORD_NOT_FOUND));
        target.update(updateData);
    }

    // ============================================  ETC  ===================================================

    private void addPriceRecordGroup(PriceRecord priceRecord) {
        priceRecord.setWiki(this);
        priceRecordGroup.add(priceRecord);
    }

    // Tag 등록 이벤트 발행 로직
    private void eventWikiTagGroup(List<String> tagRegisterGroup) {
        registerEvent(TagRegister.createTagRegister(this, tagRegisterGroup));
    }

    public void registerWikiTag(Tag tag) {
        wikiTagGroup.add(WikiTag.create(this, tag));
    }
}
