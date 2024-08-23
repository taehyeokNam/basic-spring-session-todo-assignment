package org.example.basicspringsessiontodoassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.basicspringsessiontodoassignment.dto.TodoSimpleResponseDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveRequestDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveResponseDto;
import org.example.basicspringsessiontodoassignment.entity.Todo;
import org.example.basicspringsessiontodoassignment.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<TodoSimpleResponseDto> getTodos(String date) {
        LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

        List<Todo> todoList = todoRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime);

        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(
                    todo.getId(),
                    todo.getTodo(),
                    todo.getManagerName(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
