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
@Table(name = "paper_paper", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaperPaper.findAll", query = "SELECT p FROM PaperPaper p"),
    @NamedQuery(name = "PaperPaper.findByIdPaper", query = "SELECT p FROM PaperPaper p WHERE p.paperPaperPK.idPaper = :idPaper"),
    @NamedQuery(name = "PaperPaper.findByIdPaperRef", query = "SELECT p FROM PaperPaper p WHERE p.paperPaperPK.idPaperRef = :idPaperRef"),
    @NamedQuery(name = "PaperPaper.findByCitationContext", query = "SELECT p FROM PaperPaper p WHERE p.citationContext = :citationContext")})
public class PaperPaper implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PaperPaperPK paperPaperPK;
    @Column(name = "citationContext", length = 20000)
    private String citationContext;
    @JoinColumn(name = "idPaperRef", referencedColumnName = "idPaper", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paper paper;
    @JoinColumn(name = "idPaper", referencedColumnName = "idPaper", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paper paper1;

    public PaperPaper() {
    }

    public PaperPaper(PaperPaperPK paperPaperPK) {
        this.paperPaperPK = paperPaperPK;
    }

    public PaperPaper(int idPaper, int idPaperRef) {
        this.paperPaperPK = new PaperPaperPK(idPaper, idPaperRef);
    }

    public PaperPaperPK getPaperPaperPK() {
        return paperPaperPK;
    }

    public void setPaperPaperPK(PaperPaperPK paperPaperPK) {
        this.paperPaperPK = paperPaperPK;
    }

    public String getCitationContext() {
        return citationContext;
    }

    public void setCitationContext(String citationContext) {
        this.citationContext = citationContext;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Paper getPaper1() {
        return paper1;
    }

    public void setPaper1(Paper paper1) {
        this.paper1 = paper1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paperPaperPK != null ? paperPaperPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaperPaper)) {
            return false;
        }
        PaperPaper other = (PaperPaper) object;
        if ((this.paperPaperPK == null && other.paperPaperPK != null) || (this.paperPaperPK != null && !this.paperPaperPK.equals(other.paperPaperPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.PaperPaper[ paperPaperPK=" + paperPaperPK + " ]";
    }
    
}
