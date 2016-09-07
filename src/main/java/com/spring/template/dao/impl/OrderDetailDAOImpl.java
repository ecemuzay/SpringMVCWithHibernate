package com.spring.template.dao.impl;


import com.spring.template.dao.OrderDetailDAO;
import com.spring.template.model.Order;
import com.spring.template.model.OrderDetail;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
@Repository("orderDetailDAO")
public class OrderDetailDAOImpl extends BaseHibernateDAO<OrderDetail> implements OrderDetailDAO {

    @Override
    public List<OrderDetail> getAllByOrder(Order order)  throws Exception {
        return findAllByCriteria(Restrictions.eq("order", order));
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) throws Exception {
        save(orderDetail);
    }
}
