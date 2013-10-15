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
@Table(name = "_rank_conference_keyword", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankConferenceKeyword.findAll", query = "SELECT r FROM RankConferenceKeyword r"),
    @NamedQuery(name = "RankConferenceKeyword.findByIdConference", query = "SELECT r FROM RankConferenceKeyword r WHERE r.rankConferenceKeywordPK.idConference = :idConference"),
    @NamedQuery(name = "RankConferenceKeyword.findByIdKeyword", query = "SELECT r FROM RankConferenceKeyword r WHERE r.rankConferenceKeywordPK.idKeyword = :idKeyword"),
    @NamedQuery(name = "RankConferenceKeyword.findByPublicationCount", query = "SELECT r FROM RankConferenceKeyword r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankConferenceKeyword.findByCitationCount", query = "SELECT r FROM RankConferenceKeyword r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankConferenceKeyword.findByRank", query = "SELECT r FROM RankConferenceKeyword r WHERE r.rank = :rank")})
public class RankConferenceKeyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankConferenceKeywordPK rankConferenceKeywordPK;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;

    public RankConferenceKeyword() {
    }

    public RankConferenceKeyword(RankConferenceKeywordPK rankConferenceKeywordPK) {
        this.rankConferenceKeywordPK = rankConferenceKeywordPK;
    }

    public RankConferenceKeyword(RankConferenceKeywordPK rankConferenceKeywordPK, int rank) {
        this.rankConferenceKeywordPK = rankConferenceKeywordPK;
        this.rank = rank;
    }

    public RankConferenceKeyword(int idConference, int idKeyword) {
        this.rankConferenceKeywordPK = new RankConferenceKeywordPK(idConference, idKeyword);
    }

    public RankConferenceKeywordPK getRankConferenceKeywordPK() {
        return rankConferenceKeywordPK;
    }

    public void setRankConferenceKeywordPK(RankConferenceKeywordPK rankConferenceKeywordPK) {
        this.rankConferenceKeywordPK = rankConferenceKeywordPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rankConferenceKeywordPK != null ? rankConferenceKeywordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankConferenceKeyword)) {
            return false;
        }
        RankConferenceKeyword other = (RankConferenceKeyword) object;
        if ((this.rankConferenceKeywordPK == null && other.rankConferenceKeywordPK != null) || (this.rankConferenceKeywordPK != null && !this.rankConferenceKeywordPK.equals(other.rankConferenceKeywordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankConferenceKeyword[ rankConferenceKeywordPK=" + rankConferenceKeywordPK + " ]";
    }
    
}
