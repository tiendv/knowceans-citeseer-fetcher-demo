/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "journal", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Journal.findAll", query = "SELECT j FROM Journal j"),
    @NamedQuery(name = "Journal.findByIdJournal", query = "SELECT j FROM Journal j WHERE j.idJournal = :idJournal"),
    @NamedQuery(name = "Journal.findByJournalName", query = "SELECT j FROM Journal j WHERE j.journalName = :journalName"),
    @NamedQuery(name = "Journal.findByWebsite", query = "SELECT j FROM Journal j WHERE j.website = :website"),
    @NamedQuery(name = "Journal.findByYearStart", query = "SELECT j FROM Journal j WHERE j.yearStart = :yearStart"),
    @NamedQuery(name = "Journal.findByYearEnd", query = "SELECT j FROM Journal j WHERE j.yearEnd = :yearEnd"),
    @NamedQuery(name = "Journal.findByOrganization", query = "SELECT j FROM Journal j WHERE j.organization = :organization"),
    @NamedQuery(name = "Journal.findByUrl", query = "SELECT j FROM Journal j WHERE j.url = :url")})
public class Journal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idJournal", nullable = false)
    private Integer idJournal;
    @Column(name = "journalName", length = 1000)
    private String journalName;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "yearStart")
    private Integer yearStart;
    @Column(name = "yearEnd")
    private Integer yearEnd;
    @Column(name = "organization", length = 1000)
    private String organization;
    @Column(name = "url", length = 1000)
    private String url;
    @OneToMany(mappedBy = "idJournal")
    private Collection<Paper> paperCollection;

    public Journal() {
    }

    public Journal(Integer idJournal) {
        this.idJournal = idJournal;
    }

    public Integer getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Integer idJournal) {
        this.idJournal = idJournal;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJournal != null ? idJournal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Journal)) {
            return false;
        }
        Journal other = (Journal) object;
        if ((this.idJournal == null && other.idJournal != null) || (this.idJournal != null && !this.idJournal.equals(other.idJournal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Journal[ idJournal=" + idJournal + " ]";
    }
    
}
