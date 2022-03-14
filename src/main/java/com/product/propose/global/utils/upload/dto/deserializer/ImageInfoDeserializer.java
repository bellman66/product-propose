package com.product.propose.global.utils.upload.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.upload.dto.ImageInfoDto;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ImageInfoDeserializer extends StdDeserializer<ImageInfoDto> {

    protected ImageInfoDeserializer(Class<?> vc) {
        super(vc);
    }

    public ImageInfoDeserializer() {
        this(null);
    }

    @Override
    public ImageInfoDto deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode rootNode = treeNode.get("data");

        final boolean success = treeNode.get("success").asBoolean(false);
        CommonAssert.isTrue(success, ErrorCode.API_STATUS_NOT_OK);

        final String filename = rootNode.get("image").get("filename").asText("");
        final String originImgUrl = rootNode.get("image").get("url").asText("");
        final String mediumImgUrl = rootNode.get("medium").get("url").asText("");
        final String thumbnail = rootNode.get("thumb").get("url").asText("");

        Instant time = new Date(rootNode.get("time").asLong()).toInstant();
        final LocalDateTime registerDateTime = LocalDateTime.ofInstant(time, ZoneId.systemDefault());

        return ImageInfoDto.builder()
                .success(success)
                .imageFileName(filename)
                .imageUrl(originImgUrl)
                .mediumUrl(mediumImgUrl)
                .thumbUrl(thumbnail)
                .time(registerDateTime)
                .build();
    }
}
