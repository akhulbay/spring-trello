package com.example.springtrelloproject.mapper;

import com.example.springtrelloproject.dto.FolderReadDto;
import com.example.springtrelloproject.dto.TaskReadDto;
import com.example.springtrelloproject.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskReadMapper implements Mapper<Task, TaskReadDto> {

    private final FolderReadMapper folderReadMapper;

    @Override
    public TaskReadDto map(Task object) {
        FolderReadDto folderReadDto = Optional.ofNullable(object.getFolder())
                .map(folderReadMapper::map)
                .orElse(null);
        return new TaskReadDto(
                object.getId(),
                object.getTitle(),
                object.getDescription(),
                object.getStatus(),
                folderReadDto
        );
    }
}
