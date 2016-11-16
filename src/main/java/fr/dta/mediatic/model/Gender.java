package fr.dta.mediatic.model;

public enum Gender {

	Homme('M') , Femme('F');
	
	private char code;
	
	private Gender(char code) {
		
		this.code = code;
	}
	
	public char getCode() {
		
		return code;
	}
}
