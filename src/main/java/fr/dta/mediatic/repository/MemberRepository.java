package fr.dta.mediatic.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Member;

@Repository
@Transactional
public class MemberRepository extends AbstractRepository<Member> {

    @Override
    protected Class<Member> getEntityClass() {
	return Member.class;
    }

}
