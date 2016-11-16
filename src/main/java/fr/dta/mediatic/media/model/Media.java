package fr.dta.mediatic.media.model;

import java.util.List;

import javax.persistence.*;

import fr.dta.mediatic.loan.model.Loan;

@Entity
@Table(name="media")
public class Media {

	@Id
	@GeneratedValue
	private Long id ;
	
	@Enumerated(EnumType.STRING)
	@Column(length=4)
	private TypeMedia type;
	
	private String title;
	
	private String author;
	
	@OneToMany(mappedBy = "media")
	private List<Loan> loanList;
	
	public Media() {}

	public Media(Long reference, TypeMedia type, String title, String author) {
		super();
		this.id = reference;
		this.type = type;
		this.title = title;
		this.author = author;
	}

	public Long getReference() {
		return id;
	}

	public void setReference(Long reference) {
		this.id = reference;
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
		return "Media [id=" + id + ", type=" + type + ", title=" + title + ", author=" + author + ", loanList="
				+ loanList + "]";
	}

	
}
