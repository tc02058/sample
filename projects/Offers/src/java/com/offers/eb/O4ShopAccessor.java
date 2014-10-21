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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chithambalamd
 */
@Entity
@Table(name = "o4_shop_accessor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4ShopAccessor.findAll", query = "SELECT o FROM O4ShopAccessor o"),
    @NamedQuery(name = "O4ShopAccessor.findByRetailerShopUser", query = "SELECT o FROM O4ShopAccessor o WHERE o.retailerShopUser = :retailerShopUser"),
    @NamedQuery(name = "O4ShopAccessor.findByDelFlag", query = "SELECT o FROM O4ShopAccessor o WHERE o.delFlag = :delFlag"),
    @NamedQuery(name = "O4ShopAccessor.findByCreatedDate", query = "SELECT o FROM O4ShopAccessor o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4ShopAccessor.findByUpdatedDate", query = "SELECT o FROM O4ShopAccessor o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4ShopAccessor.findByCreatedUser", query = "SELECT o FROM O4ShopAccessor o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4ShopAccessor.findByUpdatedUser", query = "SELECT o FROM O4ShopAccessor o WHERE o.updatedUser = :updatedUser")})
public class O4ShopAccessor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "retailer_shop_user")
    private Long retailerShopUser;
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
    @JoinColumn(name = "shop_id", referencedColumnName = "shop_id")
    @ManyToOne
    private O4RetailShopMaster shopId;
    @JoinColumn(name = "retailer_id", referencedColumnName = "retailer_id")
    @ManyToOne
    private O4RetailerMaster retailerId;

    public O4ShopAccessor() {
    }

    public O4ShopAccessor(Long retailerShopUser) {
        this.retailerShopUser = retailerShopUser;
    }

    public O4ShopAccessor(Long retailerShopUser, char delFlag) {
        this.retailerShopUser = retailerShopUser;
        this.delFlag = delFlag;
    }

    public Long getRetailerShopUser() {
        return retailerShopUser;
    }

    public void setRetailerShopUser(Long retailerShopUser) {
        this.retailerShopUser = retailerShopUser;
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

    public O4RetailShopMaster getShopId() {
        return shopId;
    }

    public void setShopId(O4RetailShopMaster shopId) {
        this.shopId = shopId;
    }

    public O4RetailerMaster getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(O4RetailerMaster retailerId) {
        this.retailerId = retailerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retailerShopUser != null ? retailerShopUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4ShopAccessor)) {
            return false;
        }
        O4ShopAccessor other = (O4ShopAccessor) object;
        if ((this.retailerShopUser == null && other.retailerShopUser != null) || (this.retailerShopUser != null && !this.retailerShopUser.equals(other.retailerShopUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4ShopAccessor[ retailerShopUser=" + retailerShopUser + " ]";
    }
    
}
