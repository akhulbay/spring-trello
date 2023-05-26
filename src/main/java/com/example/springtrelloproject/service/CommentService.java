package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.CommentCreateEditDto;
import com.example.springtrelloproject.dto.CommentReadDto;

import java.util.List;

public interface CommentService {

    List<CommentReadDto> findAll();

    CommentReadDto findById(Long id);

    CommentReadDto update(Long id, CommentCreateEditDto comment);

    CommentReadDto create(CommentCreateEditDto comment);

    void delete(Long id);

}
