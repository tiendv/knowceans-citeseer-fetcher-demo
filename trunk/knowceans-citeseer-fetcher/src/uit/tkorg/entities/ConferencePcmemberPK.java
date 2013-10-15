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
public class ConferencePcmemberPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idConference", nullable = false)
    private int idConference;
    @Basic(optional = false)
    @Column(name = "idPCMember", nullable = false)
    private int idPCMember;

    public ConferencePcmemberPK() {
    }

    public ConferencePcmemberPK(int idConference, int idPCMember) {
        this.idConference = idConference;
        this.idPCMember = idPCMember;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }

    public int getIdPCMember() {
        return idPCMember;
    }

    public void setIdPCMember(int idPCMember) {
        this.idPCMember = idPCMember;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConference;
        hash += (int) idPCMember;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConferencePcmemberPK)) {
            return false;
        }
        ConferencePcmemberPK other = (ConferencePcmemberPK) object;
        if (this.idConference != other.idConference) {
            return false;
        }
        if (this.idPCMember != other.idPCMember) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.ConferencePcmemberPK[ idConference=" + idConference + ", idPCMember=" + idPCMember + " ]";
    }
    
}
