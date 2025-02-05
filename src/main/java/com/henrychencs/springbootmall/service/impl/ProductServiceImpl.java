package com.henrychencs.springbootmall.service.impl;

import com.henrychencs.springbootmall.constant.ProductCategory;
import com.henrychencs.springbootmall.dao.ProductDao;
import com.henrychencs.springbootmall.dto.ProductQueryParams;
import com.henrychencs.springbootmall.dto.ProductRequest;
import com.henrychencs.springbootmall.model.Product;
import com.henrychencs.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer ProductId) {
        return productDao.getProductById(ProductId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer ProductId, ProductRequest productRequest) {
        productDao.updateProduct(ProductId, productRequest);
    }

    @Override
    public void deleteProductById(Integer ProductId) {
        productDao.deleteProductById(ProductId);
    }

    @Override
    public Integer countProducts(ProductQueryParams productQueryParams) {
        return productDao.countProducts(productQueryParams);
    }
}
