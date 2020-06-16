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
@Table(name = "bi_historical_medical_condition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiHistoricalMedicalCondition.findAll", query = "SELECT b FROM BiHistoricalMedicalCondition b"),
    @NamedQuery(name = "BiHistoricalMedicalCondition.findByHMedConditonId", query = "SELECT b FROM BiHistoricalMedicalCondition b WHERE b.hMedConditonId = :hMedConditonId"),
    @NamedQuery(name = "BiHistoricalMedicalCondition.findByUpdateDate", query = "SELECT b FROM BiHistoricalMedicalCondition b WHERE b.updateDate = :updateDate"),
    @NamedQuery(name = "BiHistoricalMedicalCondition.findByStatus", query = "SELECT b FROM BiHistoricalMedicalCondition b WHERE b.status = :status"),
    @NamedQuery(name = "BiHistoricalMedicalCondition.findByTextUser", query = "SELECT b FROM BiHistoricalMedicalCondition b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiHistoricalMedicalCondition.findByTextHost", query = "SELECT b FROM BiHistoricalMedicalCondition b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiHistoricalMedicalCondition.findByTextDate", query = "SELECT b FROM BiHistoricalMedicalCondition b WHERE b.textDate = :textDate")})
public class BiHistoricalMedicalCondition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "h_med_conditon_id")
    private Integer hMedConditonId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
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
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiCase caseId;
    @JoinColumn(name = "med_cond_id", referencedColumnName = "med_cond_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BiMedicalCondition medCondId;

    public BiHistoricalMedicalCondition() {
    }

    public BiHistoricalMedicalCondition(Integer hMedConditonId) {
        this.hMedConditonId = hMedConditonId;
    }

    public BiHistoricalMedicalCondition(Integer hMedConditonId, Date updateDate, int status, String textUser, String textHost, Date textDate) {
        this.hMedConditonId = hMedConditonId;
        this.updateDate = updateDate;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getHMedConditonId() {
        return hMedConditonId;
    }

    public void setHMedConditonId(Integer hMedConditonId) {
        this.hMedConditonId = hMedConditonId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public BiCase getCaseId() {
        return caseId;
    }

    public void setCaseId(BiCase caseId) {
        this.caseId = caseId;
    }

    public BiMedicalCondition getMedCondId() {
        return medCondId;
    }

    public void setMedCondId(BiMedicalCondition medCondId) {
        this.medCondId = medCondId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hMedConditonId != null ? hMedConditonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiHistoricalMedicalCondition)) {
            return false;
        }
        BiHistoricalMedicalCondition other = (BiHistoricalMedicalCondition) object;
        if ((this.hMedConditonId == null && other.hMedConditonId != null) || (this.hMedConditonId != null && !this.hMedConditonId.equals(other.hMedConditonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiHistoricalMedicalCondition[ hMedConditonId=" + hMedConditonId + " ]";
    }
    
}
