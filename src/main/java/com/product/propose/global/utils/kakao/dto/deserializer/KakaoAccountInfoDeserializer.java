package com.product.propose.global.utils.kakao.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.product.propose.global.utils.kakao.dto.KakaoAccountInfoDto;

import java.io.IOException;

public class KakaoAccountInfoDeserializer extends StdDeserializer<KakaoAccountInfoDto> {

    public KakaoAccountInfoDeserializer(Class<?> vc) {
        super(vc);
    }

    // 모듈 등록을 위한 NoArg가 필요
    public KakaoAccountInfoDeserializer() {
        this(null);
    }

    @Override
    public KakaoAccountInfoDto deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {

        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        int id = root.get("id").intValue();
        boolean hasEmail = root.get("kakao_account").get("has_email").booleanValue();
        String email = root.get("kakao_account").get("email").toString().replace("\"", "").trim();
        String nickname = root.get("kakao_account").get("profile").get("nickname").toString().replace("\"", "").trim();

        return new KakaoAccountInfoDto(id, hasEmail, email, nickname);
    }
}
