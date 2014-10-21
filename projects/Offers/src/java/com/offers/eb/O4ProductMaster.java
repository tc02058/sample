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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chithambalamd
 */
@Entity
@Table(name = "o4_product_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4ProductMaster.findAll", query = "SELECT o FROM O4ProductMaster o"),
    @NamedQuery(name = "O4ProductMaster.findByProductId", query = "SELECT o FROM O4ProductMaster o WHERE o.productId = :productId"),
    @NamedQuery(name = "O4ProductMaster.findByProductName", query = "SELECT o FROM O4ProductMaster o WHERE o.productName = :productName"),
    @NamedQuery(name = "O4ProductMaster.findByPicture", query = "SELECT o FROM O4ProductMaster o WHERE o.picture = :picture"),
    @NamedQuery(name = "O4ProductMaster.findByDelFlag", query = "SELECT o FROM O4ProductMaster o WHERE o.delFlag = :delFlag"),
    @NamedQuery(name = "O4ProductMaster.findByCreatedDate", query = "SELECT o FROM O4ProductMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4ProductMaster.findByUpdatedDate", query = "SELECT o FROM O4ProductMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4ProductMaster.findByCreatedUser", query = "SELECT o FROM O4ProductMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4ProductMaster.findByUpdatedUser", query = "SELECT o FROM O4ProductMaster o WHERE o.updatedUser = :updatedUser")})
public class O4ProductMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Long productId;
    @Size(max = 50)
    @Column(name = "product_name")
    private String productName;
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
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private O4ProductCategoryMaster categoryId;
    @OneToMany(mappedBy = "productId")
    private Collection<O4DiscountInfo> o4DiscountInfoCollection;

    public O4ProductMaster() {
    }

    public O4ProductMaster(Long productId) {
        this.productId = productId;
    }

    public O4ProductMaster(Long productId, char delFlag) {
        this.productId = productId;
        this.delFlag = delFlag;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public O4ProductCategoryMaster getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(O4ProductCategoryMaster categoryId) {
        this.categoryId = categoryId;
    }

    @XmlTransient
    public Collection<O4DiscountInfo> getO4DiscountInfoCollection() {
        return o4DiscountInfoCollection;
    }

    public void setO4DiscountInfoCollection(Collection<O4DiscountInfo> o4DiscountInfoCollection) {
        this.o4DiscountInfoCollection = o4DiscountInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4ProductMaster)) {
            return false;
        }
        O4ProductMaster other = (O4ProductMaster) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4ProductMaster[ productId=" + productId + " ]";
    }
    
}
