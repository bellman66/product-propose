package com.product.propose.domain.account.web.dto.request;

import com.product.propose.domain.account.web.dto.data.integration.ProfileUpdateData;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequest {

    @NotNull
    private ProfileUpdateData profileUpdateData;
}
