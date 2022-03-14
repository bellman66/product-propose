package com.product.propose.global.utils.upload;

import com.product.propose.global.utils.upload.dto.ImageInfoDto;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface UploadUtils {

    ImageInfoDto uploadImage(MultipartFile image);
}
