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
@Table(name = "paper_reviewer", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaperReviewer.findAll", query = "SELECT p FROM PaperReviewer p"),
    @NamedQuery(name = "PaperReviewer.findByIdPaper", query = "SELECT p FROM PaperReviewer p WHERE p.paperReviewerPK.idPaper = :idPaper"),
    @NamedQuery(name = "PaperReviewer.findByIdReviewer", query = "SELECT p FROM PaperReviewer p WHERE p.paperReviewerPK.idReviewer = :idReviewer"),
    @NamedQuery(name = "PaperReviewer.findByRating", query = "SELECT p FROM PaperReviewer p WHERE p.rating = :rating"),
    @NamedQuery(name = "PaperReviewer.findByContent", query = "SELECT p FROM PaperReviewer p WHERE p.content = :content")})
public class PaperReviewer implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PaperReviewerPK paperReviewerPK;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "content", length = 20000)
    private String content;
    @JoinColumn(name = "idReviewer", referencedColumnName = "idReviewer", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reviewer reviewer;
    @JoinColumn(name = "idPaper", referencedColumnName = "idPaper", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paper paper;

    public PaperReviewer() {
    }

    public PaperReviewer(PaperReviewerPK paperReviewerPK) {
        this.paperReviewerPK = paperReviewerPK;
    }

    public PaperReviewer(int idPaper, int idReviewer) {
        this.paperReviewerPK = new PaperReviewerPK(idPaper, idReviewer);
    }

    public PaperReviewerPK getPaperReviewerPK() {
        return paperReviewerPK;
    }

    public void setPaperReviewerPK(PaperReviewerPK paperReviewerPK) {
        this.paperReviewerPK = paperReviewerPK;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paperReviewerPK != null ? paperReviewerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaperReviewer)) {
            return false;
        }
        PaperReviewer other = (PaperReviewer) object;
        if ((this.paperReviewerPK == null && other.paperReviewerPK != null) || (this.paperReviewerPK != null && !this.paperReviewerPK.equals(other.paperReviewerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.PaperReviewer[ paperReviewerPK=" + paperReviewerPK + " ]";
    }
    
}
