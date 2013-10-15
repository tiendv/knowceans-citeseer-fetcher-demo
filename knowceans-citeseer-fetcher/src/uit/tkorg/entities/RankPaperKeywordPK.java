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
public class RankPaperKeywordPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idPaper", nullable = false)
    private int idPaper;
    @Basic(optional = false)
    @Column(name = "idKeyword", nullable = false)
    private int idKeyword;

    public RankPaperKeywordPK() {
    }

    public RankPaperKeywordPK(int idPaper, int idKeyword) {
        this.idPaper = idPaper;
        this.idKeyword = idKeyword;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    public int getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(int idKeyword) {
        this.idKeyword = idKeyword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPaper;
        hash += (int) idKeyword;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankPaperKeywordPK)) {
            return false;
        }
        RankPaperKeywordPK other = (RankPaperKeywordPK) object;
        if (this.idPaper != other.idPaper) {
            return false;
        }
        if (this.idKeyword != other.idKeyword) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankPaperKeywordPK[ idPaper=" + idPaper + ", idKeyword=" + idKeyword + " ]";
    }
    
}
