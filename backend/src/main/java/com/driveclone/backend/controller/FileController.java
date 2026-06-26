package com.driveclone.backend.controller;

import com.driveclone.backend.model.FileEntity;
import com.driveclone.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public FileEntity createFile(@RequestBody FileEntity file) {
        return fileService.saveFile(file);
    }
    @PostMapping("/folder/{folderId}")
    public FileEntity createFileForFolder(
            @PathVariable Long folderId,
            @RequestBody FileEntity file) {

        return fileService.createFileForFolder(folderId, file);
    }

    @GetMapping("/folder/{folderId}")
    public List<FileEntity> getFilesByFolderId(
            @PathVariable Long folderId) {

        return fileService.getFilesByFolderId(folderId);
    }

    @PostMapping("/upload")
    public FileEntity uploadFile(
            @RequestParam("file") MultipartFile file)
            throws Exception {

        return fileService.uploadFile(file);
    }
}