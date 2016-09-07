package com.spring.template.dao;

import com.spring.template.model.Order;
import com.spring.template.model.OrderDetail;

import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
public interface OrderDetailDAO extends BaseDAO<OrderDetail>{

    List<OrderDetail> getAllByOrder(Order order) throws Exception;

    void saveOrderDetail(OrderDetail orderDetail) throws Exception;
}
