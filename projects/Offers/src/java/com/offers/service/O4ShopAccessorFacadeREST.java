/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.service;

import com.offers.dao.ShopAccessorDAO;
import com.offers.eb.O4ShopAccessor;
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
@Path("shopaccessor")
public class O4ShopAccessorFacadeREST extends ShopAccessorDAO<O4ShopAccessor> {
    @PersistenceContext(unitName = "OffersPU")
    private EntityManager em;

    public O4ShopAccessorFacadeREST() {
        super(O4ShopAccessor.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(O4ShopAccessor entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, O4ShopAccessor entity) {
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
    public O4ShopAccessor find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<O4ShopAccessor> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<O4ShopAccessor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("getShops/{retailerId}")
    @Produces({"application/json"})
    public List<O4ShopAccessor> getShops(@PathParam("retailerId") Long retailerId) {
        
        return super.getShops(retailerId);
    }
    
}
