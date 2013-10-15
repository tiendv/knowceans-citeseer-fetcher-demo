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
public class RankPaperSubdomainPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idPaper", nullable = false)
    private int idPaper;
    @Basic(optional = false)
    @Column(name = "idSubdomain", nullable = false)
    private int idSubdomain;

    public RankPaperSubdomainPK() {
    }

    public RankPaperSubdomainPK(int idPaper, int idSubdomain) {
        this.idPaper = idPaper;
        this.idSubdomain = idSubdomain;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdSubdomain() {
        return idSubdomain;
    }

    public void setIdSubdomain(int idSubdomain) {
        this.idSubdomain = idSubdomain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPaper;
        hash += (int) idSubdomain;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankPaperSubdomainPK)) {
            return false;
        }
        RankPaperSubdomainPK other = (RankPaperSubdomainPK) object;
        if (this.idPaper != other.idPaper) {
            return false;
        }
        if (this.idSubdomain != other.idSubdomain) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankPaperSubdomainPK[ idPaper=" + idPaper + ", idSubdomain=" + idSubdomain + " ]";
    }
    
}
