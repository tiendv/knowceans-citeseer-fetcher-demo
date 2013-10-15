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
public class RankOrgSubdomainPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idOrg", nullable = false)
    private int idOrg;
    @Basic(optional = false)
    @Column(name = "idSubdomain", nullable = false)
    private int idSubdomain;

    public RankOrgSubdomainPK() {
    }

    public RankOrgSubdomainPK(int idOrg, int idSubdomain) {
        this.idOrg = idOrg;
        this.idSubdomain = idSubdomain;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
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
        hash += (int) idOrg;
        hash += (int) idSubdomain;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankOrgSubdomainPK)) {
            return false;
        }
        RankOrgSubdomainPK other = (RankOrgSubdomainPK) object;
        if (this.idOrg != other.idOrg) {
            return false;
        }
        if (this.idSubdomain != other.idSubdomain) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankOrgSubdomainPK[ idOrg=" + idOrg + ", idSubdomain=" + idSubdomain + " ]";
    }
    
}
