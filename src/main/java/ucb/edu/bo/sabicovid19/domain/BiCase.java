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
@Table(name = "bi_case")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiCase.findAll", query = "SELECT b FROM BiCase b"),
    @NamedQuery(name = "BiCase.findByCaseId", query = "SELECT b FROM BiCase b WHERE b.caseId = :caseId"),
    @NamedQuery(name = "BiCase.findByAge", query = "SELECT b FROM BiCase b WHERE b.age = :age"),
    @NamedQuery(name = "BiCase.findByUpdateDate", query = "SELECT b FROM BiCase b WHERE b.updateDate = :updateDate"),
    @NamedQuery(name = "BiCase.findByDistrict", query = "SELECT b FROM BiCase b WHERE b.district = :district"),
    @NamedQuery(name = "BiCase.findByZone", query = "SELECT b FROM BiCase b WHERE b.zone = :zone"),
    @NamedQuery(name = "BiCase.findByStatus", query = "SELECT b FROM BiCase b WHERE b.status = :status"),
    @NamedQuery(name = "BiCase.findByTextUser", query = "SELECT b FROM BiCase b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiCase.findByTextHost", query = "SELECT b FROM BiCase b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiCase.findByTextDate", query = "SELECT b FROM BiCase b WHERE b.textDate = :textDate")})
public class BiCase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "case_id")
    private Integer caseId;
    @Column(name = "age")
    private Integer age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @Size(max = 20)
    @Column(name = "district")
    private String district;
    @Size(max = 100)
    @Column(name = "zone")
    private String zone;
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
    @JoinColumn(name = "gander_id", referencedColumnName = "gender_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiGender ganderId;
    @JoinColumn(name = "med_cond_id", referencedColumnName = "med_cond_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiMedicalCondition medCondId;
    @JoinColumn(name = "municipally_id", referencedColumnName = "municipally_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiMunicipality municipallyId;
    @JoinColumn(name = "ori_contg_id", referencedColumnName = "ori_contg_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiOriginContagion oriContgId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseId", fetch = FetchType.LAZY)
    private List<BiHistoricalMedicalCondition> biHistoricalMedicalConditionList;

    public BiCase() {
    }

    public BiCase(Integer caseId) {
        this.caseId = caseId;
    }

    public BiCase(Integer caseId, Date updateDate, int status, String textUser, String textHost, Date textDate) {
        this.caseId = caseId;
        this.updateDate = updateDate;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
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

    public BiGender getGanderId() {
        return ganderId;
    }

    public void setGanderId(BiGender ganderId) {
        this.ganderId = ganderId;
    }

    public BiMedicalCondition getMedCondId() {
        return medCondId;
    }

    public void setMedCondId(BiMedicalCondition medCondId) {
        this.medCondId = medCondId;
    }

    public BiMunicipality getMunicipallyId() {
        return municipallyId;
    }

    public void setMunicipallyId(BiMunicipality municipallyId) {
        this.municipallyId = municipallyId;
    }

    public BiOriginContagion getOriContgId() {
        return oriContgId;
    }

    public void setOriContgId(BiOriginContagion oriContgId) {
        this.oriContgId = oriContgId;
    }

    @XmlTransient
    public List<BiHistoricalMedicalCondition> getBiHistoricalMedicalConditionList() {
        return biHistoricalMedicalConditionList;
    }

    public void setBiHistoricalMedicalConditionList(List<BiHistoricalMedicalCondition> biHistoricalMedicalConditionList) {
        this.biHistoricalMedicalConditionList = biHistoricalMedicalConditionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caseId != null ? caseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiCase)) {
            return false;
        }
        BiCase other = (BiCase) object;
        if ((this.caseId == null && other.caseId != null) || (this.caseId != null && !this.caseId.equals(other.caseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiCase[ caseId=" + caseId + " ]";
    }
    
}
