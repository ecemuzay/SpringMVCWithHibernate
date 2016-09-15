package com.spring.template.model;



import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */

@Entity
@Table(name = "Orders")
public class Order  extends BaseEntity implements Serializable{

    private String name;

    private Integer orderNumber;

    public Order() {
    }

    public Order(String name, Integer orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
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

    @NotNull
    @Column(name = "orderNumber")
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
