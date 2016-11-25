package fr.dta.mediatic;

import java.util.List;

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
		// Instanciation
		MediaDAO mediaDAO = MediaDAO.instance();
		
		// New medias
		System.out.println("--------NEW MEDIAS---------");
		Media mediaBookVoltaire = new Media(null, TypeMedia.BOOK, "Candide", "Voltaire");
		mediaDAO.persist(mediaBookVoltaire);
		Media mediaBookTrain = new Media(null, TypeMedia.BOOK, "Everything you need to know about Nadir", "BTrain");
		mediaDAO.persist(mediaBookTrain);
		System.out.println("--------/NEW MEDIAS---------");
		
		// Update media
		System.out.println("--------UPDATE MEDIAS---------");
		mediaBookTrain.setAuthor("b-Train");
		mediaDAO.merge(mediaBookTrain);
		System.out.println("--------/UPDATE MEDIAS---------");
		
		// Remove media
		System.out.println("--------REMOVE MEDIAS---------");
		mediaDAO.remove(mediaBookTrain.getId());
		System.out.println("--------/REMOVE MEDIAS---------");

		// Select all the medias
		List<Media> listM = mediaDAO.selectAllMedias();
		System.out.println("--------ALL MEDIAS---------");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
			System.out.println("--------/ALL MEDIAS-------");
		}
		
		// Select media by id
		System.out.println("--------MEDIA BY ID---------");
		Media mediaFind = mediaDAO.find(mediaBookVoltaire.getId());
		System.out.println(">>>>>>>>>>>>MEDIA = " + mediaFind.getTitle());
		System.out.println("--------/MEDIA BY ID---------");
		
		// Select media by title
		System.out.println("--------MEDIA BY TITLE---------");
		listM = mediaDAO.findMediaByTitle("Candi");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
			System.out.println("--------/MEDIA BY TITLE--------");
		}
		
		// Select media by title
		System.out.println("--------MEDIA BY AUTHOR---------");
		listM = mediaDAO.findMediaByAuthor("Volt");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
		}
		System.out.println("--------/MEDIA BY AUTHOR---------");
		
		// Select media by type
		System.out.println("--------MEDIA BY TYPE---------");
		listM = mediaDAO.findMediaByType(TypeMedia.BOOK);
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
		}
		System.out.println("--------/MEDIA BY TYPE---------");
	}

}
