package org.example.basicspringsessiontodoassignment.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveRequestDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveResponseDto;
import org.example.basicspringsessiontodoassignment.entity.Todo;
import org.example.basicspringsessiontodoassignment.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto requestDto) {
        Todo todo = new Todo(
                requestDto.getTodo(),
                requestDto.getManagerName(),
                requestDto.getPassword()
        );

        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTodo(),
                savedTodo.getManagerName(),
                savedTodo.getCreatedAt(),
                savedTodo.getModifiedAt());
    }

}
