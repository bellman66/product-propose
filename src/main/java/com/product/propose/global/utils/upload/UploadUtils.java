package com.product.propose.global.utils.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface UploadUtils {

    String uploadImage(MultipartFile image) throws IOException, URISyntaxException;
}
