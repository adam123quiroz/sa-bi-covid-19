/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucb.edu.bo.sabicovid19.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author artud
 */
@Entity
@Table(name = "bi_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiRole.findAll", query = "SELECT b FROM BiRole b"),
    @NamedQuery(name = "BiRole.findByRoleId", query = "SELECT b FROM BiRole b WHERE b.roleId = :roleId"),
    @NamedQuery(name = "BiRole.findByRole", query = "SELECT b FROM BiRole b WHERE b.role = :role"),
    @NamedQuery(name = "BiRole.findByStatus", query = "SELECT b FROM BiRole b WHERE b.status = :status"),
    @NamedQuery(name = "BiRole.findByTextUser", query = "SELECT b FROM BiRole b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiRole.findByTextHost", query = "SELECT b FROM BiRole b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiRole.findByTextDate", query = "SELECT b FROM BiRole b WHERE b.textDate = :textDate")})
public class BiRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "role")
    private String role;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId", fetch = FetchType.LAZY)
    private List<BiUserRole> biUserRoleList;

    public BiRole() {
    }

    public BiRole(Integer roleId) {
        this.roleId = roleId;
    }

    public BiRole(Integer roleId, String role, int status, String textUser, String textHost, Date textDate) {
        this.roleId = roleId;
        this.role = role;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @XmlTransient
    public List<BiUserRole> getBiUserRoleList() {
        return biUserRoleList;
    }

    public void setBiUserRoleList(List<BiUserRole> biUserRoleList) {
        this.biUserRoleList = biUserRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiRole)) {
            return false;
        }
        BiRole other = (BiRole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiRole[ roleId=" + roleId + " ]";
    }
    
}
