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
@Table(name = "bi_province")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiProvince.findAll", query = "SELECT b FROM BiProvince b"),
    @NamedQuery(name = "BiProvince.findByProvinceId", query = "SELECT b FROM BiProvince b WHERE b.provinceId = :provinceId"),
    @NamedQuery(name = "BiProvince.findByProvince", query = "SELECT b FROM BiProvince b WHERE b.province = :province"),
    @NamedQuery(name = "BiProvince.findByStatus", query = "SELECT b FROM BiProvince b WHERE b.status = :status"),
    @NamedQuery(name = "BiProvince.findByTextUser", query = "SELECT b FROM BiProvince b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiProvince.findByTextHost", query = "SELECT b FROM BiProvince b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiProvince.findByTextDate", query = "SELECT b FROM BiProvince b WHERE b.textDate = :textDate")})
public class BiProvince implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "province_id")
    private Integer provinceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "province")
    private String province;
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
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiDepartment departmentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceId", fetch = FetchType.LAZY)
    private List<BiMunicipality> biMunicipalityList;

    public BiProvince() {
    }

    public BiProvince(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public BiProvince(Integer provinceId, String province, int status, String textUser, String textHost, Date textDate) {
        this.provinceId = provinceId;
        this.province = province;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public BiDepartment getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(BiDepartment departmentId) {
        this.departmentId = departmentId;
    }

    @XmlTransient
    public List<BiMunicipality> getBiMunicipalityList() {
        return biMunicipalityList;
    }

    public void setBiMunicipalityList(List<BiMunicipality> biMunicipalityList) {
        this.biMunicipalityList = biMunicipalityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provinceId != null ? provinceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiProvince)) {
            return false;
        }
        BiProvince other = (BiProvince) object;
        if ((this.provinceId == null && other.provinceId != null) || (this.provinceId != null && !this.provinceId.equals(other.provinceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiProvince[ provinceId=" + provinceId + " ]";
    }
    
}
