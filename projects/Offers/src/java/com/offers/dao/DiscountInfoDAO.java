/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offers.dao;

import com.offers.eb.DiscountInfo;
import com.offers.eb.O4SearchTable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author chithambalamd
 */
public abstract class DiscountInfoDAO<T> {

    private Class<T> entityClass;

    public DiscountInfoDAO(Class<T> entityClass) {
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

    public List<T> getCityDiscount(Long cityId, int pageNo) {
        int start = (pageNo - 1) * 3;
        Query query = getEntityManager().createQuery("SELECT o FROM O4DiscountInfo o "
                + " WHERE o.shopId.city.cityId =:cityId order by o.createdDate desc");
        query.setParameter("cityId", cityId);
        query.setMaxResults(3);
        query.setFirstResult(start);

        return query.getResultList();
    }

    public List<O4SearchTable> getSearchTbleLst(String searchStr, Long cityId) {
        Query q = getEntityManager().createQuery("SELECT o FROM O4SearchTable o "
                + "WHERE o.searchName= :searchStr and o.cityId=:cityId");
        q.setParameter("searchStr", searchStr);
        q.setParameter("cityId", cityId);
        return q.getResultList();
    }

    public List<T> getDiscountLst(String searchName, Long cityId, int pageNo) {
        int start = (pageNo - 1) * 3;

        String searchQuery = "";
        String searchId = searchName.substring(1);

        if (searchName.startsWith("C")) {
            searchQuery = "SELECT o FROM O4DiscountInfo o WHERE (o.categoryId.categoryId='" 
                    + searchId + "') and (o.shopId.city.cityId='" + cityId + "') "
                    + "order by o.createdDate desc";

        } else if (searchName.startsWith("P")) {
            searchQuery = "SELECT o FROM O4DiscountInfo o WHERE (o.productId.productId='" 
                    + searchId + "') and (o.shopId.city.cityId='" + cityId + "') "
                    + "order by o.createdDate desc";

        } else if (searchName.startsWith("S")) {
            searchQuery = "SELECT o FROM O4DiscountInfo o WHERE (o.shopId.shopId='" 
                    + searchId + "') and (o.shopId.city.cityId='" + cityId + "') "
                    + "order by o.createdDate desc";

        }

        System.out.println("Dharma -------->" + searchQuery);
        Query query = getEntityManager().createQuery(searchQuery);
        //    query.setParameter("searchId", searchId);
        //    query.setParameter("cityId", cityId);

        query.setMaxResults(3);
        query.setFirstResult(start);
        return query.getResultList();

    }

    public int countTotal(Long cityId) {
        Query cq = getEntityManager().createQuery("select count(o.discountId)  FROM O4DiscountInfo o"
                + " WHERE o.shopId.city.cityId =:cityId");
        cq.setParameter("cityId", cityId);
        return ((Long) cq.getSingleResult()).intValue();
    }

    public int getDiscountLstCount(String searchName, Long cityId) {
        String searchQuery = "";
        String searchId = searchName.substring(1);
        //  Long searchId = Long.getLong(searchIdStr);

        if (searchName.startsWith("C")) {
            searchQuery = "SELECT count(o.discountId) FROM O4DiscountInfo o WHERE (o.categoryId.categoryId='" + searchId + "') and (o.shopId.city.cityId='" + cityId + "')";

        } else if (searchName.startsWith("P")) {
            searchQuery = "SELECT count(o.discountId) FROM O4DiscountInfo o WHERE (o.productId.productId='" + searchId + "') and (o.shopId.city.cityId='" + cityId + "')";

        } else if (searchName.startsWith("S")) {
            searchQuery = "SELECT count(o.discountId) FROM O4DiscountInfo o WHERE (o.shopId.shopId='" + searchId + "') and (o.shopId.city.cityId='" + cityId + "')";

        }

        /*if (searchName.startsWith("C")) {
         searchQuery = "SELECT o FROM O4DiscountInfo o WHERE (o.categoryId.categoryId=:searchId)  and o.shopId.city.cityId=:cityId)";

         } else if (searchName.startsWith("P")) {
         searchQuery = "SELECT o FROM O4DiscountInfo o WHERE (o.productId.productId=:searchId) and (o.shopId.city.cityId=:cityId)";

         } else if (searchName.startsWith("S")) {
         searchQuery = "SELECT o FROM O4DiscountInfo o WHERE (o.shopId.shopId=:searchId) and (o.shopId.city.cityId=:cityId)";

         } */
        System.out.println("Dharma -------->" + searchQuery);
        Query query = getEntityManager().createQuery(searchQuery);
        //    query.setParameter("searchId", searchId);
        //    query.setParameter("cityId", cityId);
        //   return query;
        //  Query cq = EntityQuery.getInstance().getDiscountLstCountQuery(getEntityManager(), searchName, cityId);
        //  cq.setMaxResults(pageNo + 2);
        // cq.setFirstResult((pageNo - 1) * 2);
        return ((Long) query.getSingleResult()).intValue();

    }

    public List<T> getShopDiscountLst(String shopId) {

        return getEntityManager().createQuery("SELECT o FROM O4DiscountInfo o WHERE o.shopId.shopId" + "=" + shopId + "").getResultList();

    }

    public void createOffer(DiscountInfo entity) {
       // BigDecimal difference;
      int  difference = entity.getPrice().intValue()- entity.getDiscPrice().intValue();
      int discount = entity.getPrice().intValue()/difference;
      String discVal = discount+"";
      if(discVal.length()==1)
      {
      String discPer = discount+"0";
      entity.setDiscountPercentage(Integer.parseInt(discPer));
      }
      else
      {
           entity.setDiscountPercentage(discount);
      }
      
    
     
        Query cq = getEntityManager().createNativeQuery("insert into O4_Discount_info"
                + "(product_id , shop_id ,category_id, city_id,price, disc_price,"
                + " discount_percentage, discount_description , valid_start_date , valid_end_date ,"
                + " created_date , updated_date  , created_user, updated_user)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        cq.setParameter(1, entity.getProductId());
        cq.setParameter(2, entity.getShopId());
        cq.setParameter(3, entity.getCategoryId());
        cq.setParameter(4, entity.getCityId());
        cq.setParameter(5, entity.getPrice());
        cq.setParameter(6, entity.getDiscPrice());
        cq.setParameter(7, entity.getDiscountPercentage());
        cq.setParameter(8, entity.getDiscountDescription());
        cq.setParameter(9, entity.getValidStartDate());
        cq.setParameter(10, entity.getValidEndDate());
        cq.setParameter(11, entity.getCreatedDate());
        cq.setParameter(12, entity.getUpdatedDate());
        cq.setParameter(13, entity.getCreatedUser());
        cq.setParameter(14, entity.getUpdatedUser());
        int i = cq.executeUpdate();
    }
}
