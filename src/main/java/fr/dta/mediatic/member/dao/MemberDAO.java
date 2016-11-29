package fr.dta.mediatic.member.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.member.model.Member;

public class MemberDAO extends GenericDAO<Member> {

    private static MemberDAO dao;

    private MemberDAO() {
	super(Member.class);
    }

    public static MemberDAO instance() {
	if (dao == null) {
	    dao = new MemberDAO();
	}
	return dao;
    }
    
    /**
     * Select all the members with the loans list and the media of the loan
     * @return
     */
    public List<Member> selectAllMembers() {
	EntityManager em = DataBaseHelper.createEntityManager();
	DataBaseHelper.beginTx(em);
	TypedQuery<Member> query = em.createQuery("SELECT m "
						+ "FROM Member m " 
						+ "LEFT OUTER JOIN m.listLoan l ", Member.class);
	List<Member> listeReturn = query.getResultList();
	em.close();
	return listeReturn;
    }

    /**
     * 
     * @param id
     * @return
     */
    public List<Member> findMembersByIdPartial(String id) {

	EntityManager em = DataBaseHelper.createEntityManager();

	DataBaseHelper.beginTx(em);
	TypedQuery<Member> query = em.createQuery("SELECT m " 
						+ "FROM Member m " 
						+ "WHERE m.identifier LIKE :id", Member.class);

	query.setParameter("id",  id + "%");
	List<Member> listeReturn = query.getResultList();
	em.close();

	return listeReturn;
    }

    /**
     * Find members by name
     * @param lastname
     * @param firstname
     * @return
     */
    public List<Member> findMemberByName(String lastname, String firstname) {

	EntityManager em = DataBaseHelper.createEntityManager();

	DataBaseHelper.beginTx(em);
	TypedQuery<Member> query = em.createQuery("SELECT m " 
						+ "FROM Member m " 
						+ "WHERE upper(m.person.lastname) LIKE :lastname " 
						+ "AND upper(m.person.firstname) LIKE :firstname", Member.class);

	query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
	query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
	List<Member> listeReturn = query.getResultList();
	em.close();

	return listeReturn;
    }

    /**
     * Find the members and its loan from a media
     * @param media
     * @return
     */
    public List<Member> findMembersFromMedia(Media media) {
	EntityManager em = DataBaseHelper.createEntityManager();
	DataBaseHelper.beginTx(em);
	TypedQuery<Member> query = em.createQuery("SELECT m " 
						+ "FROM Member m " 
						+ "JOIN m.listLoan l " 
						+ "WHERE l.media.id = :id ", Member.class);
	query.setParameter("id", media.getId());
	List<Member> listeReturn = query.getResultList();
	em.close();
	return listeReturn;
    }

    /**
     * Find the members by id or lastname or firstname
     * @param id
     * @param lastname
     * @param firstname
     * @return List<Member>
     */
    public List<Member> findMemberByIdOrNames(String id, String lastname, String firstname) {
	EntityManager em = DataBaseHelper.createEntityManager();
	DataBaseHelper.beginTx(em);
	TypedQuery<Member> query = em.createQuery("SELECT m " 
						+ "FROM Member m " 
						+ "WHERE m.person.lastname = :lastname " 
						+ "OR m.person.firstname = :firstname " 
						+ "OR m.identifier = :id", Member.class);
	query.setParameter("id", id);
	query.setParameter("lastname", lastname);
	query.setParameter("firstname", firstname);
	// TODO : pas de LIKE sur lastname and firstname ?
	List<Member> listeReturn = query.getResultList();
	em.close();
	return listeReturn;
    }

}
