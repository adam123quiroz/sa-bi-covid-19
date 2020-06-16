/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucb.edu.bo.sabicovid19.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author artud
 */
@Entity
@Table(name = "bi_user_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiUserRole.findAll", query = "SELECT b FROM BiUserRole b"),
    @NamedQuery(name = "BiUserRole.findByUserRoleId", query = "SELECT b FROM BiUserRole b WHERE b.userRoleId = :userRoleId"),
    @NamedQuery(name = "BiUserRole.findByStatus", query = "SELECT b FROM BiUserRole b WHERE b.status = :status"),
    @NamedQuery(name = "BiUserRole.findByTextUser", query = "SELECT b FROM BiUserRole b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiUserRole.findByTextHost", query = "SELECT b FROM BiUserRole b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiUserRole.findByTextDate", query = "SELECT b FROM BiUserRole b WHERE b.textDate = :textDate")})
public class BiUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_role_id")
    private Integer userRoleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "text_user")
    private String textUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "text_host")
    private String textHost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "text_date")
    @Temporal(TemporalType.DATE)
    private Date textDate;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiRole roleId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiUser userId;

    public BiUserRole() {
    }

    public BiUserRole(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BiUserRole(Integer userRoleId, int status, String textUser, String textHost, Date textDate) {
        this.userRoleId = userRoleId;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTextUser() {
        return textUser;
    }

    public void setTextUser(String textUser) {
        this.textUser = textUser;
    }

    public String getTextHost() {
        return textHost;
    }

    public void setTextHost(String textHost) {
        this.textHost = textHost;
    }

    public Date getTextDate() {
        return textDate;
    }

    public void setTextDate(Date textDate) {
        this.textDate = textDate;
    }

    public BiRole getRoleId() {
        return roleId;
    }

    public void setRoleId(BiRole roleId) {
        this.roleId = roleId;
    }

    public BiUser getUserId() {
        return userId;
    }

    public void setUserId(BiUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRoleId != null ? userRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiUserRole)) {
            return false;
        }
        BiUserRole other = (BiUserRole) object;
        if ((this.userRoleId == null && other.userRoleId != null) || (this.userRoleId != null && !this.userRoleId.equals(other.userRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiUserRole[ userRoleId=" + userRoleId + " ]";
    }
    
}
