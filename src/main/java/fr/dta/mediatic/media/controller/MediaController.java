package fr.dta.mediatic.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.service.MediaService;

@RestController
@RequestMapping("/medias")
public class MediaController {
	
	@Autowired
	private MediaService mediaService;
	
	@RequestMapping( method = RequestMethod.GET)
	@ResponseBody
	public List<Media> findAll() {
		return mediaService.selectAllMedias();
	}
	
	
}
