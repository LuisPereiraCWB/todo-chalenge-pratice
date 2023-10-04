package br.com.luispereira.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.luispereira.todolist.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodoListApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("Todo 1", "desc todo 1", false, 1);
		
		webTestClient
		  .post()
		  .uri("/todos")
		  .bodyValue(todo)
		  .exchange()
		  .expectStatus().isOk()
		  .expectBody()
		  .jsonPath("$").isArray()
		  .jsonPath("$.length()").isEqualTo(1)
		  .jsonPath("$[0].name").isEqualTo(todo.getName())
		  .jsonPath("$[0].description").isEqualTo(todo.getDescription())
		  .jsonPath("$[0].released").isEqualTo(todo.isReleased())
		  .jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}
	
	@Test
	void testCreateTodoFailure() {
		webTestClient
		  .post()
		  .uri("/todos")
		  .bodyValue(new Todo("", "", false, 1))
		  .exchange()
		  .expectStatus().isBadRequest();
	}

}
