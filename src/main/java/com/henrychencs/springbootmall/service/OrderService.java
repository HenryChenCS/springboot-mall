package com.henrychencs.springbootmall.service;

import com.henrychencs.springbootmall.dto.CreateOrderRequest;
import com.henrychencs.springbootmall.dto.OrderQueryParams;
import com.henrychencs.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
}
