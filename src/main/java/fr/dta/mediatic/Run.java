package fr.dta.mediatic;

import java.util.List;

import javax.persistence.EntityManager;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.media.dao.MediaDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;

public class Run {

	public static void main(String[] args) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);

		MediaDAO mediaDAO = MediaDAO.instance();

		// New medias
		Media mediaBookVoltaire = new Media(null, TypeMedia.BOOK, "Candide", "Voltaire");
		mediaDAO.persist(mediaBookVoltaire);
		Media mediaBookTrain = new Media(null, TypeMedia.BOOK, "Everything you need to know about Nadir", "BTrain");
		mediaDAO.persist(mediaBookTrain);

		List<Media> listM = mediaDAO.selectAllMedias();
		System.out.println("--------ALL MEDIAS---------");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
			System.out.println("------------------------");
		}

		em.close();
	}

}
