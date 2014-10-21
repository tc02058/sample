/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.eb;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chithambalamd
 */
@Entity
@Table(name = "o4_state_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4StateMaster.findAll", query = "SELECT o FROM O4StateMaster o"),
    @NamedQuery(name = "O4StateMaster.findByStateId", query = "SELECT o FROM O4StateMaster o WHERE o.stateId = :stateId"),
    @NamedQuery(name = "O4StateMaster.findByStateName", query = "SELECT o FROM O4StateMaster o WHERE o.stateName = :stateName"),
    @NamedQuery(name = "O4StateMaster.findByCreatedDate", query = "SELECT o FROM O4StateMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4StateMaster.findByUpdatedDate", query = "SELECT o FROM O4StateMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4StateMaster.findByCreatedUser", query = "SELECT o FROM O4StateMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4StateMaster.findByUpdatedUser", query = "SELECT o FROM O4StateMaster o WHERE o.updatedUser = :updatedUser")})
public class O4StateMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "state_id")
    private Integer stateId;
    @Size(max = 20)
    @Column(name = "state_name")
    private String stateName;
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
    @OneToMany(mappedBy = "stateId")
    private Collection<O4CityMaster> o4CityMasterCollection;
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    @ManyToOne
    private O4CountryMaster countryId;
    @OneToMany(mappedBy = "state")
    private Collection<O4RetailShopMaster> o4RetailShopMasterCollection;

    public O4StateMaster() {
    }

    public O4StateMaster(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    @XmlTransient
    public Collection<O4CityMaster> getO4CityMasterCollection() {
        return o4CityMasterCollection;
    }

    public void setO4CityMasterCollection(Collection<O4CityMaster> o4CityMasterCollection) {
        this.o4CityMasterCollection = o4CityMasterCollection;
    }

    public O4CountryMaster getCountryId() {
        return countryId;
    }

    public void setCountryId(O4CountryMaster countryId) {
        this.countryId = countryId;
    }

    @XmlTransient
    public Collection<O4RetailShopMaster> getO4RetailShopMasterCollection() {
        return o4RetailShopMasterCollection;
    }

    public void setO4RetailShopMasterCollection(Collection<O4RetailShopMaster> o4RetailShopMasterCollection) {
        this.o4RetailShopMasterCollection = o4RetailShopMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4StateMaster)) {
            return false;
        }
        O4StateMaster other = (O4StateMaster) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4StateMaster[ stateId=" + stateId + " ]";
    }
    
}
