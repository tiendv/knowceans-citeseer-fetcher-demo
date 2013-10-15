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
@Table(name = "paper_type", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaperType.findAll", query = "SELECT p FROM PaperType p"),
    @NamedQuery(name = "PaperType.findByIdPaperType", query = "SELECT p FROM PaperType p WHERE p.idPaperType = :idPaperType"),
    @NamedQuery(name = "PaperType.findByNameType", query = "SELECT p FROM PaperType p WHERE p.nameType = :nameType")})
public class PaperType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPaperType", nullable = false)
    private Integer idPaperType;
    @Column(name = "nameType", length = 1000)
    private String nameType;
    @OneToMany(mappedBy = "idPaperType")
    private Collection<Paper> paperCollection;

    public PaperType() {
    }

    public PaperType(Integer idPaperType) {
        this.idPaperType = idPaperType;
    }

    public Integer getIdPaperType() {
        return idPaperType;
    }

    public void setIdPaperType(Integer idPaperType) {
        this.idPaperType = idPaperType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
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
        hash += (idPaperType != null ? idPaperType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaperType)) {
            return false;
        }
        PaperType other = (PaperType) object;
        if ((this.idPaperType == null && other.idPaperType != null) || (this.idPaperType != null && !this.idPaperType.equals(other.idPaperType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.PaperType[ idPaperType=" + idPaperType + " ]";
    }
    
}
