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
@Table(name = "_rank_org_subdomain", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankOrgSubdomain.findAll", query = "SELECT r FROM RankOrgSubdomain r"),
    @NamedQuery(name = "RankOrgSubdomain.findByIdOrg", query = "SELECT r FROM RankOrgSubdomain r WHERE r.rankOrgSubdomainPK.idOrg = :idOrg"),
    @NamedQuery(name = "RankOrgSubdomain.findByIdSubdomain", query = "SELECT r FROM RankOrgSubdomain r WHERE r.rankOrgSubdomainPK.idSubdomain = :idSubdomain"),
    @NamedQuery(name = "RankOrgSubdomain.findByPublicationCount", query = "SELECT r FROM RankOrgSubdomain r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankOrgSubdomain.findByCitationCount", query = "SELECT r FROM RankOrgSubdomain r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankOrgSubdomain.findByRank", query = "SELECT r FROM RankOrgSubdomain r WHERE r.rank = :rank"),
    @NamedQuery(name = "RankOrgSubdomain.findByAuthorCount", query = "SELECT r FROM RankOrgSubdomain r WHERE r.authorCount = :authorCount")})
public class RankOrgSubdomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankOrgSubdomainPK rankOrgSubdomainPK;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;
    @Column(name = "authorCount")
    private Integer authorCount;

    public RankOrgSubdomain() {
    }

    public RankOrgSubdomain(RankOrgSubdomainPK rankOrgSubdomainPK) {
        this.rankOrgSubdomainPK = rankOrgSubdomainPK;
    }

    public RankOrgSubdomain(RankOrgSubdomainPK rankOrgSubdomainPK, int rank) {
        this.rankOrgSubdomainPK = rankOrgSubdomainPK;
        this.rank = rank;
    }

    public RankOrgSubdomain(int idOrg, int idSubdomain) {
        this.rankOrgSubdomainPK = new RankOrgSubdomainPK(idOrg, idSubdomain);
    }

    public RankOrgSubdomainPK getRankOrgSubdomainPK() {
        return rankOrgSubdomainPK;
    }

    public void setRankOrgSubdomainPK(RankOrgSubdomainPK rankOrgSubdomainPK) {
        this.rankOrgSubdomainPK = rankOrgSubdomainPK;
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
        hash += (rankOrgSubdomainPK != null ? rankOrgSubdomainPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankOrgSubdomain)) {
            return false;
        }
        RankOrgSubdomain other = (RankOrgSubdomain) object;
        if ((this.rankOrgSubdomainPK == null && other.rankOrgSubdomainPK != null) || (this.rankOrgSubdomainPK != null && !this.rankOrgSubdomainPK.equals(other.rankOrgSubdomainPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankOrgSubdomain[ rankOrgSubdomainPK=" + rankOrgSubdomainPK + " ]";
    }
    
}
