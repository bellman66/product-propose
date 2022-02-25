package com.product.propose.domain.account.web.dto.data.integration;

import com.product.propose.domain.account.web.dto.data.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateData {

    @NotNull
    private AccountUpdateForm accountUpdateForm;
    @NotNull
    private ProfileUpdateForm profileUpdateForm;
}
