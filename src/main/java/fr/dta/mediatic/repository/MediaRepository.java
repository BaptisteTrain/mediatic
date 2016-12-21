package fr.dta.mediatic.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.model.TypeMedia;

@Repository
@Transactional
public class MediaRepository extends AbstractRepository<Media> {

    @Override
    protected Class<Media> getEntityClass() {
	return Media.class;
    }

	/**
	 * Select all the medias with the loans list for the member in param
	 * 
	 * @return
	 */
	public List<Media> selectMediasFromMember(Member member) {
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "LEFT OUTER JOIN m.loanList l "
											   + "WHERE l.member.id = :id ", Media.class);
		query.setParameter("id", member.getId());
		List<Media> listeReturn = query.getResultList();
		return listeReturn;
	}

	/**
	 * Find the media by its title
	 * 
	 * @param title
	 * @return
	 */
	public List<Media> findMediaByTitle(String title) {
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE upper(m.title) LIKE :title", Media.class);
		query.setParameter("title", title.toUpperCase() + '%');
		List<Media> listeReturn = query.getResultList();
		return listeReturn;
	}

	/**
	 * Find the media by its author
	 * 
	 * @param author
	 * @return
	 */
	public List<Media> findMediaByAuthor(String author) {
		TypedQuery<Media> query = em.createQuery("SELECT m " 
											   + "FROM Media m "
											   + "WHERE upper(m.author) LIKE :author", Media.class);
		query.setParameter("author", author.toUpperCase() + '%');
		List<Media> listeReturn = query.getResultList();
		return listeReturn;
	}

	/**
	 * Find the media by its type
	 * 
	 * @param type
	 * @return
	 */
	public List<Media> findMediaByType(TypeMedia type) {
		TypedQuery<Media> query = em.createQuery("SELECT m "
											   + "FROM Media m "
											   + "WHERE m.type = :type", Media.class);
		query.setParameter("type", type);
		List<Media> listeReturn = query.getResultList();
		return listeReturn;
	}
}
