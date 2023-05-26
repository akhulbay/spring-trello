package com.example.springtrelloproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateEditDto {

    private String title;
    private String description;
    private Integer status;
    private Long folderId;
}
