package fr.dta.mediatic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Media> getAllMedia() {
		return mediaService.getAllMedias();
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public Media getDetailMedia(@PathVariable Long id) {
		System.out.println("id = "+ id);
		return mediaService.getMediaById(id);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public void createNewMedia(@RequestBody Media media) {
		mediaService.createNewMedia(media);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateMedia(@RequestBody Media media) {
		mediaService.updateMedia(media);
	}

}
