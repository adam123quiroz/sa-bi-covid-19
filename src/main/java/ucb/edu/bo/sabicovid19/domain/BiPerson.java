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
@Table(name = "bi_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiPerson.findAll", query = "SELECT b FROM BiPerson b"),
    @NamedQuery(name = "BiPerson.findByPersonId", query = "SELECT b FROM BiPerson b WHERE b.personId = :personId"),
    @NamedQuery(name = "BiPerson.findByFirstName", query = "SELECT b FROM BiPerson b WHERE b.firstName = :firstName"),
    @NamedQuery(name = "BiPerson.findBySecondName", query = "SELECT b FROM BiPerson b WHERE b.secondName = :secondName"),
    @NamedQuery(name = "BiPerson.findByThirdName", query = "SELECT b FROM BiPerson b WHERE b.thirdName = :thirdName"),
    @NamedQuery(name = "BiPerson.findByFirstSurname", query = "SELECT b FROM BiPerson b WHERE b.firstSurname = :firstSurname"),
    @NamedQuery(name = "BiPerson.findBySecondSurname", query = "SELECT b FROM BiPerson b WHERE b.secondSurname = :secondSurname"),
    @NamedQuery(name = "BiPerson.findByThirdSurname", query = "SELECT b FROM BiPerson b WHERE b.thirdSurname = :thirdSurname"),
    @NamedQuery(name = "BiPerson.findByStatus", query = "SELECT b FROM BiPerson b WHERE b.status = :status"),
    @NamedQuery(name = "BiPerson.findByTextUser", query = "SELECT b FROM BiPerson b WHERE b.textUser = :textUser"),
    @NamedQuery(name = "BiPerson.findByTextHost", query = "SELECT b FROM BiPerson b WHERE b.textHost = :textHost"),
    @NamedQuery(name = "BiPerson.findByTextDate", query = "SELECT b FROM BiPerson b WHERE b.textDate = :textDate")})
public class BiPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "second_name")
    private String secondName;
    @Size(max = 50)
    @Column(name = "third_name")
    private String thirdName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_surname")
    private String firstSurname;
    @Size(max = 50)
    @Column(name = "second_surname")
    private String secondSurname;
    @Size(max = 50)
    @Column(name = "third_surname")
    private String thirdSurname;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private List<BiUser> biUserList;

    public BiPerson() {
    }

    public BiPerson(Integer personId) {
        this.personId = personId;
    }

    public BiPerson(Integer personId, String firstName, String firstSurname, int status, String textUser, String textHost, Date textDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.textDate = textDate;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getThirdSurname() {
        return thirdSurname;
    }

    public void setThirdSurname(String thirdSurname) {
        this.thirdSurname = thirdSurname;
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
    public List<BiUser> getBiUserList() {
        return biUserList;
    }

    public void setBiUserList(List<BiUser> biUserList) {
        this.biUserList = biUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiPerson)) {
            return false;
        }
        BiPerson other = (BiPerson) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucb.edu.bo.sabicovid19.domain.BiPerson[ personId=" + personId + " ]";
    }
    
}
