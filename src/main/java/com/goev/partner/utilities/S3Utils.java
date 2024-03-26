package com.goev.partner.utilities;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.goev.partner.constant.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@Slf4j
public class S3Utils {

    @Autowired
    private AmazonS3 s3;

    public String uploadFileOnS3(String fileName, String uploadFilePath) throws IOException {

        File tmpFile = new File(fileName);
        try {
            if (!tmpFile.exists() && !tmpFile.createNewFile())
                log.error(" unable to create new file");
            String uid = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            s3.putObject(new PutObjectRequest(uploadFilePath, "partner/" + getMd5("partner") + "/" + uid, new File(fileName)));
            Files.deleteIfExists(Paths.get(fileName));
            return "https://" + ApplicationConstants.S3_BUCKET_NAME + "/partner/" + getMd5("partner") + "/" + uid;
        } catch (Exception e) {
            log.error("Error in saving file", e);
            throw e;
        } finally {
            if (tmpFile.exists() && tmpFile.delete())
                log.info("temp file {}, deleted successfully in final block", fileName);
        }
    }


    private String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

