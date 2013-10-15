/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "magazine", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Magazine.findAll", query = "SELECT m FROM Magazine m"),
    @NamedQuery(name = "Magazine.findByIdMagazine", query = "SELECT m FROM Magazine m WHERE m.idMagazine = :idMagazine"),
    @NamedQuery(name = "Magazine.findByMagazineName", query = "SELECT m FROM Magazine m WHERE m.magazineName = :magazineName"),
    @NamedQuery(name = "Magazine.findByWebsite", query = "SELECT m FROM Magazine m WHERE m.website = :website"),
    @NamedQuery(name = "Magazine.findByYearStart", query = "SELECT m FROM Magazine m WHERE m.yearStart = :yearStart"),
    @NamedQuery(name = "Magazine.findByYearEnd", query = "SELECT m FROM Magazine m WHERE m.yearEnd = :yearEnd"),
    @NamedQuery(name = "Magazine.findByOrganization", query = "SELECT m FROM Magazine m WHERE m.organization = :organization"),
    @NamedQuery(name = "Magazine.findByUrl", query = "SELECT m FROM Magazine m WHERE m.url = :url")})
public class Magazine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMagazine", nullable = false)
    private Integer idMagazine;
    @Column(name = "magazineName", length = 1000)
    private String magazineName;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "yearStart")
    private Integer yearStart;
    @Column(name = "yearEnd")
    private Integer yearEnd;
    @Column(name = "organization", length = 1000)
    private String organization;
    @Column(name = "url", length = 1000)
    private String url;
    @OneToMany(mappedBy = "idMagazine")
    private Collection<Paper> paperCollection;

    public Magazine() {
    }

    public Magazine(Integer idMagazine) {
        this.idMagazine = idMagazine;
    }

    public Integer getIdMagazine() {
        return idMagazine;
    }

    public void setIdMagazine(Integer idMagazine) {
        this.idMagazine = idMagazine;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Paper> getPaperCollection() {
        return paperCollection;
    }

    public void setPaperCollection(Collection<Paper> paperCollection) {
        this.paperCollection = paperCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMagazine != null ? idMagazine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Magazine)) {
            return false;
        }
        Magazine other = (Magazine) object;
        if ((this.idMagazine == null && other.idMagazine != null) || (this.idMagazine != null && !this.idMagazine.equals(other.idMagazine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Magazine[ idMagazine=" + idMagazine + " ]";
    }
    
}
