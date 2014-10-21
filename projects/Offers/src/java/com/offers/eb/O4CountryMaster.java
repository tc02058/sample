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
@Table(name = "o4_country_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4CountryMaster.findAll", query = "SELECT o FROM O4CountryMaster o"),
    @NamedQuery(name = "O4CountryMaster.findByCountryId", query = "SELECT o FROM O4CountryMaster o WHERE o.countryId = :countryId"),
    @NamedQuery(name = "O4CountryMaster.findByCountryName", query = "SELECT o FROM O4CountryMaster o WHERE o.countryName = :countryName"),
    @NamedQuery(name = "O4CountryMaster.findByCreatedDate", query = "SELECT o FROM O4CountryMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4CountryMaster.findByUpdatedDate", query = "SELECT o FROM O4CountryMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4CountryMaster.findByCreatedUser", query = "SELECT o FROM O4CountryMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4CountryMaster.findByUpdatedUser", query = "SELECT o FROM O4CountryMaster o WHERE o.updatedUser = :updatedUser")})
public class O4CountryMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "country_id")
    private Integer countryId;
    @Size(max = 20)
    @Column(name = "country_name")
    private String countryName;
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
    @OneToMany(mappedBy = "countryId")
    private Collection<O4StateMaster> o4StateMasterCollection;

    public O4CountryMaster() {
    }

    public O4CountryMaster(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
    public Collection<O4StateMaster> getO4StateMasterCollection() {
        return o4StateMasterCollection;
    }

    public void setO4StateMasterCollection(Collection<O4StateMaster> o4StateMasterCollection) {
        this.o4StateMasterCollection = o4StateMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryId != null ? countryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4CountryMaster)) {
            return false;
        }
        O4CountryMaster other = (O4CountryMaster) object;
        if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4CountryMaster[ countryId=" + countryId + " ]";
    }
    
}
