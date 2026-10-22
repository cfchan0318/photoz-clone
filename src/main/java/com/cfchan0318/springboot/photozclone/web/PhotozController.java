package com.cfchan0318.springboot.photozclone.web;

import com.cfchan0318.springboot.photozclone.model.Photo;
import com.cfchan0318.springboot.photozclone.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String getHello(){
        return "hello";
    }


    @GetMapping("/photoz")
    public Iterable<Photo> get(){
        return photozService.values();
    }

    //url path variable can be
    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id){
        Photo photo = photozService.get(id);
        if (photo == null) throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id){
        photozService.remove(id);
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.save(file.getOriginalFilename(), file.getBytes(), file.getContentType());
    }
}
