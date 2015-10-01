import java.net.URL;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // KMS encrypted objects require using SigV4
        System.setProperty(SDKGlobalConfiguration.ENFORCE_S3_SIGV4_SYSTEM_PROPERTY, Boolean.TRUE.toString());

        AmazonS3 s3 = new AmazonS3Client();
        s3.setRegion(Regions.getCurrentRegion()); // KMS encrypted objects require specifying region
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("testBucketName", "testObjectKey");
        URL presignedUrl = s3.generatePresignedUrl(generatePresignedUrlRequest);

        System.out.println(presignedUrl);
    }
}

