package com.hg1.dsHydrocabure.common.services;

import com.hg1.dsHydrocabure.common.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
@Slf4j
public class CommonService {
    public  CommonService() {
    }

    public String[] getFromInputStream(String filePhath) {
        ClassPathResource classPathResource = new ClassPathResource(filePhath);
        try {
            byte[] streamData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            String lines = new String(streamData, StandardCharsets.UTF_8);
            return splitLine(lines);
        } catch (IOException e) {
            throw new AppException(e.getMessage());
        }
    }

    private String[] splitLine(String lines) {
        return lines.split("\\n");
    }

}
