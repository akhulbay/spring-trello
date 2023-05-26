package com.example.springtrelloproject.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentReadDto {

    private Long id;
    private String comment;
    private TaskReadDto task;
}
