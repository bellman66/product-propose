package com.product.propose.domain.account.repository.extension;

import com.product.propose.domain.account.entity.aggregate.Account;

public interface AccountRepositoryExtension {

    Account findLoginByEmail(String email);
}
