package org.example.basicspringsessiontodoassignment.dto;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {
    private String todo;
    private String managerName;
    private String password;
}
