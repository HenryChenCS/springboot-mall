package com.henrychencs.springbootmall.service;

import com.henrychencs.springbootmall.constant.ProductCategory;
import com.henrychencs.springbootmall.dto.ProductQueryParams;
import com.henrychencs.springbootmall.dto.ProductRequest;
import com.henrychencs.springbootmall.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer id, ProductRequest productRequest);

    void deleteProductById(Integer id);
}