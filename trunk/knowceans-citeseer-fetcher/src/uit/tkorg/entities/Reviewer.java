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
@Table(name = "reviewer", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reviewer.findAll", query = "SELECT r FROM Reviewer r"),
    @NamedQuery(name = "Reviewer.findByIdReviewer", query = "SELECT r FROM Reviewer r WHERE r.idReviewer = :idReviewer"),
    @NamedQuery(name = "Reviewer.findByReviewerName", query = "SELECT r FROM Reviewer r WHERE r.reviewerName = :reviewerName"),
    @NamedQuery(name = "Reviewer.findByImage", query = "SELECT r FROM Reviewer r WHERE r.image = :image"),
    @NamedQuery(name = "Reviewer.findByEmailAddress", query = "SELECT r FROM Reviewer r WHERE r.emailAddress = :emailAddress"),
    @NamedQuery(name = "Reviewer.findByWebsite", query = "SELECT r FROM Reviewer r WHERE r.website = :website"),
    @NamedQuery(name = "Reviewer.findByOrganization", query = "SELECT r FROM Reviewer r WHERE r.organization = :organization")})
public class Reviewer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReviewer", nullable = false)
    private Integer idReviewer;
    @Column(name = "reviewerName", length = 1000)
    private String reviewerName;
    @Column(name = "image", length = 1000)
    private String image;
    @Column(name = "emailAddress", length = 1000)
    private String emailAddress;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "organization", length = 1000)
    private String organization;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
    private Collection<PaperReviewer> paperReviewerCollection;

    public Reviewer() {
    }

    public Reviewer(Integer idReviewer) {
        this.idReviewer = idReviewer;
    }

    public Integer getIdReviewer() {
        return idReviewer;
    }

    public void setIdReviewer(Integer idReviewer) {
        this.idReviewer = idReviewer;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
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
    public Collection<PaperReviewer> getPaperReviewerCollection() {
        return paperReviewerCollection;
    }

    public void setPaperReviewerCollection(Collection<PaperReviewer> paperReviewerCollection) {
        this.paperReviewerCollection = paperReviewerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReviewer != null ? idReviewer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reviewer)) {
            return false;
        }
        Reviewer other = (Reviewer) object;
        if ((this.idReviewer == null && other.idReviewer != null) || (this.idReviewer != null && !this.idReviewer.equals(other.idReviewer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Reviewer[ idReviewer=" + idReviewer + " ]";
    }
    
}
