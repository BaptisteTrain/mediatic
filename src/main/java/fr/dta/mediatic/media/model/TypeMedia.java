package fr.dta.mediatic.media.model;

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
			return 30;
		case CD:
			return 15;
		case DVD:
			return 15;
		default:
			return 0;
		}
	}
}
