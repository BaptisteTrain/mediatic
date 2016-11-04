package fr.dta.mediatic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class MediaticWebApplication extends SpringBootServletInitializer {

	/**
	 * Constructeur
	 * @param String test
	 */
	public MediaticWebApplication() {
		
		// Do nothing
		
	}
	
	/**
	 *  Renvoie les sources d'une application
	 *  @param SpringApplicationBuilder application
	 *  @return SpringApplicationBuilder
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MediaticApplication.class);
    }

}
