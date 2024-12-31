package com.thenovels.TheNovels.service.image;

import com.thenovels.TheNovels.dto.ImageDto;
import com.thenovels.TheNovels.model.Image;
import com.thenovels.TheNovels.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
