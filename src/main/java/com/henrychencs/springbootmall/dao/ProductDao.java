package com.henrychencs.springbootmall.dao;

import com.henrychencs.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer id);
}
