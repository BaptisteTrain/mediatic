package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.repository.MediaRepository;

@Service
public class MediaService {

	@Autowired
	private MediaRepository repo;

	/**
	 * Select all the medias
	 * 
	 * @return
	 */
	public List<Media> getAllMedias() {
		return repo.getAllMediasAndLoan();
	}

	/**
	 * Select the media by its id
	 * 
	 * @param id
	 * @return
	 */
	public Media getMediaById(Long id) {
		return repo.getMediaById(id);
	}

	/**
	 * Create a new media
	 * 
	 * @param media
	 * @return
	 */
	public void createNewMedia(Media media) {
		repo.add(media);
	}

	/**
	 * Update a media
	 * 
	 * @param media
	 * @return
	 */
	public void updateMedia(Media media) {
		repo.update(media);
	}

	/**
	 * Select all the medias with the loans list for the member in param
	 * 
	 * @return
	 */
	public List<Media> selectMediasFromMember(Member member) {
		return repo.selectMediasFromMember(member);
	}

	/**
	 * Find the media by its title
	 * 
	 * @param title
	 * @return
	 *
	 * 		public List<Media> findMediaByTitle(String title) { return
	 *         repo.findMediaByTitle(title); }
	 * 
	 *         /** Find the media by its author
	 * @param author
	 * @return
	 *
	 * 		public List<Media> findMediaByAuthor(String author) { return
	 *         repo.findMediaByAuthor(author); }
	 * 
	 *         /** Find the media by its type
	 * @param type
	 * @return
	 *
	 * 		public List<Media> findMediaByType(TypeMedia type) { return
	 *         repo.findMediaByType(type); }
	 */
}
