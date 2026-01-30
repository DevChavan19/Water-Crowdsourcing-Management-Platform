package com.watercrowdsourcing.image_service.service;


import org.springframework.web.multipart.MultipartFile;

import com.watercrowdsourcing.image_service.entities.Image;

public interface ImageService {

    Long uploadImage(MultipartFile file);

    Image getImage(Long imageId);
}

