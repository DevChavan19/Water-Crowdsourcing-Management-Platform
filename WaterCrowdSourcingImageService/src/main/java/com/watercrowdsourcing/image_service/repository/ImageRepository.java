package com.watercrowdsourcing.image_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.watercrowdsourcing.image_service.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}