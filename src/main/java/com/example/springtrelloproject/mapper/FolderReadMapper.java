package com.example.springtrelloproject.mapper;

import com.example.springtrelloproject.dto.FolderReadDto;
import com.example.springtrelloproject.dto.TaskCategoryReadDto;
import com.example.springtrelloproject.model.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FolderReadMapper implements Mapper<Folder, FolderReadDto> {

    private final TaskCategoryReadMapper taskCategoryReadMapper;

    @Override
    public FolderReadDto map(Folder object) {
        List<TaskCategoryReadDto> taskCategories = Optional.ofNullable(object.getCategories())
                .stream()
                .flatMap(Collection::stream)
                .map(taskCategoryReadMapper::map)
                .toList();
        return new FolderReadDto(
                object.getId(),
                object.getName(),
                taskCategories
        );
    }
}