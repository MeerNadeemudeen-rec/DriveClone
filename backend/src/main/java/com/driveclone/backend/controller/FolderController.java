package com.driveclone.backend.controller;

import com.driveclone.backend.model.Folder;
import com.driveclone.backend.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping
    public Folder createFolder(@RequestBody Folder folder) {
        return folderService.saveFolder(folder);
    }

    @PostMapping("/user/{userId}")
    public Folder createFolderForUser(
            @PathVariable Long userId,
            @RequestBody Folder folder) {

        return folderService.createFolderForUser(userId, folder);
    }
    @GetMapping("/user/{userId}")
    public List<Folder> getFoldersByUserId(@PathVariable Long userId) {
        return folderService.getFoldersByUserId(userId);
    }
}