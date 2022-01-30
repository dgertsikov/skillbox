package main.controllers;

import main.model.ToDo;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController
{
    @Autowired
    private TodoRepository todoRepository;

//    @Value("${someParameter.value}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model)
    {
        Iterable<ToDo> todoIterable = todoRepository.findAll();
        ArrayList<ToDo> todos = new ArrayList<>();
        for(ToDo todo : todoIterable) {
            todos.add(todo);
        }
        model.addAttribute("todos", todos);
        model.addAttribute("todosCount", todos.size());
        model.addAttribute("someParameter", someParameter);

        return "index";
    }
}
