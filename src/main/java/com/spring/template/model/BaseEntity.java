package com.spring.template.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */

@MappedSuperclass
public class BaseEntity implements Serializable{


    private Integer id;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
