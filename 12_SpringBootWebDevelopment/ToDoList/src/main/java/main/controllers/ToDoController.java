package main.controllers;

import main.model.ToDo;
import main.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoController
{
    private final TodoService todoService;

    @Autowired
    public ToDoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos/")
    public int add(ToDo toDo)
    {
        return todoService.addToDo(toDo);
    }

    @GetMapping("/todos/")
    public List<ToDo> list()
    {
        return todoService.getAllTodos();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<ToDo> get(@PathVariable int id)
    {
        return todoService.getTodo(id);
    }
    
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        return todoService.delete(id);
    }

    @DeleteMapping("/todos/")
    public ResponseEntity<Void> deleteAll()
    {
        return todoService.deleteAll();
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<ToDo> editToDo(@PathVariable int id, @RequestBody ToDo toDo)
    {
        if (!todoService.editToDo(id, toDo)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
