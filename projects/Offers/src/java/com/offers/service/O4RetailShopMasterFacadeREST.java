/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.service;

import com.offers.eb.RetailShopMaster;
import com.offers.dao.RetailShopMasterDAO;
import com.offers.eb.O4RetailShopMaster;
import com.offers.eb.O4RetailerMaster;
import com.offers.eb.O4ShopAccessor;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 *
 * @author chithambalamd
 */
@Stateless
@Path("retailshopmaster")
public class O4RetailShopMasterFacadeREST extends RetailShopMasterDAO<O4RetailShopMaster> {
    @PersistenceContext(unitName = "OffersPU")
    private EntityManager em;

    public O4RetailShopMasterFacadeREST() {
        super(O4RetailShopMaster.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(O4RetailShopMaster entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, O4RetailShopMaster entity) {
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
    public O4RetailShopMaster find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<O4RetailShopMaster> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<O4RetailShopMaster> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

     @POST
    @Path("createShop")
    @Consumes({"application/json"})
    public void createShop(RetailShopMaster entity, @Context HttpServletRequest req) {

        Date date = new Date();

        super.createShop(entity);
        O4RetailShopMaster retailShopMaster = super.getShopDetails(entity.getShopName());

        HttpSession session = req.getSession();
        O4RetailerMaster retailerMaster = (O4RetailerMaster) session.getAttribute("RetailerInfo");
        O4ShopAccessor shopAccessor = new O4ShopAccessor();
        shopAccessor.setShopId(retailShopMaster);
        shopAccessor.setRetailerId(retailerMaster);
        shopAccessor.setCreatedDate(date);
        shopAccessor.setUpdatedDate(date);
        shopAccessor.setCreatedUser(retailerMaster.getCreatedUser());
        shopAccessor.setUpdatedUser(retailerMaster.getUpdatedUser());
        super.createShopAccessor(shopAccessor);

    }


 
  
}
