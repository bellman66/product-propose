package com.product.propose.domain.account.web.dto.mapper;

import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.web.dto.response.InfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "nickName", target = "nickName")
    @Mapping(source = "userProfile.userName", target = "userName")
    InfoResponse accountToInfo(Account account);
}
