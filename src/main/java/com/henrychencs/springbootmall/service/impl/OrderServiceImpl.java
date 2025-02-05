package com.henrychencs.springbootmall.service.impl;


import com.henrychencs.springbootmall.dao.OrderDao;
import com.henrychencs.springbootmall.dao.ProductDao;
import com.henrychencs.springbootmall.dto.BuyItem;
import com.henrychencs.springbootmall.dto.CreateOrderRequest;
import com.henrychencs.springbootmall.model.OrderItem;
import com.henrychencs.springbootmall.model.Product;
import com.henrychencs.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int total_amount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //  計算總價
            int amount = product.getPrice() * buyItem.getQuantity();
            total_amount += amount;

            //  轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

//        System.out.println("Service:" + userId);

        Integer orderId = orderDao.createOrder(userId, total_amount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
