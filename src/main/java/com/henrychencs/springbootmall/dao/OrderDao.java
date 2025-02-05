package com.henrychencs.springbootmall.dao;


import com.henrychencs.springbootmall.model.Order;
import com.henrychencs.springbootmall.model.OrderItem;
import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItem);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsById(Integer orderItemId);
}
