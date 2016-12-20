package fr.dta.mediatic.media.model;

import fr.dta.mediatic.Constants;

public enum TypeMedia {

	BOOK, CD, DVD;
	
	/**
	 * 
	 * @param type le type de média
	 * @return la durée de l'emprunt
	 */
	public static Integer getDuration(TypeMedia typeMedia) {
		switch (typeMedia) {
		case BOOK:
			return Constants.MEDIA_DURATION_BOOK;
		case CD:
			return Constants.MEDIA_DURATION_CD;
		case DVD:
			return Constants.MEDIA_DURATION_DVD;
		default:
			return 0;
		}
	}
}
