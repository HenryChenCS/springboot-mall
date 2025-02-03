package com.henrychencs.springbootmall.service;

import com.henrychencs.springbootmall.dto.ProductRequest;
import com.henrychencs.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer id, ProductRequest productRequest);
}