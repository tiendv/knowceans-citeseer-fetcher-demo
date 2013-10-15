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
@Table(name = "_rank_author", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankAuthor.findAll", query = "SELECT r FROM RankAuthor r"),
    @NamedQuery(name = "RankAuthor.findByIdAuthor", query = "SELECT r FROM RankAuthor r WHERE r.idAuthor = :idAuthor"),
    @NamedQuery(name = "RankAuthor.findByPublicationCount", query = "SELECT r FROM RankAuthor r WHERE r.publicationCount = :publicationCount"),
    @NamedQuery(name = "RankAuthor.findByCitationCount", query = "SELECT r FROM RankAuthor r WHERE r.citationCount = :citationCount"),
    @NamedQuery(name = "RankAuthor.findByRank", query = "SELECT r FROM RankAuthor r WHERE r.rank = :rank"),
    @NamedQuery(name = "RankAuthor.findByCoAuthorCount", query = "SELECT r FROM RankAuthor r WHERE r.coAuthorCount = :coAuthorCount")})
public class RankAuthor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAuthor", nullable = false)
    private Integer idAuthor;
    @Column(name = "publicationCount")
    private Integer publicationCount;
    @Column(name = "citationCount")
    private Integer citationCount;
    @Basic(optional = false)
    @Column(name = "rank", nullable = false)
    private int rank;
    @Column(name = "coAuthorCount")
    private Integer coAuthorCount;

    public RankAuthor() {
    }

    public RankAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public RankAuthor(Integer idAuthor, int rank) {
        this.idAuthor = idAuthor;
        this.rank = rank;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
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
        hash += (idAuthor != null ? idAuthor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RankAuthor)) {
            return false;
        }
        RankAuthor other = (RankAuthor) object;
        if ((this.idAuthor == null && other.idAuthor != null) || (this.idAuthor != null && !this.idAuthor.equals(other.idAuthor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.RankAuthor[ idAuthor=" + idAuthor + " ]";
    }
    
}
