package com.example.springtrelloproject.mapper;

import com.example.springtrelloproject.dto.CommentReadDto;
import com.example.springtrelloproject.dto.TaskReadDto;
import com.example.springtrelloproject.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentReadMapper implements Mapper<Comment, CommentReadDto> {

    private final TaskReadMapper taskReadMapper;

    @Override
    public CommentReadDto map(Comment object) {
        TaskReadDto taskReadDto = Optional.ofNullable(object.getTask())
                .map(taskReadMapper::map)
                .orElse(null);
        return new CommentReadDto(
                object.getId(),
                object.getComment(),
                taskReadDto
        );
    }
}
