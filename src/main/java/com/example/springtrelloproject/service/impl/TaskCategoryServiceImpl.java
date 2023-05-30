package com.example.springtrelloproject.service.impl;

import com.example.springtrelloproject.dto.TaskCategoryCreateEditDto;
import com.example.springtrelloproject.dto.TaskCategoryReadDto;
import com.example.springtrelloproject.mapper.TaskCategoryCreateEditMapper;
import com.example.springtrelloproject.mapper.TaskCategoryReadMapper;
import com.example.springtrelloproject.repository.TaskCategoryRepository;
import com.example.springtrelloproject.service.TaskCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskCategoryReadMapper taskCategoryReadMapper;
    private final TaskCategoryCreateEditMapper taskCategoryCreateEditMapper;

    @Override
    public List<TaskCategoryReadDto> findAll() {
        return taskCategoryRepository.findAll().stream()
                .map(taskCategoryReadMapper::map)
                .toList();
    }

    @Override
    public TaskCategoryReadDto findById(Long id) {
        return taskCategoryRepository.findById(id)
                .map(taskCategoryReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public TaskCategoryReadDto create(TaskCategoryCreateEditDto task) {
        return Optional.of(task)
                .map(taskCategoryCreateEditMapper::map)
                .map(taskCategoryRepository::save)
                .map(taskCategoryReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public TaskCategoryReadDto update(Long id, TaskCategoryCreateEditDto taskDto) {
        return taskCategoryRepository.findById(id)
                .map(entity -> taskCategoryCreateEditMapper.map(taskDto, entity))
                .map(taskCategoryRepository::saveAndFlush)
                .map(taskCategoryReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskCategoryRepository.findById(id)
                .ifPresent(entity -> taskCategoryRepository.deleteById(id));
    }
}
