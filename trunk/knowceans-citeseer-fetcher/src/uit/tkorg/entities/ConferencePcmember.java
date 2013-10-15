/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "conference_pcmember", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConferencePcmember.findAll", query = "SELECT c FROM ConferencePcmember c"),
    @NamedQuery(name = "ConferencePcmember.findByIdConference", query = "SELECT c FROM ConferencePcmember c WHERE c.conferencePcmemberPK.idConference = :idConference"),
    @NamedQuery(name = "ConferencePcmember.findByIdPCMember", query = "SELECT c FROM ConferencePcmember c WHERE c.conferencePcmemberPK.idPCMember = :idPCMember"),
    @NamedQuery(name = "ConferencePcmember.findByYear", query = "SELECT c FROM ConferencePcmember c WHERE c.year = :year")})
public class ConferencePcmember implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConferencePcmemberPK conferencePcmemberPK;
    @Column(name = "year")
    private Integer year;
    @JoinColumn(name = "idPCMember", referencedColumnName = "idPCMember", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pcmember pcmember;
    @JoinColumn(name = "idConference", referencedColumnName = "idConference", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Conference conference;

    public ConferencePcmember() {
    }

    public ConferencePcmember(ConferencePcmemberPK conferencePcmemberPK) {
        this.conferencePcmemberPK = conferencePcmemberPK;
    }

    public ConferencePcmember(int idConference, int idPCMember) {
        this.conferencePcmemberPK = new ConferencePcmemberPK(idConference, idPCMember);
    }

    public ConferencePcmemberPK getConferencePcmemberPK() {
        return conferencePcmemberPK;
    }

    public void setConferencePcmemberPK(ConferencePcmemberPK conferencePcmemberPK) {
        this.conferencePcmemberPK = conferencePcmemberPK;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Pcmember getPcmember() {
        return pcmember;
    }

    public void setPcmember(Pcmember pcmember) {
        this.pcmember = pcmember;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conferencePcmemberPK != null ? conferencePcmemberPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConferencePcmember)) {
            return false;
        }
        ConferencePcmember other = (ConferencePcmember) object;
        if ((this.conferencePcmemberPK == null && other.conferencePcmemberPK != null) || (this.conferencePcmemberPK != null && !this.conferencePcmemberPK.equals(other.conferencePcmemberPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.ConferencePcmember[ conferencePcmemberPK=" + conferencePcmemberPK + " ]";
    }
    
}
