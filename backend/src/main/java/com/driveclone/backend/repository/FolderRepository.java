package com.driveclone.backend.repository;

import com.driveclone.backend.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findByOwnerId(Long userId);

}