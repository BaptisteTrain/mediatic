package fr.dta.mediatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.dta.mediatic")
public class MediaticApplication {

    public static void main(String[] args) {
    	
    	// Lancement de l'app
    	ConfigurableApplicationContext context = SpringApplication.run(MediaticApplication.class, args);
        
    	// Fermeture du ConfigurableApplicationContext
    	context.close();
    }

}
