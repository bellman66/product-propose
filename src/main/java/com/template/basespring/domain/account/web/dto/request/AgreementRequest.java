package com.template.basespring.domain.account.web.dto.request;

import lombok.Data;
import com.template.basespring.domain.account.entity.Account;

import javax.validation.constraints.NotNull;

@Data
public class AgreementRequest {

    @NotNull
    private boolean emailReceiveAgreement;
    @NotNull
    private boolean phoneReceiveAgreement;

    public static AgreementRequest loadAgreement(Account account) {
        AgreementRequest agreementRequest = new AgreementRequest();
        agreementRequest.emailReceiveAgreement = account.isEmailRecvAgreement();
        agreementRequest.phoneReceiveAgreement = account.isPhoneRecvAgreement();
        return agreementRequest;
    }
}
