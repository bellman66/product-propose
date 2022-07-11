package com.product.propose.global.utils.upload.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.upload.dto.ImageInfoDto;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.product.propose.global.utils.json.JsonNodeUtil.ReturnType.BOOLEAN;
import static com.product.propose.global.utils.json.JsonNodeUtil.ReturnType.STRING;
import static com.product.propose.global.utils.json.JsonNodeUtil.getNodeValueWithNull;

public class ImageInfoDeserializer extends StdDeserializer<ImageInfoDto> {

    protected ImageInfoDeserializer(Class<?> vc) {
        super(vc);
    }

    public ImageInfoDeserializer() {
        this(null);
    }

    @Override
    public ImageInfoDto deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode rootNode = treeNode.get("data");

        final boolean success = getNodeValueWithNull(BOOLEAN, treeNode, "success");
        CommonAssert.isTrue(success, ErrorCode.API_STATUS_NOT_OK);

        final String filename = getNodeValueWithNull(STRING, rootNode,"image", "filename");
        final String originImgUrl = getNodeValueWithNull(STRING, rootNode,"image", "url");
        final String mediumImgUrl = getNodeValueWithNull(STRING, rootNode,"medium", "url");
        final String thumbnail = getNodeValueWithNull(STRING, rootNode,"thumb", "url");
        final LocalDateTime registerDateTime = LocalDateTime.now();

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
