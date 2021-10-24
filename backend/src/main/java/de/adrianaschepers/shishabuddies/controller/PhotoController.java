package de.adrianaschepers.shishabuddies.controller;

import de.adrianaschepers.shishabuddies.model.Photo;
import de.adrianaschepers.shishabuddies.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {

    public final PhotoService service;

    @Autowired
    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Photo> findAll(){
        return service.findAll();
    }
}
