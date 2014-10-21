/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.offers.eb;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "o4_discount_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4DiscountInfo.findAll", query = "SELECT o FROM O4DiscountInfo o"),
    @NamedQuery(name = "O4DiscountInfo.findByDiscountId", query = "SELECT o FROM O4DiscountInfo o WHERE o.discountId = :discountId"),
    @NamedQuery(name = "O4DiscountInfo.findByPrice", query = "SELECT o FROM O4DiscountInfo o WHERE o.price = :price"),
    @NamedQuery(name = "O4DiscountInfo.findByDiscPrice", query = "SELECT o FROM O4DiscountInfo o WHERE o.discPrice = :discPrice"),
    @NamedQuery(name = "O4DiscountInfo.findByDiscountPercentage", query = "SELECT o FROM O4DiscountInfo o WHERE o.discountPercentage = :discountPercentage"),
    @NamedQuery(name = "O4DiscountInfo.findByDiscountDescription", query = "SELECT o FROM O4DiscountInfo o WHERE o.discountDescription = :discountDescription"),
    @NamedQuery(name = "O4DiscountInfo.findByValidStartDate", query = "SELECT o FROM O4DiscountInfo o WHERE o.validStartDate = :validStartDate"),
    @NamedQuery(name = "O4DiscountInfo.findByValidEndDate", query = "SELECT o FROM O4DiscountInfo o WHERE o.validEndDate = :validEndDate"),
    @NamedQuery(name = "O4DiscountInfo.findByDelFlag", query = "SELECT o FROM O4DiscountInfo o WHERE o.delFlag = :delFlag"),
    @NamedQuery(name = "O4DiscountInfo.findByCreatedDate", query = "SELECT o FROM O4DiscountInfo o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4DiscountInfo.findByUpdatedDate", query = "SELECT o FROM O4DiscountInfo o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4DiscountInfo.findByCreatedUser", query = "SELECT o FROM O4DiscountInfo o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4DiscountInfo.findByUpdatedUser", query = "SELECT o FROM O4DiscountInfo o WHERE o.updatedUser = :updatedUser")})
public class O4DiscountInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "discount_id")
    private Long discountId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "disc_price")
    private BigDecimal discPrice;
    @Column(name = "discount_percentage")
    private Integer discountPercentage;
    @Size(max = 500)
    @Column(name = "discount_description")
    private String discountDescription;
    @Column(name = "valid_start_date")
    @Temporal(TemporalType.DATE)
    private Date validStartDate;
    @Column(name = "valid_end_date")
    @Temporal(TemporalType.DATE)
    private Date validEndDate;
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
    @JoinColumn(name = "shop_id", referencedColumnName = "shop_id")
    @ManyToOne
    private O4RetailShopMaster shopId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private O4ProductMaster productId;
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne
    private O4CityMaster cityId;

    public O4DiscountInfo() {
    }

    public O4DiscountInfo(Long discountId) {
        this.discountId = discountId;
    }

    public O4DiscountInfo(Long discountId, char delFlag) {
        this.discountId = discountId;
        this.delFlag = delFlag;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(BigDecimal discPrice) {
        this.discPrice = discPrice;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
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

    public O4RetailShopMaster getShopId() {
        return shopId;
    }

    public void setShopId(O4RetailShopMaster shopId) {
        this.shopId = shopId;
    }

    public O4ProductMaster getProductId() {
        return productId;
    }

    public void setProductId(O4ProductMaster productId) {
        this.productId = productId;
    }

    public O4CityMaster getCityId() {
        return cityId;
    }

    public void setCityId(O4CityMaster cityId) {
        this.cityId = cityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (discountId != null ? discountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4DiscountInfo)) {
            return false;
        }
        O4DiscountInfo other = (O4DiscountInfo) object;
        if ((this.discountId == null && other.discountId != null) || (this.discountId != null && !this.discountId.equals(other.discountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4DiscountInfo[ discountId=" + discountId + " ]";
    }
    
}
