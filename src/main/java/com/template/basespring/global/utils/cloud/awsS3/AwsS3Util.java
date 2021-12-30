package com.template.basespring.global.utils.cloud.awsS3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.template.basespring.global.config.props.aws.AwsS3PathProps;
import com.template.basespring.global.exception.dto.CommonException;
import com.template.basespring.global.exception.dto.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AwsS3Util {

    // Setting - infra/config/cloud/amazon/AwsS3Config
    private final AmazonS3 amazonS3;
    // Setting - infra/config/props/aws/AwsS3PathProps
    private final AwsS3PathProps awsS3PathProps;

    public void uploadFile(MultipartFile multipartFile, String transForName)  {
        ObjectMetadata metadata = new ObjectMetadata();
        try {
            metadata.setHeader("filename" , transForName);
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());

            amazonS3.putObject(new PutObjectRequest(awsS3PathProps.getLocationUrl(), transForName , multipartFile.getInputStream() , metadata));
        }
        catch (Exception e) {
            throw new CommonException(ErrorCode.AMAZON_S3_FILE_UPLOAD_FAIL, e);
        }
    }

    public void deleteFile(String targetName) {
        DeleteObjectRequest deleteRequest;
        try {
            deleteRequest = new DeleteObjectRequest(awsS3PathProps.getLocationUrl(), targetName);
            amazonS3.deleteObject(deleteRequest);
        }
        catch (Exception e) {
            throw new CommonException(ErrorCode.AMAZON_S3_FILE_UPLOAD_FAIL);
        }
    }
}
