package com.product.propose.domain.account.web.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateForm {

    private String userName;
    private String phoneNumber;
    private String postCode;
    private String address;

    private boolean emailRecv;
    private boolean phoneRecv;
}
