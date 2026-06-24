package com.driveclone.backend.repository;

import com.driveclone.backend.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByFolderId(Long folderId);
}