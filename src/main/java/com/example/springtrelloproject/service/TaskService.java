package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.TaskCreateEditDto;
import com.example.springtrelloproject.dto.TaskReadDto;

import java.util.List;

public interface TaskService {

    List<TaskReadDto> findAll();

    TaskReadDto findById(Long id);

    List<TaskReadDto> findByFolderId(Long folderId);

    TaskReadDto create(TaskCreateEditDto task);

    TaskReadDto update(Long id, TaskCreateEditDto task);

    void delete(Long id);

}
