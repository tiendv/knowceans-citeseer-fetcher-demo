/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiendv
 */
@Entity
@Table(name = "author", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
    @NamedQuery(name = "Author.findByIdAuthor", query = "SELECT a FROM Author a WHERE a.idAuthor = :idAuthor"),
    @NamedQuery(name = "Author.findByAuthorName", query = "SELECT a FROM Author a WHERE a.authorName = :authorName"),
    @NamedQuery(name = "Author.findByImage", query = "SELECT a FROM Author a WHERE a.image = :image"),
    @NamedQuery(name = "Author.findByEmailAddress", query = "SELECT a FROM Author a WHERE a.emailAddress = :emailAddress"),
    @NamedQuery(name = "Author.findByWebsite", query = "SELECT a FROM Author a WHERE a.website = :website"),
    @NamedQuery(name = "Author.findByHIndex", query = "SELECT a FROM Author a WHERE a.hIndex = :hIndex"),
    @NamedQuery(name = "Author.findByGIndex", query = "SELECT a FROM Author a WHERE a.gIndex = :gIndex"),
    @NamedQuery(name = "Author.findByUrl", query = "SELECT a FROM Author a WHERE a.url = :url")})
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAuthor", nullable = false)
    private Integer idAuthor;
    @Column(name = "authorName", length = 1000)
    private String authorName;
    @Column(name = "image", length = 1000)
    private String image;
    @Column(name = "emailAddress", length = 1000)
    private String emailAddress;
    @Column(name = "website", length = 1000)
    private String website;
    @Column(name = "h_index")
    private Integer hIndex;
    @Column(name = "g_index")
    private Integer gIndex;
    @Column(name = "url", length = 1000)
    private String url;
    @JoinTable(name = "author_paper", joinColumns = {
        @JoinColumn(name = "idAuthor", referencedColumnName = "idAuthor", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idPaper", referencedColumnName = "idPaper", nullable = false)})
    @ManyToMany
    private Collection<Paper> paperCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAuthor")
    private Collection<Authorinstance> authorinstanceCollection;
    @JoinColumn(name = "idOrg", referencedColumnName = "idOrg")
    @ManyToOne
    private Org idOrg;

    public Author() {
    }

    public Author(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getHIndex() {
        return hIndex;
    }

    public void setHIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }

    public Integer getGIndex() {
        return gIndex;
    }

    public void setGIndex(Integer gIndex) {
        this.gIndex = gIndex;
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

    @XmlTransient
    public Collection<Authorinstance> getAuthorinstanceCollection() {
        return authorinstanceCollection;
    }

    public void setAuthorinstanceCollection(Collection<Authorinstance> authorinstanceCollection) {
        this.authorinstanceCollection = authorinstanceCollection;
    }

    public Org getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Org idOrg) {
        this.idOrg = idOrg;
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
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.idAuthor == null && other.idAuthor != null) || (this.idAuthor != null && !this.idAuthor.equals(other.idAuthor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Author[ idAuthor=" + idAuthor + " ]";
    }
    
}
