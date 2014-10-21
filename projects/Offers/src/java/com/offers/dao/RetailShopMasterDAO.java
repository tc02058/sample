/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offers.dao;

import com.offers.eb.O4RetailShopMaster;
import com.offers.eb.RetailShopMaster;
import com.offers.eb.O4ShopAccessor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author chithambalamd
 */
public abstract class RetailShopMasterDAO<T> {

    private Class<T> entityClass;

    public RetailShopMasterDAO(Class<T> entityClass) {
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

    public void createShop(RetailShopMaster entity) {
        Query cq = getEntityManager().createNativeQuery("insert into O4_retail_shop_master"
                + "(shop_Name,address, locality, city, state, zip_code, phone1,phone2,"
                + "website,parent_shop_id,google_address,created_date,updated_date,created_user,updated_user) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        cq.setParameter(1, entity.getShopName());
        cq.setParameter(2, entity.getAddress());
        cq.setParameter(3, entity.getLocality());
        cq.setParameter(4, entity.getCity());
        cq.setParameter(5, entity.getState());
        cq.setParameter(6, entity.getZipCode());
        cq.setParameter(7, entity.getPhone1());
        cq.setParameter(8, entity.getPhone2());
        cq.setParameter(9, entity.getWebsite());
        cq.setParameter(10, entity.getParentShopId());
        cq.setParameter(11, entity.getGoogleAddress());
        cq.setParameter(12, entity.getCreatedDate());
        cq.setParameter(13, entity.getUpdatedDate());
        cq.setParameter(14, entity.getCreatedUser());
        cq.setParameter(15, entity.getUpdatedUser());
        int i = cq.executeUpdate();
    }

    public void createShopAccessor(O4ShopAccessor shopAccessor) {
        getEntityManager().persist(shopAccessor);
    }

    public O4RetailShopMaster getShopDetails(String shopName) {

        Query cq = getEntityManager().createQuery("SELECT o FROM O4RetailShopMaster o WHERE o.shopName = :shopName");
        cq.setParameter("shopName", shopName);
        return (O4RetailShopMaster) cq.getResultList().get(0);
    }
}
