/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.service;

import com.offers.dao.RetailerMasterDAO;
import com.offers.eb.O4RetailerMaster;
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
@Path("retailermaster")
public class O4RetailerMasterFacadeREST extends RetailerMasterDAO<O4RetailerMaster> {
    @PersistenceContext(unitName = "OffersPU")
    private EntityManager em;

    public O4RetailerMasterFacadeREST() {
        super(O4RetailerMaster.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(O4RetailerMaster entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, O4RetailerMaster entity) {
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
    public O4RetailerMaster find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<O4RetailerMaster> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<O4RetailerMaster> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("validateUser")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public O4RetailerMaster validateUser(O4RetailerMaster entity, @Context HttpServletRequest req) {

        O4RetailerMaster retailerMaster = super.validateUser(entity.getEmail(), entity.getPassword()).get(0);
        HttpSession session = req.getSession();
        session.setAttribute("RetailerInfo", retailerMaster);
        return retailerMaster;
    }
    
}
