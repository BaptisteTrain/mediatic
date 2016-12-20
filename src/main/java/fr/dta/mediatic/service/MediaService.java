package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.repository.MediaRepository;

@Service
public class MediaService {

    @Autowired
    private MediaRepository repo;

    /**
     * Select all the medias
     * @return
     */
    public List<Media> getAllMedias() {
	return repo.getAll();
    }

    /**
     * Select all the medias with the loans list for the member in param
     * @return
     *
    public List<Media> selectMediasFromMember(Member member) {
    	return mediaRepository.selectMediasFromMember(member);
    }
    
    /**
     * Find the media by its title
     * @param title
     * @return
     *
    public List<Media> findMediaByTitle(String title) {
    	return mediaRepository.findMediaByTitle(title);
    }
    
    /**
     * Find the media by its author
     * @param author
     * @return
     *
    public List<Media> findMediaByAuthor(String author) {
    	return mediaRepository.findMediaByAuthor(author);
    }
    
    /**
     * Find the media by its type
     * @param type
     * @return
     *
    public List<Media> findMediaByType(TypeMedia type) {
    	return mediaRepository.findMediaByType(type);
    }*/
}
