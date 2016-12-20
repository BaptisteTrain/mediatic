package fr.dta.mediatic.media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.dta.mediatic.media.dao.MediaDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;
import fr.dta.mediatic.member.model.Member;

@Service
public class MediaService {

	private static MediaDAO mediaDAO = MediaDAO.instance();

	/**
	 * Select all the medias
	 * 
	 * @return
	 */
	public List<Media> selectAllMedias() {
		return mediaDAO.selectAllMedias();
	}

	/**
	 * Select all the medias with the loans list for the member in param
	 * 
	 * @return
	 */
	public List<Media> selectMediasFromMember(Member member) {
		return mediaDAO.selectMediasFromMember(member);
	}

	/**
	 * Find the media by its title
	 * 
	 * @param title
	 * @return
	 */
	public List<Media> findMediaByTitle(String title) {
		return mediaDAO.findMediaByTitle(title);
	}

	/**
	 * Find the media by its author
	 * 
	 * @param author
	 * @return
	 */
	public List<Media> findMediaByAuthor(String author) {
		return mediaDAO.findMediaByAuthor(author);
	}

	/**
	 * Find the media by its type
	 * 
	 * @param type
	 * @return
	 */
	public List<Media> findMediaByType(TypeMedia type) {
		return mediaDAO.findMediaByType(type);
	}
}
