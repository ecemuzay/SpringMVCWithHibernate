package com.spring.template.dao;



import com.spring.template.model.BaseEntity;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
public interface BaseDAO<T extends BaseEntity> extends Serializable {

    public Serializable save(T entity) throws HibernateException;

    public void saveOrUpdate(T entity) throws HibernateException;

    public void update(T entity) throws HibernateException;

    public void delete(T entity) throws HibernateException;

    public void detach(T entity) throws HibernateException;

    public void refresh(T entity) throws HibernateException;

    T getById(Integer id) throws HibernateException;

    List<T> getAll() throws HibernateException;

    T getBySql(String query) throws HibernateException;

    List<T> getAllBySql(String query) throws HibernateException;

    void executeSQLQuery(String query) throws HibernateException;
}
