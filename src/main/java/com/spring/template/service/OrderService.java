package com.spring.template.service;



import com.spring.template.model.Order;
import com.spring.template.model.OrderDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
@Transactional
public interface OrderService {

    List<Order> getAllOrder() throws Exception;

    void deleteOrder(int id) throws Exception;

    void deleteOrderDetail(int id) throws Exception;

    Order findOrderById(int id) throws Exception;

    OrderDetail findOrderDetailById(int id) throws Exception;

    List<OrderDetail> getAllOrderDetailByOrder(Order order) throws Exception;

    void saveOrUpdateOrder(Order order) throws Exception;

    void saveOrUpdateOrderDetail(OrderDetail orderDetail) throws Exception;
}

