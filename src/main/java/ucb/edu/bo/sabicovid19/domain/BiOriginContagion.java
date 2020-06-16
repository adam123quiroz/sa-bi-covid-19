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
@Table(name = "bi_origin_contagion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiOriginContagion.findAll", query = "SELECT b FROM BiOriginContagion b"),
    @NamedQuery(name = "BiOriginContagion.findByOriContgId", query = "SELECT b FROM BiOriginContagion b WHERE b.oriContgId = :oriContgId"),
    @NamedQuery(name = "BiOriginContagion.findByOriginContagion", query = "SELECT b FROM BiOriginContagion b WHERE b.originContagion = :originContagion"),
    @NamedQuery(name = "BiOriginContagion.findByStatus", query = "SELECT b FROM BiOriginContagion b WHERE b.status = :status"),
    @NamedQuery(name = "BiOriginContagion.findByTextUser", query = "SELECT b FROM BiOriginContagion b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiOriginContagion.findByTextHost", query = "SELECT b FROM BiOriginContagion b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiOriginContagion.findByTextDate", query = "SELECT b FROM BiOriginContagion b WHERE b.textDate = :textDate")})
public class BiOriginContagion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ori_contg_id")
    private Integer oriContgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "origin_contagion")
    private String originContagion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oriContgId", fetch = FetchType.LAZY)
    private List<BiCase> biCaseList;

    public BiOriginContagion() {
    }

    public BiOriginContagion(Integer oriContgId) {
        this.oriContgId = oriContgId;
    }

    public BiOriginContagion(Integer oriContgId, String originContagion, int status, String textUser, String textHost, Date textDate) {
        this.oriContgId = oriContgId;
        this.originContagion = originContagion;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getOriContgId() {
        return oriContgId;
    }

    public void setOriContgId(Integer oriContgId) {
        this.oriContgId = oriContgId;
    }

    public String getOriginContagion() {
        return originContagion;
    }

    public void setOriginContagion(String originContagion) {
        this.originContagion = originContagion;
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
    public List<BiCase> getBiCaseList() {
        return biCaseList;
    }

    public void setBiCaseList(List<BiCase> biCaseList) {
        this.biCaseList = biCaseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oriContgId != null ? oriContgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiOriginContagion)) {
            return false;
        }
        BiOriginContagion other = (BiOriginContagion) object;
        if ((this.oriContgId == null && other.oriContgId != null) || (this.oriContgId != null && !this.oriContgId.equals(other.oriContgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiOriginContagion[ oriContgId=" + oriContgId + " ]";
    }
    
}
