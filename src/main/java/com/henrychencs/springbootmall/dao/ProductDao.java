package com.henrychencs.springbootmall.dao;

import com.henrychencs.springbootmall.constant.ProductCategory;
import com.henrychencs.springbootmall.dto.ProductRequest;
import com.henrychencs.springbootmall.model.Product;
import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String keyword);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer id, ProductRequest productRequest);

    void deleteProductById(Integer id);
}
