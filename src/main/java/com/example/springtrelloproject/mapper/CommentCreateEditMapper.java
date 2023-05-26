package com.example.springtrelloproject.mapper;

import com.example.springtrelloproject.dto.CommentCreateEditDto;
import com.example.springtrelloproject.model.Comment;
import com.example.springtrelloproject.model.Task;
import com.example.springtrelloproject.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentCreateEditMapper implements Mapper<CommentCreateEditDto, Comment> {

    private final TaskRepository taskRepository;


    @Override
    public Comment map(CommentCreateEditDto fromObject, Comment toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Comment map(CommentCreateEditDto object) {
        Comment comment = new Comment();
        copy(object, comment);
        return comment;
    }

    private void copy(CommentCreateEditDto object, Comment comment) {
        comment.setComment(object.getComment());
        comment.setTask(getTask(object.getTaskId()));
    }

    private Task getTask(Long taskId) {
        return Optional.ofNullable(taskId)
                .flatMap(taskRepository::findById)
                .orElse(null);
    }
}
