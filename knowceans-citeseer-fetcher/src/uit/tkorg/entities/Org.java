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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "org", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Org.findAll", query = "SELECT o FROM Org o"),
    @NamedQuery(name = "Org.findByIdOrg", query = "SELECT o FROM Org o WHERE o.idOrg = :idOrg"),
    @NamedQuery(name = "Org.findByOrgName", query = "SELECT o FROM Org o WHERE o.orgName = :orgName"),
    @NamedQuery(name = "Org.findByWebsite", query = "SELECT o FROM Org o WHERE o.website = :website"),
    @NamedQuery(name = "Org.findByContinent", query = "SELECT o FROM Org o WHERE o.continent = :continent"),
    @NamedQuery(name = "Org.findByHIndex", query = "SELECT o FROM Org o WHERE o.hIndex = :hIndex"),
    @NamedQuery(name = "Org.findByUrl", query = "SELECT o FROM Org o WHERE o.url = :url")})
public class Org implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrg", nullable = false)
    private Integer idOrg;
    @Column(name = "orgName", length = 1000)
    private String orgName;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "continent", length = 1000)
    private String continent;
    @Column(name = "h_index")
    private Integer hIndex;
    @Column(name = "url", length = 1000)
    private String url;
    @OneToMany(mappedBy = "idOrgParent")
    private Collection<Org> orgCollection;
    @JoinColumn(name = "idOrgParent", referencedColumnName = "idOrg")
    @ManyToOne
    private Org idOrgParent;
    @OneToMany(mappedBy = "idOrg")
    private Collection<Author> authorCollection;

    public Org() {
    }

    public Org(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getHIndex() {
        return hIndex;
    }

    public void setHIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Org> getOrgCollection() {
        return orgCollection;
    }

    public void setOrgCollection(Collection<Org> orgCollection) {
        this.orgCollection = orgCollection;
    }

    public Org getIdOrgParent() {
        return idOrgParent;
    }

    public void setIdOrgParent(Org idOrgParent) {
        this.idOrgParent = idOrgParent;
    }

    @XmlTransient
    public Collection<Author> getAuthorCollection() {
        return authorCollection;
    }

    public void setAuthorCollection(Collection<Author> authorCollection) {
        this.authorCollection = authorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrg != null ? idOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Org)) {
            return false;
        }
        Org other = (Org) object;
        if ((this.idOrg == null && other.idOrg != null) || (this.idOrg != null && !this.idOrg.equals(other.idOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Org[ idOrg=" + idOrg + " ]";
    }
    
}
