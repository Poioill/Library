package com.example.libraryCoursework.service;

import com.example.libraryCoursework.entity.Reader;
import com.example.libraryCoursework.entity.ReaderImage;
import com.example.libraryCoursework.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public ReaderImage toImageEntity(MultipartFile file) throws IOException {
        ReaderImage img = new ReaderImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void saveImageReader(Reader reader,
                                MultipartFile file) throws IOException {
        ReaderImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            reader.addImageToReader(img);
        }
        reader.setName(reader.getName());
        log.info("Saving Reader: {}", reader.getName());
        readerRepository.save(reader);
    }

    public List<Reader> listReader(String name) {
        if (name != null && readerRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            return readerRepository.findAll();
        } else if (name != null) {
            return readerRepository.findByNameContainingIgnoreCase(name);
        } else return readerRepository.findAll();
    }

    public Reader findReaderById(Long id) {
        return readerRepository.findReaderById(id);
    }

    public List<Reader> findAllReaders(){
        return readerRepository.findAll();
    }
}
