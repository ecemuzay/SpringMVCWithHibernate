package com.spring.template.dao.impl;


import com.spring.template.dao.BaseDAO;
import com.spring.template.model.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
public abstract class BaseHibernateDAO<T extends BaseEntity> implements
        BaseDAO<T> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory
            .getLogger(BaseHibernateDAO.class);

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    public BaseHibernateDAO() {
        super();
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public Serializable save(T entity) throws HibernateException{
        Serializable serializable = null;

        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }

        try {
            serializable = getCurrentSession().save(entity);

        } catch (HibernateException e) {
            LOG.error("Error while saving Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
            throw  e;
        }
        return serializable;
    }

    public void saveOrUpdate(T entity) throws HibernateException{
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }

        try {
            getCurrentSession().saveOrUpdate(entity);

        } catch (HibernateException e) {
            LOG.error("Error while saveOrUpdate Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
    }

    public void update(T entity) throws HibernateException{
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }

        try {
            getCurrentSession().update(entity);

        } catch (HibernateException e) {
            LOG.error("Error while update Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
    }

    public void delete(T entity) throws HibernateException{
        if (entity == null) {
            throw new IllegalArgumentException("Entity Must not be Null");
        }

        try {
            getCurrentSession().delete(entity);
        } catch (HibernateException e) {
            LOG.error("Error while delete Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
    }

    public void detach(T entity) throws HibernateException{
        if (entity == null) {
            throw new IllegalArgumentException("Entity Must not be null");
        }

        try {
            getCurrentSession().evict(entity);

        } catch (Exception e) {
            LOG.error("Error while detach Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
    }

    public void refresh(T entity) throws HibernateException{
        if (entity == null) {
            throw new IllegalArgumentException("Entity Must not be null");
        }

        try {
            getCurrentSession().refresh(entity);

        } catch (Exception e) {
            LOG.error("Error while refresh Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
    }

    public T getById(Integer id) throws HibernateException{
        T entity = null;
        try {
             entity = (T) getCurrentSession().get(getPersistentClass(), id);

        } catch (Exception e) {
            LOG.error("Error while getById Entity. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
        return entity;
    }

    public List<T> getAll() throws HibernateException{
        try {
            List<T> list = getCurrentSession().createCriteria(getPersistentClass()).list();
            return list;
        } catch (Exception e) {
            LOG.error("Error while getAll Entities. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
        return null;
    }

    public T getBySql(String query) throws HibernateException{
        T entity = null;
        try {
            entity = (T) getCurrentSession().createSQLQuery(query)
                    .addEntity(getPersistentClass()).uniqueResult();
        } catch (Exception e) {
            LOG.error("Error while getWithSql Entity. M: " + e.getMessage()
                    + " C: " + e.getCause() + " SQL: " + query, e);
        }
        return entity;
    }

    public List<T> getAllBySql(String query) throws HibernateException{
        List<T> ts = null;
        try {
            ts = getCurrentSession().createSQLQuery(query).addEntity(getPersistentClass())
                    .list();
        } catch (Exception e) {
            LOG.error(
                    "Error while getAllWithSql Entities. M: " + e.getMessage()
                            + " C: " + e.getCause() + " SQL: " + query, e);
        }
        return ts;
    }

    public void executeSQLQuery(String query) throws HibernateException {
        try {
            getCurrentSession().createSQLQuery(query).addEntity(getPersistentClass())
                    .executeUpdate();
        } catch (Exception e) {
            LOG.error(
                    "Error while executeSQLQuery Entities. M: "
                            + e.getMessage() + " C: " + e.getCause() + " SQL: "
                            + query, e);
        }
    }

    protected List<T> findAllByCriteria(Criterion... criterions) throws HibernateException{
        return findAllByCriteriaAndOrder(null, criterions);
    }

    protected List<T> findAllByCriteriaAndOrder(Order order,
                                                Criterion... criterions) throws HibernateException{
        List<T> ts = null;
        try {
            Criteria criteria = createCriteria(getCurrentSession(), criterions);
            if (order != null) {
                criteria.addOrder(order);
            }
            ts = criteria.list();
        } catch (Exception e) {
            LOG.error(
                    "Error while findAllByCriteria Entities and Order them. M: "
                            + e.getMessage() + " C: " + e.getCause(), e);
        }
        return ts;
    }

    protected T findByCriteria(Criterion... criterions) throws HibernateException{
        T t = null;
        try {
            Criteria criteria = createCriteria(getCurrentSession(), criterions);
            t = (T) criteria.uniqueResult();
        } catch (Exception e) {
            LOG.error("Error while findByCriteria Entiry. M: " + e.getMessage()
                    + " C: " + e.getCause(), e);
        }
        return t;
    }

    protected Object findByProjection(Projection projection,
                                      Criterion... criterions) throws HibernateException{
        Object result = null;
        try {
            Criteria criteria = createCriteria(getCurrentSession(), criterions);
            criteria.setProjection(projection);
            result = criteria.uniqueResult();
        } catch (Exception e) {
            LOG.error(
                    "Error while findByCriteria Entities. M: " + e.getMessage()
                            + " C: " + e.getCause(), e);
        }
        return result;
    }

    protected Criteria createCriteria(Session session, Criterion... criterions) throws HibernateException{
        Criteria criteria = session.createCriteria(getPersistentClass());
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return criteria;
    }
}
