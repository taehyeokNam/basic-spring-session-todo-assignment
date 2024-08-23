package org.example.basicspringsessiontodoassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.basicspringsessiontodoassignment.dto.TodoDetailResponseDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveRequestDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveResponseDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSimpleResponseDto;
import org.example.basicspringsessiontodoassignment.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public TodoSaveResponseDto saveTodo(@RequestBody TodoSaveRequestDto requestDto) {
        return todoService.saveTodo(requestDto);
    }

    @GetMapping("/todos")
    public List<TodoSimpleResponseDto> getTodos(@RequestParam String date) {
        return todoService.getTodos(date);
    }

    @GetMapping("/todos/{todoId}")
    public TodoDetailResponseDto getTodo(@PathVariable Long todoId) {
        return todoService.getTodo(todoId);
    }
}
