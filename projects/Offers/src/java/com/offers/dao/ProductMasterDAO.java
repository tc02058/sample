/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.dao;

import com.offers.eb.ProductMaster;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author chithambalamd
 */
public abstract class ProductMasterDAO<T> {
    private Class<T> entityClass;

    public ProductMasterDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
     public List<T> getSearchLstFromProductTble(String searchStr) {
            Query query = getEntityManager().createQuery("SELECT o FROM O4ProductMaster o "
                + " WHERE o.productName like '%" + searchStr + "%'");
       return query.getResultList();
    }
     
    public void createProduct(ProductMaster entity) {
       
        Query cq = getEntityManager().createNativeQuery("insert into O4_Product_Master"
                + "(product_name, category_id,"
                + " created_date , updated_date  , created_user, updated_user)"
                + "values(?,?,?,?,?,?)");

        cq.setParameter(1, entity.getProductName());
        cq.setParameter(2, entity.getCategoryId());
        cq.setParameter(3, entity.getCreatedDate());
        cq.setParameter(4, entity.getUpdatedDate());
        cq.setParameter(5, entity.getCreatedUser());
        cq.setParameter(6, entity.getUpdatedUser());
        int i = cq.executeUpdate();
    }
    
}
