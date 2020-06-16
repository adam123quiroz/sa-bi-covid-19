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
@Table(name = "bi_department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiDepartment.findAll", query = "SELECT b FROM BiDepartment b"),
    @NamedQuery(name = "BiDepartment.findByDepartmentId", query = "SELECT b FROM BiDepartment b WHERE b.departmentId = :departmentId"),
    @NamedQuery(name = "BiDepartment.findByDepartment", query = "SELECT b FROM BiDepartment b WHERE b.department = :department"),
    @NamedQuery(name = "BiDepartment.findByStatus", query = "SELECT b FROM BiDepartment b WHERE b.status = :status"),
    @NamedQuery(name = "BiDepartment.findByTextUser", query = "SELECT b FROM BiDepartment b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiDepartment.findByTextHost", query = "SELECT b FROM BiDepartment b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiDepartment.findByTextDate", query = "SELECT b FROM BiDepartment b WHERE b.textDate = :textDate")})
public class BiDepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "department_id")
    private Integer departmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "department")
    private String department;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId", fetch = FetchType.LAZY)
    private List<BiProvince> biProvinceList;

    public BiDepartment() {
    }

    public BiDepartment(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public BiDepartment(Integer departmentId, String department, int status, String textUser, String textHost, Date textDate) {
        this.departmentId = departmentId;
        this.department = department;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
    public List<BiProvince> getBiProvinceList() {
        return biProvinceList;
    }

    public void setBiProvinceList(List<BiProvince> biProvinceList) {
        this.biProvinceList = biProvinceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departmentId != null ? departmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiDepartment)) {
            return false;
        }
        BiDepartment other = (BiDepartment) object;
        if ((this.departmentId == null && other.departmentId != null) || (this.departmentId != null && !this.departmentId.equals(other.departmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiDepartment[ departmentId=" + departmentId + " ]";
    }
    
}
