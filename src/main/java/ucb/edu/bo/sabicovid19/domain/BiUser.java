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
 * @author artud
 */
@Entity
@Table(name = "bi_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiUser.findAll", query = "SELECT b FROM BiUser b"),
    @NamedQuery(name = "BiUser.findByUserId", query = "SELECT b FROM BiUser b WHERE b.userId = :userId"),
    @NamedQuery(name = "BiUser.findByUsername", query = "SELECT b FROM BiUser b WHERE b.username = :username"),
    @NamedQuery(name = "BiUser.findByPassword", query = "SELECT b FROM BiUser b WHERE b.password = :password"),
    @NamedQuery(name = "BiUser.findByAccountExpired", query = "SELECT b FROM BiUser b WHERE b.accountExpired = :accountExpired"),
    @NamedQuery(name = "BiUser.findByAccountLocked", query = "SELECT b FROM BiUser b WHERE b.accountLocked = :accountLocked"),
    @NamedQuery(name = "BiUser.findByCredentialsExpired", query = "SELECT b FROM BiUser b WHERE b.credentialsExpired = :credentialsExpired"),
    @NamedQuery(name = "BiUser.findByStatus", query = "SELECT b FROM BiUser b WHERE b.status = :status"),
    @NamedQuery(name = "BiUser.findByTextUser", query = "SELECT b FROM BiUser b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiUser.findByTextHost", query = "SELECT b FROM BiUser b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiUser.findByTextDate", query = "SELECT b FROM BiUser b WHERE b.textDate = :textDate")})
public class BiUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_expired")
    private int accountExpired;
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_locked")
    private int accountLocked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "credentials_expired")
    private int credentialsExpired;
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
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiPerson personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<BiUserRole> biUserRoleList;

    public BiUser() {
    }

    public BiUser(Integer userId) {
        this.userId = userId;
    }

    public BiUser(Integer userId, String username, String password, int accountExpired, int accountLocked, int credentialsExpired, int status, String textUser, String textHost, Date textDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(int accountExpired) {
        this.accountExpired = accountExpired;
    }

    public int getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(int accountLocked) {
        this.accountLocked = accountLocked;
    }

    public int getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(int credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
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

    public BiPerson getPersonId() {
        return personId;
    }

    public void setPersonId(BiPerson personId) {
        this.personId = personId;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiUser)) {
            return false;
        }
        BiUser other = (BiUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiUser[ userId=" + userId + " ]";
    }

}
