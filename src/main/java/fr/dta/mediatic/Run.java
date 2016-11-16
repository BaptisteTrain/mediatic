package fr.dta.mediatic;

import java.util.List;

import javax.persistence.EntityManager;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.media.dao.MediaDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;

public class Run {

	public static void main(String[] args) {
		// Media
		mediaOperations();
	}
	
	
	/**
	 * Operations on the media
	 */
	public static void mediaOperations() {
		MediaDAO mediaDAO = MediaDAO.instance();
		// New medias
		Media mediaBookVoltaire = new Media(null, TypeMedia.BOOK, "Candide", "Voltaire");
		mediaDAO.persist(mediaBookVoltaire);
		Media mediaBookTrain = new Media(null, TypeMedia.BOOK, "Everything you need to know about Nadir", "BTrain");
		mediaDAO.persist(mediaBookTrain);
		
		// Update media
		mediaBookTrain.setAuthor("b-Train");
		mediaDAO.merge(mediaBookTrain);
		
		// Remove media
		mediaDAO.remove(mediaBookTrain.getId());

		// Select all the medias
		List<Media> listM = mediaDAO.selectAllMedias();
		System.out.println("--------ALL MEDIAS---------");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
			System.out.println("------------------------");
		}
	}

}
