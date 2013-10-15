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
import javax.persistence.Lob;
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
@Table(name = "paper", catalog = "publicationguru", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paper.findAll", query = "SELECT p FROM Paper p"),
    @NamedQuery(name = "Paper.findByIdPaper", query = "SELECT p FROM Paper p WHERE p.idPaper = :idPaper"),
    @NamedQuery(name = "Paper.findByDoi", query = "SELECT p FROM Paper p WHERE p.doi = :doi"),
    @NamedQuery(name = "Paper.findByIsbn", query = "SELECT p FROM Paper p WHERE p.isbn = :isbn"),
    @NamedQuery(name = "Paper.findByUrl", query = "SELECT p FROM Paper p WHERE p.url = :url"),
    @NamedQuery(name = "Paper.findByTitle", query = "SELECT p FROM Paper p WHERE p.title = :title"),
    @NamedQuery(name = "Paper.findByAbstract1", query = "SELECT p FROM Paper p WHERE p.abstract1 = :abstract1"),
    @NamedQuery(name = "Paper.findByVolume", query = "SELECT p FROM Paper p WHERE p.volume = :volume"),
    @NamedQuery(name = "Paper.findByPages", query = "SELECT p FROM Paper p WHERE p.pages = :pages"),
    @NamedQuery(name = "Paper.findByYear", query = "SELECT p FROM Paper p WHERE p.year = :year"),
    @NamedQuery(name = "Paper.findByBibTex", query = "SELECT p FROM Paper p WHERE p.bibTex = :bibTex"),
    @NamedQuery(name = "Paper.findByEndNote", query = "SELECT p FROM Paper p WHERE p.endNote = :endNote"),
    @NamedQuery(name = "Paper.findByDblpKey", query = "SELECT p FROM Paper p WHERE p.dblpKey = :dblpKey"),
    @NamedQuery(name = "Paper.findByVersion", query = "SELECT p FROM Paper p WHERE p.version = :version"),
    @NamedQuery(name = "Paper.findByPaperFile", query = "SELECT p FROM Paper p WHERE p.paperFile = :paperFile"),
    @NamedQuery(name = "Paper.findByAdress", query = "SELECT p FROM Paper p WHERE p.adress = :adress"),
    @NamedQuery(name = "Paper.findByNumber", query = "SELECT p FROM Paper p WHERE p.number = :number"),
    @NamedQuery(name = "Paper.findByMonth", query = "SELECT p FROM Paper p WHERE p.month = :month"),
    @NamedQuery(name = "Paper.findByEe", query = "SELECT p FROM Paper p WHERE p.ee = :ee"),
    @NamedQuery(name = "Paper.findByCrossref", query = "SELECT p FROM Paper p WHERE p.crossref = :crossref"),
    @NamedQuery(name = "Paper.findBySeries", query = "SELECT p FROM Paper p WHERE p.series = :series"),
    @NamedQuery(name = "Paper.findBySchool", query = "SELECT p FROM Paper p WHERE p.school = :school"),
    @NamedQuery(name = "Paper.findByChapter", query = "SELECT p FROM Paper p WHERE p.chapter = :chapter"),
    @NamedQuery(name = "Paper.findByCdrom", query = "SELECT p FROM Paper p WHERE p.cdrom = :cdrom"),
    @NamedQuery(name = "Paper.findByCite", query = "SELECT p FROM Paper p WHERE p.cite = :cite")})
public class Paper implements Serializable {
    @Column(name = "doiID")
    private String doiID;
    @Column(name = "doiRef")
    private String doiRef;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPaper", nullable = false)
    private Integer idPaper;
    @Column(name = "doi", length = 100)
    private String doi;
    @Column(name = "isbn", length = 100)
    private String isbn;
    @Column(name = "url", length = 1000)
    private String url;
    @Column(name = "title", length = 1000)
    private String title;
    @Column(name = "abstract", length = 10000)
    private String abstract1;
    @Column(name = "volume", length = 100)
    private String volume;
    @Column(name = "pages", length = 100)
    private String pages;
    @Column(name = "year")
    private Integer year;
    @Lob
    @Column(name = "viewPublication", length = 65535)
    private String viewPublication;
    @Column(name = "bibTex", length = 1000)
    private String bibTex;
    @Column(name = "endNote", length = 1000)
    private String endNote;
    @Column(name = "dblpKey", length = 1000)
    private String dblpKey;
    @Column(name = "version")
    private Integer version;
    @Column(name = "paperFile", length = 1000)
    private String paperFile;
    @Column(name = "adress", length = 1000)
    private String adress;
    @Column(name = "number", length = 20)
    private String number;
    @Column(name = "month", length = 30)
    private String month;
    @Column(name = "ee", length = 1000)
    private String ee;
    @Column(name = "crossref", length = 50)
    private String crossref;
    @Column(name = "series", length = 100)
    private String series;
    @Column(name = "school", length = 1000)
    private String school;
    @Column(name = "chapter", length = 50)
    private String chapter;
    @Column(name = "cdrom", length = 100)
    private String cdrom;
    @Column(name = "cite", length = 100)
    private String cite;
    @ManyToMany(mappedBy = "paperCollection")
    private Collection<Keyword> keywordCollection;
    @ManyToMany(mappedBy = "paperCollection")
    private Collection<Author> authorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper")
    private Collection<PaperReviewer> paperReviewerCollection;
    @JoinColumn(name = "idPublisher", referencedColumnName = "idPublisher")
    @ManyToOne
    private Publisher idPublisher;
    @JoinColumn(name = "idPaperType", referencedColumnName = "idPaperType")
    @ManyToOne
    private PaperType idPaperType;
    @JoinColumn(name = "idMagazine", referencedColumnName = "idMagazine")
    @ManyToOne
    private Magazine idMagazine;
    @JoinColumn(name = "idJournal", referencedColumnName = "idJournal")
    @ManyToOne
    private Journal idJournal;
    @JoinColumn(name = "idConference", referencedColumnName = "idConference")
    @ManyToOne
    private Conference idConference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper")
    private Collection<PaperPaper> paperPaperCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper1")
    private Collection<PaperPaper> paperPaperCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaper")
    private Collection<Comment> commentCollection;

    public Paper() {
    }

    public Paper(Integer idPaper) {
        this.idPaper = idPaper;
    }

    public Integer getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(Integer idPaper) {
        this.idPaper = idPaper;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getViewPublication() {
        return viewPublication;
    }

    public void setViewPublication(String viewPublication) {
        this.viewPublication = viewPublication;
    }

    public String getBibTex() {
        return bibTex;
    }

    public void setBibTex(String bibTex) {
        this.bibTex = bibTex;
    }

    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    public String getDblpKey() {
        return dblpKey;
    }

    public void setDblpKey(String dblpKey) {
        this.dblpKey = dblpKey;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPaperFile() {
        return paperFile;
    }

    public void setPaperFile(String paperFile) {
        this.paperFile = paperFile;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }

    public String getCrossref() {
        return crossref;
    }

    public void setCrossref(String crossref) {
        this.crossref = crossref;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getCdrom() {
        return cdrom;
    }

    public void setCdrom(String cdrom) {
        this.cdrom = cdrom;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    @XmlTransient
    public Collection<Keyword> getKeywordCollection() {
        return keywordCollection;
    }

    public void setKeywordCollection(Collection<Keyword> keywordCollection) {
        this.keywordCollection = keywordCollection;
    }

    @XmlTransient
    public Collection<Author> getAuthorCollection() {
        return authorCollection;
    }

    public void setAuthorCollection(Collection<Author> authorCollection) {
        this.authorCollection = authorCollection;
    }

    @XmlTransient
    public Collection<PaperReviewer> getPaperReviewerCollection() {
        return paperReviewerCollection;
    }

    public void setPaperReviewerCollection(Collection<PaperReviewer> paperReviewerCollection) {
        this.paperReviewerCollection = paperReviewerCollection;
    }

    public Publisher getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Publisher idPublisher) {
        this.idPublisher = idPublisher;
    }

    public PaperType getIdPaperType() {
        return idPaperType;
    }

    public void setIdPaperType(PaperType idPaperType) {
        this.idPaperType = idPaperType;
    }

    public Magazine getIdMagazine() {
        return idMagazine;
    }

    public void setIdMagazine(Magazine idMagazine) {
        this.idMagazine = idMagazine;
    }

    public Journal getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Journal idJournal) {
        this.idJournal = idJournal;
    }

    public Conference getIdConference() {
        return idConference;
    }

    public void setIdConference(Conference idConference) {
        this.idConference = idConference;
    }

    @XmlTransient
    public Collection<PaperPaper> getPaperPaperCollection() {
        return paperPaperCollection;
    }

    public void setPaperPaperCollection(Collection<PaperPaper> paperPaperCollection) {
        this.paperPaperCollection = paperPaperCollection;
    }

    @XmlTransient
    public Collection<PaperPaper> getPaperPaperCollection1() {
        return paperPaperCollection1;
    }

    public void setPaperPaperCollection1(Collection<PaperPaper> paperPaperCollection1) {
        this.paperPaperCollection1 = paperPaperCollection1;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaper != null ? idPaper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paper)) {
            return false;
        }
        Paper other = (Paper) object;
        if ((this.idPaper == null && other.idPaper != null) || (this.idPaper != null && !this.idPaper.equals(other.idPaper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uit.tkorg.entities.Paper[ idPaper=" + idPaper + " ]";
    }

    public String getDoiID() {
        return doiID;
    }

    public void setDoiID(String doiID) {
        this.doiID = doiID;
    }

    public String getDoiRef() {
        return doiRef;
    }

    public void setDoiRef(String doiRef) {
        this.doiRef = doiRef;
    }
    
}
