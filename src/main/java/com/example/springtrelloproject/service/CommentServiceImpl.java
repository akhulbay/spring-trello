package com.example.springtrelloproject.service;

import com.example.springtrelloproject.dto.CommentCreateEditDto;
import com.example.springtrelloproject.dto.CommentReadDto;
import com.example.springtrelloproject.mapper.CommentCreateEditMapper;
import com.example.springtrelloproject.mapper.CommentReadMapper;
import com.example.springtrelloproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentReadMapper commentReadMapper;
    private final CommentCreateEditMapper commentCreateEditMapper;

    @Override
    public List<CommentReadDto> findAll() {
        return commentRepository.findAll().stream()
                .map(commentReadMapper::map)
                .toList();
    }

    @Override
    public CommentReadDto findById(Long id) {
        return commentRepository.findById(id)
                .map(commentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public CommentReadDto create(CommentCreateEditDto comment) {
        return Optional.of(comment)
                .map(commentCreateEditMapper::map)
                .map(commentRepository::save)
                .map(commentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public CommentReadDto update(Long id, CommentCreateEditDto commentDto) {
        return commentRepository.findById(id)
                .map(entity -> commentCreateEditMapper.map(commentDto, entity))
                .map(commentRepository::saveAndFlush)
                .map(commentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        commentRepository.findById(id)
                .ifPresent(entity -> commentRepository.deleteById(id));
    }
}
