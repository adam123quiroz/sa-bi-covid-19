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
@Table(name = "bi_municipality")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiMunicipality.findAll", query = "SELECT b FROM BiMunicipality b"),
    @NamedQuery(name = "BiMunicipality.findByMunicipallyId", query = "SELECT b FROM BiMunicipality b WHERE b.municipallyId = :municipallyId"),
    @NamedQuery(name = "BiMunicipality.findByMunicipally", query = "SELECT b FROM BiMunicipality b WHERE b.municipally = :municipally"),
    @NamedQuery(name = "BiMunicipality.findByStatus", query = "SELECT b FROM BiMunicipality b WHERE b.status = :status"),
    @NamedQuery(name = "BiMunicipality.findByTextUser", query = "SELECT b FROM BiMunicipality b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiMunicipality.findByTextHost", query = "SELECT b FROM BiMunicipality b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiMunicipality.findByTextDate", query = "SELECT b FROM BiMunicipality b WHERE b.textDate = :textDate")})
public class BiMunicipality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "municipally_id")
    private Integer municipallyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "municipally")
    private String municipally;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipallyId", fetch = FetchType.LAZY)
    private List<BiCase> biCaseList;
    @JoinColumn(name = "province_id", referencedColumnName = "province_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiProvince provinceId;

    public BiMunicipality() {
    }

    public BiMunicipality(Integer municipallyId) {
        this.municipallyId = municipallyId;
    }

    public BiMunicipality(Integer municipallyId, String municipally, int status, String textUser, String textHost, Date textDate) {
        this.municipallyId = municipallyId;
        this.municipally = municipally;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getMunicipallyId() {
        return municipallyId;
    }

    public void setMunicipallyId(Integer municipallyId) {
        this.municipallyId = municipallyId;
    }

    public String getMunicipally() {
        return municipally;
    }

    public void setMunicipally(String municipally) {
        this.municipally = municipally;
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

    public BiProvince getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(BiProvince provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipallyId != null ? municipallyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiMunicipality)) {
            return false;
        }
        BiMunicipality other = (BiMunicipality) object;
        if ((this.municipallyId == null && other.municipallyId != null) || (this.municipallyId != null && !this.municipallyId.equals(other.municipallyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiMunicipality[ municipallyId=" + municipallyId + " ]";
    }
    
}
