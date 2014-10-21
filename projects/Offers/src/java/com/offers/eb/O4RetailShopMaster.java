/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.eb;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chithambalamd
 */
@Entity
@Table(name = "o4_retail_shop_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4RetailShopMaster.findAll", query = "SELECT o FROM O4RetailShopMaster o"),
    @NamedQuery(name = "O4RetailShopMaster.findByShopId", query = "SELECT o FROM O4RetailShopMaster o WHERE o.shopId = :shopId"),
    @NamedQuery(name = "O4RetailShopMaster.findByShopName", query = "SELECT o FROM O4RetailShopMaster o WHERE o.shopName = :shopName"),
    @NamedQuery(name = "O4RetailShopMaster.findByAddress", query = "SELECT o FROM O4RetailShopMaster o WHERE o.address = :address"),
    @NamedQuery(name = "O4RetailShopMaster.findByLocality", query = "SELECT o FROM O4RetailShopMaster o WHERE o.locality = :locality"),
    @NamedQuery(name = "O4RetailShopMaster.findByZipCode", query = "SELECT o FROM O4RetailShopMaster o WHERE o.zipCode = :zipCode"),
    @NamedQuery(name = "O4RetailShopMaster.findByGoogleAddress", query = "SELECT o FROM O4RetailShopMaster o WHERE o.googleAddress = :googleAddress"),
    @NamedQuery(name = "O4RetailShopMaster.findByPhone1", query = "SELECT o FROM O4RetailShopMaster o WHERE o.phone1 = :phone1"),
    @NamedQuery(name = "O4RetailShopMaster.findByPhone2", query = "SELECT o FROM O4RetailShopMaster o WHERE o.phone2 = :phone2"),
    @NamedQuery(name = "O4RetailShopMaster.findByWebsite", query = "SELECT o FROM O4RetailShopMaster o WHERE o.website = :website"),
    @NamedQuery(name = "O4RetailShopMaster.findByParentShopId", query = "SELECT o FROM O4RetailShopMaster o WHERE o.parentShopId = :parentShopId"),
    @NamedQuery(name = "O4RetailShopMaster.findByPicture", query = "SELECT o FROM O4RetailShopMaster o WHERE o.picture = :picture"),
    @NamedQuery(name = "O4RetailShopMaster.findByDelFlag", query = "SELECT o FROM O4RetailShopMaster o WHERE o.delFlag = :delFlag"),
    @NamedQuery(name = "O4RetailShopMaster.findByCreatedDate", query = "SELECT o FROM O4RetailShopMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4RetailShopMaster.findByUpdatedDate", query = "SELECT o FROM O4RetailShopMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4RetailShopMaster.findByCreatedUser", query = "SELECT o FROM O4RetailShopMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4RetailShopMaster.findByUpdatedUser", query = "SELECT o FROM O4RetailShopMaster o WHERE o.updatedUser = :updatedUser")})
public class O4RetailShopMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shop_id")
    private Long shopId;
    @Size(max = 50)
    @Column(name = "shop_name")
    private String shopName;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Size(max = 100)
    @Column(name = "Locality")
    private String locality;
    @Size(max = 15)
    @Column(name = "zip_code")
    private String zipCode;
    @Size(max = 300)
    @Column(name = "google_address")
    private String googleAddress;
    @Size(max = 15)
    @Column(name = "phone1")
    private String phone1;
    @Size(max = 15)
    @Column(name = "phone2")
    private String phone2;
    @Size(max = 100)
    @Column(name = "website")
    private String website;
    @Column(name = "parent_shop_id")
    private BigInteger parentShopId;
    @Size(max = 20)
    @Column(name = "picture")
    private String picture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "del_flag")
    private char delFlag;
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
    @OneToMany(mappedBy = "shopId")
    private Collection<O4ShopAccessor> o4ShopAccessorCollection;
    @OneToMany(mappedBy = "shopId")
    private Collection<O4DiscountInfo> o4DiscountInfoCollection;
    @JoinColumn(name = "city", referencedColumnName = "city_id")
    @ManyToOne
    private O4CityMaster city;
    @JoinColumn(name = "state", referencedColumnName = "state_id")
    @ManyToOne
    private O4StateMaster state;

    public O4RetailShopMaster() {
    }

    public O4RetailShopMaster(Long shopId) {
        this.shopId = shopId;
    }

    public O4RetailShopMaster(Long shopId, char delFlag) {
        this.shopId = shopId;
        this.delFlag = delFlag;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getGoogleAddress() {
        return googleAddress;
    }

    public void setGoogleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public BigInteger getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(BigInteger parentShopId) {
        this.parentShopId = parentShopId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public char getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(char delFlag) {
        this.delFlag = delFlag;
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
    public Collection<O4ShopAccessor> getO4ShopAccessorCollection() {
        return o4ShopAccessorCollection;
    }

    public void setO4ShopAccessorCollection(Collection<O4ShopAccessor> o4ShopAccessorCollection) {
        this.o4ShopAccessorCollection = o4ShopAccessorCollection;
    }

    @XmlTransient
    public Collection<O4DiscountInfo> getO4DiscountInfoCollection() {
        return o4DiscountInfoCollection;
    }

    public void setO4DiscountInfoCollection(Collection<O4DiscountInfo> o4DiscountInfoCollection) {
        this.o4DiscountInfoCollection = o4DiscountInfoCollection;
    }

    public O4CityMaster getCity() {
        return city;
    }

    public void setCity(O4CityMaster city) {
        this.city = city;
    }

    public O4StateMaster getState() {
        return state;
    }

    public void setState(O4StateMaster state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shopId != null ? shopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4RetailShopMaster)) {
            return false;
        }
        O4RetailShopMaster other = (O4RetailShopMaster) object;
        if ((this.shopId == null && other.shopId != null) || (this.shopId != null && !this.shopId.equals(other.shopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4RetailShopMaster[ shopId=" + shopId + " ]";
    }
    
}
