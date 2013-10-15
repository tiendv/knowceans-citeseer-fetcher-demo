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
public class RankAuthorSubdomainPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idAuthor", nullable = false)
    private int idAuthor;
    @Basic(optional = false)
    @Column(name = "idSubdomain", nullable = false)
    private int idSubdomain;

    public RankAuthorSubdomainPK() {
    }

    public RankAuthorSubdomainPK(int idAuthor, int idSubdomain) {
        this.idAuthor = idAuthor;
        this.idSubdomain = idSubdomain;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
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
        hash += (int) idAuthor;
        hash += (int) idSubdomain;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankAuthorSubdomainPK)) {
            return false;
        }
        RankAuthorSubdomainPK other = (RankAuthorSubdomainPK) object;
        if (this.idAuthor != other.idAuthor) {
            return false;
        }
        if (this.idSubdomain != other.idSubdomain) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankAuthorSubdomainPK[ idAuthor=" + idAuthor + ", idSubdomain=" + idSubdomain + " ]";
    }
    
}
