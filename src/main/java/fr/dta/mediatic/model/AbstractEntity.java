package fr.dta.mediatic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;

    public Long getId() {
	return id;
    }

}
