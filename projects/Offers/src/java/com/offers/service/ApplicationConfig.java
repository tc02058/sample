/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author chithambalamd
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.offers.service.O4CityMasterFacadeREST.class);
        resources.add(com.offers.service.O4CountryMasterFacadeREST.class);
        resources.add(com.offers.service.O4DiscountInfoFacadeREST.class);
        resources.add(com.offers.service.O4ProductCategoryMasterFacadeREST.class);
        resources.add(com.offers.service.O4ProductMasterFacadeREST.class);
        resources.add(com.offers.service.O4RetailShopMasterFacadeREST.class);
        resources.add(com.offers.service.O4RetailerMasterFacadeREST.class);
        resources.add(com.offers.service.O4SearchTableFacadeREST.class);
        resources.add(com.offers.service.O4ShopAccessorFacadeREST.class);
        resources.add(com.offers.service.O4StateMasterFacadeREST.class);
    }
    
}
