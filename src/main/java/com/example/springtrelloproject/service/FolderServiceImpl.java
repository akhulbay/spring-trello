package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.FolderCreateEditDto;
import com.example.springtrelloproject.dto.FolderReadDto;
import com.example.springtrelloproject.mapper.FolderCreateEditMapper;
import com.example.springtrelloproject.mapper.FolderReadMapper;
import com.example.springtrelloproject.model.Folder;
import com.example.springtrelloproject.model.TaskCategory;
import com.example.springtrelloproject.repository.FolderRepository;
import com.example.springtrelloproject.repository.TaskCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final TaskCategoryRepository taskCategoryRepository;

    private final FolderReadMapper folderReadMapper;
    private final FolderCreateEditMapper folderCreateEditMapper;

    @Override
    public List<FolderReadDto> findAll() {
        return folderRepository.findAll().stream()
                .map(folderReadMapper::map)
                .toList();
    }

    @Override
    public FolderReadDto findById(Long id) {
        return folderRepository.findById(id)
                .map(folderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public FolderReadDto create(FolderCreateEditDto folder) {
        return Optional.of(folder)
                .map(folderCreateEditMapper::map)
                .map(folderRepository::save)
                .map(folderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public FolderReadDto update(Long id, FolderCreateEditDto folderDto) {
        return folderRepository.findById(id)
                .map(entity -> folderCreateEditMapper.map(folderDto, entity))
                .map(folderRepository::saveAndFlush)
                .map(folderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        folderRepository.findById(id)
                .ifPresent(entity -> folderRepository.deleteById(id));
    }

    @Transactional
    @Override
    public void assignCategory(Long folderId, Long categoryId) {
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);
        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(categoryId);

        optionalFolder.ifPresent(folder -> {
            optionalTaskCategory.ifPresent(taskCategory -> {
                List<TaskCategory> folderCategories = folder.getCategories();
                if (folderCategories == null) {
                    folderCategories = new ArrayList<>();
                }
                if (!folderCategories.contains(taskCategory)) {
                    folderCategories.add(taskCategory);
                    folder.setCategories(folderCategories);
                    folderRepository.save(folder);
                }
            });
        });
    }

    @Transactional
    @Override
    public void unassignCategory(Long folderId, Long categoryId) {
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);
        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(categoryId);

        optionalFolder.ifPresent(folder -> {
            optionalTaskCategory.ifPresent(taskCategory -> {
                List<TaskCategory> folderCategories = folder.getCategories();
                if (folderCategories == null) {
                    folderCategories = new ArrayList<>();
                }
                if (folderCategories.contains(taskCategory)) {
                    folderCategories.remove(taskCategory);
                    folder.setCategories(folderCategories);
                    folderRepository.save(folder);
                }
            });
        });
    }
}
