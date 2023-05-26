package com.example.springtrelloproject.mapper;

import com.example.springtrelloproject.dto.TaskCategoryReadDto;
import com.example.springtrelloproject.model.TaskCategory;
import org.springframework.stereotype.Component;

@Component
public class TaskCategoryReadMapper implements Mapper<TaskCategory, TaskCategoryReadDto> {

    @Override
    public TaskCategoryReadDto map(TaskCategory object) {
        return new TaskCategoryReadDto(
                object.getId(),
                object.getName()
        );
    }
}
