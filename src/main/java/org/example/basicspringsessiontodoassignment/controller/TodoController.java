package org.example.basicspringsessiontodoassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveRequestDto;
import org.example.basicspringsessiontodoassignment.dto.TodoSaveResponseDto;
import org.example.basicspringsessiontodoassignment.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos/")
    public TodoSaveResponseDto saveTodo(@RequestBody TodoSaveRequestDto requestDto){
        return todoService.saveTodo(requestDto);
    }
}
