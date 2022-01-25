package com.product.propose.domain.account.web.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileCreateForm {

    @NotBlank
    private String userName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String postCode;

    @NotBlank
    private String address;
}
