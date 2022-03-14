package com.product.propose.global.utils.upload.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.product.propose.global.utils.upload.dto.deserializer.ImageInfoDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = ImageInfoDeserializer.class)
public class ImageInfoDto {

    private boolean success;

    private String imageFileName;

    private String imageUrl;
    private String mediumUrl;
    private String thumbUrl;

    private LocalDateTime time;
}
