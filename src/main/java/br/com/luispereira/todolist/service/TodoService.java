package br.com.luispereira.todolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.luispereira.todolist.entity.Todo;
import br.com.luispereira.todolist.repository.TodoRepository;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo todo){
		todoRepository.save(todo);
		return listAll();
	}
	
	public List<Todo> listAll(){
		Sort sort = Sort.by("priority").descending().and(
			Sort.by("name").ascending());
		return todoRepository.findAll(sort);		
	}
	
	public List<Todo> update(Todo todo){
		todoRepository.save(todo);
		return listAll();
	}
	
	public List<Todo> delete(Long id){
		todoRepository.deleteById(id);
		return listAll();
	}

}
