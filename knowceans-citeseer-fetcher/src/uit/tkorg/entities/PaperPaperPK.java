/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author tiendv
 */
@Embeddable
public class PaperPaperPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idPaper", nullable = false)
    private int idPaper;
    @Basic(optional = false)
    @Column(name = "idPaperRef", nullable = false)
    private int idPaperRef;

    public PaperPaperPK() {
    }

    public PaperPaperPK(int idPaper, int idPaperRef) {
        this.idPaper = idPaper;
        this.idPaperRef = idPaperRef;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdPaperRef() {
        return idPaperRef;
    }

    public void setIdPaperRef(int idPaperRef) {
        this.idPaperRef = idPaperRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPaper;
        hash += (int) idPaperRef;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaperPaperPK)) {
            return false;
        }
        PaperPaperPK other = (PaperPaperPK) object;
        if (this.idPaper != other.idPaper) {
            return false;
        }
        if (this.idPaperRef != other.idPaperRef) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.PaperPaperPK[ idPaper=" + idPaper + ", idPaperRef=" + idPaperRef + " ]";
    }
    
}
