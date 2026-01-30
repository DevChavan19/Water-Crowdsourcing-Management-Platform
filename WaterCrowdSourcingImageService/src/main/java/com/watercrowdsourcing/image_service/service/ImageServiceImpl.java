package com.watercrowdsourcing.image_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.watercrowdsourcing.image_service.entities.Image;
import com.watercrowdsourcing.image_service.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Long uploadImage(MultipartFile file) {

        try {
            Image image = new Image();
            image.setFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setData(file.getBytes());
            image.setCreatedAt(LocalDateTime.now());

            Image saved = imageRepository.save(image);
            return saved.getImageId();

        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }
    }

    @Override
    public Image getImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
