package com.product.propose.global.utils.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.global.config.props.UploadProps;
import com.product.propose.global.exception.dto.CommonException;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import com.product.propose.global.utils.upload.dto.ImageInfoDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageBBUploadUtil implements UploadUtils{

    private static final String IMAGEBB_UPLOAD_URL = "https://api.imgbb.com/1/upload";
    private final UploadProps uploadProps;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ImageBBUploadUtil(UploadProps uploadProps, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.uploadProps = uploadProps;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public ImageInfoDto uploadImage(MultipartFile image) {
        // Set Entity
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(getHttpBody(image));

        // Call Upload
        ResponseEntity<ImageInfoDto> response = restTemplate.postForEntity(IMAGEBB_UPLOAD_URL + "?key=" + uploadProps.getImageApiKey(), httpEntity, ImageInfoDto.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            ImageInfoDto content = response.getBody();
            return content;
        }

        throw new CommonException(ErrorCode.API_PROCESS_NOT_NORMAL, response.toString());
    }

    // Set Body
    private MultiValueMap<String,Object> getHttpBody(MultipartFile image) {
        LinkedMultiValueMap<String, Object> result = new LinkedMultiValueMap<>() {{
            try {
                add("image", Base64Utils.encode(image.getBytes()));
            } catch (IOException exception) {
                throw new CommonException(ErrorCode.FILE_IO_ERROR, exception);
            }
        }};
        return result;
    }
}
