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
@Table(name = "authorinstance", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authorinstance.findAll", query = "SELECT a FROM Authorinstance a"),
    @NamedQuery(name = "Authorinstance.findByAutoID", query = "SELECT a FROM Authorinstance a WHERE a.autoID = :autoID"),
    @NamedQuery(name = "Authorinstance.findByInstanceName", query = "SELECT a FROM Authorinstance a WHERE a.instanceName = :instanceName")})
public class Authorinstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "autoID", nullable = false)
    private Integer autoID;
    @Column(name = "instanceName", length = 1000)
    private String instanceName;
    @JoinColumn(name = "idAuthor", referencedColumnName = "idAuthor", nullable = false)
    @ManyToOne(optional = false)
    private Author idAuthor;

    public Authorinstance() {
    }

    public Authorinstance(Integer autoID) {
        this.autoID = autoID;
    }

    public Integer getAutoID() {
        return autoID;
    }

    public void setAutoID(Integer autoID) {
        this.autoID = autoID;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Author getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Author idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoID != null ? autoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authorinstance)) {
            return false;
        }
        Authorinstance other = (Authorinstance) object;
        if ((this.autoID == null && other.autoID != null) || (this.autoID != null && !this.autoID.equals(other.autoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Authorinstance[ autoID=" + autoID + " ]";
    }
    
}
