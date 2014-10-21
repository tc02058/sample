/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.eb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chithambalamd
 */
@Entity
@Table(name = "o4_city_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4CityMaster.findAll", query = "SELECT o FROM O4CityMaster o"),
    @NamedQuery(name = "O4CityMaster.findByCityId", query = "SELECT o FROM O4CityMaster o WHERE o.cityId = :cityId"),
    @NamedQuery(name = "O4CityMaster.findByCityName", query = "SELECT o FROM O4CityMaster o WHERE o.cityName = :cityName"),
    @NamedQuery(name = "O4CityMaster.findByCreatedDate", query = "SELECT o FROM O4CityMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4CityMaster.findByUpdatedDate", query = "SELECT o FROM O4CityMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4CityMaster.findByCreatedUser", query = "SELECT o FROM O4CityMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4CityMaster.findByUpdatedUser", query = "SELECT o FROM O4CityMaster o WHERE o.updatedUser = :updatedUser"),
    @NamedQuery(name = "O4CityMaster.findByAltCityName", query = "SELECT o FROM O4CityMaster o WHERE o.altCityName = :altCityName")})
public class O4CityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "city_id")
    private Integer cityId;
    @Size(max = 20)
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Size(max = 50)
    @Column(name = "created_user")
    private String createdUser;
    @Size(max = 50)
    @Column(name = "updated_user")
    private String updatedUser;
    @Size(max = 20)
    @Column(name = "alt_city_name")
    private String altCityName;
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @ManyToOne
    private O4StateMaster stateId;

    public O4CityMaster() {
    }

    public O4CityMaster(Integer cityId) {
        this.cityId = cityId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getAltCityName() {
        return altCityName;
    }

    public void setAltCityName(String altCityName) {
        this.altCityName = altCityName;
    }

    public O4StateMaster getStateId() {
        return stateId;
    }

    public void setStateId(O4StateMaster stateId) {
        this.stateId = stateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4CityMaster)) {
            return false;
        }
        O4CityMaster other = (O4CityMaster) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4CityMaster[ cityId=" + cityId + " ]";
    }
    
}
