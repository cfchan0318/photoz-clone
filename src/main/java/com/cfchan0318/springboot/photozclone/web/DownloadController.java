package com.cfchan0318.springboot.photozclone.web;

import com.cfchan0318.springboot.photozclone.model.Photo;
import com.cfchan0318.springboot.photozclone.service.PhotozService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotozService photozService;

    public DownloadController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        Photo p = photozService.get(id);
        if(p == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = p.getData();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(p.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(p.getFileName())
                .build();
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
