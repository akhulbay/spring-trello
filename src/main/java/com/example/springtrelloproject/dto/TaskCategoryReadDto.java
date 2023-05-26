package com.example.springtrelloproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCategoryReadDto {

    private Long id;
    private String name;
}
