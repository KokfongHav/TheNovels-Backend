package com.thenovels.TheNovels.service.product;

import com.thenovels.TheNovels.model.Product;
import com.thenovels.TheNovels.request.AddProductRequest;
import com.thenovels.TheNovels.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long product_id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductByName(String name);
    Long countProductsByBrandAndName(String brand, String name);
}
