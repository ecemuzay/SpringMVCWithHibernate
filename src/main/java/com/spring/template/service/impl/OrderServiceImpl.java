package com.spring.template.service.impl;


import com.spring.template.dao.OrderDAO;
import com.spring.template.dao.OrderDetailDAO;
import com.spring.template.exception.CustomException;
import com.spring.template.model.Order;
import com.spring.template.model.OrderDetail;
import com.spring.template.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
@Service("orderService")
@Transactional(rollbackFor = {Exception.class, CustomException.class})
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    public List<Order> getAllOrder() throws Exception {
        return orderDAO.getAllOrder();
    }

    @Override
    public void deleteOrder(int id) throws Exception {

        Order order = orderDAO.getById(id);

        List<OrderDetail> orderDetailList=orderDetailDAO.getAllByOrder(order);
        if(orderDetailList!=null)
            for (OrderDetail orderDetail:orderDetailList) {
                orderDetailDAO.delete(orderDetail);
            }

        orderDAO.delete(order);
    }

    @Override
    public void deleteOrderDetail(int id) throws Exception {

        OrderDetail orderDetail= orderDetailDAO.getById(id);

        orderDetailDAO.delete(orderDetail);

    }

    @Override
    public Order findOrderById(int id) throws Exception {
        return orderDAO.getById(id);
    }

    @Override
    public List<OrderDetail> getAllOrderDetailByOrder(Order order) throws Exception {
       return orderDetailDAO.getAllByOrder(order);
    }

    @Override
    public void saveOrUpdateOrder(Order order) throws Exception {
        if(order.getId()==null)
            orderDAO.save(order);
        else
            orderDAO.update(order);

    }

    @Override
    public void saveOrUpdateOrderDetail(OrderDetail orderDetail) throws Exception {
        if(orderDetail.getId()==null)
            orderDetailDAO.save(orderDetail);
        else
            orderDetailDAO.update(orderDetail);

    }

    @Override
    public OrderDetail findOrderDetailById(int id) throws Exception {
        return orderDetailDAO.getById(id);
    }

}
