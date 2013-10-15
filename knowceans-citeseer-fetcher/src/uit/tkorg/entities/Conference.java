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
@Table(name = "conference", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conference.findAll", query = "SELECT c FROM Conference c"),
    @NamedQuery(name = "Conference.findByIdConference", query = "SELECT c FROM Conference c WHERE c.idConference = :idConference"),
    @NamedQuery(name = "Conference.findByConferenceName", query = "SELECT c FROM Conference c WHERE c.conferenceName = :conferenceName"),
    @NamedQuery(name = "Conference.findByWebsite", query = "SELECT c FROM Conference c WHERE c.website = :website"),
    @NamedQuery(name = "Conference.findByOrganization", query = "SELECT c FROM Conference c WHERE c.organization = :organization"),
    @NamedQuery(name = "Conference.findByOrganizedLocation", query = "SELECT c FROM Conference c WHERE c.organizedLocation = :organizedLocation"),
    @NamedQuery(name = "Conference.findByDuration", query = "SELECT c FROM Conference c WHERE c.duration = :duration"),
    @NamedQuery(name = "Conference.findByYearStart", query = "SELECT c FROM Conference c WHERE c.yearStart = :yearStart"),
    @NamedQuery(name = "Conference.findByYearEnd", query = "SELECT c FROM Conference c WHERE c.yearEnd = :yearEnd"),
    @NamedQuery(name = "Conference.findByUrl", query = "SELECT c FROM Conference c WHERE c.url = :url")})
public class Conference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConference", nullable = false)
    private Integer idConference;
    @Column(name = "conferenceName", length = 1000)
    private String conferenceName;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "organization", length = 1000)
    private String organization;
    @Column(name = "organizedLocation", length = 1000)
    private String organizedLocation;
    @Column(name = "duration", length = 1000)
    private String duration;
    @Column(name = "yearStart")
    private Integer yearStart;
    @Column(name = "yearEnd")
    private Integer yearEnd;
    @Column(name = "url", length = 1000)
    private String url;
    @OneToMany(mappedBy = "idConference")
    private Collection<Paper> paperCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference")
    private Collection<ConferencePcmember> conferencePcmemberCollection;

    public Conference() {
    }

    public Conference(Integer idConference) {
        this.idConference = idConference;
    }

    public Integer getIdConference() {
        return idConference;
    }

    public void setIdConference(Integer idConference) {
        this.idConference = idConference;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
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

    public String getOrganizedLocation() {
        return organizedLocation;
    }

    public void setOrganizedLocation(String organizedLocation) {
        this.organizedLocation = organizedLocation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Paper> getPaperCollection() {
        return paperCollection;
    }

    public void setPaperCollection(Collection<Paper> paperCollection) {
        this.paperCollection = paperCollection;
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
        hash += (idConference != null ? idConference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conference)) {
            return false;
        }
        Conference other = (Conference) object;
        if ((this.idConference == null && other.idConference != null) || (this.idConference != null && !this.idConference.equals(other.idConference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Conference[ idConference=" + idConference + " ]";
    }
    
}
