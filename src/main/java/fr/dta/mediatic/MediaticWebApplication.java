package fr.dta.mediatic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class MediaticWebApplication extends SpringBootServletInitializer {

	// Constructeur
	public MediaticWebApplication() {
		
		
		
	}
	
	// Renvoie les sources d'une application
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MediaticApplication.class);
    }

}
