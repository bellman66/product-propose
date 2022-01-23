package com.product.propose.domain.account.web.dto.request;

import com.product.propose.domain.account.entity.Account;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileRequest {

    @NotBlank
    private String phone;

    private byte[] profileImage;

    public static ProfileRequest loadProfile(Account account) {
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.phone = account.getPhone();
        profileRequest.profileImage = account.getProfileImage();
        return profileRequest;
    }
}
