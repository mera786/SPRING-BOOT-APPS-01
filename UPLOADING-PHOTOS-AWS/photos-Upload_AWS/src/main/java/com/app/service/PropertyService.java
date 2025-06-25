package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.PropertyDto;
import com.app.entities.Property;
import com.app.entities.PropertyPhotos;
import com.app.repository.PhotosRepository;
import com.app.repository.PropertyRepository;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository PropertyRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private PhotosRepository photosRepository;

    public PropertyDto addProperty(PropertyDto dto, MultipartFile[] files) {
        Property property = new Property();
        property.setName(dto.getName());
        property.setNumberOfRooms(dto.getNumberOfRooms());

        Property savedProperty = PropertyRepository.save(property);

        // Upload files to S3
        List<String> fileUrls = s3Service.uploadFiles(files);
        for(String url: fileUrls){
            PropertyPhotos photo= new PropertyPhotos();
            photo.setUrl(url);
            photo.setProperty(savedProperty);
            photosRepository.save(photo);
        }

        dto.setImageUrls(fileUrls); // ✅ this will be sent back in API response
        return dto;
    }
}
