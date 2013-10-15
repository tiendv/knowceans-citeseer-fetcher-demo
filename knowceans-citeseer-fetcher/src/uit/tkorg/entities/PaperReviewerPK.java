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
public class PaperReviewerPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idPaper", nullable = false)
    private int idPaper;
    @Basic(optional = false)
    @Column(name = "idReviewer", nullable = false)
    private int idReviewer;

    public PaperReviewerPK() {
    }

    public PaperReviewerPK(int idPaper, int idReviewer) {
        this.idPaper = idPaper;
        this.idReviewer = idReviewer;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public void setIdReviewer(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPaper;
        hash += (int) idReviewer;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaperReviewerPK)) {
            return false;
        }
        PaperReviewerPK other = (PaperReviewerPK) object;
        if (this.idPaper != other.idPaper) {
            return false;
        }
        if (this.idReviewer != other.idReviewer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.PaperReviewerPK[ idPaper=" + idPaper + ", idReviewer=" + idReviewer + " ]";
    }
    
}
