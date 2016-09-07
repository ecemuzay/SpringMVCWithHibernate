package com.spring.template.dao.impl;

import com.spring.template.dao.OrderDAO;
import com.spring.template.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
@Repository("orderDAO")
public class OrderDAOImpl extends BaseHibernateDAO<Order> implements OrderDAO {

    @Override
    public List<Order> getAllOrder() throws Exception{
        return getAll();
    }

    @Override
    public void saveOrder(Order order) throws Exception{
        save(order);
    }
}
