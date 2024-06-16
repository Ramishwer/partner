package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.utilities.StorageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class FileController {

    private final StorageUtils storageUtils;

    @PostMapping(value = "/files")
    public ResponseDto<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseDto<>(
                StatusDto.builder().message("SUCCESS").build(), 200,
                storageUtils.store(file)
        );
    }
}
