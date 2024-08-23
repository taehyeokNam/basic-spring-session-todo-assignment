package org.example.basicspringsessiontodoassignment.repository;

import org.example.basicspringsessiontodoassignment.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
