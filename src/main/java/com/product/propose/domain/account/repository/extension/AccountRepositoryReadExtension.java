package com.product.propose.domain.account.repository.extension;

import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.response.InfoResponse;
import com.product.propose.domain.account.web.dto.response.ProfileResponse;

import java.util.List;

/**
*   @Author : Youn
*   @Summary : Return Read Only Query
*   @Memo : Read 관련 처리를 대부분 담당.
**/
public interface AccountRepositoryReadExtension {

    InfoResponse readInfoById(Long accountId);

    ProfileResponse readProfileById(Long id);

    List<AccountType> readAuthTypeById(Long id);
}
