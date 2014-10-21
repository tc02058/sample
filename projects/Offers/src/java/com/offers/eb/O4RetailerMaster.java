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
@Table(name = "o4_retailer_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "O4RetailerMaster.findAll", query = "SELECT o FROM O4RetailerMaster o"),
    @NamedQuery(name = "O4RetailerMaster.findByRetailerId", query = "SELECT o FROM O4RetailerMaster o WHERE o.retailerId = :retailerId"),
    @NamedQuery(name = "O4RetailerMaster.findByFirstName", query = "SELECT o FROM O4RetailerMaster o WHERE o.firstName = :firstName"),
    @NamedQuery(name = "O4RetailerMaster.findByLastName", query = "SELECT o FROM O4RetailerMaster o WHERE o.lastName = :lastName"),
    @NamedQuery(name = "O4RetailerMaster.findByMobile", query = "SELECT o FROM O4RetailerMaster o WHERE o.mobile = :mobile"),
    @NamedQuery(name = "O4RetailerMaster.findByEmail", query = "SELECT o FROM O4RetailerMaster o WHERE o.email = :email"),
    @NamedQuery(name = "O4RetailerMaster.findByPassword", query = "SELECT o FROM O4RetailerMaster o WHERE o.password = :password"),
    @NamedQuery(name = "O4RetailerMaster.findByManagerId", query = "SELECT o FROM O4RetailerMaster o WHERE o.managerId = :managerId"),
    @NamedQuery(name = "O4RetailerMaster.findByDelFlag", query = "SELECT o FROM O4RetailerMaster o WHERE o.delFlag = :delFlag"),
    @NamedQuery(name = "O4RetailerMaster.findByCreatedDate", query = "SELECT o FROM O4RetailerMaster o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "O4RetailerMaster.findByUpdatedDate", query = "SELECT o FROM O4RetailerMaster o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "O4RetailerMaster.findByCreatedUser", query = "SELECT o FROM O4RetailerMaster o WHERE o.createdUser = :createdUser"),
    @NamedQuery(name = "O4RetailerMaster.findByUpdatedUser", query = "SELECT o FROM O4RetailerMaster o WHERE o.updatedUser = :updatedUser")})
public class O4RetailerMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "retailer_id")
    private Long retailerId;
    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 20)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 15)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 10)
    @Column(name = "password")
    private String password;
    @Column(name = "manager_id")
    private BigInteger managerId;
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
    @OneToMany(mappedBy = "retailerId")
    private Collection<O4ShopAccessor> o4ShopAccessorCollection;

    public O4RetailerMaster() {
    }

    public O4RetailerMaster(Long retailerId) {
        this.retailerId = retailerId;
    }

    public O4RetailerMaster(Long retailerId, char delFlag) {
        this.retailerId = retailerId;
        this.delFlag = delFlag;
    }

    public Long getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Long retailerId) {
        this.retailerId = retailerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getManagerId() {
        return managerId;
    }

    public void setManagerId(BigInteger managerId) {
        this.managerId = managerId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retailerId != null ? retailerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof O4RetailerMaster)) {
            return false;
        }
        O4RetailerMaster other = (O4RetailerMaster) object;
        if ((this.retailerId == null && other.retailerId != null) || (this.retailerId != null && !this.retailerId.equals(other.retailerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offers.eb.O4RetailerMaster[ retailerId=" + retailerId + " ]";
    }
    
}
