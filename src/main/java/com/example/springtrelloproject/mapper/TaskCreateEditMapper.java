package com.example.springtrelloproject.mapper;

import com.example.springtrelloproject.dto.TaskCreateEditDto;
import com.example.springtrelloproject.model.Folder;
import com.example.springtrelloproject.model.Task;
import com.example.springtrelloproject.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskCreateEditMapper implements Mapper<TaskCreateEditDto, Task> {

    private final FolderRepository folderRepository;

    @Override
    public Task map(TaskCreateEditDto fromObject, Task toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Task map(TaskCreateEditDto object) {
        Task task = new Task();
        copy(object, task);
        return task;
    }

    private void copy(TaskCreateEditDto object, Task task) {
        task.setTitle(object.getTitle());
        task.setDescription(object.getDescription());
        task.setStatus(object.getStatus());
        task.setFolder(getFolder(object.getFolderId()));
    }

    private Folder getFolder(Long folderId) {
        return Optional.ofNullable(folderId)
                .flatMap(folderRepository::findById)
                .orElse(null);
    }
}
