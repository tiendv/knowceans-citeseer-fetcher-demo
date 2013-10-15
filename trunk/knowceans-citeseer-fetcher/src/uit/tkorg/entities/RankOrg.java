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
@Table(name = "_rank_org", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankOrg.findAll", query = "SELECT r FROM RankOrg r"),
    @NamedQuery(name = "RankOrg.findByIdOrg", query = "SELECT r FROM RankOrg r WHERE r.idOrg = :idOrg"),
    @NamedQuery(name = "RankOrg.findByPublicationCount", query = "SELECT r FROM RankOrg r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankOrg.findByCitationCount", query = "SELECT r FROM RankOrg r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankOrg.findByRank", query = "SELECT r FROM RankOrg r WHERE r.rank = :rank"),
    @NamedQuery(name = "RankOrg.findByAuthorCount", query = "SELECT r FROM RankOrg r WHERE r.authorCount = :authorCount")})
public class RankOrg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrg", nullable = false)
    private Integer idOrg;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;
    @Column(name = "authorCount")
    private Integer authorCount;

    public RankOrg() {
    }

    public RankOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public RankOrg(Integer idOrg, int rank) {
        this.idOrg = idOrg;
        this.rank = rank;
    }

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
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
        hash += (idOrg != null ? idOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankOrg)) {
            return false;
        }
        RankOrg other = (RankOrg) object;
        if ((this.idOrg == null && other.idOrg != null) || (this.idOrg != null && !this.idOrg.equals(other.idOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankOrg[ idOrg=" + idOrg + " ]";
    }
    
}
