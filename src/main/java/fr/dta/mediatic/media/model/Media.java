package fr.dta.mediatic.media.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import fr.dta.mediatic.loan.model.Loan;

@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 4)
    @Length(max = 4)
	@NotNull
	@NotBlank
    private TypeMedia type;

    @Column(length = 150)
    @Length(max = 150)
	@NotNull
	@NotBlank
    private String title;

    @Column(length = 150)
    @Length(max = 150)
	@NotNull
	@NotBlank
    private String author;

    @OneToMany(mappedBy = "media")
    private List<Loan> loanList;

    public Media() {
    }

    public Media(Long reference, TypeMedia type, String title, String author) {
	super();
	this.id = reference;
	this.type = type;
	this.title = title;
	this.author = author;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public TypeMedia getType() {
	return type;
    }

    public void setType(TypeMedia type) {
	this.type = type;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public List<Loan> getLoanList() {
	return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
	this.loanList = loanList;
    }

    @Override
    public String toString() {
	return "Media [id=" + id + ", type=" + type + ", title=" + title + ", author=" + author + "]";
    }

}
