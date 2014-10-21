/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offers.service;

import com.offers.eb.DiscountInfo;
import com.offers.dao.DiscountInfoDAO;
import com.offers.eb.O4DiscountInfo;
import com.offers.eb.O4SearchTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author chithambalamd
 */
@Stateless
@Path("discountinfo")
public class O4DiscountInfoFacadeREST extends DiscountInfoDAO<O4DiscountInfo> {

    @PersistenceContext(unitName = "OffersPU")
    private EntityManager em;

    public O4DiscountInfoFacadeREST() {
        super(O4DiscountInfo.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(O4DiscountInfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, O4DiscountInfo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public O4DiscountInfo find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<O4DiscountInfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<O4DiscountInfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("getCityDiscount/{cityId}/{pageNo}")
    @Produces({"application/json"})
    public List<O4DiscountInfo> getCityDiscount(@PathParam("cityId") Long cityId,
            @PathParam("pageNo") int pageNo) {
        return super.getCityDiscount(cityId, pageNo);
    }

    @GET
    @Path("searchDiscount/{discountName}/{cityId}/{pageNo}")
    @Produces({"application/json"})
    public List<O4DiscountInfo> searchDiscount(@PathParam("discountName") String discountName,
            @PathParam("cityId") Long cityId, @PathParam("pageNo") int pageNo) {
        List searchLst = super.getSearchTbleLst(discountName, cityId);
        if (searchLst.isEmpty()) {
            return super.getCityDiscount(cityId, pageNo);
        } else {
            O4SearchTable searchTable = (O4SearchTable) searchLst.get(0);
            return super.getDiscountLst(searchTable.getSearchId(), cityId, pageNo);
        }

    }

    @GET
    @Path("getCityCount/{cityId}")
    @Produces("text/plain")
    public String getCityCount(@PathParam("cityId") Long cityId) {
        return String.valueOf(super.countTotal(cityId));
    }

    @GET
    @Path("getSearchDiscountCount/{discountName}/{cityId}")
    @Produces("text/plain")
    public String getSearchDiscountCount(@PathParam("discountName") String discountName,
            @PathParam("cityId") Long cityId) {
        List searchLst = super.getSearchTbleLst(discountName, cityId);
        if (searchLst.isEmpty()) {
            return String.valueOf(super.countTotal(cityId));
        } else {
            O4SearchTable searchTable = (O4SearchTable) searchLst.get(0);
            return String.valueOf(super.getDiscountLstCount(searchTable.getSearchId(), cityId));

        }
    }

    @GET
    @Path("getShopDiscounts/{shopId}")
    @Produces({"application/json"})
    public List<O4DiscountInfo> getShopDiscounts(@PathParam("shopId") String shopId) {
        return super.getShopDiscountLst(shopId);
    }

    @POST
    @Path("createOffer")
    @Consumes({"application/json"})
    public void createOffer(DiscountInfo entity) {
        super.createOffer(entity);
    }
}
