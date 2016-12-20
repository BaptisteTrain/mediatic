package fr.dta.mediatic.media.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;
import fr.dta.mediatic.member.model.Member;

public class MediaDAO extends GenericDAO<Media> {

	private static MediaDAO dao;

	private MediaDAO() {
		super(Media.class);
	}

	public static MediaDAO instance() {
		if (dao == null) {
			dao = new MediaDAO();
		}
		return dao;
	}

	/**
	 * Select all the medias
	 * 
	 * @return
	 */
	public List<Media> selectAllMedias() {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "LEFT OUTER JOIN m.loanList l ", Media.class);
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}

	/**
	 * Select all the medias with the loans list for the member in param
	 * 
	 * @return
	 */
	public List<Media> selectMediasFromMember(Member member) {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "LEFT OUTER JOIN m.loanList l "
											   + "WHERE l.member.id = :id ", Media.class);
		query.setParameter("id", member.getId());
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}

	/**
	 * Find the media by its title
	 * 
	 * @param title
	 * @return
	 */
	public List<Media> findMediaByTitle(String title) {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE upper(m.title) LIKE :title", Media.class);
		query.setParameter("title", title.toUpperCase() + '%');
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}

	/**
	 * Find the media by its author
	 * 
	 * @param author
	 * @return
	 */
	public List<Media> findMediaByAuthor(String author) {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m " 
											   + "FROM Media m "
											   + "WHERE upper(m.author) LIKE :author", Media.class);
		query.setParameter("author", author.toUpperCase() + '%');
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}

	/**
	 * Find the media by its type
	 * 
	 * @param type
	 * @return
	 */
	public List<Media> findMediaByType(TypeMedia type) {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE m.type = :type", Media.class);
		query.setParameter("type", type);
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}

}
