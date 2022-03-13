package com.product.propose.global.utils.upload;

import com.product.propose.global.config.props.UploadProps;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class ImageBBUploadUtil implements UploadUtils{

    private static final String IMAGEBB_UPLOAD_URL = "https://api.imgbb.com/1/upload";
    private final UploadProps uploadProps;
    private final RestTemplate restTemplate;

    public ImageBBUploadUtil(UploadProps uploadProps, RestTemplate restTemplate) {
        this.uploadProps = uploadProps;
        this.restTemplate = restTemplate;
    }

    @Override
    public String uploadImage(MultipartFile image) throws IOException, URISyntaxException {

        System.out.println("uploadProps = " + uploadProps.getImageApiKey());

        // ?expiration=600&key=YOUR_CLIENT_API_KEY
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
        body.add("image", image.getBytes());

        MultiValueMap<String,String> header = new LinkedMultiValueMap<>();
        header.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, header);

        ResponseEntity<String> response = restTemplate.postForEntity(IMAGEBB_UPLOAD_URL + "?expiration=600&key=" + uploadProps.getImageApiKey(), httpEntity, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            System.out.println("response.getStatusCode() = " + response.getStatusCode());
        }
        else {
            System.out.println("response = " + response);
        }
        return response.getBody();
    }
}
