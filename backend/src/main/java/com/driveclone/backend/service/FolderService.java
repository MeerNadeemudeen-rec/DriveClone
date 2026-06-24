package com.driveclone.backend.service;

import com.driveclone.backend.model.Folder;
import com.driveclone.backend.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.driveclone.backend.repository.UserRepository;
import com.driveclone.backend.model.User;
import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserRepository userRepository;

    public Folder saveFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    public Folder createFolderForUser(Long userId, Folder folder) {

        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            return null;
        }

        folder.setOwner(user);

        return folderRepository.save(folder);
    }
    public List<Folder> getFoldersByUserId(Long userId) {
        return folderRepository.findByOwnerId(userId);
    }

    public Folder getFolderById(Long id) {
        return folderRepository.findById(id).orElse(null);
    }
}