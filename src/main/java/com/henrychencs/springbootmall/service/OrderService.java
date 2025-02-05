package com.henrychencs.springbootmall.service;

import com.henrychencs.springbootmall.dto.CreateOrderRequest;
import com.henrychencs.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
