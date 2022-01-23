package com.product.propose.global.utils.iamport.dto;

import lombok.Data;

@Data
public class IamportInfo {
    private int paymentAmount;

    private String pg;
    private String payMethod;
    private String impUid;
    private String merchantUid;
}
