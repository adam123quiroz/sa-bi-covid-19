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
@Table(name = "bi_gender")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiGender.findAll", query = "SELECT b FROM BiGender b"),
    @NamedQuery(name = "BiGender.findByGenderId", query = "SELECT b FROM BiGender b WHERE b.genderId = :genderId"),
    @NamedQuery(name = "BiGender.findByGender", query = "SELECT b FROM BiGender b WHERE b.gender = :gender"),
    @NamedQuery(name = "BiGender.findByStatus", query = "SELECT b FROM BiGender b WHERE b.status = :status"),
    @NamedQuery(name = "BiGender.findByTextUser", query = "SELECT b FROM BiGender b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiGender.findByTextHost", query = "SELECT b FROM BiGender b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiGender.findByTextDate", query = "SELECT b FROM BiGender b WHERE b.textDate = :textDate")})
public class BiGender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gender_id")
    private Integer genderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gender")
    private String gender;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ganderId", fetch = FetchType.LAZY)
    private List<BiCase> biCaseList;

    public BiGender() {
    }

    public BiGender(Integer genderId) {
        this.genderId = genderId;
    }

    public BiGender(Integer genderId, String gender, int status, String textUser, String textHost, Date textDate) {
        this.genderId = genderId;
        this.gender = gender;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        hash += (genderId != null ? genderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiGender)) {
            return false;
        }
        BiGender other = (BiGender) object;
        if ((this.genderId == null && other.genderId != null) || (this.genderId != null && !this.genderId.equals(other.genderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiGender[ genderId=" + genderId + " ]";
    }
    
}
