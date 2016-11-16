package fr.dta.mediatic.media.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;

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
	 * Select all the medias with the loans list and the member of the loan
	 * @return
	 */
	public static List<Media> selectAllMedias() {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "INNER JOIN m.loanList l ", Media.class);
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}
	
	/**
	 * Find the media by its title
	 * @param id
	 * @return
	 */
	public static List<Media> findMediaByTitle(String title) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE m.title LIKE '%:title%'", Media.class);
		query.setParameter("title", title);
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}
	
	/**
	 * Find the media by its author
	 * @param id
	 * @return
	 */
	public static List<Media> findMediaByAuthor(String author) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE m.title LIKE '%:author%'", Media.class);
		query.setParameter("author", author);
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}
	
	/**
	 * Find the media by its type
	 * @param id
	 * @return
	 */
	public static List<Media> findMediaByType(TypeMedia type) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE m.title = :type", Media.class);
		query.setParameter("type", type);
		List<Media> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}
	

}
