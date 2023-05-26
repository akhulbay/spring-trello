package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.TaskCreateEditDto;
import com.example.springtrelloproject.dto.TaskReadDto;
import com.example.springtrelloproject.mapper.TaskCreateEditMapper;
import com.example.springtrelloproject.mapper.TaskReadMapper;
import com.example.springtrelloproject.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskReadMapper taskReadMapper;
    private final TaskCreateEditMapper taskCreateEditMapper;

    @Override
    public List<TaskReadDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskReadMapper::map)
                .toList();
    }

    @Override
    public TaskReadDto findById(Long id) {
        return taskRepository.findById(id)
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public TaskReadDto create(TaskCreateEditDto task) {
        return Optional.of(task)
                .map(taskCreateEditMapper::map)
                .map(taskRepository::save)
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public TaskReadDto update(Long id, TaskCreateEditDto taskDto) {
        return taskRepository.findById(id)
                .map(entity -> taskCreateEditMapper.map(taskDto, entity))
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepository.findById(id)
                .ifPresent(entity -> taskRepository.deleteById(id));
    }
}
