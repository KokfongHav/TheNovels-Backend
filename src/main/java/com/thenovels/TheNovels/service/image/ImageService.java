package com.thenovels.TheNovels.service.image;

import com.thenovels.TheNovels.dto.ImageDto;
import com.thenovels.TheNovels.exception.ResourceNotFoundException;
import com.thenovels.TheNovels.model.Image;
import com.thenovels.TheNovels.model.Product;
import com.thenovels.TheNovels.repository.ImageRepositiory;
import com.thenovels.TheNovels.service.product.IProductService;
import com.thenovels.TheNovels.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepositiory imageRepositiory;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepositiory.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Image not found."));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepositiory.findById(id).ifPresentOrElse(imageRepositiory::delete, ()->{
            throw new ResourceNotFoundException("No Image found with id: " + id);
        });
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for (MultipartFile file : files) {
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepositiory.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepositiory.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDto.add(imageDto);

            }   catch (IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
       Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepositiory.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
