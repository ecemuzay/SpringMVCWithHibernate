package com.spring.template.dao;


import com.spring.template.model.Order;

import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
public interface OrderDAO extends BaseDAO<Order> {

    List<Order> getAllOrder() throws Exception;


    void saveOrder(Order order) throws Exception;


}
