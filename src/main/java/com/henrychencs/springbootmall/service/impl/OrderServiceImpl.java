package com.henrychencs.springbootmall.service.impl;


import com.henrychencs.springbootmall.dao.OrderDao;
import com.henrychencs.springbootmall.dao.ProductDao;
import com.henrychencs.springbootmall.dao.UserDao;
import com.henrychencs.springbootmall.dao.impl.OrderDaoImpl;
import com.henrychencs.springbootmall.dto.BuyItem;
import com.henrychencs.springbootmall.dto.CreateOrderRequest;
import com.henrychencs.springbootmall.dto.OrderQueryParams;
import com.henrychencs.springbootmall.model.Order;
import com.henrychencs.springbootmall.model.OrderItem;
import com.henrychencs.springbootmall.model.Product;
import com.henrychencs.springbootmall.model.User;
import com.henrychencs.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList= orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsById(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);

        if (user == null){
            logger.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int total_amount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //  System.out.println(product.getStock());
            //  System.out.println(buyItem.getQuantity());

            //  檢查商品是否存在, 商品庫存
            if (product == null){
                logger.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()){
                logger.warn("商品 {} 庫存不足, 無法購買, 剩餘庫存 {}, 欲購買數量 {}", product.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //  更新商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());


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

    @Transactional
    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        //  System.out.println("success:" + order);

        List<OrderItem> orderItemList = orderDao.getOrderItemsById(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
}
