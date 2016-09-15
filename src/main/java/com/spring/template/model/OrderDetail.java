package com.spring.template.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
@Entity
@Table(name = "OrderDetail")
public class OrderDetail extends BaseEntity implements Serializable{

    private String name;

    private Order order;


    public OrderDetail(String name, Order order) {
        this.name = name;
        this.order = order;
    }

    public OrderDetail() {
    }

    @Transient
    public boolean isNew() {
        return (this.getId() == null);
    }

    @NotEmpty
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "id", updatable = true, insertable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
