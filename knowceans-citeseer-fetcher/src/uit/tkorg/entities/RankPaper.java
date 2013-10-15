/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "_rank_paper", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankPaper.findAll", query = "SELECT r FROM RankPaper r"),
    @NamedQuery(name = "RankPaper.findByIdPaper", query = "SELECT r FROM RankPaper r WHERE r.idPaper = :idPaper"),
    @NamedQuery(name = "RankPaper.findByCitationCount", query = "SELECT r FROM RankPaper r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankPaper.findByRank", query = "SELECT r FROM RankPaper r WHERE r.rank = :rank")})
public class RankPaper implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPaper", nullable = false)
    private Integer idPaper;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;

    public RankPaper() {
    }

    public RankPaper(Integer idPaper) {
        this.idPaper = idPaper;
    }

    public RankPaper(Integer idPaper, int rank) {
        this.idPaper = idPaper;
        this.rank = rank;
    }

    public Integer getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(Integer idPaper) {
        this.idPaper = idPaper;
    }

    public Integer getCitationCount() {
        return citationCount;
    }

    public void setCitationCount(Integer citationCount) {
        this.citationCount = citationCount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaper != null ? idPaper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankPaper)) {
            return false;
        }
        RankPaper other = (RankPaper) object;
        if ((this.idPaper == null && other.idPaper != null) || (this.idPaper != null && !this.idPaper.equals(other.idPaper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankPaper[ idPaper=" + idPaper + " ]";
    }
    
}
