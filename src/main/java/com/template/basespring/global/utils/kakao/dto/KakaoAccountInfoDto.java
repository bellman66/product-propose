package com.template.basespring.global.utils.kakao.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.template.basespring.global.utils.kakao.dto.deserializer.KakaoAccountInfoDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize(using = KakaoAccountInfoDeserializer.class)
public class KakaoAccountInfoDto {

    private int id;
    private boolean hasEmail;

    private String email;
    private String nickname;
}
