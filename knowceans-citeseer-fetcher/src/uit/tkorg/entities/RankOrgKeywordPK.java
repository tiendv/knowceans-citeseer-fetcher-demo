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
public class RankOrgKeywordPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idOrg", nullable = false)
    private int idOrg;
    @Basic(optional = false)
    @Column(name = "idKeyword", nullable = false)
    private int idKeyword;

    public RankOrgKeywordPK() {
    }

    public RankOrgKeywordPK(int idOrg, int idKeyword) {
        this.idOrg = idOrg;
        this.idKeyword = idKeyword;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
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
        hash += (int) idOrg;
        hash += (int) idKeyword;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankOrgKeywordPK)) {
            return false;
        }
        RankOrgKeywordPK other = (RankOrgKeywordPK) object;
        if (this.idOrg != other.idOrg) {
            return false;
        }
        if (this.idKeyword != other.idKeyword) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankOrgKeywordPK[ idOrg=" + idOrg + ", idKeyword=" + idKeyword + " ]";
    }
    
}
