package org.example.basicspringsessiontodoassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.basicspringsessiontodoassignment.dto.*;
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
                savedTodo.getModifiedAt()
        );
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
                    todo.getModifiedAt()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다."));

        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("존재하지 않는 일정입니다."));

        todo.update(
                requestDto.getTodo(),
                requestDto.getManagerName()
        );

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName()
        );
    }

    @Transactional
    public void deleteTodo(Long todoId, TodoDeleteRequestDto requestDto){
        String password = requestDto.getPasswrod();
        if (password == null) {
            throw new NullPointerException("비밀번호를 입력하세요");
        }

        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다"));

        if (!password.equals(todo.getPassword())) {
            throw new NullPointerException("잘못된 비밀번호 입니다.");
        }

        todoRepository.deleteById(todoId);
    }
}
