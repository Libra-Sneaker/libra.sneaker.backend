package com.web.librasneaker.business.admin.fileManagement.web;

import com.web.librasneaker.config.cloudinary.CloudinaryUploadImages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/file-management")
@RequiredArgsConstructor
public class FileMngController {

    private final CloudinaryUploadImages cloudinaryUploadImages;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileImage(@RequestBody MultipartFile multipartFile) {
        return new ResponseEntity<>(cloudinaryUploadImages.uploadImage(multipartFile, "image_product"), HttpStatus.CREATED);
    }

}
