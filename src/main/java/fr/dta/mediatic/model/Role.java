package fr.dta.mediatic.model;

public enum Role {

    Manager('M'), Employee('E');

    private char code;

    private Role(char code) {

	this.code = code;
    }

    public char getCode() {

	return code;
    }

}
