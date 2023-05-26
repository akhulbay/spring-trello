package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.TaskCategoryCreateEditDto;
import com.example.springtrelloproject.dto.TaskCategoryReadDto;

import java.util.List;

public interface TaskCategoryService {

    List<TaskCategoryReadDto> findAll();

    TaskCategoryReadDto findById(Long id);

    TaskCategoryReadDto create(TaskCategoryCreateEditDto task);

    TaskCategoryReadDto update(Long id, TaskCategoryCreateEditDto task);

    void delete(Long id);

}
