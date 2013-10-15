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
@Table(name = "_rank_org_keyword", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankOrgKeyword.findAll", query = "SELECT r FROM RankOrgKeyword r"),
    @NamedQuery(name = "RankOrgKeyword.findByIdOrg", query = "SELECT r FROM RankOrgKeyword r WHERE r.rankOrgKeywordPK.idOrg = :idOrg"),
    @NamedQuery(name = "RankOrgKeyword.findByIdKeyword", query = "SELECT r FROM RankOrgKeyword r WHERE r.rankOrgKeywordPK.idKeyword = :idKeyword"),
    @NamedQuery(name = "RankOrgKeyword.findByPublicationCount", query = "SELECT r FROM RankOrgKeyword r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankOrgKeyword.findByCitationCount", query = "SELECT r FROM RankOrgKeyword r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankOrgKeyword.findByRank", query = "SELECT r FROM RankOrgKeyword r WHERE r.rank = :rank"),
    @NamedQuery(name = "RankOrgKeyword.findByAuthorCount", query = "SELECT r FROM RankOrgKeyword r WHERE r.authorCount = :authorCount")})
public class RankOrgKeyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankOrgKeywordPK rankOrgKeywordPK;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;
    @Column(name = "authorCount")
    private Integer authorCount;

    public RankOrgKeyword() {
    }

    public RankOrgKeyword(RankOrgKeywordPK rankOrgKeywordPK) {
        this.rankOrgKeywordPK = rankOrgKeywordPK;
    }

    public RankOrgKeyword(RankOrgKeywordPK rankOrgKeywordPK, int rank) {
        this.rankOrgKeywordPK = rankOrgKeywordPK;
        this.rank = rank;
    }

    public RankOrgKeyword(int idOrg, int idKeyword) {
        this.rankOrgKeywordPK = new RankOrgKeywordPK(idOrg, idKeyword);
    }

    public RankOrgKeywordPK getRankOrgKeywordPK() {
        return rankOrgKeywordPK;
    }

    public void setRankOrgKeywordPK(RankOrgKeywordPK rankOrgKeywordPK) {
        this.rankOrgKeywordPK = rankOrgKeywordPK;
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

    public Integer getAuthorCount() {
        return authorCount;
    }

    public void setAuthorCount(Integer authorCount) {
        this.authorCount = authorCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rankOrgKeywordPK != null ? rankOrgKeywordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankOrgKeyword)) {
            return false;
        }
        RankOrgKeyword other = (RankOrgKeyword) object;
        if ((this.rankOrgKeywordPK == null && other.rankOrgKeywordPK != null) || (this.rankOrgKeywordPK != null && !this.rankOrgKeywordPK.equals(other.rankOrgKeywordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankOrgKeyword[ rankOrgKeywordPK=" + rankOrgKeywordPK + " ]";
    }
    
}
