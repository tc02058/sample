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
@Table(name = "o4_product_category_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4ProductCategoryMaster.findAll", query = "SELECT o FROM O4ProductCategoryMaster o"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByCategoryId", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.categoryId = :categoryId"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByCategoryName", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.categoryName = :categoryName"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByCategoryParentId", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.categoryParentId = :categoryParentId"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByPicture", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.picture = :picture"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByDelFlag", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.delFlag = :delFlag"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByCreatedDate", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByUpdatedDate", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByCreatedUser", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4ProductCategoryMaster.findByUpdatedUser", query = "SELECT o FROM O4ProductCategoryMaster o WHERE o.updatedUser = :updatedUser")})
public class O4ProductCategoryMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
    private Long categoryId;
    @Size(max = 20)
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_parent_id")
    private BigInteger categoryParentId;
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
    @OneToMany(mappedBy = "categoryId")
    private Collection<O4ProductMaster> o4ProductMasterCollection;
    @OneToMany(mappedBy = "categoryId")
    private Collection<O4DiscountInfo> o4DiscountInfoCollection;

    public O4ProductCategoryMaster() {
    }

    public O4ProductCategoryMaster(Long categoryId) {
        this.categoryId = categoryId;
    }

    public O4ProductCategoryMaster(Long categoryId, char delFlag) {
        this.categoryId = categoryId;
        this.delFlag = delFlag;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigInteger getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(BigInteger categoryParentId) {
        this.categoryParentId = categoryParentId;
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
    public Collection<O4ProductMaster> getO4ProductMasterCollection() {
        return o4ProductMasterCollection;
    }

    public void setO4ProductMasterCollection(Collection<O4ProductMaster> o4ProductMasterCollection) {
        this.o4ProductMasterCollection = o4ProductMasterCollection;
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
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4ProductCategoryMaster)) {
            return false;
        }
        O4ProductCategoryMaster other = (O4ProductCategoryMaster) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4ProductCategoryMaster[ categoryId=" + categoryId + " ]";
    }
    
}
