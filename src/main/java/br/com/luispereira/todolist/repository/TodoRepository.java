package br.com.luispereira.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luispereira.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
