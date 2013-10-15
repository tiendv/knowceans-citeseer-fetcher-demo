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
@Table(name = "_rank_author_subdomain", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankAuthorSubdomain.findAll", query = "SELECT r FROM RankAuthorSubdomain r"),
    @NamedQuery(name = "RankAuthorSubdomain.findByIdAuthor", query = "SELECT r FROM RankAuthorSubdomain r WHERE r.rankAuthorSubdomainPK.idAuthor = :idAuthor"),
    @NamedQuery(name = "RankAuthorSubdomain.findByIdSubdomain", query = "SELECT r FROM RankAuthorSubdomain r WHERE r.rankAuthorSubdomainPK.idSubdomain = :idSubdomain"),
    @NamedQuery(name = "RankAuthorSubdomain.findByPublicationCount", query = "SELECT r FROM RankAuthorSubdomain r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankAuthorSubdomain.findByCitationCount", query = "SELECT r FROM RankAuthorSubdomain r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankAuthorSubdomain.findByRank", query = "SELECT r FROM RankAuthorSubdomain r WHERE r.rank = :rank"),
    @NamedQuery(name = "RankAuthorSubdomain.findByCoAuthorCount", query = "SELECT r FROM RankAuthorSubdomain r WHERE r.coAuthorCount = :coAuthorCount")})
public class RankAuthorSubdomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankAuthorSubdomainPK rankAuthorSubdomainPK;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;
    @Column(name = "coAuthorCount")
    private Integer coAuthorCount;

    public RankAuthorSubdomain() {
    }

    public RankAuthorSubdomain(RankAuthorSubdomainPK rankAuthorSubdomainPK) {
        this.rankAuthorSubdomainPK = rankAuthorSubdomainPK;
    }

    public RankAuthorSubdomain(RankAuthorSubdomainPK rankAuthorSubdomainPK, int rank) {
        this.rankAuthorSubdomainPK = rankAuthorSubdomainPK;
        this.rank = rank;
    }

    public RankAuthorSubdomain(int idAuthor, int idSubdomain) {
        this.rankAuthorSubdomainPK = new RankAuthorSubdomainPK(idAuthor, idSubdomain);
    }

    public RankAuthorSubdomainPK getRankAuthorSubdomainPK() {
        return rankAuthorSubdomainPK;
    }

    public void setRankAuthorSubdomainPK(RankAuthorSubdomainPK rankAuthorSubdomainPK) {
        this.rankAuthorSubdomainPK = rankAuthorSubdomainPK;
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
        hash += (rankAuthorSubdomainPK != null ? rankAuthorSubdomainPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankAuthorSubdomain)) {
            return false;
        }
        RankAuthorSubdomain other = (RankAuthorSubdomain) object;
        if ((this.rankAuthorSubdomainPK == null && other.rankAuthorSubdomainPK != null) || (this.rankAuthorSubdomainPK != null && !this.rankAuthorSubdomainPK.equals(other.rankAuthorSubdomainPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankAuthorSubdomain[ rankAuthorSubdomainPK=" + rankAuthorSubdomainPK + " ]";
    }
    
}
