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
@Table(name = "_rank_author_keyword", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankAuthorKeyword.findAll", query = "SELECT r FROM RankAuthorKeyword r"),
    @NamedQuery(name = "RankAuthorKeyword.findByIdAuthor", query = "SELECT r FROM RankAuthorKeyword r WHERE r.rankAuthorKeywordPK.idAuthor = :idAuthor"),
    @NamedQuery(name = "RankAuthorKeyword.findByIdKeyword", query = "SELECT r FROM RankAuthorKeyword r WHERE r.rankAuthorKeywordPK.idKeyword = :idKeyword"),
    @NamedQuery(name = "RankAuthorKeyword.findByPublicationCount", query = "SELECT r FROM RankAuthorKeyword r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankAuthorKeyword.findByCitationCount", query = "SELECT r FROM RankAuthorKeyword r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankAuthorKeyword.findByRank", query = "SELECT r FROM RankAuthorKeyword r WHERE r.rank = :rank"),
    @NamedQuery(name = "RankAuthorKeyword.findByCoAuthorCount", query = "SELECT r FROM RankAuthorKeyword r WHERE r.coAuthorCount = :coAuthorCount")})
public class RankAuthorKeyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankAuthorKeywordPK rankAuthorKeywordPK;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;
    @Column(name = "coAuthorCount")
    private Integer coAuthorCount;

    public RankAuthorKeyword() {
    }

    public RankAuthorKeyword(RankAuthorKeywordPK rankAuthorKeywordPK) {
        this.rankAuthorKeywordPK = rankAuthorKeywordPK;
    }

    public RankAuthorKeyword(RankAuthorKeywordPK rankAuthorKeywordPK, int rank) {
        this.rankAuthorKeywordPK = rankAuthorKeywordPK;
        this.rank = rank;
    }

    public RankAuthorKeyword(int idAuthor, int idKeyword) {
        this.rankAuthorKeywordPK = new RankAuthorKeywordPK(idAuthor, idKeyword);
    }

    public RankAuthorKeywordPK getRankAuthorKeywordPK() {
        return rankAuthorKeywordPK;
    }

    public void setRankAuthorKeywordPK(RankAuthorKeywordPK rankAuthorKeywordPK) {
        this.rankAuthorKeywordPK = rankAuthorKeywordPK;
    }

    public Integer getPublicationCount() {
        return publicationCount;
    }

    public void setPublicationCount(Integer publicationCount) {
        this.publicationCount = publicationCount;
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

    public Integer getCoAuthorCount() {
        return coAuthorCount;
    }

    public void setCoAuthorCount(Integer coAuthorCount) {
        this.coAuthorCount = coAuthorCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rankAuthorKeywordPK != null ? rankAuthorKeywordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankAuthorKeyword)) {
            return false;
        }
        RankAuthorKeyword other = (RankAuthorKeyword) object;
        if ((this.rankAuthorKeywordPK == null && other.rankAuthorKeywordPK != null) || (this.rankAuthorKeywordPK != null && !this.rankAuthorKeywordPK.equals(other.rankAuthorKeywordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankAuthorKeyword[ rankAuthorKeywordPK=" + rankAuthorKeywordPK + " ]";
    }
    
}
