package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.FolderCreateEditDto;
import com.example.springtrelloproject.dto.FolderReadDto;

import java.util.List;

public interface FolderService {

    List<FolderReadDto> findAll();

    FolderReadDto findById(Long id);

    FolderReadDto create(FolderCreateEditDto folder);

    FolderReadDto update(Long id, FolderCreateEditDto folderDto);

    void delete(Long id);

    void assignCategory(Long folderId, Long categoryId);

    void unassignCategory(Long folderId, Long categoryId);

}
