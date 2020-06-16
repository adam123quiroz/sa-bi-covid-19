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
@Table(name = "bi_medical_condition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiMedicalCondition.findAll", query = "SELECT b FROM BiMedicalCondition b"),
    @NamedQuery(name = "BiMedicalCondition.findByMedCondId", query = "SELECT b FROM BiMedicalCondition b WHERE b.medCondId = :medCondId"),
    @NamedQuery(name = "BiMedicalCondition.findByMedicalCondition", query = "SELECT b FROM BiMedicalCondition b WHERE b.medicalCondition = :medicalCondition"),
    @NamedQuery(name = "BiMedicalCondition.findByStatus", query = "SELECT b FROM BiMedicalCondition b WHERE b.status = :status"),
    @NamedQuery(name = "BiMedicalCondition.findByTextUser", query = "SELECT b FROM BiMedicalCondition b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiMedicalCondition.findByTextHost", query = "SELECT b FROM BiMedicalCondition b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiMedicalCondition.findByTextDate", query = "SELECT b FROM BiMedicalCondition b WHERE b.textDate = :textDate")})
public class BiMedicalCondition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "med_cond_id")
    private Integer medCondId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "medical_condition")
    private String medicalCondition;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medCondId", fetch = FetchType.LAZY)
    private List<BiCase> biCaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medCondId", fetch = FetchType.LAZY)
    private List<BiHistoricalMedicalCondition> biHistoricalMedicalConditionList;

    public BiMedicalCondition() {
    }

    public BiMedicalCondition(Integer medCondId) {
        this.medCondId = medCondId;
    }

    public BiMedicalCondition(Integer medCondId, String medicalCondition, int status, String textUser, String textHost, Date textDate) {
        this.medCondId = medCondId;
        this.medicalCondition = medicalCondition;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getMedCondId() {
        return medCondId;
    }

    public void setMedCondId(Integer medCondId) {
        this.medCondId = medCondId;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
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
        hash += (medCondId != null ? medCondId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiMedicalCondition)) {
            return false;
        }
        BiMedicalCondition other = (BiMedicalCondition) object;
        if ((this.medCondId == null && other.medCondId != null) || (this.medCondId != null && !this.medCondId.equals(other.medCondId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiMedicalCondition[ medCondId=" + medCondId + " ]";
    }
    
}
