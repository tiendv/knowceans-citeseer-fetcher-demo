/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "subdomain", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subdomain.findAll", query = "SELECT s FROM Subdomain s"),
    @NamedQuery(name = "Subdomain.findByIdSubdomain", query = "SELECT s FROM Subdomain s WHERE s.idSubdomain = :idSubdomain"),
    @NamedQuery(name = "Subdomain.findBySubdomainName", query = "SELECT s FROM Subdomain s WHERE s.subdomainName = :subdomainName")})
public class Subdomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSubdomain", nullable = false)
    private Integer idSubdomain;
    @Column(name = "subdomainName", length = 1000)
    private String subdomainName;
    @JoinColumn(name = "idDomain", referencedColumnName = "idDomain", nullable = false)
    @ManyToOne(optional = false)
    private Domain idDomain;

    public Subdomain() {
    }

    public Subdomain(Integer idSubdomain) {
        this.idSubdomain = idSubdomain;
    }

    public Integer getIdSubdomain() {
        return idSubdomain;
    }

    public void setIdSubdomain(Integer idSubdomain) {
        this.idSubdomain = idSubdomain;
    }

    public String getSubdomainName() {
        return subdomainName;
    }

    public void setSubdomainName(String subdomainName) {
        this.subdomainName = subdomainName;
    }

    public Domain getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(Domain idDomain) {
        this.idDomain = idDomain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubdomain != null ? idSubdomain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subdomain)) {
            return false;
        }
        Subdomain other = (Subdomain) object;
        if ((this.idSubdomain == null && other.idSubdomain != null) || (this.idSubdomain != null && !this.idSubdomain.equals(other.idSubdomain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Subdomain[ idSubdomain=" + idSubdomain + " ]";
    }
    
}
