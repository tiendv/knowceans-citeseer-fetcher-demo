/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "keyword", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keyword.findAll", query = "SELECT k FROM Keyword k"),
    @NamedQuery(name = "Keyword.findByIdKeyword", query = "SELECT k FROM Keyword k WHERE k.idKeyword = :idKeyword"),
    @NamedQuery(name = "Keyword.findByKeyword", query = "SELECT k FROM Keyword k WHERE k.keyword = :keyword"),
    @NamedQuery(name = "Keyword.findByStemmingVariations", query = "SELECT k FROM Keyword k WHERE k.stemmingVariations = :stemmingVariations"),
    @NamedQuery(name = "Keyword.findByUrl", query = "SELECT k FROM Keyword k WHERE k.url = :url")})
public class Keyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKeyword", nullable = false)
    private Integer idKeyword;
    @Column(name = "keyword", length = 1000)
    private String keyword;
    @Column(name = "stemmingVariations", length = 10000)
    private String stemmingVariations;
    @Column(name = "url", length = 1000)
    private String url;
    @JoinTable(name = "paper_keyword", joinColumns = {
        @JoinColumn(name = "idKeyword", referencedColumnName = "idKeyword", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idPaper", referencedColumnName = "idPaper", nullable = false)})
    @ManyToMany
    private Collection<Paper> paperCollection;

    public Keyword() {
    }

    public Keyword(Integer idKeyword) {
        this.idKeyword = idKeyword;
    }

    public Integer getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(Integer idKeyword) {
        this.idKeyword = idKeyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStemmingVariations() {
        return stemmingVariations;
    }

    public void setStemmingVariations(String stemmingVariations) {
        this.stemmingVariations = stemmingVariations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Paper> getPaperCollection() {
        return paperCollection;
    }

    public void setPaperCollection(Collection<Paper> paperCollection) {
        this.paperCollection = paperCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKeyword != null ? idKeyword.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Keyword)) {
            return false;
        }
        Keyword other = (Keyword) object;
        if ((this.idKeyword == null && other.idKeyword != null) || (this.idKeyword != null && !this.idKeyword.equals(other.idKeyword))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Keyword[ idKeyword=" + idKeyword + " ]";
    }
    
}
