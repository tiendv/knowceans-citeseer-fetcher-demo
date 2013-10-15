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
public class RankAuthorKeywordPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idAuthor", nullable = false)
    private int idAuthor;
    @Basic(optional = false)
    @Column(name = "idKeyword", nullable = false)
    private int idKeyword;

    public RankAuthorKeywordPK() {
    }

    public RankAuthorKeywordPK(int idAuthor, int idKeyword) {
        this.idAuthor = idAuthor;
        this.idKeyword = idKeyword;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
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
        hash += (int) idAuthor;
        hash += (int) idKeyword;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankAuthorKeywordPK)) {
            return false;
        }
        RankAuthorKeywordPK other = (RankAuthorKeywordPK) object;
        if (this.idAuthor != other.idAuthor) {
            return false;
        }
        if (this.idKeyword != other.idKeyword) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankAuthorKeywordPK[ idAuthor=" + idAuthor + ", idKeyword=" + idKeyword + " ]";
    }
    
}
