/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.service;

import com.offers.dao.ProductMasterDAO;
import com.offers.eb.O4ProductMaster;
import com.offers.eb.ProductMaster;
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
@Path("productmaster")
public class O4ProductMasterFacadeREST extends ProductMasterDAO<O4ProductMaster> {
    @PersistenceContext(unitName = "OffersPU")
    private EntityManager em;

    public O4ProductMasterFacadeREST() {
        super(O4ProductMaster.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(O4ProductMaster entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, O4ProductMaster entity) {
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
    public O4ProductMaster find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<O4ProductMaster> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<O4ProductMaster> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("getProductLst/{searchStr}")
    @Produces({"application/json"})
    public List<O4ProductMaster> getProductLst(@PathParam("searchStr") String searchStr) {
        return super.getSearchLstFromProductTble(searchStr);
    }

     @POST
     @Path("createProduct")
    @Consumes({"application/json"})
    public void createProduct(ProductMaster entity) {
       
        super.createProduct(entity);
    }
    
}
