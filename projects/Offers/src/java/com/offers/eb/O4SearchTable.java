/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.eb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chithambalamd
 */
@Entity
@Table(name = "o4_search_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4SearchTable.findAll", query = "SELECT o FROM O4SearchTable o"),
    @NamedQuery(name = "O4SearchTable.findBySearchId", query = "SELECT o FROM O4SearchTable o WHERE o.searchId = :searchId"),
    @NamedQuery(name = "O4SearchTable.findBySearchName", query = "SELECT o FROM O4SearchTable o WHERE o.searchName = :searchName"),
    @NamedQuery(name = "O4SearchTable.findByCityId", query = "SELECT o FROM O4SearchTable o WHERE o.cityId = :cityId"),
    @NamedQuery(name = "O4SearchTable.findByCityName", query = "SELECT o FROM O4SearchTable o WHERE o.cityName = :cityName")})
public class O4SearchTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 21)
    @Column(name = "searchId")
    @Id
    private String searchId;
    @Size(max = 50)
    @Column(name = "search_name")
    private String searchName;
    @Column(name = "city_id")
    private Integer cityId;
    @Size(max = 20)
    @Column(name = "city_name")
    private String cityName;

    public O4SearchTable() {
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
}
