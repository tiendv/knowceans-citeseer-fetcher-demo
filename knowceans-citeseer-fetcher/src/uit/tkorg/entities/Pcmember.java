/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "pcmember", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pcmember.findAll", query = "SELECT p FROM Pcmember p"),
    @NamedQuery(name = "Pcmember.findByIdPCMember", query = "SELECT p FROM Pcmember p WHERE p.idPCMember = :idPCMember"),
    @NamedQuery(name = "Pcmember.findByPcMemberName", query = "SELECT p FROM Pcmember p WHERE p.pcMemberName = :pcMemberName"),
    @NamedQuery(name = "Pcmember.findByImage", query = "SELECT p FROM Pcmember p WHERE p.image = :image"),
    @NamedQuery(name = "Pcmember.findByEmailAddress", query = "SELECT p FROM Pcmember p WHERE p.emailAddress = :emailAddress"),
    @NamedQuery(name = "Pcmember.findByWebsite", query = "SELECT p FROM Pcmember p WHERE p.website = :website"),
    @NamedQuery(name = "Pcmember.findByOrganization", query = "SELECT p FROM Pcmember p WHERE p.organization = :organization")})
public class Pcmember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPCMember", nullable = false)
    private Integer idPCMember;
    @Column(name = "pcMemberName", length = 1000)
    private String pcMemberName;
    @Column(name = "image", length = 1000)
    private String image;
    @Column(name = "emailAddress", length = 1000)
    private String emailAddress;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "organization", length = 1000)
    private String organization;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pcmember")
    private Collection<ConferencePcmember> conferencePcmemberCollection;

    public Pcmember() {
    }

    public Pcmember(Integer idPCMember) {
        this.idPCMember = idPCMember;
    }

    public Integer getIdPCMember() {
        return idPCMember;
    }

    public void setIdPCMember(Integer idPCMember) {
        this.idPCMember = idPCMember;
    }

    public String getPcMemberName() {
        return pcMemberName;
    }

    public void setPcMemberName(String pcMemberName) {
        this.pcMemberName = pcMemberName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @XmlTransient
    public Collection<ConferencePcmember> getConferencePcmemberCollection() {
        return conferencePcmemberCollection;
    }

    public void setConferencePcmemberCollection(Collection<ConferencePcmember> conferencePcmemberCollection) {
        this.conferencePcmemberCollection = conferencePcmemberCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPCMember != null ? idPCMember.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pcmember)) {
            return false;
        }
        Pcmember other = (Pcmember) object;
        if ((this.idPCMember == null && other.idPCMember != null) || (this.idPCMember != null && !this.idPCMember.equals(other.idPCMember))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Pcmember[ idPCMember=" + idPCMember + " ]";
    }
    
}
