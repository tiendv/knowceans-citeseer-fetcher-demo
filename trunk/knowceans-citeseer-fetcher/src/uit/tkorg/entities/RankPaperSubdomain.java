/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "_rank_paper_subdomain", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankPaperSubdomain.findAll", query = "SELECT r FROM RankPaperSubdomain r"),
    @NamedQuery(name = "RankPaperSubdomain.findByIdPaper", query = "SELECT r FROM RankPaperSubdomain r WHERE r.rankPaperSubdomainPK.idPaper = :idPaper"),
    @NamedQuery(name = "RankPaperSubdomain.findByIdSubdomain", query = "SELECT r FROM RankPaperSubdomain r WHERE r.rankPaperSubdomainPK.idSubdomain = :idSubdomain"),
    @NamedQuery(name = "RankPaperSubdomain.findByCitationCount", query = "SELECT r FROM RankPaperSubdomain r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankPaperSubdomain.findByRank", query = "SELECT r FROM RankPaperSubdomain r WHERE r.rank = :rank")})
public class RankPaperSubdomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankPaperSubdomainPK rankPaperSubdomainPK;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;

    public RankPaperSubdomain() {
    }

    public RankPaperSubdomain(RankPaperSubdomainPK rankPaperSubdomainPK) {
        this.rankPaperSubdomainPK = rankPaperSubdomainPK;
    }

    public RankPaperSubdomain(RankPaperSubdomainPK rankPaperSubdomainPK, int rank) {
        this.rankPaperSubdomainPK = rankPaperSubdomainPK;
        this.rank = rank;
    }

    public RankPaperSubdomain(int idPaper, int idSubdomain) {
        this.rankPaperSubdomainPK = new RankPaperSubdomainPK(idPaper, idSubdomain);
    }

    public RankPaperSubdomainPK getRankPaperSubdomainPK() {
        return rankPaperSubdomainPK;
    }

    public void setRankPaperSubdomainPK(RankPaperSubdomainPK rankPaperSubdomainPK) {
        this.rankPaperSubdomainPK = rankPaperSubdomainPK;
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
        hash += (rankPaperSubdomainPK != null ? rankPaperSubdomainPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankPaperSubdomain)) {
            return false;
        }
        RankPaperSubdomain other = (RankPaperSubdomain) object;
        if ((this.rankPaperSubdomainPK == null && other.rankPaperSubdomainPK != null) || (this.rankPaperSubdomainPK != null && !this.rankPaperSubdomainPK.equals(other.rankPaperSubdomainPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankPaperSubdomain[ rankPaperSubdomainPK=" + rankPaperSubdomainPK + " ]";
    }
    
}
