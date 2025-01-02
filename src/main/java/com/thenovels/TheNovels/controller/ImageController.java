package com.thenovels.TheNovels.controller;

import com.thenovels.TheNovels.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
//    private final IImageService imageService;

}
