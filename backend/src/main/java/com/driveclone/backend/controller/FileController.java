package com.driveclone.backend.controller;

import com.driveclone.backend.model.FileEntity;
import com.driveclone.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public FileEntity createFile(@RequestBody FileEntity file) {
        return fileService.saveFile(file);
    }
}