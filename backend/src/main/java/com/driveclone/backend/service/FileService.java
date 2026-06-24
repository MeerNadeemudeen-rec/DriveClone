package com.driveclone.backend.service;

import com.driveclone.backend.model.FileEntity;
import com.driveclone.backend.repository.FileRepository;
import com.driveclone.backend.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.driveclone.backend.model.Folder;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

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

    public List<FileEntity> getFilesByFolderId(Long folderId) {
        return fileRepository.findByFolderId(folderId);
    }
}