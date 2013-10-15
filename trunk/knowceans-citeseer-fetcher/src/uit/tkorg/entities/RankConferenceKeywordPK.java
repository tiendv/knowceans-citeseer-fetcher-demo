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
public class RankConferenceKeywordPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idConference", nullable = false)
    private int idConference;
    @Basic(optional = false)
    @Column(name = "idKeyword", nullable = false)
    private int idKeyword;

    public RankConferenceKeywordPK() {
    }

    public RankConferenceKeywordPK(int idConference, int idKeyword) {
        this.idConference = idConference;
        this.idKeyword = idKeyword;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
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
        hash += (int) idConference;
        hash += (int) idKeyword;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankConferenceKeywordPK)) {
            return false;
        }
        RankConferenceKeywordPK other = (RankConferenceKeywordPK) object;
        if (this.idConference != other.idConference) {
            return false;
        }
        if (this.idKeyword != other.idKeyword) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankConferenceKeywordPK[ idConference=" + idConference + ", idKeyword=" + idKeyword + " ]";
    }
    
}
