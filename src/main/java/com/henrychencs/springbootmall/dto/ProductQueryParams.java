package com.henrychencs.springbootmall.dto;

import com.henrychencs.springbootmall.constant.ProductCategory;

public class ProductQueryParams {

    ProductCategory category;
    String keyword;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
