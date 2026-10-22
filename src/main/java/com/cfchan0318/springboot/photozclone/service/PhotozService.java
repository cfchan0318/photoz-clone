package com.cfchan0318.springboot.photozclone.service;

import com.cfchan0318.springboot.photozclone.model.Photo;
import com.cfchan0318.springboot.photozclone.repository.PhotozRepository;
import org.springframework.stereotype.Service;

//@Component
@Service
public class PhotozService {
    private final PhotozRepository photozRepository;

    public PhotozService(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }


    public Iterable<Photo> values() {
        return photozRepository.findAll();
    }

    public Photo get(Integer id) {
        return photozRepository.findById(id).orElse(null);
    }

    public Photo remove(Integer id) {
        photozRepository.deleteById(id);
        return null;
    }

    public Photo save(String fileName, byte[] data, String contentType) {
        Photo photo = new Photo(fileName, data, contentType);
        photozRepository.save(photo);
        return photo;
    }
}
