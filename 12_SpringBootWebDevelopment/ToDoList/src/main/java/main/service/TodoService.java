package main.service;

import main.model.ToDo;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService
{
    @Autowired
    private TodoRepository todoRepository;

    public int addToDo(ToDo toDo)
    {
        ToDo newTodo = todoRepository.save(toDo);
        return newTodo.getId();
    }

    public boolean editToDo(int id, ToDo toDo)
    {
        if (!todoRepository.findById(id).isPresent())
        {
            return false;
        }
        ToDo toDo1 = todoRepository.save(toDo);
        return true;
    }

    public ResponseEntity<ToDo> getTodo(int id)
    {
        return todoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public List<ToDo> getAllTodos(){

        Iterable<ToDo> toDoIterable = todoRepository.findAll();
        ArrayList<ToDo> toDos = new ArrayList<>();
        for (ToDo toDo : toDoIterable)
        {
            toDos.add(toDo);
        }
        return toDos;
    }

    public ResponseEntity<Void> delete(int id)
    {
        todoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> deleteAll()
    {
        todoRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
