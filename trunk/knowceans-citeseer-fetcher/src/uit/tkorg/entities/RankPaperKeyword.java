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
@Table(name = "_rank_paper_keyword", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankPaperKeyword.findAll", query = "SELECT r FROM RankPaperKeyword r"),
    @NamedQuery(name = "RankPaperKeyword.findByIdPaper", query = "SELECT r FROM RankPaperKeyword r WHERE r.rankPaperKeywordPK.idPaper = :idPaper"),
    @NamedQuery(name = "RankPaperKeyword.findByIdKeyword", query = "SELECT r FROM RankPaperKeyword r WHERE r.rankPaperKeywordPK.idKeyword = :idKeyword"),
    @NamedQuery(name = "RankPaperKeyword.findByCitationCount", query = "SELECT r FROM RankPaperKeyword r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankPaperKeyword.findByRank", query = "SELECT r FROM RankPaperKeyword r WHERE r.rank = :rank")})
public class RankPaperKeyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankPaperKeywordPK rankPaperKeywordPK;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;

    public RankPaperKeyword() {
    }

    public RankPaperKeyword(RankPaperKeywordPK rankPaperKeywordPK) {
        this.rankPaperKeywordPK = rankPaperKeywordPK;
    }

    public RankPaperKeyword(RankPaperKeywordPK rankPaperKeywordPK, int rank) {
        this.rankPaperKeywordPK = rankPaperKeywordPK;
        this.rank = rank;
    }

    public RankPaperKeyword(int idPaper, int idKeyword) {
        this.rankPaperKeywordPK = new RankPaperKeywordPK(idPaper, idKeyword);
    }

    public RankPaperKeywordPK getRankPaperKeywordPK() {
        return rankPaperKeywordPK;
    }

    public void setRankPaperKeywordPK(RankPaperKeywordPK rankPaperKeywordPK) {
        this.rankPaperKeywordPK = rankPaperKeywordPK;
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
        hash += (rankPaperKeywordPK != null ? rankPaperKeywordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankPaperKeyword)) {
            return false;
        }
        RankPaperKeyword other = (RankPaperKeyword) object;
        if ((this.rankPaperKeywordPK == null && other.rankPaperKeywordPK != null) || (this.rankPaperKeywordPK != null && !this.rankPaperKeywordPK.equals(other.rankPaperKeywordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankPaperKeyword[ rankPaperKeywordPK=" + rankPaperKeywordPK + " ]";
    }
    
}
