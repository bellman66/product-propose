package com.product.propose.domain.account.web.dto.data;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserProfileCreateForm {

    @NotBlank
    private final String userName;

    @NotBlank
    private final String phoneNumber;

    @NotBlank
    private final String postCode;

    @NotBlank
    private final String address;
}
