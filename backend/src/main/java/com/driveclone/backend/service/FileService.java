package com.driveclone.backend.service;

import com.driveclone.backend.model.FileEntity;
import com.driveclone.backend.repository.FileRepository;
import com.driveclone.backend.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.driveclone.backend.model.Folder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@Service
public class FileService {


    @Autowired
    private FolderRepository folderRepository;

    public FileEntity saveFile(FileEntity file) {
        return fileRepository.save(file);
    }

    public FileEntity createFileForFolder(Long folderId, FileEntity file) {

        Folder folder = folderRepository.findById(folderId).orElse(null);

        if(folder == null) {
            return null;
        }

        file.setFolder(folder);

        return fileRepository.save(file);
    }

    @Autowired
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    public List<FileEntity> getFilesByFolderId(Long folderId) {
        return fileRepository.findByFolderId(folderId);
    }

    public FileEntity uploadFile(MultipartFile file) throws IOException {

        String uploadDir = "uploads/";

        Path path = Paths.get(uploadDir + file.getOriginalFilename());

        Files.write(path, file.getBytes());

        FileEntity fileEntity = new FileEntity();

        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setSize(file.getSize());
        fileEntity.setType(file.getContentType());
        fileEntity.setPath(path.toString());
        fileEntity.setUploadedAt(java.time.LocalDate.now().toString());

        return fileRepository.save(fileEntity);
    }

    public Resource downloadFile(Long id) throws MalformedURLException {

        FileEntity file = fileRepository.findById(id).orElse(null);

        if (file == null) {
            return null;
        }

        Path path = Paths.get(file.getPath());

        return new UrlResource(path.toUri());
    }
}