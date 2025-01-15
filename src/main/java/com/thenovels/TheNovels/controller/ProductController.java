package com.thenovels.TheNovels.controller;

import com.thenovels.TheNovels.exception.ResourceNotFoundException;
import com.thenovels.TheNovels.model.Product;
import com.thenovels.TheNovels.request.AddProductRequest;
import com.thenovels.TheNovels.request.ProductUpdateRequest;
import com.thenovels.TheNovels.response.ApiResponse;
import com.thenovels.TheNovels.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService iProductService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = iProductService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Success", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("product/{productId/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = iProductService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct (@RequestBody AddProductRequest product) {
        try {
            Product theProduct = iProductService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Add product success!", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId){
        try {
            Product theProduct = iProductService.updateProduct(request, productId);
            return ResponseEntity.ok(new ApiResponse("Update product success!", theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            iProductService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete product success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
