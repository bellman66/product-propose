package com.product.propose.domain.wiki.web.dto.mapper;

import com.product.propose.domain.wiki.web.dto.data.ProductImageCreateForm;
import com.product.propose.global.utils.upload.dto.ImageInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WikiMapper {

    WikiMapper INSTANCE = Mappers.getMapper(WikiMapper.class);

    @Mapping(source = "imageFileName", target = "fileName")
    @Mapping(source = "imageUrl", target = "originImgUrl")
    @Mapping(source = "mediumUrl", target = "mediumImgUrl")
    @Mapping(source = "thumbUrl", target = "thumbnail")
    @Mapping(source = "time", target = "registerDateTime")
    ProductImageCreateForm ImageInfoToProductImage(ImageInfoDto imageInfo);
}
