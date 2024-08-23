package org.example.basicspringsessiontodoassignment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSaveResponseDto {
    private final Long todoId;
    private final String todo;
    private final String managerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoSaveResponseDto(Long todoId, String todo, String managerName, LocalDateTime createdAt, LocalDateTime modifiedAt ) {
        this.todoId = todoId;
        this.todo = todo;
        this.managerName = managerName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
