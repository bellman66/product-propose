package com.template.basespring.domain.account.repository;

import com.template.basespring.domain.account.entity.Account;
import com.template.basespring.domain.account.repository.extension.AccountRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryExtension {

    Account findByEmail(String email);
}
