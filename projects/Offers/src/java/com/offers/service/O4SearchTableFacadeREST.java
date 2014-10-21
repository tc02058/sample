/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.service;

import com.offers.dao.SearchTableDAO;
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
@Path("searchtable")
public class O4SearchTableFacadeREST extends SearchTableDAO<O4SearchTable> {
    @PersistenceContext(unitName = "OffersPU")
    private EntityManager em;

    public O4SearchTableFacadeREST() {
        super(O4SearchTable.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(O4SearchTable entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, O4SearchTable entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public O4SearchTable find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<O4SearchTable> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<O4SearchTable> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("getSearchList/{searchStr}/{cityId}")
    @Produces({"application/json"})
    public List<O4SearchTable> getSearchList(@PathParam("searchStr") String searchStr, @PathParam("cityId") Long cityId) {
        return super.getSearchList(searchStr, cityId);
    }

    @GET
    @Path("getCitywiseSearchList/{cityId}")
    @Produces({"application/json"})
    public  List<O4SearchTable> getCitywiseSearchList(@PathParam("cityId") Long cityId) {
        return super.getCitySearchList(cityId); 
    }
    
    
    
}
