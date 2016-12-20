package fr.dta.mediatic.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Loan;

@Repository
@Transactional
public class LoanRepository extends AbstractRepository<Loan> {

    @Override
    protected Class<Loan> getEntityClass() {
	return Loan.class;
    }

}
